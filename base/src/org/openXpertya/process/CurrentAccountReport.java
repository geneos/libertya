package org.openXpertya.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.openXpertya.model.MCurrency;
import org.openXpertya.util.DB;
import org.openXpertya.util.Env;

public class CurrentAccountReport extends SvrProcess {

	/** Entidad Comercial de los comprobantes a consultar */
	private Integer p_C_BPartnerID;
	/** Fecha inicial del rango de fechas de la transacción */
	private Timestamp p_DateTrx_From;
	/** Fecha final del rango de fechas de la transacción */
	private Timestamp p_DateTrx_To;
	/** Organización de los comprobantes a consultar */
	private Integer p_AD_Org_ID;
	/** Tipo de documento de los comprobantes a consultar */
	private Integer p_C_DocType_ID;
	/** Tipo de Cuenta del Reporte: Cliente o Proveedor */
	private String p_AccountType;
	/** Incluir pedidos no facturados en el informe */
	private boolean p_includeOpenOrders;
	private String p_includeOpenOrders_char;
	/** Mostrar detalle de Cobros y Pagos en el informe */
	private boolean p_ShowDetailedReceiptsPayments;
	private String p_ShowDetailedReceiptsPayments_char;

	/** Signo de documentos que son débitos (depende de p_AccountType) */
	private int debit_signo_issotrx;
	/** Signo de documentos que son créditos (depende de p_AccountType) */
	private int credit_signo_isotrx;
	/** Moneda en la que trabaja la compañía */
	private int client_Currency_ID;

	/**
	 * Importe acumulado del Crédito hasta la fecha inicial parámetro
	 * p_DateTrx_From
	 */
	private BigDecimal acumCredit = BigDecimal.ZERO;
	/**
	 * Importe acumulado del Débito hasta la fecha inicial parámetro
	 * p_DateTrx_From
	 */
	private BigDecimal acumDebit = BigDecimal.ZERO;
	/**
	 * Importe acumulado del Saldo hasta la fecha inicial parámetro
	 * p_DateTrx_From
	 */
	private BigDecimal acumBalance = BigDecimal.ZERO;

	/** Tipos de documento segun columna documenttable de v_documents */
	protected static final String DOC_INVOICE = "C_Invoice";
	protected static final String DOC_PAYMENT = "C_Payment";
	protected static final String DOC_CASHLINE = "C_CashLine";
	protected static final String DOC_ALLOCATIONHDR = "C_AllocationHdr";

	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();

		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();

