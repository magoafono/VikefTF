package ilc.vikef;

import ilc.vikef.ontologymanager.OntologyManagerSoapServiceProviderService;
import ilc.vikef.ontologymanager.OntologyManagerSoapServiceProviderServiceLocator;
import ilc.vikef.ontologymanager.OntologyManagerSoapServiceProvider_PortType;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import javax.xml.rpc.ServiceException;

public class ClientOntology {

	/**
	 * @param args
	 */
	static Logger logger;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger = Logger.getLogger("ilc.vikef");
	/*	System.err.println(args.length);
		for (int i=0; i < args.length; i++){
			System.err.println(i + " " +args[i]);
		}*/
		if (args.length == 0){
			usage();
		}
		if("-u".equals(args[0])){
			if (args.length > 1) {
				System.out.println("uploading ontology: " + args[1]);
				if (addVikefOntology(args[1]) < 0 )	
					System.err.println("Error uploading "+ args[1]);
				else
					System.err.println("Ontology successfully uploaded ");
			}
			else{
				System.err.println("no ontology name... fail!");
			}
		}
		else
			if("-r".equals(args[0])){
				//if (args.length > 1) {
					//System.out.println("retrieve ontology : " + args[1]);
					if ( retrieveVikefOntology(/*args[1],*/ "ontology.xml") < 0 )
						System.err.println("Error retrieving "+ args[1]);
					else
						System.err.println("Ontology successfully retrieved : " + "ontology.xml");
				/*}
				else{
					System.err.println("no ontology id... fail!");
				}*/
			}
			else
				usage();
	}

	public static int retrieveVikefOntology(/*String ontologyID,*/ String ontologyName){
		logger.info(ontologyName);

		try {

			OntologyManagerSoapServiceProviderService ontoManagerSL = new OntologyManagerSoapServiceProviderServiceLocator ();
			OntologyManagerSoapServiceProvider_PortType ontoManager = ontoManagerSL.getOntologyManagerSoapServiceProvider();
			/*String owlFile = ontoManager.getOntologyVersion(Config.ontologyUser,
						Config.ontologyPwd,
						Config.ontologyFurnitureID,
						Config.ontologyType);
			 */
			String [] ontologies = ontoManager.listOntologies(Config.ontologyUser, Config.ontologyPwd);
			for (int i = 0; i < ontologies.length; i++) {
				logger.warning("Ontology ["+i+"] : " + ontologies[i]);
			}
			/*String owlFile = ontoManager.getOntology(Config.ontologyUser,
					Config.ontologyPwd,
					Config.ontologyID,
					Config.ontologyType);
			
			if(owlFile == null){
				logger.severe("Error during retrieve ontology " + Config.ontologyID );
				return -1;
			}
			Utils.writeFile(owlFile, ontologyName); */
			//logger.info(owlFile);
			return 0;
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}

	public static int addVikefOntology(String ontologyName){
		logger.info(ontologyName);
		String ontology = Utils.readFromFile(ontologyName);
		try {

			OntologyManagerSoapServiceProviderService ontoManagerSL = new OntologyManagerSoapServiceProviderServiceLocator();
			OntologyManagerSoapServiceProvider_PortType ontoManager = ontoManagerSL.getOntologyManagerSoapServiceProvider();
			if (ontoManager.isLockedOntology(Config.ontologyUser, Config.ontologyPwd, Config.ontologyID)){
				logger.warning("Ontology "+  Config.ontologyID +" is locked by "+
						ontoManager.getOntologyLock(Config.ontologyUser, Config.ontologyPwd, Config.ontologyID) );
				if (ontoManager.unlockOntology(Config.ontologyUser, Config.ontologyPwd, Config.ontologyID)){
					logger.warning("Now is ulocked!");
				}
				
				return -1;
			}else{
				ontoManager.lockOntology(Config.ontologyUser, Config.ontologyPwd, Config.ontologyID);
				System.err.println("No lock on ontology "+  Config.ontologyID );
				String result = ontoManager.addOntology(Config.ontologyUser, Config.ontologyPwd, Config.ontologyID, ontology);
				logger.info(result);
				ontoManager.unlockOntology(Config.ontologyUser, Config.ontologyPwd, Config.ontologyID);
			}
			return 0;
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}

	public static void usage () {
		System.err.println("Usage: java -cp .:$AXISCLASSPATH ilc.vikef.ClientOntology (-r ontology_id | -u ontology_file_name)");
		System.exit(-1);

	}
	
}
