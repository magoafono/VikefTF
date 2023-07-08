/* $Id: Config.java,v 1.8 2006/10/04 10:19:55 simone Exp $ */
package ilc.vikef;

import java.util.HashMap;



/**
 * 
 * 
 */
public class Config {

/**
 * <p>Represents ...</p>
 * 
 */
    public static String regExpFileName = "regole.txt";;

/**
 * <p>Represents ...</p>
 * 
 */
    public static String groupsRootFileName = "groups";

/**
 * <p>Represents ...</p>
 * 
 */
    public static String relationsRootFileName = "relations";

    public static String rootDirectory = "/usr/share/tomcat/webapps/axis/WEB-INF/lib/vista/";
    //public static String rootDirectory = "/home/simone/PROGETTI/";

    public static String librariesPath= "/usr/share/tomcat/webapps/axis/WEB-INF/lib/vista/";
    //public static String librariesPath= "/home/simone/PROGETTI/workspace/VikefTF/";
    public static String dataRepository = rootDirectory + "data-vikef/";

	public static String tempOutDir = "/tmp/vista/";
	
	public static String LESSICO = "LESSICO.txt";
	public static String MACRO = "MACRO.txt";
	public static String RULE = "RULE.txt";
	public static String TERM_T2C = "TERM_IKEA.txt";
	public static String ONTOLEX = "ontolex.xml";
	public static String ONTOLOGY = "ontology.owl";

	public static String relTypeAssocFileName = "reltypeassoc.txt";
	public static HashMap relTypeAssoc = new HashMap();
	
	public static String ontologyUser = "avare";
	public static String ontologyPwd = "vikef";
	public static String ontologyID = "avare____http://www.ipsi.fraunhofer.de/__ONTO__furniture_v1_1";
	public static String ontologyFurnitureID = "avare____http://www.ipsi.fraunhofer.de/__ONTO__furniture_v1__Version__Wed Sep 27 09:25:23 BST 2006";
	public static String ontologyType = "RDF/XML-ABBREV";
	
	
	
}
