/** Modelo Generado - NO CAMBIAR MANUALMENTE - Copyright (C) 2006 FUNDESLE */
package org.openXpertya.model;
import java.util.*;
import java.sql.*;
import java.math.*;
import org.openXpertya.util.*;
/** Modelo Generado por I_Payment
 *  @author Comunidad de Desarrollo openXpertya*         *Basado en Codigo Original Modificado, Revisado y Optimizado de:*         * Jorg Janke 
 *  @version  - 2008-01-03 10:26:35.046 */
public class X_I_Payment extends PO
{
/** Constructor estándar */
public X_I_Payment (Properties ctx, int I_Payment_ID, String trxName)
{
super (ctx, I_Payment_ID, trxName);
/** if (I_Payment_ID == 0)
{
setI_IsImported (false);
setI_Payment_ID (0);
}
 */
}
/** Load Constructor */
public X_I_Payment (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=597 */
public static final int Table_ID=597;

/** TableName=I_Payment */
public static final String Table_Name="I_Payment";

protected static KeyNamePair Model = new KeyNamePair(597,"I_Payment");
protected static BigDecimal AccessLevel = new BigDecimal(2);

/** Load Meta Data */
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
public String toString()
{
StringBuffer sb = new StringBuffer ("X_I_Payment[").append(getID()).append("]");
return sb.toString();
}
/** Set Account City.
City or the Credit Card or Account Holder */
public void setA_City (String A_City)
{
if (A_City != null && A_City.length() > 60)
{
log.warning("Length > 60 - truncated");
A_City = A_City.substring(0,59);
}
set_Value ("A_City", A_City);
}
/** Get Account City.
City or the Credit Card or Account Holder */
public String getA_City() 
{
return (String)get_Value("A_City");
}
/** Set Account Country.
Country */
public void setA_Country (String A_Country)
{
if (A_Country != null && A_Country.length() > 40)
{
log.warning("Length > 40 - truncated");
A_Country = A_Country.substring(0,39);
}
set_Value ("A_Country", A_Country);
}
/** Get Account Country.
Country */
public String getA_Country() 
{
return (String)get_Value("A_Country");
}
/** Set Account EMail.
Email Address */
public void setA_EMail (String A_EMail)
{
if (A_EMail != null && A_EMail.length() > 60)
{
log.warning("Length > 60 - truncated");
A_EMail = A_EMail.substring(0,59);
}
set_Value ("A_EMail", A_EMail);
}
/** Get Account EMail.
Email Address */
public String getA_EMail() 
{
return (String)get_Value("A_EMail");
}
/** Set Driver License.
Payment Identification - Driver License */
public void setA_Ident_DL (String A_Ident_DL)
{
if (A_Ident_DL != null && A_Ident_DL.length() > 20)
{
log.warning("Length > 20 - truncated");
A_Ident_DL = A_Ident_DL.substring(0,19);
}
set_Value ("A_Ident_DL", A_Ident_DL);
}
/** Get Driver License.
Payment Identification - Driver License */
public String getA_Ident_DL() 
{
return (String)get_Value("A_Ident_DL");
}
/** Set Social Security No.
Payment Identification - Social Security No */
public void setA_Ident_SSN (String A_Ident_SSN)
{
if (A_Ident_SSN != null && A_Ident_SSN.length() > 20)
{
log.warning("Length > 20 - truncated");
A_Ident_SSN = A_Ident_SSN.substring(0,19);
}
set_Value ("A_Ident_SSN", A_Ident_SSN);
}
/** Get Social Security No.
Payment Identification - Social Security No */
public String getA_Ident_SSN() 
{
return (String)get_Value("A_Ident_SSN");
}
/** Set Account Name.
Name on Credit Card or Account holder */
public void setA_Name (String A_Name)
{
if (A_Name != null && A_Name.length() > 60)
{
log.warning("Length > 60 - truncated");
A_Name = A_Name.substring(0,59);
}
set_Value ("A_Name", A_Name);
}
/** Get Account Name.
Name on Credit Card or Account holder */
public String getA_Name() 
{
return (String)get_Value("A_Name");
}
/** Set Account State.
State of the Credit Card or Account holder */
public void setA_State (String A_State)
{
if (A_State != null && A_State.length() > 40)
{
log.warning("Length > 40 - truncated");
A_State = A_State.substring(0,39);
}
set_Value ("A_State", A_State);
}
/** Get Account State.
State of the Credit Card or Account holder */
public String getA_State() 
{
return (String)get_Value("A_State");
}
/** Set Account Street.
Street address of the Credit Card or Account holder */
public void setA_Street (String A_Street)
{
if (A_Street != null && A_Street.length() > 60)
{
log.warning("Length > 60 - truncated");
A_Street = A_Street.substring(0,59);
}
set_Value ("A_Street", A_Street);
}
/** Get Account Street.
Street address of the Credit Card or Account holder */
public String getA_Street() 
{
return (String)get_Value("A_Street");
}
/** Set Account Zip/Postal.
Zip Code of the Credit Card or Account Holder */
public void setA_Zip (String A_Zip)
{
if (A_Zip != null && A_Zip.length() > 20)
{
log.warning("Length > 20 - truncated");
A_Zip = A_Zip.substring(0,19);
}
set_Value ("A_Zip", A_Zip);
}
/** Get Account Zip/Postal.
Zip Code of the Credit Card or Account Holder */
public String getA_Zip() 
{
return (String)get_Value("A_Zip");
}
/** Set Account No.
Account Number */
public void setAccountNo (String AccountNo)
{
if (AccountNo != null && AccountNo.length() > 20)
{
log.warning("Length > 20 - truncated");
AccountNo = AccountNo.substring(0,19);
}
set_Value ("AccountNo", AccountNo);
}
/** Get Account No.
Account Number */
public String getAccountNo() 
{
return (String)get_Value("AccountNo");
}
/** Set Business Partner Key.
Key of the Business Partner */
public void setBPartnerValue (String BPartnerValue)
{
if (BPartnerValue != null && BPartnerValue.length() > 40)
{
log.warning("Length > 40 - truncated");
BPartnerValue = BPartnerValue.substring(0,39);
}
set_Value ("BPartnerValue", BPartnerValue);
}
/** Get Business Partner Key.
Key of the Business Partner */
public String getBPartnerValue() 
{
return (String)get_Value("BPartnerValue");
}
/** Set Bank Account No.
Bank Account Number */
public void setBankAccountNo (String BankAccountNo)
{
if (BankAccountNo != null && BankAccountNo.length() > 20)
{
log.warning("Length > 20 - truncated");
BankAccountNo = BankAccountNo.substring(0,19);
}
set_Value ("BankAccountNo", BankAccountNo);
}
/** Get Bank Account No.
Bank Account Number */
public String getBankAccountNo() 
{
return (String)get_Value("BankAccountNo");
}
/** Set Business Partner .
Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_Value ("C_BPartner_ID", null);
 else 
set_Value ("C_BPartner_ID", new Integer(C_BPartner_ID));
}
/** Get Business Partner .
Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Bank Account.
Account at the Bank */
public void setC_BankAccount_ID (int C_BankAccount_ID)
{
if (C_BankAccount_ID <= 0) set_Value ("C_BankAccount_ID", null);
 else 
set_Value ("C_BankAccount_ID", new Integer(C_BankAccount_ID));
}
/** Get Bank Account.
Account at the Bank */
public int getC_BankAccount_ID() 
{
Integer ii = (Integer)get_Value("C_BankAccount_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Charge.
Additional document charges */
public void setC_Charge_ID (int C_Charge_ID)
{
if (C_Charge_ID <= 0) set_Value ("C_Charge_ID", null);
 else 
set_Value ("C_Charge_ID", new Integer(C_Charge_ID));
}
/** Get Charge.
Additional document charges */
public int getC_Charge_ID() 
{
Integer ii = (Integer)get_Value("C_Charge_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Currency.
The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID <= 0) set_Value ("C_Currency_ID", null);
 else 
set_Value ("C_Currency_ID", new Integer(C_Currency_ID));
}
/** Get Currency.
The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Document Type.
Document type or rules */
public void setC_DocType_ID (int C_DocType_ID)
{
if (C_DocType_ID <= 0) set_Value ("C_DocType_ID", null);
 else 
set_Value ("C_DocType_ID", new Integer(C_DocType_ID));
}
/** Get Document Type.
Document type or rules */
public int getC_DocType_ID() 
{
Integer ii = (Integer)get_Value("C_DocType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Invoice.
Invoice Identifier */
public void setC_Invoice_ID (int C_Invoice_ID)
{
if (C_Invoice_ID <= 0) set_Value ("C_Invoice_ID", null);
 else 
set_Value ("C_Invoice_ID", new Integer(C_Invoice_ID));
}
/** Get Invoice.
Invoice Identifier */
public int getC_Invoice_ID() 
{
Integer ii = (Integer)get_Value("C_Invoice_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Payment.
Payment identifier */
public void setC_Payment_ID (int C_Payment_ID)
{
if (C_Payment_ID <= 0) set_Value ("C_Payment_ID", null);
 else 
set_Value ("C_Payment_ID", new Integer(C_Payment_ID));
}
/** Get Payment.
Payment identifier */
public int getC_Payment_ID() 
{
Integer ii = (Integer)get_Value("C_Payment_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Charge amount.
Charge Amount */
public void setChargeAmt (BigDecimal ChargeAmt)
{
set_Value ("ChargeAmt", ChargeAmt);
}
/** Get Charge amount.
Charge Amount */
public BigDecimal getChargeAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("ChargeAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Charge Name.
Name of the Charge */
public void setChargeName (String ChargeName)
{
if (ChargeName != null && ChargeName.length() > 60)
{
log.warning("Length > 60 - truncated");
ChargeName = ChargeName.substring(0,59);
}
set_Value ("ChargeName", ChargeName);
}
/** Get Charge Name.
Name of the Charge */
public String getChargeName() 
{
return (String)get_Value("ChargeName");
}
/** Set Check No.
Check Number */
public void setCheckNo (String CheckNo)
{
if (CheckNo != null && CheckNo.length() > 20)
{
log.warning("Length > 20 - truncated");
CheckNo = CheckNo.substring(0,19);
}
set_Value ("CheckNo", CheckNo);
}
/** Get Check No.
Check Number */
public String getCheckNo() 
{
return (String)get_Value("CheckNo");
}
/** Set Exp. Month.
Expiry Month */
public void setCreditCardExpMM (int CreditCardExpMM)
{
set_Value ("CreditCardExpMM", new Integer(CreditCardExpMM));
}
/** Get Exp. Month.
Expiry Month */
public int getCreditCardExpMM() 
{
Integer ii = (Integer)get_Value("CreditCardExpMM");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Exp. Year.
Expiry Year */
public void setCreditCardExpYY (int CreditCardExpYY)
{
set_Value ("CreditCardExpYY", new Integer(CreditCardExpYY));
}
/** Get Exp. Year.
Expiry Year */
public int getCreditCardExpYY() 
{
Integer ii = (Integer)get_Value("CreditCardExpYY");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Number.
Credit Card Number  */
public void setCreditCardNumber (String CreditCardNumber)
{
if (CreditCardNumber != null && CreditCardNumber.length() > 20)
{
log.warning("Length > 20 - truncated");
CreditCardNumber = CreditCardNumber.substring(0,19);
}
set_Value ("CreditCardNumber", CreditCardNumber);
}
/** Get Number.
Credit Card Number  */
public String getCreditCardNumber() 
{
return (String)get_Value("CreditCardNumber");
}
public static final int CREDITCARDTYPE_AD_Reference_ID=149;
/** Diners = D */
public static final String CREDITCARDTYPE_Diners = "D";
/** ATM = C */
public static final String CREDITCARDTYPE_ATM = "C";
/** Purchase Card = P */
public static final String CREDITCARDTYPE_PurchaseCard = "P";
/** MasterCard = M */
public static final String CREDITCARDTYPE_MasterCard = "M";
/** Visa = V */
public static final String CREDITCARDTYPE_Visa = "V";
/** Amex = A */
public static final String CREDITCARDTYPE_Amex = "A";
/** Discover = N */
public static final String CREDITCARDTYPE_Discover = "N";
/** Set Credit Card.
Credit Card (Visa, MC, AmEx) */
public void setCreditCardType (String CreditCardType)
{
if (CreditCardType == null || CreditCardType.equals("D") || CreditCardType.equals("C") || CreditCardType.equals("P") || CreditCardType.equals("M") || CreditCardType.equals("V") || CreditCardType.equals("A") || CreditCardType.equals("N"));
 else throw new IllegalArgumentException ("CreditCardType Invalid value - Reference_ID=149 - D - C - P - M - V - A - N");
if (CreditCardType != null && CreditCardType.length() > 1)
{
log.warning("Length > 1 - truncated");
CreditCardType = CreditCardType.substring(0,0);
}
set_Value ("CreditCardType", CreditCardType);
}
/** Get Credit Card.
Credit Card (Visa, MC, AmEx) */
public String getCreditCardType() 
{
return (String)get_Value("CreditCardType");
}
/** Set Verification Code.
Credit Card Verification code on credit card */
public void setCreditCardVV (String CreditCardVV)
{
if (CreditCardVV != null && CreditCardVV.length() > 4)
{
log.warning("Length > 4 - truncated");
CreditCardVV = CreditCardVV.substring(0,3);
}
set_Value ("CreditCardVV", CreditCardVV);
}
/** Get Verification Code.
Credit Card Verification code on credit card */
public String getCreditCardVV() 
{
return (String)get_Value("CreditCardVV");
}
/** Set Transaction Date.
Transaction Date */
public void setDateTrx (Timestamp DateTrx)
{
set_Value ("DateTrx", DateTrx);
}
/** Get Transaction Date.
Transaction Date */
public Timestamp getDateTrx() 
{
return (Timestamp)get_Value("DateTrx");
}
/** Set Discount Amount.
Calculated amount of discount */
public void setDiscountAmt (BigDecimal DiscountAmt)
{
set_Value ("DiscountAmt", DiscountAmt);
}
/** Get Discount Amount.
Calculated amount of discount */
public BigDecimal getDiscountAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("DiscountAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Document Type Name.
Name of the Document Type */
public void setDocTypeName (String DocTypeName)
{
if (DocTypeName != null && DocTypeName.length() > 60)
{
log.warning("Length > 60 - truncated");
DocTypeName = DocTypeName.substring(0,59);
}
set_Value ("DocTypeName", DocTypeName);
}
/** Get Document Type Name.
Name of the Document Type */
public String getDocTypeName() 
{
return (String)get_Value("DocTypeName");
}
/** Set Document No.
Document sequence number of the document */
public void setDocumentNo (String DocumentNo)
{
if (DocumentNo != null && DocumentNo.length() > 30)
{
log.warning("Length > 30 - truncated");
DocumentNo = DocumentNo.substring(0,29);
}
set_Value ("DocumentNo", DocumentNo);
}
/** Get Document No.
Document sequence number of the document */
public String getDocumentNo() 
{
return (String)get_Value("DocumentNo");
}
/** Set Import Error Message.
Messages generated from import process */
public void setI_ErrorMsg (String I_ErrorMsg)
{
if (I_ErrorMsg != null && I_ErrorMsg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
I_ErrorMsg = I_ErrorMsg.substring(0,1999);
}
set_Value ("I_ErrorMsg", I_ErrorMsg);
}
/** Get Import Error Message.
Messages generated from import process */
public String getI_ErrorMsg() 
{
return (String)get_Value("I_ErrorMsg");
}
/** Set Imported.
Has this import been processed */
public void setI_IsImported (boolean I_IsImported)
{
set_Value ("I_IsImported", new Boolean(I_IsImported));
}
/** Get Imported.
Has this import been processed */
public boolean isI_IsImported() 
{
Object oo = get_Value("I_IsImported");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Import Payment.
Import Payment */
public void setI_Payment_ID (int I_Payment_ID)
{
set_ValueNoCheck ("I_Payment_ID", new Integer(I_Payment_ID));
}
/** Get Import Payment.
Import Payment */
public int getI_Payment_ID() 
{
Integer ii = (Integer)get_Value("I_Payment_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Invoice Document No.
Document Number of the Invoice */
public void setInvoiceDocumentNo (String InvoiceDocumentNo)
{
if (InvoiceDocumentNo != null && InvoiceDocumentNo.length() > 30)
{
log.warning("Length > 30 - truncated");
InvoiceDocumentNo = InvoiceDocumentNo.substring(0,29);
}
set_Value ("InvoiceDocumentNo", InvoiceDocumentNo);
}
/** Get Invoice Document No.
Document Number of the Invoice */
public String getInvoiceDocumentNo() 
{
return (String)get_Value("InvoiceDocumentNo");
}
/** Set Approved.
Indicates if this document requires approval */
public void setIsApproved (boolean IsApproved)
{
set_Value ("IsApproved", new Boolean(IsApproved));
}
/** Get Approved.
Indicates if this document requires approval */
public boolean isApproved() 
{
Object oo = get_Value("IsApproved");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Delayed Capture.
Charge after Shipment */
public void setIsDelayedCapture (boolean IsDelayedCapture)
{
set_Value ("IsDelayedCapture", new Boolean(IsDelayedCapture));
}
/** Get Delayed Capture.
Charge after Shipment */
public boolean isDelayedCapture() 
{
Object oo = get_Value("IsDelayedCapture");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Over/Under Payment.
Over-Payment (unallocated) or Under-Payment (partial payment) */
public void setIsOverUnderPayment (boolean IsOverUnderPayment)
{
set_Value ("IsOverUnderPayment", new Boolean(IsOverUnderPayment));
}
/** Get Over/Under Payment.
Over-Payment (unallocated) or Under-Payment (partial payment) */
public boolean isOverUnderPayment() 
{
Object oo = get_Value("IsOverUnderPayment");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Receipt.
This is a sales transaction (receipt) */
public void setIsReceipt (boolean IsReceipt)
{
set_Value ("IsReceipt", new Boolean(IsReceipt));
}
/** Get Receipt.
This is a sales transaction (receipt) */
public boolean isReceipt() 
{
Object oo = get_Value("IsReceipt");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Self-Service.
This is a Self-Service entry or this entry can be changed via Self-Service */
public void setIsSelfService (boolean IsSelfService)
{
set_Value ("IsSelfService", new Boolean(IsSelfService));
}
/** Get Self-Service.
This is a Self-Service entry or this entry can be changed via Self-Service */
public boolean isSelfService() 
{
Object oo = get_Value("IsSelfService");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Micr.
Combination of routing no, account and check no */
public void setMicr (String Micr)
{
if (Micr != null && Micr.length() > 20)
{
log.warning("Length > 20 - truncated");
Micr = Micr.substring(0,19);
}
set_Value ("Micr", Micr);
}
/** Get Micr.
Combination of routing no, account and check no */
public String getMicr() 
{
return (String)get_Value("Micr");
}
/** Set Original Transaction ID.
Original Transaction ID */
public void setOrig_TrxID (String Orig_TrxID)
{
if (Orig_TrxID != null && Orig_TrxID.length() > 20)
{
log.warning("Length > 20 - truncated");
Orig_TrxID = Orig_TrxID.substring(0,19);
}
set_Value ("Orig_TrxID", Orig_TrxID);
}
/** Get Original Transaction ID.
Original Transaction ID */
public String getOrig_TrxID() 
{
return (String)get_Value("Orig_TrxID");
}
/** Set Over/Under Payment.
Over-Payment (unallocated) or Under-Payment (partial payment) Amount */
public void setOverUnderAmt (BigDecimal OverUnderAmt)
{
set_Value ("OverUnderAmt", OverUnderAmt);
}
/** Get Over/Under Payment.
Over-Payment (unallocated) or Under-Payment (partial payment) Amount */
public BigDecimal getOverUnderAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("OverUnderAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set PO Number.
Purchase Order Number */
public void setPONum (String PONum)
{
if (PONum != null && PONum.length() > 60)
{
log.warning("Length > 60 - truncated");
PONum = PONum.substring(0,59);
}
set_Value ("PONum", PONum);
}
/** Get PO Number.
Purchase Order Number */
public String getPONum() 
{
return (String)get_Value("PONum");
}
/** Set Payment amount.
Amount being paid */
public void setPayAmt (BigDecimal PayAmt)
{
set_Value ("PayAmt", PayAmt);
}
/** Get Payment amount.
Amount being paid */
public BigDecimal getPayAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("PayAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Processed.
The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", new Boolean(Processed));
}
/** Get Processed.
The document has been processed */
public boolean isProcessed() 
{
Object oo = get_Value("Processed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", new Boolean(Processing));
}
/** Get Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Authorization Code.
Authorization Code returned */
public void setR_AuthCode (String R_AuthCode)
{
if (R_AuthCode != null && R_AuthCode.length() > 20)
{
log.warning("Length > 20 - truncated");
R_AuthCode = R_AuthCode.substring(0,19);
}
set_Value ("R_AuthCode", R_AuthCode);
}
/** Get Authorization Code.
Authorization Code returned */
public String getR_AuthCode() 
{
return (String)get_Value("R_AuthCode");
}
/** Set Info.
Response info */
public void setR_Info (String R_Info)
{
if (R_Info != null && R_Info.length() > 2000)
{
log.warning("Length > 2000 - truncated");
R_Info = R_Info.substring(0,1999);
}
set_Value ("R_Info", R_Info);
}
/** Get Info.
Response info */
public String getR_Info() 
{
return (String)get_Value("R_Info");
}
/** Set Reference.
Payment reference */
public void setR_PnRef (String R_PnRef)
{
if (R_PnRef != null && R_PnRef.length() > 20)
{
log.warning("Length > 20 - truncated");
R_PnRef = R_PnRef.substring(0,19);
}
set_Value ("R_PnRef", R_PnRef);
}
/** Get Reference.
Payment reference */
public String getR_PnRef() 
{
return (String)get_Value("R_PnRef");
}
/** Set Response Message.
Response message */
public void setR_RespMsg (String R_RespMsg)
{
if (R_RespMsg != null && R_RespMsg.length() > 60)
{
log.warning("Length > 60 - truncated");
R_RespMsg = R_RespMsg.substring(0,59);
}
set_Value ("R_RespMsg", R_RespMsg);
}
/** Get Response Message.
Response message */
public String getR_RespMsg() 
{
return (String)get_Value("R_RespMsg");
}
/** Set Result.
Result of transmission */
public void setR_Result (String R_Result)
{
if (R_Result != null && R_Result.length() > 20)
{
log.warning("Length > 20 - truncated");
R_Result = R_Result.substring(0,19);
}
set_Value ("R_Result", R_Result);
}
/** Get Result.
Result of transmission */
public String getR_Result() 
{
return (String)get_Value("R_Result");
}
/** Set Routing No.
Bank Routing Number */
public void setRoutingNo (String RoutingNo)
{
if (RoutingNo != null && RoutingNo.length() > 20)
{
log.warning("Length > 20 - truncated");
RoutingNo = RoutingNo.substring(0,19);
}
set_Value ("RoutingNo", RoutingNo);
}
/** Get Routing No.
Bank Routing Number */
public String getRoutingNo() 
{
return (String)get_Value("RoutingNo");
}
/** Set Swipe.
Track 1 and 2 of the Credit Card */
public void setSwipe (String Swipe)
{
if (Swipe != null && Swipe.length() > 80)
{
log.warning("Length > 80 - truncated");
Swipe = Swipe.substring(0,79);
}
set_Value ("Swipe", Swipe);
}
/** Get Swipe.
Track 1 and 2 of the Credit Card */
public String getSwipe() 
{
return (String)get_Value("Swipe");
}
/** Set Tax Amount.
Tax Amount for a document */
public void setTaxAmt (BigDecimal TaxAmt)
{
set_Value ("TaxAmt", TaxAmt);
}
/** Get Tax Amount.
Tax Amount for a document */
public BigDecimal getTaxAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("TaxAmt");
if (bd == null) return Env.ZERO;
return bd;
}
public static final int TENDERTYPE_AD_Reference_ID=214;
/** Check = K */
public static final String TENDERTYPE_Check = "K";
/** Direct Debit = D */
public static final String TENDERTYPE_DirectDebit = "D";
/** Direct Deposit = A */
public static final String TENDERTYPE_DirectDeposit = "A";
/** Credit Card = C */
public static final String TENDERTYPE_CreditCard = "C";
/** Cash = CA */
public static final String TENDERTYPE_Cash = "CA";
/** Set Tender type.
Method of Payment */
public void setTenderType (String TenderType)
{
if (TenderType == null || TenderType.equals("K") || TenderType.equals("D") || TenderType.equals("A") || TenderType.equals("C") || TenderType.equals("CA"));
 else throw new IllegalArgumentException ("TenderType Invalid value - Reference_ID=214 - K - D - A - C - CA");
if (TenderType != null && TenderType.length() > 1)
{
log.warning("Length > 1 - truncated");
TenderType = TenderType.substring(0,0);
}
set_Value ("TenderType", TenderType);
}
/** Get Tender type.
Method of Payment */
public String getTenderType() 
{
return (String)get_Value("TenderType");
}
public static final int TRXTYPE_AD_Reference_ID=215;
/** Sales = S */
public static final String TRXTYPE_Sales = "S";
/** Delayed Capture = D */
public static final String TRXTYPE_DelayedCapture = "D";
/** Credit (Payment) = C */
public static final String TRXTYPE_CreditPayment = "C";
/** Voice Authorization = F */
public static final String TRXTYPE_VoiceAuthorization = "F";
/** Authorization = A */
public static final String TRXTYPE_Authorization = "A";
/** Void = V */
public static final String TRXTYPE_Void = "V";
/** Set Transaction Type.
Type of credit card transaction */
public void setTrxType (String TrxType)
{
if (TrxType == null || TrxType.equals("S") || TrxType.equals("D") || TrxType.equals("C") || TrxType.equals("F") || TrxType.equals("A") || TrxType.equals("V"));
 else throw new IllegalArgumentException ("TrxType Invalid value - Reference_ID=215 - S - D - C - F - A - V");
if (TrxType != null && TrxType.length() > 1)
{
log.warning("Length > 1 - truncated");
TrxType = TrxType.substring(0,0);
}
set_Value ("TrxType", TrxType);
}
/** Get Transaction Type.
Type of credit card transaction */
public String getTrxType() 
{
return (String)get_Value("TrxType");
}
/** Set Voice authorization code.
Voice Authorization Code from credit card company */
public void setVoiceAuthCode (String VoiceAuthCode)
{
if (VoiceAuthCode != null && VoiceAuthCode.length() > 20)
{
log.warning("Length > 20 - truncated");
VoiceAuthCode = VoiceAuthCode.substring(0,19);
}
set_Value ("VoiceAuthCode", VoiceAuthCode);
}
/** Get Voice authorization code.
Voice Authorization Code from credit card company */
public String getVoiceAuthCode() 
{
return (String)get_Value("VoiceAuthCode");
}
/** Set Write-off Amount.
Amount to write-off */
public void setWriteOffAmt (BigDecimal WriteOffAmt)
{
set_Value ("WriteOffAmt", WriteOffAmt);
}
/** Get Write-off Amount.
Amount to write-off */
public BigDecimal getWriteOffAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("WriteOffAmt");
if (bd == null) return Env.ZERO;
return bd;
}
}
