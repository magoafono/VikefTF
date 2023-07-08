/* $Id: TradeFairVikefAnnotator.java,v 1.35 2006/11/08 08:54:41 simone Exp $ */
package ilc.vikef;

import ilc.nlp.wrapper.NLPTools;
import ilc.nlp.wrapper.T2CMapper;
import ilc.nlp.wrapper.Vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Vector;
import java.util.logging.*;

import javax.xml.rpc.ServiceException;
import javax.xml.stream.XMLStreamException;

import de.fraunhofer.ipsi.ontologymanagerclient.OntologyManagerSoapServiceProvider;
import de.fraunhofer.ipsi.ontologymanagerclient.OntologyManagerSoapServiceProviderService;
import de.fraunhofer.ipsi.ontologymanagerclient.OntologyManagerSoapServiceProviderServiceLocator;

/**
 * 
 * 
 */
public class TradeFairVikefAnnotator implements ITradeFairVikefAnnotator {
	
	/**
	 * <p>Represents ...</p>
	 * 
	 */
	private String configurationPath;
	/**
	 * 
	 * 
	 * 
	 * @poseidon-type RegexManager
	 */
	public java.util.Vector regexManager = new java.util.Vector();
	
	static Logger logger;
	static Vector regExpMagagerVector;
	
	static {
		init();
	}
	
