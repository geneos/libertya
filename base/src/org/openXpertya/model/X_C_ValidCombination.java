/** Modelo Generado - NO CAMBIAR MANUALMENTE - Copyright (C) 2006 FUNDESLE */
package org.openXpertya.model;
import java.util.*;
import java.sql.*;
import java.math.*;
import org.openXpertya.util.*;
/** Modelo Generado por C_ValidCombination
 *  @author Comunidad de Desarrollo openXpertya*         *Basado en Codigo Original Modificado, Revisado y Optimizado de:*         * Jorg Janke 
 *  @version  - 2008-01-03 10:26:33.593 */
public class X_C_ValidCombination extends PO
{
/** Constructor estándar */
public X_C_ValidCombination (Properties ctx, int C_ValidCombination_ID, String trxName)
{
super (ctx, C_ValidCombination_ID, trxName);
/** if (C_ValidCombination_ID == 0)
{
setAccount_ID (0);
setC_AcctSchema_ID (0);
setC_ValidCombination_ID (0);
setIsFullyQualified (false);
}
 */
}
/** Load Constructor */
public X_C_ValidCombination (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=176 */
public static final int Table_ID=176;

/** TableName=C_ValidCombination */
public static final String Table_Name="C_ValidCombination";

protected static KeyNamePair Model = new KeyNamePair(176,"C_ValidCombination");
protected static BigDecimal AccessLevel = new BigDecimal(2);

/** Load Meta Data */
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
public String toString()
{
StringBuffer sb = new StringBuffer ("X_C_ValidCombination[").append(getID()).append("]");
return sb.toString();
}
public static final int AD_ORGTRX_ID_AD_Reference_ID=130;
/** Set Trx Organization.
Performing or initiating organization */
public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
{
if (AD_OrgTrx_ID <= 0) set_ValueNoCheck ("AD_OrgTrx_ID", null);
 else 
set_ValueNoCheck ("AD_OrgTrx_ID", new Integer(AD_OrgTrx_ID));
}
/** Get Trx Organization.
Performing or initiating organization */
public int getAD_OrgTrx_ID() 
{
Integer ii = (Integer)get_Value("AD_OrgTrx_ID");
if (ii == null) return 0;
return ii.intValue();
}
public static final int ACCOUNT_ID_AD_Reference_ID=132;
/** Set Account.
Account used */
public void setAccount_ID (int Account_ID)
{
set_ValueNoCheck ("Account_ID", new Integer(Account_ID));
}
/** Get Account.
Account used */
public int getAccount_ID() 
{
Integer ii = (Integer)get_Value("Account_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Alias.
Defines an alternate method of indicating an account combination. */
public void setAlias (String Alias)
{
if (Alias != null && Alias.length() > 40)
{
log.warning("Length > 40 - truncated");
Alias = Alias.substring(0,39);
}
set_Value ("Alias", Alias);
}
/** Get Alias.
Defines an alternate method of indicating an account combination. */
public String getAlias() 
{
return (String)get_Value("Alias");
}
/** Set Accounting Schema.
Rules for accounting */
public void setC_AcctSchema_ID (int C_AcctSchema_ID)
{
set_ValueNoCheck ("C_AcctSchema_ID", new Integer(C_AcctSchema_ID));
}
/** Get Accounting Schema.
Rules for accounting */
public int getC_AcctSchema_ID() 
{
Integer ii = (Integer)get_Value("C_AcctSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
public static final int C_ACTIVITY_ID_AD_Reference_ID=142;
/** Set Activity.
Business Activity */
public void setC_Activity_ID (int C_Activity_ID)
{
if (C_Activity_ID <= 0) set_ValueNoCheck ("C_Activity_ID", null);
 else 
set_ValueNoCheck ("C_Activity_ID", new Integer(C_Activity_ID));
}
/** Get Activity.
Business Activity */
public int getC_Activity_ID() 
{
Integer ii = (Integer)get_Value("C_Activity_ID");
if (ii == null) return 0;
return ii.intValue();
}
public static final int C_BPARTNER_ID_AD_Reference_ID=138;
/** Set Business Partner .
Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_ValueNoCheck ("C_BPartner_ID", null);
 else 
set_ValueNoCheck ("C_BPartner_ID", new Integer(C_BPartner_ID));
}
/** Get Business Partner .
Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
public static final int C_CAMPAIGN_ID_AD_Reference_ID=143;
/** Set Campaign.
Marketing Campaign */
public void setC_Campaign_ID (int C_Campaign_ID)
{
if (C_Campaign_ID <= 0) set_ValueNoCheck ("C_Campaign_ID", null);
 else 
set_ValueNoCheck ("C_Campaign_ID", new Integer(C_Campaign_ID));
}
/** Get Campaign.
Marketing Campaign */
public int getC_Campaign_ID() 
{
Integer ii = (Integer)get_Value("C_Campaign_ID");
if (ii == null) return 0;
return ii.intValue();
}
public static final int C_LOCFROM_ID_AD_Reference_ID=133;
/** Set Location From.
Location that inventory was moved from */
public void setC_LocFrom_ID (int C_LocFrom_ID)
{
if (C_LocFrom_ID <= 0) set_ValueNoCheck ("C_LocFrom_ID", null);
 else 
set_ValueNoCheck ("C_LocFrom_ID", new Integer(C_LocFrom_ID));
}
/** Get Location From.
Location that inventory was moved from */
public int getC_LocFrom_ID() 
{
Integer ii = (Integer)get_Value("C_LocFrom_ID");
if (ii == null) return 0;
return ii.intValue();
}
public static final int C_LOCTO_ID_AD_Reference_ID=133;
/** Set Location To.
Location that inventory was moved to */
public void setC_LocTo_ID (int C_LocTo_ID)
{
if (C_LocTo_ID <= 0) set_ValueNoCheck ("C_LocTo_ID", null);
 else 
set_ValueNoCheck ("C_LocTo_ID", new Integer(C_LocTo_ID));
}
/** Get Location To.
Location that inventory was moved to */
public int getC_LocTo_ID() 
{
Integer ii = (Integer)get_Value("C_LocTo_ID");
if (ii == null) return 0;
return ii.intValue();
}
public static final int C_PROJECT_ID_AD_Reference_ID=141;
/** Set Project.
Financial Project */
public void setC_Project_ID (int C_Project_ID)
{
if (C_Project_ID <= 0) set_ValueNoCheck ("C_Project_ID", null);
 else 
set_ValueNoCheck ("C_Project_ID", new Integer(C_Project_ID));
}
/** Get Project.
Financial Project */
public int getC_Project_ID() 
{
Integer ii = (Integer)get_Value("C_Project_ID");
if (ii == null) return 0;
return ii.intValue();
}
public static final int C_SALESREGION_ID_AD_Reference_ID=144;
/** Set Sales Region.
Sales coverage region */
public void setC_SalesRegion_ID (int C_SalesRegion_ID)
{
if (C_SalesRegion_ID <= 0) set_ValueNoCheck ("C_SalesRegion_ID", null);
 else 
set_ValueNoCheck ("C_SalesRegion_ID", new Integer(C_SalesRegion_ID));
}
/** Get Sales Region.
Sales coverage region */
public int getC_SalesRegion_ID() 
{
Integer ii = (Integer)get_Value("C_SalesRegion_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Combination.
Valid Account Combination */
public void setC_ValidCombination_ID (int C_ValidCombination_ID)
{
set_ValueNoCheck ("C_ValidCombination_ID", new Integer(C_ValidCombination_ID));
}
/** Get Combination.
Valid Account Combination */
public int getC_ValidCombination_ID() 
{
Integer ii = (Integer)get_Value("C_ValidCombination_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Combination.
Unique combination of account elements */
public void setCombination (String Combination)
{
if (Combination != null && Combination.length() > 60)
{
log.warning("Length > 60 - truncated");
Combination = Combination.substring(0,59);
}
set_ValueNoCheck ("Combination", Combination);
}
/** Get Combination.
Unique combination of account elements */
public String getCombination() 
{
return (String)get_Value("Combination");
}
public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(getID(), getCombination());
}
/** Set Description.
Optional short description of the record */
public void setDescription (String Description)
{
if (Description != null && Description.length() > 255)
{
log.warning("Length > 255 - truncated");
Description = Description.substring(0,254);
}
set_ValueNoCheck ("Description", Description);
}
/** Get Description.
Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Set Fully Qualified.
This account is fully qualified */
public void setIsFullyQualified (boolean IsFullyQualified)
{
set_ValueNoCheck ("IsFullyQualified", new Boolean(IsFullyQualified));
}
/** Get Fully Qualified.
This account is fully qualified */
public boolean isFullyQualified() 
{
Object oo = get_Value("IsFullyQualified");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
public static final int M_PRODUCT_ID_AD_Reference_ID=162;
/** Set Product.
Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_ValueNoCheck ("M_Product_ID", null);
 else 
set_ValueNoCheck ("M_Product_ID", new Integer(M_Product_ID));
}
/** Get Product.
Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
public static final int USER1_ID_AD_Reference_ID=134;
/** Set User1.
User defined element #1 */
public void setUser1_ID (int User1_ID)
{
if (User1_ID <= 0) set_ValueNoCheck ("User1_ID", null);
 else 
set_ValueNoCheck ("User1_ID", new Integer(User1_ID));
}
/** Get User1.
User defined element #1 */
public int getUser1_ID() 
{
Integer ii = (Integer)get_Value("User1_ID");
if (ii == null) return 0;
return ii.intValue();
}
public static final int USER2_ID_AD_Reference_ID=137;
/** Set User2.
User defined element #2 */
public void setUser2_ID (int User2_ID)
{
if (User2_ID <= 0) set_ValueNoCheck ("User2_ID", null);
 else 
set_ValueNoCheck ("User2_ID", new Integer(User2_ID));
}
/** Get User2.
User defined element #2 */
public int getUser2_ID() 
{
Integer ii = (Integer)get_Value("User2_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
