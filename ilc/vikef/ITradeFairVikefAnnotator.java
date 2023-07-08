/* $Id: ITradeFairVikefAnnotator.java,v 1.3 2006/08/24 09:37:37 simone Exp $ */
package ilc.vikef;

/**
 * 
 * 
 */
public interface ITradeFairVikefAnnotator {
/**
 * <p>Does ...</p>
 * 
 * 
 * @param inputText 
 * @return 
 */
	/* Invocazione WS x il catalogo IKEA */
    public String run(String inputText);

    public String runByURL(String aURL);

    public String runZ(String inputText);

    public String runByURLZ(String aURL);

    public String runSM(String inputText);

    public String runByURLSM(String aURL);
    
    
    
}


