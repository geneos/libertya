/** Modelo Generado - NO CAMBIAR MANUALMENTE - Copyright (C) 2006 FUNDESLE */
package org.openXpertya.model;
import java.util.*;
import java.sql.*;
import java.math.*;
import org.openXpertya.util.*;
/** Modelo Generado por AD_UserDef_Field
 *  @author Comunidad de Desarrollo openXpertya*         *Basado en Codigo Original Modificado, Revisado y Optimizado de:*         * Jorg Janke 
 *  @version  - 2008-01-03 10:26:25.625 */
public class X_AD_UserDef_Field extends PO
{
/** Constructor estándar */
public X_AD_UserDef_Field (Properties ctx, int AD_UserDef_Field_ID, String trxName)
{
super (ctx, AD_UserDef_Field_ID, trxName);
/** if (AD_UserDef_Field_ID == 0)
{
setAD_Field_ID (0);
setAD_UserDef_Field_ID (0);
setAD_UserDef_Tab_ID (0);
setDefaultValue (null);
setIsDisplayed (false);
setIsReadOnly (false);
setIsSameLine (false);
setName (null);
setSeqNo (0);
}
 */
}
/** Load Constructor */
public X_AD_UserDef_Field (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=464 */
public static final int Table_ID=464;

/** TableName=AD_UserDef_Field */
public static final String Table_Name="AD_UserDef_Field";

protected static KeyNamePair Model = new KeyNamePair(464,"AD_UserDef_Field");
protected static BigDecimal AccessLevel = new BigDecimal(4);

/** Load Meta Data */
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
public String toString()
{
StringBuffer sb = new StringBuffer ("X_AD_UserDef_Field[").append(getID()).append("]");
return sb.toString();
}
/** Set Field.
Field on a database table */
public void setAD_Field_ID (int AD_Field_ID)
{
set_Value ("AD_Field_ID", new Integer(AD_Field_ID));
}
/** Get Field.
Field on a database table */
public int getAD_Field_ID() 
{
Integer ii = (Integer)get_Value("AD_Field_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set User defined Field */
public void setAD_UserDef_Field_ID (int AD_UserDef_Field_ID)
{
set_ValueNoCheck ("AD_UserDef_Field_ID", new Integer(AD_UserDef_Field_ID));
}
/** Get User defined Field */
public int getAD_UserDef_Field_ID() 
{
Integer ii = (Integer)get_Value("AD_UserDef_Field_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set User defined Tab */
public void setAD_UserDef_Tab_ID (int AD_UserDef_Tab_ID)
{
set_ValueNoCheck ("AD_UserDef_Tab_ID", new Integer(AD_UserDef_Tab_ID));
}
/** Get User defined Tab */
public int getAD_UserDef_Tab_ID() 
{
Integer ii = (Integer)get_Value("AD_UserDef_Tab_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Default Logic.
Default value hierarchy, separated by ;
 */
public void setDefaultValue (String DefaultValue)
{
if (DefaultValue == null) throw new IllegalArgumentException ("DefaultValue is mandatory");
if (DefaultValue.length() > 2000)
{
log.warning("Length > 2000 - truncated");
DefaultValue = DefaultValue.substring(0,1999);
}
set_Value ("DefaultValue", DefaultValue);
}
/** Get Default Logic.
Default value hierarchy, separated by ;
 */
public String getDefaultValue() 
{
return (String)get_Value("DefaultValue");
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
set_Value ("Description", Description);
}
/** Get Description.
Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Set Display Length.
Length of the display in characters */
public void setDisplayLength (int DisplayLength)
{
set_Value ("DisplayLength", new Integer(DisplayLength));
}
/** Get Display Length.
Length of the display in characters */
public int getDisplayLength() 
{
Integer ii = (Integer)get_Value("DisplayLength");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Display Logic.
If the Field is displayed, the result determines if the field is actually displayed */
public void setDisplayLogic (String DisplayLogic)
{
if (DisplayLogic != null && DisplayLogic.length() > 2000)
{
log.warning("Length > 2000 - truncated");
DisplayLogic = DisplayLogic.substring(0,1999);
}
set_Value ("DisplayLogic", DisplayLogic);
}
/** Get Display Logic.
If the Field is displayed, the result determines if the field is actually displayed */
public String getDisplayLogic() 
{
return (String)get_Value("DisplayLogic");
}
/** Set Comment/Help.
Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Set Displayed.
Determines, if this field is displayed */
public void setIsDisplayed (boolean IsDisplayed)
{
set_Value ("IsDisplayed", new Boolean(IsDisplayed));
}
/** Get Displayed.
Determines, if this field is displayed */
public boolean isDisplayed() 
{
Object oo = get_Value("IsDisplayed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Read Only.
Field is read only */
public void setIsReadOnly (boolean IsReadOnly)
{
set_Value ("IsReadOnly", new Boolean(IsReadOnly));
}
/** Get Read Only.
Field is read only */
public boolean isReadOnly() 
{
Object oo = get_Value("IsReadOnly");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Same Line.
Displayed on same line as previous field */
public void setIsSameLine (boolean IsSameLine)
{
set_Value ("IsSameLine", new Boolean(IsSameLine));
}
/** Get Same Line.
Displayed on same line as previous field */
public boolean isSameLine() 
{
Object oo = get_Value("IsSameLine");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Updateable.
Determines, if the field can be updated */
public void setIsUpdateable (boolean IsUpdateable)
{
set_Value ("IsUpdateable", new Boolean(IsUpdateable));
}
/** Get Updateable.
Determines, if the field can be updated */
public boolean isUpdateable() 
{
Object oo = get_Value("IsUpdateable");
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
Name = Name.substring(0,59);
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
/** Set Sequence.
Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_Value ("SeqNo", new Integer(SeqNo));
}
/** Get Sequence.
Method of ordering records;
 lowest number comes first */
public int getSeqNo() 
{
Integer ii = (Integer)get_Value("SeqNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Record Sort No.
Determines in what order the records are displayed */
public void setSortNo (int SortNo)
{
set_Value ("SortNo", new Integer(SortNo));
}
/** Get Record Sort No.
Determines in what order the records are displayed */
public int getSortNo() 
{
Integer ii = (Integer)get_Value("SortNo");
if (ii == null) return 0;
return ii.intValue();
}
}
