/* $Id: IXqueryResult.java,v 1.1 2006/10/25 08:04:30 simone Exp $ */

package ilc.vikef;

public interface IXqueryResult {

	
    public String evaluateXQuery(String p);
    
    public String evaluateXQuerySM(String p);
    
    public String evaluateXQueryZ(String p);

	
}
