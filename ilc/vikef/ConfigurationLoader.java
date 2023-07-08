/* $Id: ConfigurationLoader.java,v 1.11 2006/05/31 12:48:39 simone Exp $ */
package ilc.vikef;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * 
 * 
 */
public class ConfigurationLoader {

/**
 * <p>Does ...</p>
 * 
 * 
 * @param configurationPath 
 * @return 
 */
    public Vector load(String configurationPath) {        
    	Logger logger = Logger.getLogger("ilc.vikef");
    	Vector regExpMagagerVector = new Vector();
    	
    	//accesso al file contenente il nome del file contenente le regular expressions indicato nel configurationPath
    	//String configFileText = Utils.readFromFile(configurationPath);
    	EntitiesLoader el = new EntitiesLoader();
    	RelationsLoader rl = new RelationsLoader();
  
 		logger.warning("configurationPath: "+configurationPath);
		int regExpIndex = 0;   //indice per distinguere le espressioni regolari
		try {
	 		logger.warning(configurationPath+System.getProperty("file.separator")+Config.regExpFileName);
			BufferedReader in = new BufferedReader(new FileReader(configurationPath+System.getProperty("file.separator")+Config.regExpFileName));
			String regularExpression;
			while ((regularExpression = in.readLine()) != null) {
				//logger.config(regularExpression);
				regExpIndex++;  	//incremento l'indice per distinguere le espressioni regolari
				if (regularExpression.startsWith("#")) continue;
				logger.config("trovata re: "+regularExpression);
				//System.out.println("RegExp "+regExpIndex+": "+regularExpression);
		        //creo un oggetto RegexManager
				RegexManager rm = new RegexManager();
				//setto l'attributo regex del RegexManager corrente
				rm.setPattern(regularExpression);
				//setto il vettore di GroupSchema del RegexManager corrente tramite l'EntitiesLoader
				rm.setGroupSchemata(el.load(configurationPath, regExpIndex));
				//setto il vettore di GroupRelationSchema del RegexManager corrente tramite il RelationsLoader
				rm.setGroupRelationSchemata(rl.load(configurationPath, regExpIndex));
			    //aggiungo il RegexManager corrente al vector di RegexManager
				regExpMagagerVector.add(rm);
				
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	/*		//accesso al file contenente le regular expressions
		String regExpFileText = Utils.readFromFile(Config.regExpFileName);
	StringTokenizer st = new StringTokenizer(regExpFileText, ";");
		while (st.hasMoreTokens()) {
	    	
			//per ogni regular expression accedo ai relativi file di gruppi e relazioni
			//tramite i due EntitiesLoader e RelationsLoader
			String regularExpression = st.nextToken();
			regExpIndex++;  	//incremento l'indice per distinguere le espressioni regolari
			if (regularExpression.startsWith("#")) continue;
			logger.info("trovata re: "+regularExpression);
			//System.out.println("RegExp "+regExpIndex+": "+regularExpression);
	        //creo un oggetto RegexManager
			RegexManager rm = new RegexManager();
			//setto l'attributo regex del RegexManager corrente
			rm.setPattern(regularExpression);
			//setto il vettore di GroupSchema del RegexManager corrente tramite l'EntitiesLoader
			rm.setGroupSchemata(el.load(regExpIndex));
			//setto il vettore di GroupRelationSchema del RegexManager corrente tramite il RelationsLoader
			rm.setGroupRelationSchemata(rl.load(regExpIndex));
		    //aggiungo il RegexManager corrente al vector di RegexManager
			regExpMagagerVector.add(rm);
		
			//String groupFileName = groupsFileRootName+"_"+regExpIndex;
	        //System.out.println("groupFileName: "+groupFileName);
	    }*/
		
		///
		Config.relTypeAssoc = Utils.readFileToHash(Config.dataRepository+Config.relTypeAssocFileName);
    	
        return regExpMagagerVector;
    } 
    
 }
