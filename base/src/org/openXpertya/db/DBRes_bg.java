/*
 * @(#)DBRes_bg.java   12.oct 2007  Versión 2.2
 *
 *    El contenido de este fichero está sujeto a la  Licencia Pública openXpertya versión 1.1 (LPO)
 * en tanto en cuanto forme parte íntegra del total del producto denominado:  openXpertya, solución 
 * empresarial global , y siempre según los términos de dicha licencia LPO.
 *    Una copia  íntegra de dicha  licencia está incluida con todas  las fuentes del producto.
 *    Partes del código son copyRight (c) 2002-2007 de Ingeniería Informática Integrada S.L., otras 
 * partes son  copyRight (c)  2003-2007 de  Consultoría y  Soporte en  Redes y  Tecnologías  de  la
 * Información S.L.,  otras partes son copyRight (c) 2005-2006 de Dataware Sistemas S.L., otras son
 * copyright (c) 2005-2006 de Indeos Consultoría S.L., otras son copyright (c) 2005-2006 de Disytel
 * Servicios Digitales S.A., y otras  partes son  adaptadas, ampliadas,  traducidas, revisadas  y/o 
 * mejoradas a partir de código original de  terceros, recogidos en el ADDENDUM  A, sección 3 (A.3)
 * de dicha licencia  LPO,  y si dicho código es extraido como parte del total del producto, estará
 * sujeto a su respectiva licencia original.  
 *    Más información en http://www.openxpertya.org/ayuda/Licencia.html
 */



package org.openXpertya.db;

import java.util.ListResourceBundle;

/**
 *  Connection Resource Strings
 *
 *  @author Comunidad de Desarrollo openXpertya
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *     Plamen Niikolov
 *  @version    $Id: DBRes_bg.java,v 1.6 2005/03/11 20:29:00 jjanke Exp $
 */
public class DBRes_bg extends ListResourceBundle {

    /** Data */
    static final Object[][]	contents	= new String[][] {
        { "CConnectionDialog", "\u0412\u0440\u044a\u0437\u043a\u0430" }, { "Name", "\u0418\u043c\u0435" }, { "AppsHost", "\u0421\u044a\u0440\u0432\u0435\u0440 \u043d\u0430 \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435\u0442\u043e" }, { "AppsPort", "\u041f\u043e\u0440\u0442 \u043d\u0430 \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435\u0442\u043e" }, { "TestApps", "\u0422\u0435\u0441\u0442 \u043d\u0430 \u043f\u0440\u0438\u043b\u043e\u0436\u0435\u043d\u0438\u0435\u0442\u043e" }, { "DBHost", "\u0421\u044a\u0440\u0432\u0435\u0440 \u043d\u0430 \u0431\u0430\u0437\u0430\u0442\u0430 \u0434\u0430\u043d\u043d\u0438" }, { "DBPort", "\u041f\u043e\u0440\u0442 \u043d\u0430 \u0431\u0430\u0437\u0430\u0442\u0430 \u0434\u0430\u043d\u043d\u0438" }, { "DBName", "\u0418\u043c\u0435 \u043d\u0430 \u0431\u0430\u0437\u0430\u0442\u0430 \u0434\u0430\u043d\u043d\u0438" }, { "DBUidPwd", "\u041f\u043e\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043b / \u041f\u0430\u0440\u043e\u043b\u0430" }, { "ViaFirewall", "\u0417\u0430\u0434 \u0437\u0430\u0449\u0438\u0442\u043d\u0430 \u0441\u0442\u0435\u043d\u0430" }, { "FWHost", "\u0421\u044a\u0440\u0432\u0435\u0440 \u043d\u0430 \u0437\u0430\u0449\u0438\u0442\u043d\u0430\u0442\u0430 \u0441\u0442\u0435\u043d\u0430" }, { "FWPort", "\u041f\u043e\u0440\u0442 \u043d\u0430 \u0437\u0430\u0449\u0438\u0442\u043d\u0430\u0442\u0430 \u0441\u0442\u0435\u043d\u0430" }, { "TestConnection", "\u0422\u0435\u0441\u0442 \u043d\u0430 \u0431\u0430\u0437\u0430\u0442\u0430 \u0434\u0430\u043d\u043d\u0438" }, { "Type", "\u0412\u0438\u0434 \u043d\u0430 \u0431\u0430\u0437\u0430\u0442\u0430 \u0434\u0430\u043d\u043d\u0438" }, { "BequeathConnection", "\u041b\u043e\u043a\u0430\u043b\u043d\u0430 \u0432\u0440\u044a\u0437\u043a\u0430" }, { "Overwrite", "\u0417\u0430\u043c\u044f\u043d\u0430" }, { "RMIoverHTTP", "Tunnel Objects via HTTP" }, { "ConnectionError", "\u0413\u0440\u0435\u0448\u043a\u0430 \u043f\u0440\u0438 \u0441\u0432\u044a\u0440\u0437\u0432\u0430\u043d\u0435" }, { "ServerNotActive", "\u0421\u044a\u0440\u0432\u0435\u0440\u044a\u0442 \u043d\u0435 \u0435 \u0430\u043a\u0442\u0438\u0432\u0435\u043d" }
    };

    //~--- get methods --------------------------------------------------------

    /**
     * Get Contsnts
     * @return contents
     */
    public Object[][] getContents() {
        return contents;
    }
}	// Res



/*
 * @(#)DBRes_bg.java   02.jul 2007
 * 
 *  Fin del fichero DBRes_bg.java
 *  
 *  Versión 2.2  - Fundesle (2007)
 *
 */


//~ Formateado de acuerdo a Sistema Fundesle en 02.jul 2007
