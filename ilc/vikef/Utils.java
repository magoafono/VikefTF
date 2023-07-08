/* $Id: Utils.java,v 1.7 2006/08/24 09:37:37 simone Exp $ */
package ilc.vikef;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

public class Utils {
	
	static Logger logger = Logger.getLogger("ilc.vikef");  
	public static String readFromFile(String fileName,String enc){
		File file = new File (fileName);
		InputStream is;
		try {
			is = new FileInputStream(file);
			// Get the size of the file
			long length = file.length();
			
			// You cannot create an array using a long type.
			// It needs to be an int type.
			// Before converting to an int type, check
			// to ensure that file is not larger than Integer.MAX_VALUE.
			if (length > Integer.MAX_VALUE) {
				// File is too large
				return(null);
			}
			
			// Create the byte array to hold the data
			byte[] bytes = new byte[(int)length];
			
			// Read in the bytes
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length
					&& (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
				offset += numRead;
			}
			
			// Ensure all the bytes have been read in
			if (offset < bytes.length) {
				throw new IOException("Could not completely read file "+file.getName());
			}
			// Close the input stream and return bytes
			is.close();
			String newString;
			if(null != enc){
				newString = new String(bytes,enc);
			}else{
				newString = new String(bytes);
			}
			
			return newString;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static String readFromFile(String fileName){
		return readFromFile(fileName,null);
	}
	
	
	public static HashMap readFileToHash(String fileName){
		logger.info(fileName);
		HashMap hash = new HashMap();
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String str;
			while ((str = in.readLine()) != null) {
				logger.fine(str);
				String[] row = str.split(" ");
				hash.put(row[0].trim(),row[1].trim());
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hash;
	}
	
	public static void printXMLHeader(BufferedWriter bw){
		try {
			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
			//bw.write("<?xml-stylesheet type=\"text/xsl\" href=\"vikef.xslt\"?>");
			bw.write("\n<VistaAnnotations>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void printXMLHeader(StringBuffer sb){
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		//sb.append("<?xml-stylesheet type=\"text/xsl\" href=\"vikef.xslt\"?>");
		sb.append("\n<VistaAnnotations>");
	}
	public static void printXMLFooter(BufferedWriter bw){
		try {
			bw.write("\n</VistaAnnotations>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void printXMLFooter(StringBuffer sb){
		sb.append("\n</VistaAnnotations>");
	}

	public static void printSystemProperties(){	
		// Get all system properties
		Properties props = System.getProperties();
		
		// Enumerate all system properties
		Enumeration enumx = props.propertyNames();
		for (; enumx.hasMoreElements(); ) {
			// Get property name
			String propName = (String)enumx.nextElement();
			
			// Get property value
			String propValue = (String)props.get(propName);
			System.err.println(propName+" "+propValue); 
		}
	}
	/**
	 * Elimina  tutti i file in una directory
	 * @param dirname nome della directory da eliminare
	 */
	public static void deleteDirectory(String dirname){
		File dir = new File(dirname);
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				boolean success = (new File(dir, children[i])).delete();
				if (!success) {
					//return false;
				}
			}
		}
//		dir.delete();
	}
	
	/**
	 * Scrive nel file <i>fileName</i> il testo contenuto in <i>text</i> 
	 * @param text testo da scrivere nel file
	 * @param fileName nome del file nel quale scrivere text. Il file e' creato come nuovo.
	 * @throws IOException
	 */
	public static void writeFile(String text, String fileName) throws IOException {
		try {
			BufferedWriter out = new BufferedWriter(
			          new OutputStreamWriter(new FileOutputStream(fileName),
			                                 "UTF8"));
			out.write(text);
			out.close();
		} catch (IOException e) {
		}
	}

	public static void printConfiguration(){
		logger.info(Config.rootDirectory);
	}
	
	
}
