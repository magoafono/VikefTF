/* $Id: RelationsLoader.java,v 1.4 2006/05/31 12:48:39 simone Exp $ */
package ilc.vikef;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
/**
 * 
 * 
 */
public class RelationsLoader {

/**
 * <p>Does ...</p>
 * 
 * 
 * @param id 
 * @return 
 */
    public Vector load(String regExpCatalogueName, int id) {        
    	Vector groupRelationsVector = new Vector();
//    	String relationsFileName = Config.dataRepository+Config.relationsRootFileName + "_" + id + ".txt";
    	String relationsFileName = regExpCatalogueName+System.getProperty("file.separator")+Config.relationsRootFileName + "_" + id + ".txt";
    	//System.out.println("RelationsLoader.Load - relationsFileName: "+relationsFileName);
    	String line = "";
    	//accedo al file delle relazioni relativo alla regular expression di indice id
    	try {
        	BufferedReader br = new BufferedReader(new FileReader(relationsFileName));
        	//su ogni linea del file ho la specificazione di una relazione...
        	while ((line = br.readLine()) != null) {	
	        	StringTokenizer st = new StringTokenizer(line, ";");
	        	//tokenizzo la linea corrente
	        	//leggo il nome della relazione
	        	String relationName = st.nextToken();
	        	//leggo il nome del soggetto della relazione
	        	String subjectName = st.nextToken();
//	        	leggo il nome dell'oggetto della relazione
	        	String objectName = st.nextToken();
	        	        	
	        	//System.out.println("RelationsLoader.Load - relationName: "+relationName+" subjectName: "+subjectName+" objectName: "+objectName);
	        	//creo un oggetto GroupRelationSchema e lo aggiungo al vettore da ritornare
	        	GroupRelationSchema grs = new GroupRelationSchema(relationName,subjectName,objectName);
	        	groupRelationsVector.add(grs);
        	}
        }
        catch (IOException e) {
        	e.printStackTrace();
        }
    	
        return groupRelationsVector;
    } 
 }
