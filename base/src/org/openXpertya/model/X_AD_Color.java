/** Modelo Generado - NO CAMBIAR MANUALMENTE - Copyright (C) 2006 FUNDESLE */
package org.openXpertya.model;
import java.util.*;
import java.sql.*;
import java.math.*;
import org.openXpertya.util.*;
/** Modelo Generado por AD_Color
 *  @author Comunidad de Desarrollo openXpertya*         *Basado en Codigo Original Modificado, Revisado y Optimizado de:*         * Jorg Janke 
 *  @version  - 2008-01-03 10:26:22.578 */
public class X_AD_Color extends PO
{
/** Constructor estándar */
public X_AD_Color (Properties ctx, int AD_Color_ID, String trxName)
{
super (ctx, AD_Color_ID, trxName);
/** if (AD_Color_ID == 0)
{
setAD_Color_ID (0);
setAlpha (0);
setBlue (0);
setColorType (null);
setGreen (0);
setImageAlpha (Env.ZERO);
setIsDefault (false);
setName (null);
setRed (0);
}
 */
}
/** Load Constructor */
public X_AD_Color (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=457 */
public static final int Table_ID=457;

/** TableName=AD_Color */
public static final String Table_Name="AD_Color";

protected static KeyNamePair Model = new KeyNamePair(457,"AD_Color");
protected static BigDecimal AccessLevel = new BigDecimal(4);

/** Load Meta Data */
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
public String toString()
{
StringBuffer sb = new StringBuffer ("X_AD_Color[").append(getID()).append("]");
return sb.toString();
}
/** Set System Color.
Color for backgrounds or indicators */
public void setAD_Color_ID (int AD_Color_ID)
{
set_ValueNoCheck ("AD_Color_ID", new Integer(AD_Color_ID));
}
/** Get System Color.
Color for backgrounds or indicators */
public int getAD_Color_ID() 
{
Integer ii = (Integer)get_Value("AD_Color_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Image.
System Image or Icon */
public void setAD_Image_ID (int AD_Image_ID)
{
if (AD_Image_ID <= 0) set_Value ("AD_Image_ID", null);
 else 
set_Value ("AD_Image_ID", new Integer(AD_Image_ID));
}
/** Get Image.
System Image or Icon */
public int getAD_Image_ID() 
{
Integer ii = (Integer)get_Value("AD_Image_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Alpha.
Color Alpha value 0-255 */
public void setAlpha (int Alpha)
{
set_Value ("Alpha", new Integer(Alpha));
}
/** Get Alpha.
Color Alpha value 0-255 */
public int getAlpha() 
{
Integer ii = (Integer)get_Value("Alpha");
if (ii == null) return 0;
return ii.intValue();
}
/** Set 2nd Alpha.
Alpha value for second color */
public void setAlpha_1 (int Alpha_1)
{
set_Value ("Alpha_1", new Integer(Alpha_1));
}
/** Get 2nd Alpha.
Alpha value for second color */
public int getAlpha_1() 
{
Integer ii = (Integer)get_Value("Alpha_1");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Blue.
Color RGB blue value */
public void setBlue (int Blue)
{
set_Value ("Blue", new Integer(Blue));
}
/** Get Blue.
Color RGB blue value */
public int getBlue() 
{
Integer ii = (Integer)get_Value("Blue");
if (ii == null) return 0;
return ii.intValue();
}
/** Set 2nd Blue.
RGB value for second color */
public void setBlue_1 (int Blue_1)
{
set_Value ("Blue_1", new Integer(Blue_1));
}
/** Get 2nd Blue.
RGB value for second color */
public int getBlue_1() 
{
Integer ii = (Integer)get_Value("Blue_1");
if (ii == null) return 0;
return ii.intValue();
}
public static final int COLORTYPE_AD_Reference_ID=243;
/** Normal (Flat) = F */
public static final String COLORTYPE_NormalFlat = "F";
/** Gradient = G */
public static final String COLORTYPE_Gradient = "G";
/** Line = L */
public static final String COLORTYPE_Line = "L";
/** Texture (Picture) = T */
public static final String COLORTYPE_TexturePicture = "T";
/** Set Color Type.
Color presentation for this color */
public void setColorType (Object ColorType)
{
if (ColorType.equals("F") || ColorType.equals("G") || ColorType.equals("L") || ColorType.equals("T"));
 else throw new IllegalArgumentException ("ColorType Invalid value - Reference_ID=243 - F - G - L - T");
if (ColorType == null) throw new IllegalArgumentException ("ColorType is mandatory");
set_Value ("ColorType", ColorType);
}
/** Get Color Type.
Color presentation for this color */
public Object getColorType() 
{
return get_Value("ColorType");
}
/** Set Green.
RGB value  */
public void setGreen (int Green)
{
set_Value ("Green", new Integer(Green));
}
/** Get Green.
RGB value  */
public int getGreen() 
{
Integer ii = (Integer)get_Value("Green");
if (ii == null) return 0;
return ii.intValue();
}
/** Set 2nd Green.
RGB value for second color */
public void setGreen_1 (int Green_1)
{
set_Value ("Green_1", new Integer(Green_1));
}
/** Get 2nd Green.
RGB value for second color */
public int getGreen_1() 
{
Integer ii = (Integer)get_Value("Green_1");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Image Alpha .
Image Texture Composite Alpha */
public void setImageAlpha (BigDecimal ImageAlpha)
{
if (ImageAlpha == null) throw new IllegalArgumentException ("ImageAlpha is mandatory");
set_Value ("ImageAlpha", ImageAlpha);
}
/** Get Image Alpha .
Image Texture Composite Alpha */
public BigDecimal getImageAlpha() 
{
BigDecimal bd = (BigDecimal)get_Value("ImageAlpha");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Default.
Default value */
public void setIsDefault (boolean IsDefault)
{
set_Value ("IsDefault", new Boolean(IsDefault));
}
/** Get Default.
Default value */
public boolean isDefault() 
{
Object oo = get_Value("IsDefault");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Line Distance.
Distance between lines */
public void setLineDistance (int LineDistance)
{
set_Value ("LineDistance", new Integer(LineDistance));
}
/** Get Line Distance.
Distance between lines */
public int getLineDistance() 
{
Integer ii = (Integer)get_Value("LineDistance");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Line Width.
Width of the lines */
public void setLineWidth (int LineWidth)
{
set_Value ("LineWidth", new Integer(LineWidth));
}
/** Get Line Width.
Width of the lines */
public int getLineWidth() 
{
Integer ii = (Integer)get_Value("LineWidth");
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
/** Set Red.
RGB value */
public void setRed (int Red)
{
set_Value ("Red", new Integer(Red));
}
/** Get Red.
RGB value */
public int getRed() 
{
Integer ii = (Integer)get_Value("Red");
if (ii == null) return 0;
return ii.intValue();
}
/** Set 2nd Red.
RGB value for second color */
public void setRed_1 (int Red_1)
{
set_Value ("Red_1", new Integer(Red_1));
}
/** Get 2nd Red.
RGB value for second color */
public int getRed_1() 
{
Integer ii = (Integer)get_Value("Red_1");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Repeat Distance.
Distance in points to repeat gradient color - or zero */
public void setRepeatDistance (int RepeatDistance)
{
set_Value ("RepeatDistance", new Integer(RepeatDistance));
}
/** Get Repeat Distance.
Distance in points to repeat gradient color - or zero */
public int getRepeatDistance() 
{
Integer ii = (Integer)get_Value("RepeatDistance");
if (ii == null) return 0;
return ii.intValue();
}
public static final int STARTPOINT_AD_Reference_ID=248;
/** North = 1 */
public static final String STARTPOINT_North = "1";
/** North East = 2 */
public static final String STARTPOINT_NorthEast = "2";
/** East = 3 */
public static final String STARTPOINT_East = "3";
/** South East = 4 */
public static final String STARTPOINT_SouthEast = "4";
/** South = 5 */
public static final String STARTPOINT_South = "5";
/** South West = 6 */
public static final String STARTPOINT_SouthWest = "6";
/** West = 7 */
public static final String STARTPOINT_West = "7";
/** North West = 8 */
public static final String STARTPOINT_NorthWest = "8";
/** Set Start Point.
Start point of the gradient colors */
public void setStartPoint (String StartPoint)
{
if (StartPoint == null || StartPoint.equals("1") || StartPoint.equals("2") || StartPoint.equals("3") || StartPoint.equals("4") || StartPoint.equals("5") || StartPoint.equals("6") || StartPoint.equals("7") || StartPoint.equals("8"));
 else throw new IllegalArgumentException ("StartPoint Invalid value - Reference_ID=248 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8");
if (StartPoint != null && StartPoint.length() > 22)
{
log.warning("Length > 22 - truncated");
StartPoint = StartPoint.substring(0,21);
}
set_Value ("StartPoint", StartPoint);
}
/** Get Start Point.
Start point of the gradient colors */
public String getStartPoint() 
{
return (String)get_Value("StartPoint");
}
}
