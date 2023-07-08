/* $Id: DataId.java,v 1.3 2006/05/09 08:35:01 simone Exp $ */
package ilc.vikef;


/**
 * 
 * 
 */
public class DataId {

/**
 * <p>Represents ...</p>
 * 
 */
    private static int dataId;

/**
 * <p>Represents ...</p>
 * 
 */
    public static void reset() {        
        dataId = 0;
    } 

/**
 * <p>Represents ...</p>
 * 
 * 
 * @return 
 */
    public static int getDataId() {        
        dataId++;
        return dataId;
    } 
 }
