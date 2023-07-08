/* $Id: XMLParser.java,v 1.22 2006/10/25 08:04:30 simone Exp $ */
package ilc.vikef;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


/**
 * 
 * 
 */
public class XMLParser {
	/**
	 * <p>Represents ...</p>
	 * 
	 */
	private XMLStreamReader parser;
	private String entryLevelUrl = null;
	private static Logger logger = Logger.getLogger("VikefTF");
	private Paragraph  lastParagraph = null;
	/**
	 * Inizializza il parser (vedi stax.java)
	 * 
	 * 
	 * @param inputText 
	 * @throws XMLStreamException 
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public void init(String inputText) throws XMLStreamException, UnsupportedEncodingException{ 
		
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream in = new ByteArrayInputStream(inputText.getBytes());
		
		//logger.info(inputText);
		
		InputStreamReader inr = new InputStreamReader(in);
		//InputStreamReader inr = new InputStreamReader(in, "ISO-8859-1");
		
		//Nello StringReader ci sono i caratteri correttamente, tipo l'euro
		/*char[] p= new char[1000000];
		try {
			while ((inr.read(p))!= -1) System.err.print(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.exit(-1);
		*/

		//parser = factory.createXMLStreamReader(in,"UTF-8");
		parser = factory.createXMLStreamReader(inr);

		/*try {
			parser = factory.createXMLStreamReader(new InputStreamReader(new FileInputStream("pdf-segment-chain1.xml"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//System.err.print("Encoding: "+parser.getEncoding());
		//System.exit(-1);
		//parser = factory.createXMLStreamReader(in,"ISO-8859-1");
		
		//StringReader sr = new StringReader(inputText);
		/* Nello StringReader ci sono i caratteri correttamente, tipo l'euro
		 char[] p= new char[1000000];
		try {
			while ((sr.read(p))!= -1) System.err.print(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		System.exit(-1);
		*/
		//parser = factory.createXMLStreamReader(sr,"UTF-8");
		//parser = factory.create
//		System.exit(-1);
	} 
	
	public Paragraph next(boolean paragraphBased) {
		if(paragraphBased)
				return nextParagraph();
		else
				return nextPage(); 
		
	}
	
	/**
	 * restituisce un oggetto Paragraph costruito opportunamente (vedi for con switch dello stax.java)
	 * 
	 * 
	 * @return 
	 */
	public Paragraph nextParagraph() {
		Paragraph par = null;
		
		boolean foundParagraph = false;
		try {
			//se ci sono altri parti da analizzare
			while(parser.hasNext()){
				int event = parser.next();
				if(event != XMLStreamConstants.END_DOCUMENT){
					
					switch (event) {
					
					case XMLStreamConstants.START_DOCUMENT: 
						//mai invocato!
						break;
					
					case XMLStreamConstants.START_ELEMENT:
						
						if(Constants.Doc.equals(parser.getLocalName())){
							for(int i=0; i<parser.getAttributeCount();i++){
								if(parser.getAttributeLocalName(i).equals(Constants.EntryLevelUrl)){
									//salvo l'entryLevelUrl
									this.entryLevelUrl = parser.getAttributeValue(i);
									//logger.info("entryLevelURL: "+this.entryLevelUrl);
								}
							}
						}
						
						if(Constants.Paragraph.equals(parser.getLocalName())){
							foundParagraph = true;
							//istanzio il paragraph
							par = new Paragraph();
							//aggiungo al paragraph l'EntryLevelURL
							par.setEntryLevelURL(this.entryLevelUrl);
							for(int i=0; i<parser.getAttributeCount();i++){
								if(parser.getAttributeLocalName(i).equals(Constants.EntryLevelIds)){
									//metto gli entryLevelIds
									par.setEntryLevelIds(parser.getAttributeValue(i));
								}
								if(parser.getAttributeLocalName(i).equals(Constants.Category)){
									//metto la category
									par.setCategory(parser.getAttributeValue(i));
								}
								if(parser.getAttributeLocalName(i).equals(Constants.ParagraphId)){
									//metto il paragraphId
									par.setParagraphId(parser.getAttributeValue(i));
								}
							}
						}
						break;
					case XMLStreamConstants.END_ELEMENT:
						//quando trovo la chiusura del tag PARAGRAPH, ritorno il Paragraph costruito
						if(foundParagraph){
							return par;
						}
						break;
					case XMLStreamConstants.CHARACTERS:
						if(foundParagraph){
							String characters = null;
							if(null != parser.getText()){
								//characters = new String (parser.getText().getBytes(),"ISO-8859-15");
								characters = parser.getText();//new String (parser.getText().getBytes(),"UTF-8");
								par.setCharacters(characters.trim());
								//logger.info("CHARACTERS: "+par.getCharacters());
							}else{
								logger.severe("problemi con il characters di un paragraph!");
								par.setCharacters("");
							}
							//aggiunto il testo trovato
						}
						break;
					case XMLStreamConstants.CDATA:
						break;
					} // end switch
				}
			}
			if(!parser.hasName()){
				parser.close();
			}
			return null;
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end while
		finally{
			try {
				parser.close();
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	} 
	
	
	/**
	 * 
	 * @return
	 */
	public Paragraph nextPage() {

		//prendo il prossimo paragraph
		Paragraph nextParagraph = nextParagraph();
		if (nextParagraph == null)
			return  null;
		Paragraph paragraphsInPage;

		//controllo se dal nextPage() precedente c'e' un paragraph residuo
		//cioe' che non faceva parte della pagina precedente e quindi fa parte 
		//della pagina che sto considerando ora
		if (lastParagraph != null){
			paragraphsInPage = lastParagraph;
		}else {
			paragraphsInPage = nextParagraph;
		}
			
//		System.err.println ("page: "+ nextParagraph.getPage() + ", " + paragraphsInPage.getPage());
		while ( nextParagraph.getPage().equals(paragraphsInPage.getPage()) ){
			//unisci
			paragraphsInPage.setCharacters(paragraphsInPage.getCharacters() + "£" + nextParagraph.getCharacters());
			paragraphsInPage.setEntryLevelIds(paragraphsInPage.getEntryLevelIds()+ " " + nextParagraph.getEntryLevelIds());
			nextParagraph = nextParagraph();
			if ( nextParagraph == null){
				break;
			}
		}

		//esci e setta nextParagraph come lastParagraph
		lastParagraph = nextParagraph;
		//System.err.println(paragraphsInPage);
		return paragraphsInPage;

	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		/*
			    if (args.length == 0) {
			      System.err.println("Usage: java XHTMLOutliner url" );
			      return;
			    }
		 */
		//String input = args[0];
		
		
		try {
			//String input = Utils.readFromFile("ikea2006.xml");
			String input = Utils.readFromFile("/home/simone/zanotta.xml");
			XMLParser xmlParser = new XMLParser();
			xmlParser.init(input);
			while(true){
//				Paragraph p = xmlParser.next();
				Paragraph p = xmlParser.nextPage();
				//System.err.println(p.getEntryLevelIds());
				if (p != null){
					System.err.println("*"+p.getCharacters()+"*");
				}else{
					System.exit(0);
					
				}
			}
			
		}
		catch (XMLStreamException ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}
		
		
	}
	
}
