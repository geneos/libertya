/** Modelo Generado - NO CAMBIAR MANUALMENTE - Copyright (C) 2006 FUNDESLE */
package org.openXpertya.model;
import java.util.*;
import java.sql.*;
import java.math.*;
import org.openXpertya.util.*;
/** Modelo Generado por AD_Menu_Trl
 *  @author Comunidad de Desarrollo openXpertya*         *Basado en Codigo Original Modificado, Revisado y Optimizado de:*         * Jorg Janke 
 *  @version  - 2009-10-22 14:51:26.911 */
public class X_AD_Menu_Trl extends PO
{
/** Constructor estándar */
public X_AD_Menu_Trl (Properties ctx, int AD_Menu_Trl_ID, String trxName)
{
super (ctx, AD_Menu_Trl_ID, trxName);
/** if (AD_Menu_Trl_ID == 0)
{
setAD_Language (null);
setAD_Menu_ID (0);
setIsTranslated (false);
setName (null);
}
 */
}
/** Load Constructor */
public X_AD_Menu_Trl (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=120 */
public static final int Table_ID=120;

/** TableName=AD_Menu_Trl */
public static final String Table_Name="AD_Menu_Trl";

protected static KeyNamePair Model = new KeyNamePair(120,"AD_Menu_Trl");
protected static BigDecimal AccessLevel = new BigDecimal(6);

/** Load Meta Data */
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
public String toString()
{
StringBuffer sb = new StringBuffer ("X_AD_Menu_Trl[").append(getID()).append("]");
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
/** Set Component Version Identifier */
public void setAD_ComponentVersion_ID (int AD_ComponentVersion_ID)
{
if (AD_ComponentVersion_ID <= 0) set_Value ("AD_ComponentVersion_ID", null);
 else 
set_Value ("AD_ComponentVersion_ID", new Integer(AD_ComponentVersion_ID));
}
/** Get Component Version Identifier */
public int getAD_ComponentVersion_ID() 
{
Integer ii = (Integer)get_Value("AD_ComponentVersion_ID");
if (ii == null) return 0;
return ii.intValue();
}
public static final int AD_LANGUAGE_AD_Reference_ID=106;
/** Set Language.
Language for this entity */
public void setAD_Language (String AD_Language)
{
if (AD_Language == null) throw new IllegalArgumentException ("AD_Language is mandatory");
if (AD_Language.length() > 6)
{
log.warning("Length > 6 - truncated");
AD_Language = AD_Language.substring(0,6);
}
set_ValueNoCheck ("AD_Language", AD_Language);
}
/** Get Language.
Language for this entity */
public String getAD_Language() 
{
return (String)get_Value("AD_Language");
}
/** Set Menu.
Identifies a Menu */
public void setAD_Menu_ID (int AD_Menu_ID)
{
set_ValueNoCheck ("AD_Menu_ID", new Integer(AD_Menu_ID));
}
/** Get Menu.
Identifies a Menu */
public int getAD_Menu_ID() 
{
Integer ii = (Integer)get_Value("AD_Menu_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Description.
Optional short description of the record */
public void setDescription (String Description)
{
if (Description != null && Description.length() > 255)
{
log.warning("Length > 255 - truncated");
Description = Description.substring(0,255);
}
set_Value ("Description", Description);
}
/** Get Description.
Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Set Translated.
This column is translated */
public void setIsTranslated (boolean IsTranslated)
{
set_Value ("IsTranslated", new Boolean(IsTranslated));
}
/** Get Translated.
This column is translated */
public boolean isTranslated() 
{
Object oo = get_Value("IsTranslated");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
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
}
