/*
 *    El contenido de este fichero está sujeto a la  Licencia Pública openXpertya versión 1.1 (LPO)
 * en tanto en cuanto forme parte íntegra del total del producto denominado:  openXpertya, solución 
 * empresarial global , y siempre según los términos de dicha licencia LPO.
 *    Una copia  íntegra de dicha  licencia está incluida con todas  las fuentes del producto.
 *    Partes del código son CopyRight (c) 2002-2007 de Ingeniería Informática Integrada S.L., otras 
 * partes son  CopyRight (c) 2002-2007 de  Consultoría y  Soporte en  Redes y  Tecnologías  de  la
 * Información S.L.,  otras partes son  adaptadas, ampliadas,  traducidas, revisadas  y/o mejoradas
 * a partir de código original de  terceros, recogidos en el  ADDENDUM  A, sección 3 (A.3) de dicha
 * licencia  LPO,  y si dicho código es extraido como parte del total del producto, estará sujeto a
 * su respectiva licencia original.  
 *     Más información en http://www.openxpertya.org/ayuda/Licencia.html
 */



package org.openXpertya.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.openXpertya.util.Env;

/**
 * Descripción de Clase
 *
 *
 * @version    2.2, 12.10.07
 * @author     Equipo de Desarrollo de openXpertya    
 */

public class MCommissionLine extends X_C_CommissionLine {

    /**
     * Constructor de la clase ...
     *
     *
     * @param ctx
     * @param C_CommissionLine_ID
     * @param trxName
     */

    public MCommissionLine( Properties ctx,int C_CommissionLine_ID,String trxName ) {
        super( ctx,C_CommissionLine_ID,trxName );

        if( C_CommissionLine_ID == 0 ) {

            // setC_Commission_ID (0);

            setLine( 0 );    // @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM C_CommissionLine WHERE C_Commission_ID=@C_Commission_ID@
            setAmtMultiplier( Env.ZERO );
            setAmtSubtract( Env.ZERO );
            setCommissionOrders( false );
            setIsPositiveOnly( false );
            setQtyMultiplier( Env.ZERO );
            setQtySubtract( Env.ZERO );
        }
    }    // MCommissionLine

    /**
     * Constructor de la clase ...
     *
     *
     * @param ctx
     * @param rs
     * @param trxName
     */

    public MCommissionLine( Properties ctx,ResultSet rs,String trxName ) {
        super( ctx,rs,trxName );
    }    // MCommissionLine
}    // MCommissionLine



/*
 *  @(#)MCommissionLine.java   02.07.07
 * 
 *  Fin del fichero MCommissionLine.java
 *  
 *  Versión 2.2
 *
 */
