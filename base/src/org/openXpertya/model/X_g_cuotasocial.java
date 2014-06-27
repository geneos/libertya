/** Modelo Generado - NO CAMBIAR MANUALMENTE - Disytel */
package org.openXpertya.model;
import java.util.logging.Level;
 import java.util.*;
import java.sql.*;
import java.math.*;
import org.openXpertya.util.*;
/** Modelo Generado por g_cuotasocial
 *  @author Comunidad de Desarrollo Libertya*         *Basado en Codigo Original Modificado, Revisado y Optimizado de:*         * Jorg Janke 
 *  @version  - 2014-05-15 17:30:33.377 */
public class X_g_cuotasocial extends org.openXpertya.model.PO
{
/** Constructor estándar */
public X_g_cuotasocial (Properties ctx, int g_cuotasocial_ID, String trxName)
{
super (ctx, g_cuotasocial_ID, trxName);
/** if (g_cuotasocial_ID == 0)
{
}
 */
}
/** Load Constructor */
public X_g_cuotasocial (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID */
public static final int Table_ID = M_Table.getTableID("g_cuotasocial");

/** TableName=g_cuotasocial */
public static final String Table_Name="g_cuotasocial";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"g_cuotasocial");
protected static BigDecimal AccessLevel = new BigDecimal(3);

/** Load Meta Data */
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
public String toString()
{
StringBuffer sb = new StringBuffer ("X_g_cuotasocial[").append(getID()).append("]");
return sb.toString();
}
}