	/**
	 * <p>Does ...</p>
	 * 
	 */
	public static void init() {        
		//Config.dataRepository = Config.rootDirectory+Config.dataRepository;
		System.err.println("java.util.logging.config.file: "+Config.rootDirectory+"log.properties");
		String old = System.setProperty("java.util.logging.config.file",Config.rootDirectory+"log.properties");
		//System.err.println(old);
		try {
			LogManager.getLogManager().readConfiguration();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger = Logger.getLogger("ilc.vikef");
		// Create a console handler that uses the custom formatter
		//ho messo questo perche' non mi funziona il caricamente dinamico
		//delle properties (ovvere non mi prende la classe custom di log e non logga piu' nulla)!!
		ConsoleHandler ch = new ConsoleHandler();
		ch.setFormatter(new MyLogFormatter());
		logger.addHandler(ch);
		FileHandler fh = null;
		reinitLog(fh);
		/*
		try {
			FileHandler fh;
			fh = new FileHandler();
			fh.setFormatter(new MyLogFormatter());
			logger.addHandler(fh);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger = Logger.getLogger("ilc.vikef");
*/
		//logger.setLevel(Level.INFO);
		//System.err.println(logger.getLevel() +" " +logger.getName());

		//System.err.println("Config.dataRepository: "+Config.dataRepository);
		//System.err.println("Prima: "+System.getProperty("java.library.path"));
		String oldPath = System.getProperty("java.library.path");
		System.setProperty("java.library.path",oldPath +":" +Config.rootDirectory);
		//System.err.println("Dopo: "+System.getProperty("java.library.path"));
		//System.err.println("Loading configuration file... "+Config.rootDirectory+"default.xml");
		logger.config("Loading configuration file... "+Config.rootDirectory+"default.xml");
		ParserXmlConfig parser = new ParserXmlConfig();
		//parser.parseXMLFile(Config.rootDirectory+"default.xml");
		logger.info("Done.");
		//Utils.printSystemProperties();
		Utils.printConfiguration();

		//CARICO LE REGEXP
//		regExpLoader("config.txt");
		/*if (retrieveVikefOntology(Config.dataRepository + Config.ONTOLOGY) < 0){
			logger.warning("Errore nel retrieve dell'ontologia di Vikef");
			//carico l'ontologia di default
		}*/
		
		//carico ANITA
		logger.config("Loading ANITA tools ... "+Config.dataRepository+"; "+Config.librariesPath);
		NLPTools.init(Config.librariesPath);
		logger.config("NLPTools.path: "+NLPTools.getPathLib());
		NLPTools.startTools(Config.dataRepository);
		logger.info("Done.");

		
	//Inizializzazione del t2cmapper (ex-Daniela+)
		logger.info("Loading T2cMapper tool ... "+Config.dataRepository + "TERM.txt");
		T2CMapper.init(Config.librariesPath);
		//System.err.println("init() 3: T2CMapper: "+T2CMapper.getPathLib());
		T2CMapper.startTools(Config.dataRepository + "TERM.txt");
		logger.info("Done.");

		//System.err.println("init() 4");
		//Inizializzo Vista
		logger.info("Loading Vista tool ... "+Config.dataRepository + Config.LESSICO);
		Vista.init(Config.librariesPath);
		int vistaReturn = Vista.start(Config.dataRepository + Config.LESSICO,
				Config.dataRepository + Config.MACRO,
				Config.dataRepository + Config.RULE,
				Config.dataRepository + Config.ONTOLEX,
				Config.dataRepository + Config.ONTOLOGY);
		logger.info("Done. " + vistaReturn);
		//System.err.println("init() fine, vistaReturn: "+vistaReturn );

	} 
	
	
	public static void reinitLog(FileHandler fh){
		try {
			if(null != fh){
				logger.removeHandler(fh);
			}
			fh = new FileHandler();
			fh.setFormatter(new MyLogFormatter());
			logger.addHandler(fh);
			logger = Logger.getLogger("ilc.vikef");

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void regExpLoader ( String pathRegExps){
		//Carico le regular expression e gruppi associati.
		ConfigurationLoader cl = new ConfigurationLoader();
		regExpMagagerVector = new Vector();
		//carico il vettore di RegexManager invocando il ConfigurationLoader
		logger.warning("Path: "+Config.dataRepository+pathRegExps);
//		regExpMagagerVector = cl.load(Config.dataRepository+configFileName);
		regExpMagagerVector = cl.load(Config.dataRepository+pathRegExps);
	}
	/**
	 * <p>Does ...</p>
	 * 
	 * 
	 * @param inputText Testo di input in XML (Harmonized text for XRCE)
	 * @return 
	 */
	public String localRun(String inputText, boolean paragraphBased) {        
		Vector productVector = null; //= new Vector();
		XMLParser xmlParser = new XMLParser();
		String preparsedString = null;
		//xmlParser.init(new String (_text.getBytes(),"ISO-8859-1"));
		logger.info("paragraphBased ? " + paragraphBased);
		try {
			Utils.writeFile(inputText,"InputText.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//xmlParser.init(new String (_text.getBytes(),"UTF-8"));
		try {
			preparsedString = inputText.replaceAll("(.)-\n\\s*", "$1"); //rimuove il problema dell'hyphen
			preparsedString = preparsedString.replaceAll("fi (.)", "fi$1"); //elimina il problema del fi, come fi sso
			xmlParser.init(preparsedString);
			logger.fine(preparsedString);
			//xmlParser.init(inputText);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (XMLStreamException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//resetto il DataId
		DataId.reset();
		Paragraph paragraph = null;
		String paragraphText;
		Product product;
		
		int j = 1;
		/*BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(Config.rootDirectory+"output.xml"), "UTF8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		productVector = new Vector();
		while ((paragraph = xmlParser.next(paragraphBased)) != null) {
			paragraphText = (paragraph.getCharacters()).replace('\r',' ');
			paragraphText = paragraphText.replace('\n',' ');
			paragraphText = paragraphText.replaceAll("( )+"," ");
			//logger.info("paragraphText: "+paragraphText);
			//String paragraphText = Utils.readFromFile("testo.txt");
			int i = 0;
			
			//logger.info("regExpMagagerVector.size(): "+regExpMagagerVector.size());
			while (i < regExpMagagerVector.size()) {
				RegexManager rm = (RegexManager) regExpMagagerVector.elementAt(i);
				//se il pattern matcha è necessario togliere il testo matchato da paragraphText e ripartire 
				if ((product = rm.evaluate(paragraphText)) != null) {
					//aggiungo il prodotto a productVector
					//aggiungo il paragraphId al prodotto
					product.setParagraphId(paragraph.getParagraphId());
					//aggiungo l'entryLevelUrl al prodotto
					product.setEntryLevelURL(paragraph.getEntryLevelURL());
					//aggiungo gli EntryLevelIds al prodotto
					product.setEntryLevelIds(paragraph.getEntryLevelIds());
					//aggiungo la category
					product.setCategory(paragraph.getCategory());
					/*StringBuffer sb = new StringBuffer();
					product.printXML(sb);
					System.err.println("xxx: "+ sb.toString());*/
					//aggiungo il prodotto al vettore di prodotti
					productVector.add(product);
					//System.err.println("nel while: productVector.size(): "+productVector.size());
					String text = product.getDescription();
					//logger.info("tolgo text: "+text);
					//logger.info("paragraphText prima della rimozione: "+paragraphText);
					paragraphText = paragraphText.replace(text, "");
					//logger.info("paragraphText dopo la rimozione:     "+paragraphText);
					i = 0;
				}
				//se il pattern non matcha passo alla regular expression successiva
				else {
					i++;
				}
				
			}
			logger.fine("non ci sono più regular expression da provare. Fine.");
			logger.fine("segue stampa prodotti e relazioni...");
			
			/*  	}
			 catch (IOException ie) {
			 
			 }*/
		}
		/*try {
			//stampa l'XML di uscita
		//stampa dell'header
			Utils.printXMLHeader(bw);
			System.err.println("productVector.size(): "+productVector.size());
			for (int i=0;i < productVector.size(); i++) {
				product = (Product) productVector.elementAt(i);
				//System.err.println(i);
				product.printXML(bw);
			}
		//stampa del footer
			Utils.printXMLFooter(bw);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		logger.info("In fondo, preparo l'output");

		StringBuffer sb = new StringBuffer();
		//stampa l'XML di uscita
//		stampa dell'header
		Utils.printXMLHeader(sb);
		System.err.println("productVector.size(): "+productVector.size());
		logger.info("productVector.size() " + productVector.size());
		for (int i=0;i < productVector.size(); i++) {
			product = (Product) productVector.elementAt(i);
			//STAMPE DI OUTPUT
			product.printSmartXML(sb);
//			product.printXML(sb);
		}
//		stampa del footer
		logger.info("stampa del footer");
		Utils.printXMLFooter(sb);
		try {
			Utils.writeFile(sb.toString(),"outfile.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	} 
	
	public String localRunByURL(String url, boolean paragraphBased) {
		// TODO Auto-generated method stub
		String result = null;
		try {
			// Create a URL for the desired page
			URL aUrl = new URL(url);
			// Read all the text returned by the server
			BufferedReader in = new BufferedReader(new InputStreamReader(aUrl.openStream()));
			String str;
			StringBuffer xmlText = new StringBuffer();
			while ((str = in.readLine()) != null) {
				xmlText.append(str);
				xmlText.append("\n");
			}
			in.close();
		/*	logger.info(url);
			url = url.replaceAll("&","&amp;");
			System.err.println(url);
			MySaxParserInstance.setUrl(url);*/
			result = localRun(xmlText.toString(), paragraphBased);
		} catch (MalformedURLException e) {
			System.err.println("URL Malformata");
		} catch (IOException e) {
			System.err.println("IOException");
		}
		return result;
}

	
	public static int retrieveVikefOntology(String ontologyName){
		logger.info(ontologyName);

		try {
			logger.warning("Retrieve ontology ID " + Config.ontologyID) ;

			OntologyManagerSoapServiceProviderService ontoManagerSL = new OntologyManagerSoapServiceProviderServiceLocator ();
			OntologyManagerSoapServiceProvider ontoManager = ontoManagerSL.getOntologyManagerSoapServiceProvider();
			/*String owlFile = ontoManager.getOntologyVersion(Config.ontologyUser,
						Config.ontologyPwd,
						Config.ontologyID,
						Config.ontologyType);*/
			
			String owlFile = ontoManager.getOntology(Config.ontologyUser,
					Config.ontologyPwd,
					Config.ontologyID,
					Config.ontologyType);
			//System.err.println("owlFile: "+ owlFile);

			if(owlFile == null){
				logger.severe("Error during retrieve ontology " + Config.ontologyID );
				return -1;
			}

			Utils.writeFile(owlFile, ontologyName);
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
	
	public static void main(String[] args)  {
		TradeFairVikefAnnotator tfva = new TradeFairVikefAnnotator();
		try {
			URL aUrl = new URL(args[0]);
			tfva.runByURL(args[0]);
			return;
		} catch (MalformedURLException e) {
			System.err.println(args[0]+" non e' una URL...");
		}
		
		//String _text = Utils.readFromFile("pdf-segment-chain1.xml");
		String _text = Utils.readFromFile(args[0]);
		//IKEA
//		tfva.run(_text);
		//SEMANA VERDE
		//tfva.runSM(_text);
		//ZANOTTA
		tfva.runZ(_text);
		//Carico il logger personalizzato
		
	}

	//IKEA
	public String run(String inputText) {
//		regExpLoader("regole_IKEA"+System.getProperty("file.separator")+"config.txt");
		regExpLoader("regole_IKEA");
		return	localRun(inputText, true);
	}
//	IKEA
	public String runByURL(String aURL) {
		regExpLoader("regole_IKEA");
		return localRunByURL(aURL, true);
	}


//SEMANA VERDE
	public String runByURLSM(String aURL) {
		regExpLoader("regole_SEMANA_VERDE");
		return localRunByURL(aURL, true);
	}
	
//	SEMANA VERDE
	public String runSM(String inputText) {
		
		regExpLoader("regole_SEMANA_VERDE");
		return localRun(inputText, true);
		// TODO Auto-generated method stub
	}
	
//	ZANOTTA
	public String runZ(String inputText) {
		regExpLoader("regole_ZANOTTA");
		return localRun(inputText, false);
	}
//	ZANOTTA
	public String runByURLZ(String aURL) {
		regExpLoader("regole_ZANOTTA");
		return localRunByURL(aURL, false);
	}


	
}
