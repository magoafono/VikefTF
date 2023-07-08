/*
 * $Id: ParserXmlConfig.java,v 1.1 2006/05/18 09:36:37 simone Exp $
 */
package ilc.vikef;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserXmlConfig{
	
	Logger logger = Logger.getLogger("ilc.vikef");  
	/**
	 * @param args
	 */
	static class MyHandler extends DefaultHandler {
		
		public void startElement(String uri, String localName, 
				String qName, Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			//super.startElement(uri, localName, qName, attributes);
			
			//System.out.println("uri : "+uri);
			//System.out.println("localName : "+localName);
			//System.out.println("qName : "+qName);
			if(qName.equals(Constants.INFO)){
				parseInfoTag(attributes);
			}				
		}
		/* (non-Javadoc)
		 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
		 */
		public void characters(char[] ch, int start, int length) throws SAXException {
			// TODO Auto-generated method stub
			//String s = new String(ch, start, length);
			//System.out.println("chars : "+s);
		}
		
	}
	public void parseXMLFile(String fileName){
		try {	
			// Create a builder factory
			SAXParserFactory factory = SAXParserFactory.newInstance();
			//factory.setValidating(true);
			
			MyHandler handler = new MyHandler();
			// Create the builder and parse the file
			factory.newSAXParser().parse(new File(fileName), handler);
		} catch (SAXException e) {
			logger.log(Level.SEVERE,e.getMessage());
			// System.exit(-1);
			return;
			// A parsing error occurred; the xml input is not valid
		} catch (ParserConfigurationException e) {
			logger.log(Level.SEVERE,e.getMessage());
			//System.exit(-1);
			return;
		} catch (IOException e) {
			logger.log(Level.SEVERE,"File "+fileName+" doesn't exist!\n Please, provide a configuration file");
			//System.exit(-1);
			return;
		}
	}
	
	public void parseXMLString(String xmlString){
		StringReader sr;
		InputSource sr_input;
		
		try {	
			// Create a builder factory
			SAXParserFactory factory = SAXParserFactory.newInstance();
			//factory.setValidating(true);
			
			MyHandler handler = new MyHandler();
			// Create the builder and parse the file
			sr = new StringReader(xmlString);
			sr_input = new InputSource(sr);
			factory.newSAXParser().parse(sr_input, handler);
		} catch (SAXException e) {
			logger.log(Level.SEVERE,e.getMessage());
			//System.exit(-1);
			return;
			// A parsing error occurred; the xml input is not valid
		} catch (ParserConfigurationException e) {
			logger.log(Level.SEVERE,e.getMessage());
			//System.exit(-1);
			return;
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Error reading XML configuration stream");
			//System.exit(-1);
			return;
		}
	}
	/* (non-Javadoc)
	 */
	
	
	public static void parseInfoTag(Attributes attributes){
		for (int i = 0; i < attributes.getLength(); i++){
			//System.out.print(i+" qname: "+attributes.getQName(i)+": value=");
			//System.out.println(attributes.getValue(i));
			
			if(attributes.getValue(i).equals(Constants.DATA_REPOSITORY)){
				i++;
				//System.out.println(": "+attributes.getValue(i));
				if(!attributes.getValue(i).endsWith(System.getProperty("file.separator"))){
					Config.dataRepository = attributes.getValue(i)+System.getProperty("file.separator");
				}else{
					Config.dataRepository = attributes.getValue(i);
				}
				//System.out.println(": "+Config.dataRepository);
			}
			if(attributes.getValue(i).equals(Constants.TMPDIR)){
				i++;
				//System.out.println(": "+attributes.getValue(i));
				if(!attributes.getValue(i).endsWith(System.getProperty("file.separator"))){
					Config.tempOutDir = attributes.getValue(i)+System.getProperty("file.separator");
				}else{
					Config.tempOutDir = attributes.getValue(i);
				}
				//System.out.println(": "+Config.dataRepository);
			}
			if(attributes.getValue(i).equals(Constants.RULES)){
				i++;
				//System.out.println(": "+attributes.getValue(i));
				Config.regExpFileName = attributes.getValue(i);
				//System.out.println(": "+Config.dataRepository);
			}
			if(attributes.getValue(i).equals(Constants.GROUP_PREFIX)){
				i++;
				//System.out.println(": "+attributes.getValue(i));
				Config.groupsRootFileName = attributes.getValue(i);
				//System.out.println(": "+Config.dataRepository);
			}
			if(attributes.getValue(i).equals(Constants.RELATION_PREFIX)){
				i++;
				//System.out.println(": "+attributes.getValue(i));
				Config.relationsRootFileName = attributes.getValue(i);
				//System.out.println(": "+Config.dataRepository);
			}
			if(attributes.getValue(i).equals(Constants.Lessico)){
				i++;
				//System.out.println(": "+attributes.getValue(i));
				Config.LESSICO = attributes.getValue(i);
				//System.out.println(": "+Config.dataRepository);
			}
			if(attributes.getValue(i).equals(Constants.Macro)){
				i++;
				//System.out.println(": "+attributes.getValue(i));
				Config.MACRO = attributes.getValue(i);
				//System.out.println(": "+Config.dataRepository);
			}
			if(attributes.getValue(i).equals(Constants.Rule)){
				i++;
				//System.out.println(": "+attributes.getValue(i));
				Config.RULE = attributes.getValue(i);
				//System.out.println(": "+Config.dataRepository);
			}
			if(attributes.getValue(i).equals(Constants.Ontolex)){
				i++;
				//System.out.println(": "+attributes.getValue(i));
				Config.ONTOLEX = attributes.getValue(i);
				//System.out.println(": "+Config.dataRepository);
			}
			if(attributes.getValue(i).equals(Constants.Ontology)){
				i++;
				//System.out.println(": "+attributes.getValue(i));
				Config.ONTOLOGY = attributes.getValue(i);
				//System.out.println(": "+Config.dataRepository);
			}
			if(attributes.getValue(i).equals(Constants.relTypeAssocFileName)){
				i++;
				//System.out.println(": "+attributes.getValue(i));
				Config.relTypeAssocFileName= attributes.getValue(i);
				//System.out.println(": "+Config.dataRepository);
			}
			//System.out.print("parseThresholdTag attributes : "+attributes.getQName(i));
			//System.out.println(": "+attributes.getValue(i));
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParserXmlConfig parser = new ParserXmlConfig();
		//parser.parseXMLFile(args[0]);
		parser.parseXMLString("<config-t2k> <threshold type=\"glossario\" value=\"3\"/>" +
				"<database type=\"servername\" value=\"192.168.1.2\"/>		" +
				"<database type=\"port\" value=\"\"/>" +
				"<database type=\"name\" value=\"t2k\"/>" +
				"<database type=\"login\" value=\"t2k\" />" +
				"<database type=\"password\" value=\"t2k\"/>" +
				"<database type=\"driver\" value=\"jdbc:jtds:sqlserver\"/>" +
		"</config-t2k>");
	}
	
	
}
