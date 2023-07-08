/*
 * $Id: ClientNLP2.java,v 1.4 2006/10/27 15:49:42 simone Exp $
 * Created on 3-mar-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ilc.vikef;

import ilc.vikef.ws.ITradeFairVikefAnnotator;
import ilc.vikef.ws.ITradeFairVikefAnnotatorServiceLocator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.rpc.ServiceException;


/**
 * @author simone
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ClientNLP2 {

	public static void main(String[] args) {

		String encoding = System.getProperty("file.encoding");
		System.err.println(encoding);
		String filename = null; 
		String fromNLP = null;

		if( args.length != 2 ){
			System.err.println("Specificare sia il tipo di catalogo che il file/URL di input "+args.length);
			System.err.println("Tipo catalogo: -i Ikea, -s Semana Verde, -z Zanotta");
			System.exit(-1);
		}
		System.err.print(args[0]+ " ");
		System.err.println(args[1]);
		ITradeFairVikefAnnotatorServiceLocator service = new ITradeFairVikefAnnotatorServiceLocator();
		try {
			ITradeFairVikefAnnotator nlp =  service.getItalianTradeFair();
			try {
				URL aUrl = new URL(args[1]);
				if("-i".equals(args[0])){
					//Ikea
					filename = new String ("output-ikea.xml" );
					fromNLP = nlp.runByURL(args[1]);
				}else				
					if("-s".equals(args[0])){
						//semana verde
						filename = new String ("output-semana.xml" );
						fromNLP = nlp.runByURLSM(args[1]);
					}else
						if("-z".equals(args[0])){
							filename = new String ("output-zanotta.xml" );
							fromNLP = nlp.runByURLZ(args[1]);
						}else{
							System.err.println("Tipo catalogo: -i Ikea, -s Semana Verde, -z Zanotta");
							System.exit(-1);
						}

				try {
					writeFile(filename, fromNLP.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			} catch (MalformedURLException e) {
				System.err.println(args[1]+" is not a URL...");
			}
			String xml = encoding.equalsIgnoreCase("cp1252")?readFile(args[1],"UTF8"):readFile(args[1]);
			//System.err.println("length " + xml.length()); 
			//System.err.println("filename " + args[1]);
			
			if("-i".equals(args[0])){
				//Ikea
				System.err.println("Ikea");
				filename = new String ("output-ikea.xml" );
				fromNLP = nlp.run(xml);
			}else				
				if("-s".equals(args[0])){
					//semana verde
					System.err.println("Semana Verde");
					filename = new String ("output-semana_verde.xml" );
					fromNLP = nlp.runSM(xml);
				}else
					if("-z".equals(args[0])){
						System.err.println("Zanotta");
						filename = new String ("output-zanotta.xml" );
						fromNLP = nlp.runZ(xml);
					}else{
						System.err.println("Tipo catalogo: -i Ikea, -s Semana Verde, -z Zanotta");
						System.exit(-1);
					}
		} catch (ServiceException e) { 
			// TODO Auto-generated catch block
			System.err.println("Errore in getnlp()");
			e.printStackTrace();
		} catch (Exception e){
			System.err.println("Errore generico " + e.getMessage() + " " + fromNLP);
			e.printStackTrace();
		}
		//System.out.println(fromNLP);
		try {
			writeFile(filename, fromNLP.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static String readFile(String fileName) throws IOException {
		return readFile(fileName,null);
	}
	protected static String readFile(String fileName, String encoding) throws IOException {
		File file = new File(fileName);
		byte[] bytes = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytes);
		fis.close();
		String content = (encoding==null?new String(bytes):new String(bytes,encoding));
		return content;
	}

	protected static void writeFile(String filename, String text) throws IOException {
		try {
			BufferedWriter out = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(filename),
							"UTF8"));
			out.write(text);
			out.close();
		} catch (IOException e) {
		}
	}



}
