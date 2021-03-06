/**
 *	Crea Funciones SQL Java Oracle)
 *
 *	$Header: createSQLJ.sql,v 2.0 Exp $
 */
 
CREATE OR REPLACE FUNCTION openxpertyaVersion
 	RETURN VARCHAR2
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.OpenXpertya.getVersion() return java.lang.String';
/
CREATE OR REPLACE FUNCTION openxpertyaProperties
 	RETURN VARCHAR2
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.OpenXpertya.getProperties() return java.lang.String';
/
CREATE OR REPLACE FUNCTION openxpertyaProperty(p_key VARCHAR2)
 	RETURN VARCHAR2
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.OpenXpertya.getProperty(java.lang.String) return java.lang.String';
/

/** Product	**/
CREATE OR REPLACE FUNCTION productAttribute (M_AttributeSetInstance_ID NUMBER)
 	RETURN NVARCHAR2
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Product.attributeName(int) return java.lang.String';
/

CREATE OR REPLACE FUNCTION bomPriceLimit (M_Product_ID NUMBER, M_PriceList_Version_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Product.bomPriceLimit(int,int) return java.math.BigDecimal';
/
CREATE OR REPLACE FUNCTION bomPriceList (M_Product_ID NUMBER, M_PriceList_Version_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Product.bomPriceList(int,int) return java.math.BigDecimal';
/
CREATE OR REPLACE FUNCTION bomPriceStd (M_Product_ID NUMBER, M_PriceList_Version_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Product.bomPriceStd(int,int) return java.math.BigDecimal';
/

CREATE OR REPLACE FUNCTION bomQtyAvailable (M_Product_ID NUMBER, M_Warehouse_ID NUMBER, M_Locator_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Product.bomQtyAvailable(int,int,int) return java.math.BigDecimal';
/
CREATE OR REPLACE FUNCTION bomQtyOnHand (M_Product_ID NUMBER, M_Warehouse_ID NUMBER, M_Locator_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Product.bomQtyOnHand(int,int,int) return java.math.BigDecimal';
/
CREATE OR REPLACE FUNCTION bomQtyOrdered (M_Product_ID NUMBER, M_Warehouse_ID NUMBER, M_Locator_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Product.bomQtyOrdered(int,int,int) return java.math.BigDecimal';
/
CREATE OR REPLACE FUNCTION bomQtyReserved (M_Product_ID NUMBER, M_Warehouse_ID NUMBER, M_Locator_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Product.bomQtyReserved(int,int,int) return java.math.BigDecimal';
/

/** Currency **/
CREATE OR REPLACE FUNCTION currencyBase (Amount NUMBER, C_CurrencyFrom_ID NUMBER, 
    ConversionDate DATE, AD_Client_ID NUMBER, AD_Org_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Currency.base(java.math.BigDecimal,int,java.sql.Timestamp,int,int) return java.math.BigDecimal';
/
CREATE OR REPLACE FUNCTION currencyConvert (Amount NUMBER, C_CurrencyFrom_ID NUMBER, C_CurrencyTo_ID NUMBER,
    ConversionDate DATE, C_ConversionType_ID NUMBER, AD_Client_ID NUMBER, AD_Org_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Currency.convert(java.math.BigDecimal,int,int,java.sql.Timestamp,int,int,int) return java.math.BigDecimal';
/
CREATE OR REPLACE FUNCTION currencyRate (C_CurrencyFrom_ID NUMBER, C_CurrencyTo_ID NUMBER,
    ConversionDate DATE, C_ConversionType_ID NUMBER, AD_Client_ID NUMBER, AD_Org_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Currency.rate(int,int,java.sql.Timestamp,int,int,int) return java.math.BigDecimal';
/

/** BPartner **/
CREATE OR REPLACE FUNCTION bpartnerRemitLocation (p_C_BPartner_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.BPartner.remitLocation(int) return int';
/

/** Invoice **/
CREATE OR REPLACE FUNCTION invoiceOpen (p_C_Invoice_ID NUMBER, p_C_InvoicePaySchedule_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Invoice.open(int,int) return java.math.BigDecimal';
/
CREATE OR REPLACE FUNCTION invoicePaid (p_C_Invoice_ID NUMBER, p_C_Currency_ID NUMBER, p_MultiplierAP NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Invoice.paid(int,int,int) return java.math.BigDecimal';
/
CREATE OR REPLACE FUNCTION invoiceDiscount (p_C_Invoice_ID NUMBER, p_PayDate Date, p_C_InvoicePaySchedule_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Invoice.discount(int,java.sql.Timestamp,int) return java.math.BigDecimal';
/

/** Payment Term **/
CREATE OR REPLACE FUNCTION paymentTermDueDays (p_C_PaymentTerm_ID NUMBER, p_DocDate DATE, p_PayDate DATE)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.PaymentTerm.dueDays(int,java.sql.Timestamp,java.sql.Timestamp) return int';
/
CREATE OR REPLACE FUNCTION paymentTermDiscount (p_Amount NUMBER, p_C_PaymentTerm_ID NUMBER, p_DocDate DATE, p_PayDate DATE)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.PaymentTerm.discount(java.math.BigDecimal,int,java.sql.Timestamp,java.sql.Timestamp) return java.math.BigDecimal';
/
CREATE OR REPLACE FUNCTION paymentTermDueDate (p_C_PaymentTerm_ID NUMBER, p_DocDate DATE)
 	RETURN DATE
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.PaymentTerm.dueDate(int,java.sql.Timestamp) return java.sql.Timestamp';
/

/** Payment **/
CREATE OR REPLACE FUNCTION paymentAllocated (p_C_Payment_ID NUMBER, p_C_Currency_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Payment.allocated(int,int) return java.math.BigDecimal';
/
CREATE OR REPLACE FUNCTION paymentAvailable (p_C_Payment_ID NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Payment.available(int) return java.math.BigDecimal';
/

/** Account **/
CREATE OR REPLACE FUNCTION acctBalance (p_Account_ID NUMBER, p_AmtDr NUMBER, p_AmtCr NUMBER)
 	RETURN NUMBER
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Account.balance(int,java.math.BigDecimal,java.math.BigDecimal) return java.math.BigDecimal';
/

/** General	**/
BEGIN
	dbms_java.grant_permission('openxp','SYS:java.util.PropertyPermission', '*', 'read,write');
END;
/

/** Get Character at Position   */
CREATE OR REPLACE FUNCTION charAt
(
    p_string    VARCHAR2,
    p_pos       NUMBER
)
 	RETURN VARCHAR2
AS
BEGIN
    RETURN SUBSTR(p_string, p_pos, 1);
END;    
/
/** GetDate                     */
CREATE OR REPLACE FUNCTION getdate
 	RETURN DATE
AS
BEGIN
    RETURN SysDate;
END;    
/
/** First Of DD/DY/MM/Q         */
CREATE OR REPLACE FUNCTION firstOf
(
    p_date      DATE,
    p_datePart  VARCHAR2
)
 	RETURN DATE
AS
BEGIN
    RETURN TRUNC(p_date, p_datePart);
END;    
/
/** Add Number of Days      */
CREATE OR REPLACE FUNCTION addDays
(
    p_date      DATE,
    p_days      NUMBER
)
 	RETURN DATE
AS
BEGIN
    RETURN TRUNC(p_date) + p_days;
END;    
/
/** Difference in Days      */
CREATE OR REPLACE FUNCTION daysBetween
(
    p_date1     DATE,
    p_date2     DATE
)
 	RETURN NUMBER
AS
BEGIN
    RETURN TRUNC(p_date1) - TRUNC(p_date2);
END;    
/

/** Manufacturing documentNo vpj-cd e-evolution  2005/04/06 */
CREATE OR REPLACE FUNCTION documentNo (p_MPC_MRP_ID NUMBER)
 	RETURN VARCHAR2
 	AS LANGUAGE JAVA 
	NAME 'org.openxpertya.sqlj.Manufacturing.documentNo(int) return java.lang.String';
/


SELECT --openxpertyaVersion(), openxpertyaProperty('java.vendor'), 
    TRUNC(getdate()) FROM DUAL
/

EXIT
