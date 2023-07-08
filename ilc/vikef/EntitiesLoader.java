/* $Id: EntitiesLoader.java,v 1.6 2006/05/31 12:48:39 simone Exp $ */
package ilc.vikef;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Logger;


/**
 * 
 * 
 */
public class EntitiesLoader {

/**
 * <p>Does ...</p>
 * 
 * 
 * @param id 
 * @return 
 */
    public Vector load(String regExpCatalogueName, int id) {     
    	Logger logger = Logger.getLogger("ilc.vikef");    
    	Vector groupsVector = new Vector();
//    	String groupsFileName = Config.dataRepository+regExpCatalogueName+Config.groupsRootFileName + "_" + id+ ".txt";
    	String groupsFileName = regExpCatalogueName+System.getProperty("file.separator")+Config.groupsRootFileName + "_" + id+ ".txt";
        //System.out.println("EntitiesLoader.Load - groupsFileName: "+groupsFileName);
    	String line = "";
        //accedo al file dei gruppi relativo alla regular expression di indice id
        try {
        	BufferedReader br = new BufferedReader(new FileReader(groupsFileName));
        	//su ogni linea del file ho la specificazione di un gruppo...
        	while ((line = br.readLine()) != null) {	
	        	StringTokenizer st = new StringTokenizer(line, ";");
	        	//tokenizzo la linea corrente
	        	//leggo il nome del gruppo
	        	String groupName = st.nextToken();
	        	//leggo l'indice del gruppo e lo converto in intero
	        	String groupIndex = st.nextToken();
	        	Integer index = new Integer(groupIndex);
	        	//leggo l'isNlpProcessable del gruppo e lo converto in booleano
	        	String isNlpProcessable = st.nextToken();
	        	//logger.info("isNlpProcessable: "+isNlpProcessable);
	           	boolean isNlp;
	        	if (isNlpProcessable.equals("false")) 
	        		isNlp = false;
	        	else
	        		isNlp = true;
	        	
	        	
	        	//System.out.println("EntitiesLoader.Load - groupName: "+groupName+" groupIndex: "+groupIndex+" isNlpProcessable: "+isNlpProcessable);
	        	//creo un oggetto GroupSchema e lo aggiungo al vettore da ritornare
	        	GroupSchema gr = new GroupSchema(groupName,index.intValue(),isNlp);
	        	groupsVector.add(gr);
        	}
        }
        catch (IOException e) {
        	e.printStackTrace();
        }
    	   	
    	
    	
        return groupsVector;
    } 
 }
