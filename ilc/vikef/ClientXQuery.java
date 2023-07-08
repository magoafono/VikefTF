/*
 * Created on 3-mar-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ilc.vikef;

import ilc.vikef.ws.ITradeFairVikefAnnotator;
import ilc.vikef.ws.ITradeFairVikefAnnotatorService;
import ilc.vikef.ws.ITradeFairVikefAnnotatorServiceLocator;
import ilc.vikef.ws.qr.IXqueryResult;
import ilc.vikef.ws.qr.IXqueryResultServiceLocator;

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
public class ClientXQuery {

	public static void main(String[] args) {
		
		String encoding = System.getProperty("file.encoding");
		System.err.println(encoding);
		StringBuffer xQueryEvaluation = null;
		//ITradeFairVikefAnnotatorServiceLocator service = new ITradeFairVikefAnnotatorServiceLocator();
		IXqueryResultServiceLocator service = new IXqueryResultServiceLocator();
		try {
			IXqueryResult xQueryResult =  service.getXqueryResult();
			//xQueryEvaluation = new StringBuffer(xQueryResult.evaluateXQuery((".submit=Invia richiesta&name=&typeSelect=&categorySelect=&materialSelect=&colourSelect=&id=&MinPrice=&MaxPrice=&MinHeight=&MaxHeight=&MinWidth=&MaxWidth=&MinDepth=&MaxDepth=&MinLength=&MaxLength=&MinDiameter=&MaxDiameter=&part1Type=&part1Material=&part1Colour=&part2Type=&part2Material=&part2Colour=&part3Type=&part3Material=&part3Colour=&.cgifields=presentation&.cgifields=have-dimensions&.cgifields=have-price&.cgifields=have-category&.cgifields=have-name")));
//			xQueryEvaluation = new StringBuffer(xQueryResult.evaluateXQueryZ((".submit=Invia richiesta&name=&typeSelect=&categorySelect=&materialSelect=&colourSelect=&id=&MinPrice=&MaxPrice=&MinHeight=&MaxHeight=&MinWidth=&MaxWidth=&MinDepth=&MaxDepth=&MinLength=&MaxLength=&MinDiameter=&MaxDiameter=&part1Type=&part1Material=&part1Colour=&part2Type=&part2Material=&part2Colour=&part3Type=&part3Material=&part3Colour=&.cgifields=presentation&.cgifields=have-dimensions&.cgifields=have-price&.cgifields=have-category&.cgifields=have-name")));
			xQueryEvaluation = new StringBuffer(xQueryResult.evaluateXQueryZ(("name=&year=&designer=&typeSelect=tavolo scrivania&categorySelect=&materialSelect=&colourSelect=&part1Type=&part1Material=&part1Colour=&part2Type=&part2Material=&part2Colour=&part3Type=&part3Material=&part3Colour=&MinPrice=&MaxPrice=&MinHeight=&MaxHeight=&MinWidth=&MaxWidth=&MinDepth=&MaxDepth=&MinLength=&MaxLength=&MinDiameter=&MaxDiameter=&image-class=0&results=20")));
			System.err.println( "xQueryEvaluation: \n" + xQueryEvaluation );
			/*try {
					writeFile(fromNLP.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}*/
			//String xml = encoding.equalsIgnoreCase("cp1252")?readFile(args[0],"UTF8"):readFile(args[0]);
		} catch (ServiceException e) { 
			// TODO Auto-generated catch block
			System.err.println("Errore in getnlp()");
			e.printStackTrace();
		} catch (Exception e){
			System.err.println("Errore generico");
			e.printStackTrace();
	    }
	    //System.out.println(fromNLP);
	    /*try {
			writeFile(xQueryEvaluation.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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

	protected static void writeFile(String text) throws IOException {
		try {
			BufferedWriter out = new BufferedWriter(
			          new OutputStreamWriter(new FileOutputStream("outfile.xml"),
			                                 "UTF8"));
			out.write(text);
			out.close();
		} catch (IOException e) {
		}
	}

		
	
}