			if (para[i].getParameter() == null)
				;
			else if (name.equalsIgnoreCase("C_BPartner_ID")) {
				p_C_BPartnerID = ((BigDecimal) para[i].getParameter())
						.intValue();
			} else if (name.equalsIgnoreCase("IncludeOpenOrders")) {
				p_includeOpenOrders_char = (String) para[i].getParameter();
				p_includeOpenOrders = "Y".equals((String) para[i]
						.getParameter());
			} else if (name.equalsIgnoreCase("ShowDetailedReceiptsPayments")) {
				p_ShowDetailedReceiptsPayments_char = (String) para[i]
						.getParameter();
				p_ShowDetailedReceiptsPayments = "Y".equals((String) para[i]
						.getParameter());
			} else if (name.equalsIgnoreCase("datetrx")) {
				p_DateTrx_From = (Timestamp) para[i].getParameter();
				p_DateTrx_To = (Timestamp) para[i].getParameter_To();
			} else if (name.equalsIgnoreCase("accounttype")) {
				p_AccountType = (String) para[i].getParameter();
			} else if (name.equalsIgnoreCase("AD_Org_ID")) {
				BigDecimal tmp = (BigDecimal) para[i].getParameter();
				p_AD_Org_ID = tmp == null ? null : tmp.intValue();
			} else if (name.equalsIgnoreCase("C_DocType_ID")) {
				BigDecimal tmp = (BigDecimal) para[i].getParameter();
				p_C_DocType_ID = tmp == null ? null : tmp.intValue();
			} else {
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
			}
		}

		// Reporte de Cta Corriente de Cliente o Proveedor.
		// +-------------------------+--------------------------+
		// | Cta Corriente Cliente | Cta Corriente Proveedor |
		// +-------------------------+--------------------------+
		// | Debitos = Signo 1 | Debitos = Signo -1 |
		// | Créditos = Signo -1 | Créditos = Signo 1 |
		// +-------------------------+--------------------------+
		debit_signo_issotrx = p_AccountType.equalsIgnoreCase("C") ? 1 : -1;
		credit_signo_isotrx = p_AccountType.equalsIgnoreCase("C") ? -1 : 1;

		// Moneda de la compañía utilizada para conversión de montos de
		// documentos.
		client_Currency_ID = Env.getContextAsInt(getCtx(), "$C_Currency_ID");

	}

	@Override
	protected String doIt() throws Exception {

		// delete all rows older than a week
		DB.executeUpdate("DELETE FROM T_CUENTACORRIENTE WHERE DATECREATED < ('now'::text)::timestamp(6) - interval '7 days'");
		// delete all rows in table with the given ad_pinstance_id
		DB.executeUpdate("DELETE FROM T_CUENTACORRIENTE WHERE AD_PInstance_ID = "
				+ getAD_PInstance_ID());

		// Consulta que obtiene el detalle de todos los documentos de la EC,
		// aplicando
		// ademas los filtros de Tipo de Documento y Organización asignados como
		// parámetro
		// del reporte. Realiza la conversión de montos a la moneda del Contexto
		// dado que
		// los pagos pueden estar expresados en otra moneda. NO aplica el filtro
		// de
		// fechas para que sea reutilizable la consulta.

		/**
		 * El saldo de las Invoices se calcula haciendo: La sumatoria de lo
		 * facturado (amount + writeoffamt + discountamt de C_AllocationLine) +
		 * La sumatoria de lo pendiente por las Facturas (invoiceOpen)
		 * convertido a tasa actual.
		 */
		/**
		 * El saldo de los Payments se calcula haciendo: La sumatoria de lo
		 * cobrado (amount de C_AllocationLine) + La sumatoria de lo pendiente
		 * por las pagos (paymentavailable) convertido a tasa actual.
		 */
		/**
		 * El saldo de las CashLine se calcula haciendo: La sumatoria de lo
		 * cobrado (amount de C_AllocationLine) + La sumatoria de lo pendiente
		 * por las pagos (cashlineavailable) convertido a tasa actual.
		 */
		StringBuffer sqlDoc = new StringBuffer();
		if (p_ShowDetailedReceiptsPayments) {
			sqlDoc.append(" SELECT ");
			sqlDoc.append(" 	d.Dateacct::date as DateTrx, ");
			sqlDoc.append(" 	d.C_DocType_ID, ");
			sqlDoc.append(" 	d.DocumentNo, ");
			sqlDoc.append(" 	d.duedate, ");
			sqlDoc.append("     ABS(CASE WHEN d.signo_issotrx = ? THEN ");
			sqlDoc.append("     (SELECT CASE ");
//			sqlDoc.append("     WHEN d.documenttable = 'C_Invoice' THEN (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount + (CASE WHEN al.c_invoice_credit_id IS NULL THEN 0.0 ELSE (al.writeoffamt + al.discountamt) END )) END) FROM C_AllocationLine al WHERE ((al.c_invoice_id = d.document_id) OR (al.c_invoice_credit_id = d.document_id)) AND (al.isactive = 'Y')) ");
			sqlDoc.append("		WHEN d.documenttable = 'C_Invoice' THEN (sign(d.amount) * ( abs(currencyConvert(d.amount, d.c_currency_id, " + client_Currency_ID + ", ('now'::text)::timestamp(6) with time zone, COALESCE(c_conversiontype_id,0), d.ad_client_id, d.ad_org_id)) - abs(invoiceOpen(d.document_id, d.c_invoicepayschedule_id, " + client_Currency_ID + " , 0)) ) )");
			sqlDoc.append("     WHEN d.documenttable = 'C_CashLine' THEN (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount) END) FROM C_AllocationLine al WHERE (al.c_cashline_id = d.document_id) AND (al.isactive = 'Y')) ");
			sqlDoc.append("     ELSE (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount) END) FROM C_AllocationLine al WHERE (al.c_payment_id = d.document_id) AND (al.isactive = 'Y')) END) ");
			// sqlDoc.append("		          currencyconvert(d.amount, d.c_currency_id, ?, ('now'::text)::timestamp(6) with time zone, COALESCE(c_conversiontype_id,0), d.ad_client_id, d.ad_org_id) ");
			sqlDoc.append("     + ");
			sqlDoc.append("     (SELECT currencyconvert ( CASE WHEN d.documenttable = 'C_Invoice' THEN ");
			sqlDoc.append("     invoiceOpen(d.document_id, coalesce(d.c_invoicepayschedule_id,0)) ");
			sqlDoc.append("     WHEN d.documenttable = 'C_CashLine' THEN ");
			sqlDoc.append("     cashlineavailable(d.document_id) ");
			sqlDoc.append("     ELSE paymentavailable(d.document_id) END, d.c_currency_id, ?, ('now'::text)::timestamp(6) with time zone, COALESCE(d.c_conversiontype_id,0), d.ad_client_id, d.ad_org_id ) ) ");
			sqlDoc.append("	         ELSE 0.0 END) * SIGN(d.amount)::numeric AS Debit, ");
			sqlDoc.append("     ABS(CASE WHEN d.signo_issotrx = ? THEN ");
			sqlDoc.append("     (SELECT CASE ");
//			sqlDoc.append("     WHEN d.documenttable = 'C_Invoice' THEN (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount + (CASE WHEN al.c_invoice_credit_id IS NULL THEN 0.0 ELSE (al.writeoffamt + al.discountamt) END )) END) FROM C_AllocationLine al WHERE ((al.c_invoice_id = d.document_id) OR (al.c_invoice_credit_id = d.document_id)) AND (al.isactive = 'Y')) ");
			sqlDoc.append("		WHEN d.documenttable = 'C_Invoice' THEN (sign(d.amount) * ( abs(currencyConvert(d.amount, d.c_currency_id, " + client_Currency_ID + ", ('now'::text)::timestamp(6) with time zone, COALESCE(c_conversiontype_id,0), d.ad_client_id, d.ad_org_id)) - abs(invoiceOpen(d.document_id, d.c_invoicepayschedule_id, " + client_Currency_ID + " , 0)) ) )");
			sqlDoc.append("     WHEN d.documenttable = 'C_CashLine' THEN (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount) END) FROM C_AllocationLine al WHERE (al.c_cashline_id = d.document_id) AND (al.isactive = 'Y')) ");
			sqlDoc.append("     ELSE (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount) END) FROM C_AllocationLine al WHERE (al.c_payment_id = d.document_id) AND (al.isactive = 'Y')) END) ");
			// sqlDoc.append("		          currencyconvert(d.amount, d.c_currency_id, ?, ('now'::text)::timestamp(6) with time zone, COALESCE(c_conversiontype_id,0), d.ad_client_id, d.ad_org_id) ");
			sqlDoc.append("     + ");
			sqlDoc.append("     (SELECT currencyconvert ( CASE WHEN d.documenttable = 'C_Invoice' THEN ");
			sqlDoc.append("     invoiceOpen(d.document_id, coalesce(d.c_invoicepayschedule_id,0)) ");
			sqlDoc.append("     WHEN d.documenttable = 'C_CashLine' THEN ");
			sqlDoc.append("     cashlineavailable(d.document_id) ");
			sqlDoc.append("     ELSE paymentavailable(d.document_id) END, d.c_currency_id, ?, ('now'::text)::timestamp(6) with time zone, COALESCE(d.c_conversiontype_id,0), d.ad_client_id, d.ad_org_id ) ) ");
			sqlDoc.append("	         ELSE 0.0 END) * SIGN(d.amount)::numeric AS Credit, ");
			sqlDoc.append("  	d.Created, ");
			sqlDoc.append("  	d.C_Currency_ID, ");
			sqlDoc.append("  	d.amount, ");
			sqlDoc.append("  	d.documenttable, ");
			sqlDoc.append("  	d.document_id, ");
			sqlDoc.append(" 	d.c_invoicepayschedule_id ");
			sqlDoc.append(" FROM V_Documents d ");
			sqlDoc.append(" WHERE d.DocStatus IN ('CO','CL', 'RE', 'VO') ");
			sqlDoc.append("   AND d.AD_Client_ID = ? ");
			sqlDoc.append("   AND d.C_Bpartner_ID = ? ");
			sqlAppend("   AND d.AD_Org_ID = ? ", p_AD_Org_ID, sqlDoc);
			sqlAppend("   AND d.C_DocType_ID = ? ", p_C_DocType_ID, sqlDoc);
		} else {
			sqlDoc.append("     SELECT ");
			sqlDoc.append("     	date_trunc('day',d.Dateacct) as DateTrx, ");
			sqlDoc.append("     	d.C_DocType_ID, ");
			sqlDoc.append("     	COALESCE((SELECT a.documentno FROM C_AllocationHdr a WHERE (a.C_AllocationHdr_ID = (SELECT al.C_AllocationHdr_ID FROM C_AllocationLine al WHERE ( ((d.documenttable = 'C_Payment') AND (al.C_Payment_ID = d.document_id)) OR ((d.documenttable = 'C_Invoice') AND (al.C_Invoice_Credit_ID = d.document_id)) OR ((d.documenttable = 'C_CashLine') AND (al.C_CashLine_ID = d.document_id)) ) LIMIT 1))),DocumentNo) AS DocumentNo, ");
			sqlDoc.append("     	ABS((CASE WHEN d.signo_issotrx = ? THEN ");
			sqlDoc.append(" 		(SELECT CASE ");
//			sqlDoc.append(" 		WHEN d.documenttable = 'C_Invoice' THEN (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount + (CASE WHEN al.c_invoice_credit_id IS NULL THEN 0.0 ELSE (al.writeoffamt + al.discountamt) END )) END) FROM C_AllocationLine al WHERE ((al.c_invoice_id = d.document_id) OR (al.c_invoice_credit_id = d.document_id)) AND (al.isactive = 'Y')) ");
			sqlDoc.append("			WHEN d.documenttable = 'C_Invoice' THEN (sign(d.amount) * ( abs(currencyConvert(d.amount, d.c_currency_id, " + client_Currency_ID + ", ('now'::text)::timestamp(6) with time zone, COALESCE(c_conversiontype_id,0), d.ad_client_id, d.ad_org_id)) - abs(invoiceOpen(d.document_id, d.c_invoicepayschedule_id, " + client_Currency_ID + " , 0)) ) )");
			sqlDoc.append(" 		WHEN d.documenttable = 'C_CashLine' THEN (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount) END) FROM C_AllocationLine al WHERE (al.c_cashline_id = d.document_id) AND (al.isactive = 'Y')) ");
			sqlDoc.append(" 		ELSE (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount) END) FROM C_AllocationLine al WHERE (al.c_payment_id = d.document_id) AND (al.isactive = 'Y')) END) ");
			sqlDoc.append(" 		+ ");
			sqlDoc.append(" 		(SELECT currencyconvert ( CASE WHEN d.documenttable = 'C_Invoice' THEN ");
			sqlDoc.append(" 		invoiceOpen(d.document_id, coalesce(d.c_invoicepayschedule_id,0)) ");
			sqlDoc.append(" 		WHEN d.documenttable = 'C_CashLine' THEN cashlineavailable(d.document_id) ");
			sqlDoc.append(" 		ELSE paymentavailable(d.document_id) END, d.c_currency_id, ?, ('now'::text)::timestamp(6) with time zone, COALESCE(d.c_conversiontype_id,0), d.ad_client_id, d.ad_org_id ) ) ELSE 0.0 END)) * SIGN(d.amount)::numeric AS Debit, ");
			sqlDoc.append(" 		ABS((CASE WHEN d.signo_issotrx = ? THEN ");
			sqlDoc.append(" 		(SELECT CASE ");
//			sqlDoc.append(" 		WHEN d.documenttable = 'C_Invoice' THEN (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount + (CASE WHEN al.c_invoice_credit_id IS NULL THEN 0.0 ELSE (al.writeoffamt + al.discountamt) END )) END) FROM C_AllocationLine al WHERE ((al.c_invoice_id = d.document_id) OR (al.c_invoice_credit_id = d.document_id)) AND (al.isactive = 'Y')) ");
			sqlDoc.append("			WHEN d.documenttable = 'C_Invoice' THEN (sign(d.amount) * ( abs(currencyConvert(d.amount, d.c_currency_id, " + client_Currency_ID + ", ('now'::text)::timestamp(6) with time zone, COALESCE(c_conversiontype_id,0), d.ad_client_id, d.ad_org_id)) - abs(invoiceOpen(d.document_id, d.c_invoicepayschedule_id, " + client_Currency_ID + " , 0)) ) )");
			sqlDoc.append(" 		WHEN d.documenttable = 'C_CashLine' THEN (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount) END) FROM C_AllocationLine al WHERE (al.c_cashline_id = d.document_id) AND (al.isactive = 'Y')) ");
			sqlDoc.append(" 		ELSE (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount) END) FROM C_AllocationLine al WHERE (al.c_payment_id = d.document_id) AND (al.isactive = 'Y')) END) ");
			sqlDoc.append(" 		+ ");
			sqlDoc.append(" 		(SELECT currencyconvert ( CASE WHEN d.documenttable = 'C_Invoice' THEN ");
			sqlDoc.append(" 		invoiceOpen(d.document_id, coalesce(d.c_invoicepayschedule_id,0)) ");
			sqlDoc.append(" 		WHEN d.documenttable = 'C_CashLine' THEN ");
			sqlDoc.append(" 		cashlineavailable(d.document_id) ");
			sqlDoc.append(" 		ELSE paymentavailable(d.document_id) END, d.c_currency_id, ?, ('now'::text)::timestamp(6) with time zone, COALESCE(d.c_conversiontype_id,0), d.ad_client_id, d.ad_org_id ) ) ELSE 0.0 END)) * SIGN(d.amount)::numeric AS Credit, ");
			sqlDoc.append(" 		date_trunc('day',d.Created) AS Created, ");
			sqlDoc.append(" 		(CASE WHEN ((SELECT al.C_AllocationHdr_ID FROM C_AllocationLine al WHERE ( ((d.documenttable = 'C_Payment') AND (al.C_Payment_ID = d.document_id)) OR ((d.documenttable = 'C_Invoice') AND (al.C_Invoice_Credit_ID = d.document_id)) OR ((d.documenttable = 'C_CashLine') AND (al.C_CashLine_ID = d.document_id)) ) LIMIT 1) IS NOT NULL) THEN '118' ELSE d.C_Currency_ID END) AS C_Currency_ID, ");
			sqlDoc.append(" 		(CASE WHEN ((SELECT al.C_AllocationHdr_ID FROM C_AllocationLine al WHERE ( ((d.documenttable = 'C_Payment') AND (al.C_Payment_ID = d.document_id) AND (al.isActive = 'Y')) OR ((d.documenttable = 'C_Invoice') AND (al.C_Invoice_Credit_ID = d.document_id) AND (al.isActive = 'Y')) OR ((d.documenttable = 'C_CashLine') AND (al.C_CashLine_ID = d.document_id) AND (al.isActive = 'Y')) ) LIMIT 1) IS NOT NULL) THEN (CASE WHEN d.signo_issotrx = '1' THEN (SELECT CASE WHEN d.documenttable = 'C_Invoice' THEN ( sign(d.amount) * ( abs(currencyConvert(d.amount, d.c_currency_id, " + client_Currency_ID + ", ('now'::text)::timestamp(6) with time zone, COALESCE(c_conversiontype_id,0), d.ad_client_id, d.ad_org_id)) - abs(invoiceOpen(d.document_id, d.c_invoicepayschedule_id, " + client_Currency_ID + " , 0)) )  ) WHEN d.documenttable = 'C_CashLine' THEN (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount) END) FROM C_AllocationLine al WHERE (al.c_cashline_id = d.document_id) AND (al.isActive = 'Y')) ELSE (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount) END) FROM C_AllocationLine al WHERE (al.c_payment_id = d.document_id) AND (al.isActive = 'Y')) END) + (SELECT currencyconvert ( CASE WHEN d.documenttable = 'C_Invoice' THEN invoiceOpen(d.document_id, coalesce(d.c_invoicepayschedule_id,0)) WHEN d.documenttable = 'C_CashLine' THEN cashlineavailable(d.document_id) ELSE paymentavailable(d.document_id) END, d.c_currency_id, '118', ('now'::text)::timestamp(6) with time zone, COALESCE(d.c_conversiontype_id,0), d.ad_client_id, d.ad_org_id ) ) ELSE 0.0 END) + (CASE WHEN d.signo_issotrx = '-1' THEN (SELECT CASE WHEN d.documenttable = 'C_Invoice' THEN ( sign(d.amount) * ( abs(currencyConvert(d.amount, d.c_currency_id, " + client_Currency_ID + ", ('now'::text)::timestamp(6) with time zone, COALESCE(c_conversiontype_id,0), d.ad_client_id, d.ad_org_id)) - abs(invoiceOpen(d.document_id, d.c_invoicepayschedule_id, " + client_Currency_ID + " , 0)) )  ) WHEN d.documenttable = 'C_CashLine' THEN (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount) END) FROM C_AllocationLine al WHERE (al.c_cashline_id = d.document_id) AND (al.isActive = 'Y')) ELSE (select (CASE WHEN SUM(al.amount) IS NULL THEN 0.0 ELSE SUM(al.amount) END) FROM C_AllocationLine al WHERE (al.c_payment_id = d.document_id) AND (al.isActive = 'Y')) END) + (SELECT currencyconvert ( CASE WHEN d.documenttable = 'C_Invoice' THEN invoiceOpen(d.document_id, coalesce(d.c_invoicepayschedule_id,0)) WHEN d.documenttable = 'C_CashLine' THEN cashlineavailable(d.document_id) ELSE paymentavailable(d.document_id) END, d.c_currency_id, '118', ('now'::text)::timestamp(6) with time zone, COALESCE(d.c_conversiontype_id,0), d.ad_client_id, d.ad_org_id ) ) ELSE 0.0 END) ELSE d.amount END) AS amount, ");
			sqlDoc.append(" 		(CASE WHEN ((SELECT al.C_AllocationHdr_ID FROM C_AllocationLine al WHERE ( ((d.documenttable = 'C_Payment') AND (al.C_Payment_ID = d.document_id)) OR ((d.documenttable = 'C_Invoice') AND (al.C_Invoice_Credit_ID = d.document_id)) OR ((d.documenttable = 'C_CashLine') AND (al.C_CashLine_ID = d.document_id)) ) LIMIT 1) IS NOT NULL) THEN 'C_AllocationHdr' ELSE d.documenttable END) AS documenttable, ");
			sqlDoc.append(" 		(CASE WHEN ((SELECT al.C_AllocationHdr_ID FROM C_AllocationLine al WHERE ( ((d.documenttable = 'C_Payment') AND (al.C_Payment_ID = d.document_id)) OR ((d.documenttable = 'C_Invoice') AND (al.C_Invoice_Credit_ID = d.document_id)) OR ((d.documenttable = 'C_CashLine') AND (al.C_CashLine_ID = d.document_id)) ) LIMIT 1) IS NOT NULL) THEN (SELECT al.C_AllocationHdr_ID FROM C_AllocationLine al WHERE ( ((d.documenttable = 'C_Payment') AND (al.C_Payment_ID = d.document_id)) OR ((d.documenttable = 'C_Invoice') AND (al.C_Invoice_Credit_ID = d.document_id)) OR ((d.documenttable = 'C_CashLine') AND (al.C_CashLine_ID = d.document_id)) ) LIMIT 1) ELSE d.document_id END) AS document_id, ");
			sqlDoc.append(" 	d.c_invoicepayschedule_id ");
			sqlDoc.append(" 	FROM V_Documents d ");
			sqlDoc.append(" 	WHERE d.DocStatus IN ('CO','CL', 'RE', 'VO') ");
			sqlDoc.append("     AND d.AD_Client_ID = ? ");
			sqlDoc.append("   AND d.C_Bpartner_ID = ? ");
			sqlAppend("   AND d.AD_Org_ID = ? ", p_AD_Org_ID, sqlDoc);
			sqlAppend("   AND d.C_DocType_ID = ? ", p_C_DocType_ID, sqlDoc);
		}

		// Saldo acumulado, por defecto es 0.
		acumBalance = BigDecimal.ZERO;

		// Si se ingresó un filtro de fecha de inicio, entonces se calcula el
		// saldo acumulado
		// a partir de todos los comprobantes que tiene fecha de transacción
		// menor a la
		// fecha inicial seteada para el reporte.
		if (p_DateTrx_From != null) {
			calculateBalance(sqlDoc);
		}

		StringBuffer sql = new StringBuffer();

		sql.append(sqlDoc); // Consulta de todos los comprobantes
		sqlAppend("   AND ?::date <= d.Dateacct::date ", p_DateTrx_From, sql);
		sqlAppend("   AND d.Dateacct::date <= ?::date ", p_DateTrx_To, sql);
		sql.append(" ORDER BY d.Dateacct, d.Created");

		if (!p_ShowDetailedReceiptsPayments) {
			StringBuffer sqlGroupBy = new StringBuffer();
			sqlGroupBy
					.append(" SELECT DateTrx, C_DocType_ID, DocumentNo, SUM(Debit) AS Debit, SUM(Credit) AS Credit, Created, C_Currency_ID, SUM(amount) AS Amount, documenttable, document_id, c_invoicepayschedule_id");
			sqlGroupBy.append(" FROM( ");
			sqlGroupBy.append(sql);
			sqlGroupBy.append(" ) AS aux ");
			sqlGroupBy
					.append(" GROUP BY DateTrx, C_DocType_ID, DocumentNo, Created, C_Currency_ID, documenttable, document_id, c_invoicepayschedule_id ");
			sqlGroupBy.append(" ORDER BY DateTrx, Created ");
			sql = sqlGroupBy;
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			int i = 1;
			// Parámetros de sqlDoc
			pstmt.setInt(i++, debit_signo_issotrx);
			pstmt.setInt(i++, client_Currency_ID);
			pstmt.setInt(i++, credit_signo_isotrx);
			pstmt.setInt(i++, client_Currency_ID);
			pstmt.setInt(i++, getAD_Client_ID());
			pstmt.setInt(i++, p_C_BPartnerID);
			i = pstmtSetParam(i, p_AD_Org_ID, pstmt);
			i = pstmtSetParam(i, p_C_DocType_ID, pstmt);
			// Parámetros para el filtro de fechas
			i = pstmtSetParam(i, p_DateTrx_From, pstmt);
			i = pstmtSetParam(i, p_DateTrx_To, pstmt);

			rs = pstmt.executeQuery();

			int subIndice = 0;
			int trx_Org_ID = p_AD_Org_ID != null ? p_AD_Org_ID : Env
					.getContextAsInt(getCtx(), "#AD_Org_ID");
			StringBuffer usql = new StringBuffer();

			if (p_DateTrx_From != null) {
				subIndice++;
				// insert first row: before query balance period
				// Field used for 'date field' in temporary table: DATETRX
				usql.append(" INSERT INTO T_CUENTACORRIENTE (SUBINDICE, IncludeOpenOrders, ShowDetailedReceiptsPayments, AD_CLIENT_ID, AD_ORG_ID, AD_PINSTANCE_ID, ISO_CODE, AMOUNT, DEBE, HABER, SALDO, NUMEROCOMPROBANTE, C_BPARTNER_ID, ACCOUNTTYPE, DATETRX, C_DOCTYPE_ID) "
						+ " VALUES ("
						+ subIndice
						+ ", '"
						+ p_includeOpenOrders_char
						+ "', '"
						+ p_ShowDetailedReceiptsPayments_char
						+ "', "
						+ getAD_Client_ID()
						+ ", "
						+ trx_Org_ID
						+ " , "
						+ getAD_PInstance_ID()
						+ ", '"
						+ "', "
						+ BigDecimal.ZERO
						+ ", "
						+ acumDebit
						+ ", "
						+ acumCredit
						+ ", "
						+ acumBalance
						+ ", '-', "
						+ p_C_BPartnerID
						+ ", '"
						+ p_AccountType
						+ "', '"
						+ p_DateTrx_From + "', NULL);");
			}

			BigDecimal credit = null;
			BigDecimal debit = null;
			// process rs & insert rows in table
			while (rs.next()) {
				Timestamp fechaVencimiento = null;
				if (!p_ShowDetailedReceiptsPayments) {
					// Obtengo la última fecha de vencimiento de la factura, si
					// es que tiene.
					String sqlFechaVen = "select MAX(duedate) from libertya.c_invoicepayschedule ipc inner join c_invoice i on (ipc.c_invoice_id = i.c_invoice_id) where (i.c_invoice_id = '"
							+ rs.getString("document_id")
							+ "') and ('"
							+ rs.getString("documenttable")
							+ "' = 'C_Invoice')";
					PreparedStatement pstmFechaVen = DB.prepareStatement(
							sqlFechaVen, get_TrxName(), true);
					ResultSet rsFechaVen = pstmFechaVen.executeQuery();
					if (rsFechaVen.next()) {
						fechaVencimiento = rsFechaVen.getTimestamp(1);
					}
				} else
					fechaVencimiento = rs.getTimestamp("duedate");

				subIndice++;
				credit = rs.getBigDecimal("Credit");
				debit = rs.getBigDecimal("Debit");
				int currencyID = rs.getInt("C_Currency_ID");
				// Validación de Conversión de monedas.
				// En caso de no existir una conversión entre la moneda del
				// documento y la moneda de
				// la compañía, entonces el monto Debit y Credit serán NULL.
				if ((credit == null || debit == null)
						&& currencyID != client_Currency_ID) {
					String fromISO = MCurrency
							.getISO_Code(getCtx(), currencyID);
					String toISO = MCurrency.getISO_Code(getCtx(),
							client_Currency_ID);
					log.severe("No Currency Conversion from " + fromISO
							+ " to " + toISO);
					throw new Exception("@NoCurrencyConversion@ (" + fromISO
							+ "->" + toISO + ")");
				}

				// ANTONIO: La cuenta es al reves acumBalance =
				// acumBalance.add(credit.subtract(debit));
				acumBalance = acumBalance.add(debit.subtract(credit));
				usql.append(" INSERT INTO T_CUENTACORRIENTE (SUBINDICE, IncludeOpenOrders, ShowDetailedReceiptsPayments, AD_CLIENT_ID, AD_ORG_ID, AD_PINSTANCE_ID, ISO_CODE, AMOUNT, DEBE, HABER, SALDO, NUMEROCOMPROBANTE, C_BPARTNER_ID, ACCOUNTTYPE, DATETRX, C_DOCTYPE_ID, C_INVOICE_ID, C_PAYMENT_ID, C_CASHLINE_ID, C_ALLOCATIONHDR_ID, duedate) "
						+ " VALUES ("
						+ subIndice
						+ ", '"
						+ p_includeOpenOrders_char
						+ "', '"
						+ p_ShowDetailedReceiptsPayments_char
						+ "', "
						+ getAD_Client_ID()
						+ ", "
						+ trx_Org_ID
						+ " , "
						+ getAD_PInstance_ID()
						+ " ,'"
						+ MCurrency.getISO_Code(getCtx(),
								rs.getInt("C_Currency_ID"))
						+ "', "
						+ rs.getBigDecimal("Amount")
						+ ", "
						+ rs.getBigDecimal("Debit")
						+ ", "
						+ rs.getBigDecimal("Credit")
						+ ", "
						+ acumBalance
						+ ", '"
						+ rs.getString("DocumentNo")
						+ "', "
						+ p_C_BPartnerID
						+ ", '"
						+ p_AccountType
						+ "', '"
						+ rs.getTimestamp("DateTrx")
						+ "', "
						+ rs.getInt("C_DocType_ID") + ", ");

				// La linea es de una factura?
				usql.append(
						DOC_INVOICE.equals(rs.getString("documenttable")) ? rs
								.getString("document_id") : "NULL")
						.append(", ");
				// La linea es de un pago/cheque?
				usql.append(
						DOC_PAYMENT.equals(rs.getString("documenttable")) ? rs
								.getString("document_id") : "NULL")
						.append(", ");
				// La linea es de una linea de caja?
				usql.append(
						DOC_CASHLINE.equals(rs.getString("documenttable")) ? rs
								.getString("document_id") : "NULL")
						.append(", ");
				// Es un recibo agrupado?
				usql.append(
						DOC_ALLOCATIONHDR.equals(rs.getString("documenttable")) ? rs
								.getString("document_id") : "NULL").append(
						" , ");

				// Si se tiene una fecha de vencimiento se inserta
				if (fechaVencimiento != null) {
					usql.append("'" + fechaVencimiento + "'");
				} else
					usql.append("NULL");

				usql.append(" ); ");

			}

			// incorporar los pedidos no facturados (parcial o total)
			if (p_includeOpenOrders)
				usql.append(appendOrdersNotInvoiced(subIndice, trx_Org_ID));

			if (usql.length() > 0)
				// Se insertan todas las líneas en la tabla.
				DB.executeUpdate(usql.toString(), get_TrxName());

		} catch (SQLException e) {
			log.log(Level.SEVERE, "Fill T_CuentaCorriente error", e);
			throw new Exception("Current Account Error", e);
		}
		return "";
	}

	private void calculateBalance(StringBuffer sqlDoc) throws Exception {
		StringBuffer sqlBalance = new StringBuffer();
		sqlBalance
				.append(" SELECT COALESCE(SUM(t.Credit),0.0) AS Credit, COALESCE(SUM(t.Debit),0.0) AS Debit ");
		sqlBalance.append(" FROM ( ");
		sqlBalance.append(sqlDoc).append(" AND d.DateTrx < ? ");
		sqlBalance.append(" ) t");

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sqlBalance.toString(), get_TrxName());
			// Parámetros de sqlDoc
			int i = 1;
			pstmt.setInt(i++, debit_signo_issotrx);
			pstmt.setInt(i++, client_Currency_ID);
			pstmt.setInt(i++, credit_signo_isotrx);
			pstmt.setInt(i++, client_Currency_ID);
			pstmt.setInt(i++, getAD_Client_ID());
			pstmt.setInt(i++, p_C_BPartnerID);
			i = pstmtSetParam(i, p_AD_Org_ID, pstmt);
			i = pstmtSetParam(i, p_C_DocType_ID, pstmt);
			// Parámetros de sqlBalance
			pstmt.setTimestamp(i++, p_DateTrx_From);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				acumDebit = rs.getBigDecimal("Debit");
				acumCredit = rs.getBigDecimal("Credit");
				// ANTONIO: La cuenta es al reves acumBalance =
				// acumCredit.subtract(acumDebit);
				acumBalance = acumDebit.subtract(acumCredit);
			}

		} catch (SQLException e) {
			throw new Exception("Calculate previous balance error", e);
		}
	}

	private void sqlAppend(String clause, Object value, StringBuffer sql) {
		if (value != null)
			sql.append(clause);
	}

	private int pstmtSetParam(int index, Object value, PreparedStatement pstmt)
			throws SQLException {
		int i = index;
		if (value != null)
			pstmt.setObject(i++, value);
		return i;
	}

	/**
	 * Incorpora las sentencias sql correspondientes a los pedidos no
	 * facturados, ya sea de manera total o parcial, contemplando las
	 * referencias indicadas en las líneas de factura que apuntan hacia las
	 * lineas de pedido, de compra o de venta Para cada linea de pedido, ver
	 * cuantas lineas de factura las referencian (con sus cantidades facturadas
	 * y monto)
	 */
	protected StringBuffer appendOrdersNotInvoiced(int subIndice, int trx_Org_ID)
			throws Exception {
		StringBuffer query = new StringBuffer(
				" SELECT 	o.C_Order_ID, o.DocumentNo, o.DateAcct, o.C_DocType_ID, ")
				.append(" 			coalesce(currencyconvert(o.grandtotal - sum(matches.totalamtinvoiced), o.c_currency_id, ?, ('now'::text)::timestamp(6) with time zone, COALESCE(c_conversiontype_id,0), o.ad_client_id, o.ad_org_id),0) as pendingToInvoiceAmt, o.c_currency_id, o.grandtotal as Amount")
				.append(" FROM ")
				.append(" ( ")
				.append("				SELECT ol.c_orderline_id, ol.linetotalamt, ol.qtyordered, coalesce(sum(il.qtyinvoiced),0) as qtyinvoiced, coalesce(sum(il.linetotalamt),0) as totalamtinvoiced ")
				.append("				FROM C_OrderLine ol ")
				.append("				JOIN C_Order o ON ol.C_Order_ID = o.C_Order_ID ")
				.append("				LEFT JOIN C_InvoiceLine il ON ol.C_OrderLine_ID = il.C_OrderLine_ID ")
				.append("				LEFT JOIN C_Invoice i ON il.C_Invoice_ID = i.C_Invoice_ID ")
				.append("				WHERE ol.AD_Client_ID = ? ")
				.append("				AND o.DocStatus IN ('CO','CL', 'RE', 'VO') ")
				.append("				AND (i.C_Invoice_ID IS NULL OR i.DocStatus IN ('CO','CL', 'RE', 'VO')) ")
				.append(" 				AND o.C_BPartner_ID = ? ");
		sqlAppend("   	AND ? <= o.Dateacct ", p_DateTrx_From, query);
		sqlAppend("   	AND o.Dateacct <= ? ", p_DateTrx_To, query);
		sqlAppend("	AND o.AD_Org_ID = ? ", p_AD_Org_ID, query);
		query.append(
				"  GROUP BY ol.c_orderline_id, ol.linetotalamt, ol.qtyordered ")
				.append("				HAVING sum(il.qtyinvoiced) IS NULL OR ol.qtyordered - sum(il.qtyinvoiced) > 0 ")
				.append(" ) AS matches ")
				.append(" JOIN C_OrderLine ol ON ol.C_OrderLine_ID = matches.C_OrderLine_ID ")
				.append(" JOIN C_Order o ON o.C_Order_ID = ol.C_Order_ID ")
				.append(" GROUP BY o.C_Order_ID, o.DocumentNo, o.C_DocType_ID, o.DateAcct, o.C_Currency_ID, o.C_ConversionType_ID, o.AD_Client_ID, o.AD_Org_ID, o.GrandTotal ")
				.append(" ORDER BY o.C_Order_ID ASC ");

		int i = 1;
		PreparedStatement pstmt = DB.prepareStatement(query.toString());

		pstmt.setInt(i++, client_Currency_ID);
		pstmt.setInt(i++, getAD_Client_ID());
		pstmt.setInt(i++, p_C_BPartnerID);
		i = pstmtSetParam(i, p_DateTrx_From, pstmt);
		i = pstmtSetParam(i, p_DateTrx_To, pstmt);
		i = pstmtSetParam(i, p_AD_Org_ID, pstmt);

		ResultSet rs = pstmt.executeQuery();
		StringBuffer usql = new StringBuffer();
		while (rs.next()) {
			subIndice++;
			usql.append(" INSERT INTO T_CUENTACORRIENTE (SUBINDICE, IncludeOpenOrders, ShowDetailedReceiptsPayments, AD_CLIENT_ID, AD_ORG_ID, AD_PINSTANCE_ID, ISO_CODE, AMOUNT, DEBE, HABER, SALDO, NUMEROCOMPROBANTE, C_BPARTNER_ID, ACCOUNTTYPE, DATETRX, C_DOCTYPE_ID, C_INVOICE_ID, C_PAYMENT_ID, C_CASHLINE_ID) "
					+ " VALUES ("
					+ subIndice
					+ ", '"
					+ p_includeOpenOrders_char
					+ "', '"
					+ p_ShowDetailedReceiptsPayments_char
					+ "', "
					+ getAD_Client_ID()
					+ ", "
					+ trx_Org_ID
					+ " , "
					+ getAD_PInstance_ID()
					+ " ,'"
					+ MCurrency.getISO_Code(getCtx(),
							rs.getInt("C_Currency_ID"))
					+ "', "
					+ rs.getBigDecimal("Amount")
					+ ", "
					+ (p_AccountType.equalsIgnoreCase("C") ? BigDecimal.ZERO
							: rs.getBigDecimal("pendingToInvoiceAmt"))
					+ ", "
					+ (p_AccountType.equalsIgnoreCase("C") ? rs
							.getBigDecimal("pendingToInvoiceAmt")
							: BigDecimal.ZERO)
					+ ", "
					+ acumBalance
					+ ", '"
					+ rs.getString("DocumentNo")
					+ "', "
					+ p_C_BPartnerID
					+ ", '"
					+ p_AccountType
					+ "', '"
					+ rs.getTimestamp("DateAcct")
					+ "', "
					+ rs.getInt("C_DocType_ID") + ", " + " null, null, null); ");
		}

		return usql;
	}

}
