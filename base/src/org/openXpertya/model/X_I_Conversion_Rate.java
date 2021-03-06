/** Modelo Generado - NO CAMBIAR MANUALMENTE - Copyright (C) 2006 FUNDESLE */
package org.openXpertya.model;
import java.util.*;
import java.sql.*;
import java.math.*;
import org.openXpertya.util.*;
/** Modelo Generado por I_Conversion_Rate
 *  @author Comunidad de Desarrollo openXpertya*         *Basado en Codigo Original Modificado, Revisado y Optimizado de:*         * Jorg Janke 
 *  @version  - 2008-01-03 10:26:34.578 */
public class X_I_Conversion_Rate extends PO
{
/** Constructor estándar */
public X_I_Conversion_Rate (Properties ctx, int I_Conversion_Rate_ID, String trxName)
{
super (ctx, I_Conversion_Rate_ID, trxName);
/** if (I_Conversion_Rate_ID == 0)
{
setI_Conversion_Rate_ID (0);
}
 */
}
/** Load Constructor */
public X_I_Conversion_Rate (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=641 */
public static final int Table_ID=641;

/** TableName=I_Conversion_Rate */
public static final String Table_Name="I_Conversion_Rate";

protected static KeyNamePair Model = new KeyNamePair(641,"I_Conversion_Rate");
protected static BigDecimal AccessLevel = new BigDecimal(6);

/** Load Meta Data */
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
public String toString()
{
StringBuffer sb = new StringBuffer ("X_I_Conversion_Rate[").append(getID()).append("]");
return sb.toString();
}
/** Set Currency Type.
Currency Conversion Rate Type */
public void setC_ConversionType_ID (int C_ConversionType_ID)
{
if (C_ConversionType_ID <= 0) set_Value ("C_ConversionType_ID", null);
 else 
set_Value ("C_ConversionType_ID", new Integer(C_ConversionType_ID));
}
/** Get Currency Type.
Currency Conversion Rate Type */
public int getC_ConversionType_ID() 
{
Integer ii = (Integer)get_Value("C_ConversionType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Conversion Rate.
Rate used for converting currencies */
public void setC_Conversion_Rate_ID (int C_Conversion_Rate_ID)
{
if (C_Conversion_Rate_ID <= 0) set_Value ("C_Conversion_Rate_ID", null);
 else 
set_Value ("C_Conversion_Rate_ID", new Integer(C_Conversion_Rate_ID));
}
/** Get Conversion Rate.
Rate used for converting currencies */
public int getC_Conversion_Rate_ID() 
{
Integer ii = (Integer)get_Value("C_Conversion_Rate_ID");
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
public static final int C_CURRENCY_ID_TO_AD_Reference_ID=112;
/** Set Currency To.
Target currency */
public void setC_Currency_ID_To (int C_Currency_ID_To)
{
set_Value ("C_Currency_ID_To", new Integer(C_Currency_ID_To));
}
/** Get Currency To.
Target currency */
public int getC_Currency_ID_To() 
{
Integer ii = (Integer)get_Value("C_Currency_ID_To");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Currency Type Key.
Key value for the Currency Conversion Rate Type */
public void setConversionTypeValue (String ConversionTypeValue)
{
if (ConversionTypeValue != null && ConversionTypeValue.length() > 40)
{
log.warning("Length > 40 - truncated");
ConversionTypeValue = ConversionTypeValue.substring(0,39);
}
set_Value ("ConversionTypeValue", ConversionTypeValue);
}
/** Get Currency Type Key.
Key value for the Currency Conversion Rate Type */
public String getConversionTypeValue() 
{
return (String)get_Value("ConversionTypeValue");
}
/** Set Create Reciprocal Rate.
Create Reciprocal Rate from current information */
public void setCreateReciprocalRate (boolean CreateReciprocalRate)
{
set_Value ("CreateReciprocalRate", new Boolean(CreateReciprocalRate));
}
/** Get Create Reciprocal Rate.
Create Reciprocal Rate from current information */
public boolean isCreateReciprocalRate() 
{
Object oo = get_Value("CreateReciprocalRate");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Divide Rate.
To convert Source number to Target number, the Source is divided */
public void setDivideRate (BigDecimal DivideRate)
{
set_Value ("DivideRate", DivideRate);
}
/** Get Divide Rate.
To convert Source number to Target number, the Source is divided */
public BigDecimal getDivideRate() 
{
BigDecimal bd = (BigDecimal)get_Value("DivideRate");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set ISO Currency Code.
Three letter ISO 4217 Code of the Currency */
public void setISO_Code (String ISO_Code)
{
if (ISO_Code != null && ISO_Code.length() > 3)
{
log.warning("Length > 3 - truncated");
ISO_Code = ISO_Code.substring(0,2);
}
set_Value ("ISO_Code", ISO_Code);
}
/** Get ISO Currency Code.
Three letter ISO 4217 Code of the Currency */
public String getISO_Code() 
{
return (String)get_Value("ISO_Code");
}
/** Set ISO Currency To Code.
Three letter ISO 4217 Code of the To Currency */
public void setISO_Code_To (String ISO_Code_To)
{
if (ISO_Code_To != null && ISO_Code_To.length() > 3)
{
log.warning("Length > 3 - truncated");
ISO_Code_To = ISO_Code_To.substring(0,2);
}
set_Value ("ISO_Code_To", ISO_Code_To);
}
/** Get ISO Currency To Code.
Three letter ISO 4217 Code of the To Currency */
public String getISO_Code_To() 
{
return (String)get_Value("ISO_Code_To");
}
/** Set Import Conversion Rate.
Import Currency Conversion Rate */
public void setI_Conversion_Rate_ID (int I_Conversion_Rate_ID)
{
set_ValueNoCheck ("I_Conversion_Rate_ID", new Integer(I_Conversion_Rate_ID));
}
/** Get Import Conversion Rate.
Import Currency Conversion Rate */
public int getI_Conversion_Rate_ID() 
{
Integer ii = (Integer)get_Value("I_Conversion_Rate_ID");
if (ii == null) return 0;
return ii.intValue();
}
public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(getID(), String.valueOf(getI_Conversion_Rate_ID()));
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
/** Set Multiply Rate.
Rate to multiple the source by to calculate the target. */
public void setMultiplyRate (BigDecimal MultiplyRate)
{
set_Value ("MultiplyRate", MultiplyRate);
}
/** Get Multiply Rate.
Rate to multiple the source by to calculate the target. */
public BigDecimal getMultiplyRate() 
{
BigDecimal bd = (BigDecimal)get_Value("MultiplyRate");
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
/** Set Valid from.
Valid from including this date (first day) */
public void setValidFrom (Timestamp ValidFrom)
{
set_Value ("ValidFrom", ValidFrom);
}
/** Get Valid from.
Valid from including this date (first day) */
public Timestamp getValidFrom() 
{
return (Timestamp)get_Value("ValidFrom");
}
/** Set Valid to.
Valid to including this date (last day) */
public void setValidTo (Timestamp ValidTo)
{
set_Value ("ValidTo", ValidTo);
}
/** Get Valid to.
Valid to including this date (last day) */
public Timestamp getValidTo() 
{
return (Timestamp)get_Value("ValidTo");
}
}
