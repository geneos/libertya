/** Modelo Generado - NO CAMBIAR MANUALMENTE - Disytel */
package org.openXpertya.model;
import java.util.logging.Level;
 import java.util.*;
import java.sql.*;
import java.math.*;
import org.openXpertya.util.*;
/** Modelo Generado por M_Amortization_Method
 *  @author Comunidad de Desarrollo Libertya*         *Basado en Codigo Original Modificado, Revisado y Optimizado de:*         * Jorg Janke 
 *  @version  - 2011-12-14 18:46:01.19 */
public class X_M_Amortization_Method extends org.openXpertya.model.PO
{
/** Constructor estándar */
public X_M_Amortization_Method (Properties ctx, int M_Amortization_Method_ID, String trxName)
{
super (ctx, M_Amortization_Method_ID, trxName);
/** if (M_Amortization_Method_ID == 0)
{
setAmortizationAppPeriod (null);
setM_Amortization_Method_ID (0);
setM_Amortization_Processor_ID (0);
setName (null);
}
 */
}
/** Load Constructor */
public X_M_Amortization_Method (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID */
public static final int Table_ID = M_Table.getTableID("M_Amortization_Method");

/** TableName=M_Amortization_Method */
public static final String Table_Name="M_Amortization_Method";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_Amortization_Method");
protected static BigDecimal AccessLevel = new BigDecimal(3);

/** Load Meta Data */
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
public String toString()
{
StringBuffer sb = new StringBuffer ("X_M_Amortization_Method[").append(getID()).append("]");
return sb.toString();
}
/** Set AD_ComponentObjectUID */
public void setAD_ComponentObjectUID (String AD_ComponentObjectUID)
{
if (AD_ComponentObjectUID != null && AD_ComponentObjectUID.length() > 100)
{
log.warning("Length > 100 - truncated");
AD_ComponentObjectUID = AD_ComponentObjectUID.substring(0,100);
}
set_Value ("AD_ComponentObjectUID", AD_ComponentObjectUID);
}
/** Get AD_ComponentObjectUID */
public String getAD_ComponentObjectUID() 
{
return (String)get_Value("AD_ComponentObjectUID");
}
public static final int AMORTIZATIONAPPPERIOD_AD_Reference_ID = MReference.getReferenceID("Amortization Application Period");
/** Annual = A */
public static final String AMORTIZATIONAPPPERIOD_Annual = "A";
/** Monthly = M */
public static final String AMORTIZATIONAPPPERIOD_Monthly = "M";
/** Set Amortization Application Period */
public void setAmortizationAppPeriod (String AmortizationAppPeriod)
{
if (AmortizationAppPeriod.equals("A") || AmortizationAppPeriod.equals("M"));
 else throw new IllegalArgumentException ("AmortizationAppPeriod Invalid value - Reference = AMORTIZATIONAPPPERIOD_AD_Reference_ID - A - M");
if (AmortizationAppPeriod == null) throw new IllegalArgumentException ("AmortizationAppPeriod is mandatory");
if (AmortizationAppPeriod.length() > 1)
{
log.warning("Length > 1 - truncated");
AmortizationAppPeriod = AmortizationAppPeriod.substring(0,1);
}
set_Value ("AmortizationAppPeriod", AmortizationAppPeriod);
}
/** Get Amortization Application Period */
public String getAmortizationAppPeriod() 
{
return (String)get_Value("AmortizationAppPeriod");
}
/** Set Description.
Optional short description of the record */
public void setDescription (String Description)
{
if (Description != null && Description.length() > 60)
{
log.warning("Length > 60 - truncated");
Description = Description.substring(0,60);
}
set_Value ("Description", Description);
}
/** Get Description.
Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Set Amortization Method */
public void setM_Amortization_Method_ID (int M_Amortization_Method_ID)
{
set_ValueNoCheck ("M_Amortization_Method_ID", new Integer(M_Amortization_Method_ID));
}
/** Get Amortization Method */
public int getM_Amortization_Method_ID() 
{
Integer ii = (Integer)get_Value("M_Amortization_Method_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Amortization Processor */
public void setM_Amortization_Processor_ID (int M_Amortization_Processor_ID)
{
set_Value ("M_Amortization_Processor_ID", new Integer(M_Amortization_Processor_ID));
}
/** Get Amortization Processor */
public int getM_Amortization_Processor_ID() 
{
Integer ii = (Integer)get_Value("M_Amortization_Processor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Name.
Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory");
if (Name.length() > 60)
{
log.warning("Length > 60 - truncated");
Name = Name.substring(0,60);
}
set_Value ("Name", Name);
}
/** Get Name.
Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(getID(), getName());
}
}
