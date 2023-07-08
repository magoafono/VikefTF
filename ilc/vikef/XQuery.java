package ilc.vikef;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.StringTokenizer;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.xml.namespace.QName;

import net.sf.saxon.javax.xml.xquery.XQConnection;
import net.sf.saxon.javax.xml.xquery.XQDataSource;
import net.sf.saxon.javax.xml.xquery.XQException;
import net.sf.saxon.javax.xml.xquery.XQExpression;
import net.sf.saxon.javax.xml.xquery.XQItemType;
import net.sf.saxon.javax.xml.xquery.XQResultSequence;
import net.sf.saxon.xqj.SaxonXQDataFactory;
import net.sf.saxon.xqj.SaxonXQDataSource;

import ilc.vikef.ws.qr.IXqueryResult;

public class XQuery implements IXqueryResult {

	static Logger logger = Logger.getLogger("ilc.vikef.XQuery");  
	/**
	 * @param args
	 */

	public static void main(String[] args) {

		XQuery xq = new XQuery();
		try {
			String result = xq.evaluateXQueryZ(".submit=Invia richiesta&year=&designer=Emaf Progetti&name=&typeSelect=&categorySelect=&materialSelect=&colourSelect=&id=&MinPrice=&MaxPrice=&MinHeight=&MaxHeight=&MinWidth=&MaxWidth=&MinDepth=&MaxDepth=&MinLength=&MaxLength=&MinDiameter=&MaxDiameter=&part1Type=&part1Material=&part1Colour=&part2Type=&part2Material=&part2Colour=&part3Type=&part3Material=&part3Colour=&.cgifields=presentation&.cgifields=have-dimensions&.cgifields=have-price&.cgifields=have-category&.cgifields=have-name");
			System.err.println(result);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * SEMANA VERDE
	 */
	public String evaluateXQuerySM(String in0) throws RemoteException {
		System.err.println("semana");
		return localEvaluateXQuery("/usr/share/tomcat/webapps/axis/WEB-INF/lib/vista/semana-product.xml",in0);

	}

	/**
	 * ZANOTTA
	 */
	public String evaluateXQueryZ(String in0) throws RemoteException {
		System.err.println("zanotta");
		return localEvaluateXQuery("/usr/share/tomcat/webapps/axis/WEB-INF/lib/vista/zanotta-product.xml",in0);
	}

	/**
	 * IKEA
	 */
	public String evaluateXQuery(String in0) throws RemoteException {
		System.err.println("ikea");
		return localEvaluateXQuery("/usr/share/tomcat/webapps/axis/WEB-INF/lib/vista/ikea-product.xml",in0);
	}

	/**
	 * 
	 */
	public String localEvaluateXQuery(String xmlfile, String in0) throws RemoteException {
		LogManager.getLogManager().reset();
		try {
			LogManager.getLogManager().readConfiguration();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TradeFairVikefAnnotator.reinitLog(null);

		//logger.info(in0);
		StringBuffer products = new StringBuffer(/*"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"*/);
		//System.err.println("in0: " + in0);
		String formInput = in0.replaceAll("\\+", " ");
		//System.err.println("formInput: " + formInput);
		try {
//			String xmlfile="/usr/share/tomcat/webapps/axis/WEB-INF/lib/vista/ikea-product.xml";
			XQDataSource xqds = new SaxonXQDataSource();
//			establish a connection to the XQuery engine
			XQConnection conn = xqds.getConnection();
//			create an expression object that is later used
//			to execute an XQuery expression
			//XQExpression expr = conn.createExpression();
//			The XQuery expression to be executed
			/*		String es = "for $n in fn:doc('outfile.xml')//product [height>70]" +
			" return ($n)";*/

			/*			String es = "declare variable $x as xs:integer external;" +
						"declare variable $y as xs:integer external;" +
						"declare variable $z as xs:string external;" +
						" for $n in fn:doc('outfile.xml')//product" +
						" where ($n/height > $x and $n/price > $y " + 
						" and fn:matches($n/name, $z)) " +
						" return $n";

//			 the QName x
			QName height = new QName("x");
			expr.bindInt(height, 70, null);

			QName price = new QName("y");
			expr.bindInt(price, 80, null);
			QName name = new QName("z");

			SaxonXQDataFactory df = new SaxonXQDataSource();
			XQItemType it = df.createAtomicItemType(XQItemType.XQBASETYPE_STRING);
			expr.bindAtomicValue(name, ".*", it);*/

			/*			String es = 
			"declare variable $z as xs:string external;" +
			" for $n in fn:doc('outfile.xml')//product " +
				" return "+
					"  for $m in fn:doc('outfile.xml')//product/part [product=$n] " +
						//" where fn:matches($m/value, $z) " +
						" return $n ";*/
			/*			String es = 
				"declare variable $z as xs:string external;" +
				" for $n in fn:doc('outfile.xml')//product " +
					" return for $m in fn:distinct-values ($n//part/value) " +
						"where fn:matches($m, $z) " +
							" return $n ";
//			the QName z
			QName name = new QName("z");
			SaxonXQDataFactory df = new SaxonXQDataSource();
			XQItemType it = df.createAtomicItemType(XQItemType.XQBASETYPE_STRING);
			expr.bindAtomicValue(name, "", it);
			 */

			XQExpression expr = parsingAttributes(formInput,conn);

			String es = 
				"declare variable $name as xs:string external;" +
				"declare variable $type as xs:string external;" +
				"declare variable $year as xs:string external;" +
				"declare variable $designer as xs:string external;" +
				"declare variable $category as xs:string external;" +
				"declare variable $material as xs:string external;" +
				"declare variable $colour as xs:string external;" +
				//interi
				"declare variable $MinPrice as xs:integer external;" +
				"declare variable $MaxPrice as xs:integer external;" +
				"declare variable $MinHeight as xs:integer external;" +
				"declare variable $MaxHeight as xs:integer external;" +
				"declare variable $MinWidth as xs:integer external;" +
				"declare variable $MaxWidth as xs:integer external;" +
				"declare variable $MinLength as xs:integer external;" +
				"declare variable $MaxLength as xs:integer external;" +
				"declare variable $MinDepth as xs:integer external;" +
				"declare variable $MaxDepth as xs:integer external;" +
				"declare variable $MinDiameter as xs:integer external;" +
				"declare variable $MaxDiameter as xs:integer external;" +

				//parte 1 
				"declare variable $part1Type as xs:string external;" +
				"declare variable $part1Material as xs:string external;" +
				"declare variable $part1Colour as xs:string external;" +
				//parte 2
				"declare variable $part2Type as xs:string external;" +
				"declare variable $part2Material as xs:string external;" +
				"declare variable $part2Colour as xs:string external;" +
				//parte 3
				"declare variable $part3Type as xs:string external;" +
				"declare variable $part3Material as xs:string external;" +
				"declare variable $part3Colour as xs:string external;" +

				"for $n in fn:doc('"+xmlfile+"')//product" +

				" where ( " + 
				//" fn:matches($n/name, $name) and " +
				" fn:matches(fn:upper-case($n/name), fn:upper-case($name)) and " +
				" fn:starts-with(fn:upper-case($n/type), fn:upper-case($type)) and " +
				" fn:matches($n/year, $year) and " +
				" fn:matches(fn:upper-case($n/designer), fn:upper-case($designer)) and " +
				" fn:matches(fn:upper-case($n/category), fn:upper-case($category)) and " +
				" fn:matches(fn:upper-case($n/material), fn:upper-case($material)) and " +
				" fn:matches(fn:upper-case($n/colour), fn:upper-case($colour)) )" +
				" and " +
				" (if((fn:index-of($n/price,'price'))>0 or $MinPrice>0 or $MaxPrice<10000) "+
				"then ($n/price >= $MinPrice       and $n/price <= $MaxPrice)  else (fn:true()) ) and " +  
//				" ($n/price >= $MinPrice       and $n/price <= $MaxPrice) and " +  
				" (if((fn:index-of($n/height,'height'))>0 or $MinHeight>0 or $MaxHeight<10000) "+
				"then ($n/height >= $MinHeight     and $n/height <= $MaxHeight)  else ( fn:true()) ) and " + 
//				" ($n/height >= $MinHeight     and $n/height <= $MaxHeight) and " + 
				" (if((fn:index-of($n/length,'length'))>0 or $MinLength>0 or $MaxLength<10000)"+
				" then ($n/length >= $MinLength and $n/length <= $MaxLength) else ( fn:true()) ) and " + 

				" (if((fn:index-of($n/width,'width'))>0 or $MinWidth>0 or $MaxWidth<10000)"+
				" then ($n/width >= $MinWidth and $n/width <= $MaxWidth) else ( fn:true()) ) and " + 
//				" (if(fn:matches($n/width,'width')) then ($n/width >= $MinWidth and $n/width <= $MaxWidth) else (fn:true()) )and " +
				//" ($n/depth >= $MinDepth       and $n/depth <= $MaxDepth) and " + 
				" (if((fn:index-of($n/diameter,'diameter'))>0 or $MinDiameter>0 or $MaxDiameter<10000)" +
				" then ($n/diameter >= $MinDiameter and $n/diameter <= $MaxDiameter) else (fn:true()) )" + 
				" and "+
//				" fn:matches($n/part/value, $part1Type) and " +
//				" fn:matches($n/part/material, $part1Material) and " +
//				" fn:matches($n/part/colour, $part1Colour) " +


				//type della parte
				/*				" (if((fn:index-of($n/part/value, $part1Type)>0) or  (fn:index-of($n/part/value, $part2Type)>0) or  (fn:index-of($n/part/value, $part3Type)>0) or " +
				" ( fn:compare($part1Type,\".*\")=0 and fn:compare($part2Type,\".*\")=0 and fn:compare($part3Type,\".*\")=0 ) )" +
				" then (fn:true()) else (fn:false())) and " +*/
				"( if( fn:compare($part1Type,\".*\")=0  ) then  ( fn:true() ) else ( fn:index-of($n/part/value, $part1Type)>0)  )  and " +
				"( if( fn:compare($part2Type,\".*\")=0  ) then  ( fn:true() ) else ( fn:index-of($n/part/value, $part2Type)>0)  )  and " +
				"( if( fn:compare($part3Type,\".*\")=0  ) then  ( fn:true() ) else ( fn:index-of($n/part/value, $part3Type)>0)  )  and " +
				

				//material della parte
/*				" (if((fn:index-of($n/part/material, $part1Material)>0) or (fn:index-of($n/part/material, $part2Material)>0) or (fn:index-of($n/part/material, $part2Material)>0) or" +
				" ( fn:compare($part1Material,\".*\")=0 and fn:compare($part2Material,\".*\")=0 and fn:compare($part3Material,\".*\")=0 ) )" +
				"then (fn:true()) else (fn:false())) and " +*/
				"( if( fn:compare($part1Material,\".*\")=0  ) then  ( fn:true() ) else ( fn:index-of($n/part/material, $part1Material)>0)  )  and " +
				"( if( fn:compare($part2Material,\".*\")=0  ) then  ( fn:true() ) else ( fn:index-of($n/part/material, $part2Material)>0)  )  and " +
				"( if( fn:compare($part3Material,\".*\")=0  ) then  ( fn:true() ) else ( fn:index-of($n/part/material, $part3Material)>0)  )  and " +

				//colour della parte
/*				" (if((fn:index-of($n/part/colour, $part1Colour)>0) or (fn:index-of($n/part/colour, $part2Colour)>0) or (fn:index-of($n/part/colour, $part3Colour)>0) or" +
				" ( fn:compare($part1Colour,\".*\")=0 and fn:compare($part2Colour,\".*\")=0 and fn:compare($part3Colour,\".*\")=0 ) )" +
				"then (fn:true()) else (fn:false()))  " + */
				"( if( fn:compare($part1Colour,\".*\")=0  ) then  ( fn:true() ) else ( fn:index-of($n/part/colour, $part1Colour)>0)  )  and " +
				"( if( fn:compare($part2Colour,\".*\")=0  ) then  ( fn:true() ) else ( fn:index-of($n/part/colour, $part2Colour)>0)  )  and " +
				"( if( fn:compare($part3Colour,\".*\")=0  ) then  ( fn:true() ) else ( fn:index-of($n/part/colour, $part3Colour)>0)  )   " +


				/*			" ( for $p in $n" +
			//" return (fn:true()) " + 

//			" where ( " +
			" return ( " + 
 			" (if((fn:index-of($p/value, $part1Type)>0) or (fn:index-of($p/value, $part2Type)>0) or (fn:index-of($p/value, $part3Type)>0) or" +
			" ( fn:compare($part1Type,\".*\")=0 and fn:compare($part2Type,\".*\")=0 and fn:compare($part3Type,\".*\")=0 ) )" +
			"then (fn:true()) else (fn:false())) )" + 
				 */
//				"then (fn:true()) else (fn:false())) and " +
				/*
			" (if((fn:index-of($p/material, $part1Material)>0) or (fn:index-of($p/material, $part2Material)>0) or (fn:index-of($p/material, $part2Material)>0) or" +
			" ( fn:compare($part1Material,\".*\")=0 and fn:compare($part2Material,\".*\")=0 and fn:compare($part3Material,\".*\")=0 ) )" +
			"then (fn:true()) else (fn:false())) and " +

			" (if((fn:index-of($p/colour, $part1Colour)>0) or (fn:index-of($p/colour, $part2Colour)>0) or (fn:index-of($p/colour, $part3Colour)>0) or" +
			" ( fn:compare($part1Colour,\".*\")=0 and fn:compare($part2Colour,\".*\")=0 and fn:compare($part3Colour,\".*\")=0 ) )" +
			"then (fn:true()) else (fn:false()))  " +
	 		")" +*/	
				//"return (fn:true())"+
				//	")" +	
				" return $n";

//			System.err.println(es);
//			execute the XQuery expression
			XQResultSequence result = expr.executeQuery(es);
//			process the result (sequence) iteratively
			products.append("<doc>\n");

			while ( result.next() ){
//				retrieve the current item of the sequence as a String
				String str = result.getItemAsString();
				//System.err.println("str: "+str.substring(38));
				products.append(str.substring(38));
				products.append("\n");
			}
			products.append("</doc>");

//			free all resources allocated for the result
			result.close();
//			free all resources allocated for the expression
			expr.close();
//			free all resources allocated for the connection
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			products.append("</doc>");
		}
		logger.info(products.toString());
		logger.warning("End");
		return products.toString();
	}

	/**
	 * Data una stringa rappresentante l'input del POST, si costruisce la condizione di ricerca
	 * della XQuery
	 * 
	 * @param input
	 * @return
	 */
	public XQExpression parsingAttributes(String input, XQConnection conn){

		XQExpression expr = null;
		StringBuffer sb = new StringBuffer();
		try {
			expr = conn.createExpression();
		} catch (XQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.err.println(input);
		StringTokenizer st = new StringTokenizer(input,"&");
		sb.append("\n");
		while (st.hasMoreTokens()){
			String[] elem = (st.nextToken()).split("=");
			String name = elem[0];
			String value = "";
			if( elem.length > 1){
				value = elem[1];
			}
			sb.append(name + " = " + value +"\n");
			try {
				attachParameter(expr, name, value);
			} catch (XQException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.err.println("parametri: "  + sb.toString());
		logger.warning(sb.toString());
		return expr;
	}

	/**
	 * 
	 * @param expr
	 * @param name
	 * @param value
	 * @throws XQException
	 */
	public void attachParameter (XQExpression expr, String name, String value) throws XQException {

		SaxonXQDataFactory df = new SaxonXQDataSource();
		XQItemType it = df.createAtomicItemType(XQItemType.XQBASETYPE_STRING);

		if( "name".equals(name) ){
			QName qname = new QName("name");
			if	("".equals(value)){
				expr.bindAtomicValue(qname, ".*", it);
			}else{
				expr.bindAtomicValue(qname, "^"+value.toUpperCase()+"$", it);
				//expr.bindAtomicValue(qname, "^"+value, it);
			}
		}else
			if( "typeSelect".equals(name) ){
				QName qname = new QName("type");
				if	("".equals(value)){
					expr.bindAtomicValue(qname, "", it);
				}else{
					expr.bindAtomicValue(qname, value.toLowerCase(), it);
				}
			}else
				if( "year".equals(name) ){
					QName qname = new QName("year");
					if	("".equals(value)){
						expr.bindAtomicValue(qname, ".*", it);
					}else{
						expr.bindAtomicValue(qname, "^"+value+"$", it);
					}
				}else
					if( "designer".equals(name) ){
						QName qname = new QName("designer");
						if	("".equals(value)){
							expr.bindAtomicValue(qname, ".*", it);
						}else{
							expr.bindAtomicValue(qname, value, it);
						}
					}else
						if( "categorySelect".equals(name) ){
							QName qname = new QName("category");
							if	("".equals(value)){
								expr.bindAtomicValue(qname, ".*", it);
							}else{
//								System.err.println("category: *"+value + "* "+ value.replaceAll("%26", "&"));
								expr.bindAtomicValue(qname, "^"+value.replaceAll("%26", "&")+"$", it);
							}
						}else
							if( "materialSelect".equals(name) ){
								QName qname = new QName("material");
								if	("".equals(value)){
									expr.bindAtomicValue(qname, ".*", it);
								}else{
									expr.bindAtomicValue(qname, value.toLowerCase(), it);
								}
							}else
								if( "colourSelect".equals(name) ){
									QName qname = new QName("colour");
									if	("".equals(value)){
										expr.bindAtomicValue(qname, ".*", it);
									}else{
										expr.bindAtomicValue(qname, value.toLowerCase(), it);
									}
								}else

									//Interi
									if( "MinPrice".equals(name) ){
										QName qname = new QName("MinPrice");
										if	("".equals(value)){
											expr.bindInt(qname, 0, null);
										}else{
											expr.bindInt(qname, Integer.parseInt(value), null);
										}
									}else
										if( "MaxPrice".equals(name) ){
											QName qname = new QName("MaxPrice");
											if	("".equals(value)){
												expr.bindInt(qname, Integer.MAX_VALUE, null);
											}else{
												expr.bindInt(qname, Integer.parseInt(value), null);
											}
										}else

											if( "MinHeight".equals(name) ){
												QName qname = new QName("MinHeight");
												if	("".equals(value)){
													expr.bindInt(qname, 0, null);
												}else{
													expr.bindInt(qname, Integer.parseInt(value), null);
												}
											}else
												if( "MaxHeight".equals(name) ){
													QName qname = new QName("MaxHeight");
													if	("".equals(value)){
														expr.bindInt(qname, Integer.MAX_VALUE, null);
													}else{
														expr.bindInt(qname, Integer.parseInt(value), null);
													}
												}else
													if( "MinLength".equals(name) ){
														QName qname = new QName("MinLength");
														if	("".equals(value)){
															expr.bindInt(qname, 0, null);
														}else{
															expr.bindInt(qname, Integer.parseInt(value), null);
														}
													}else
														if( "MaxLength".equals(name) ){
															QName qname = new QName("MaxLength");
															if	("".equals(value)){
																expr.bindInt(qname, Integer.MAX_VALUE, null);
															}else{
																expr.bindInt(qname, Integer.parseInt(value), null);
															}
														}else
															if( "MinWidth".equals(name) ){
																QName qname = new QName("MinWidth");
																if	("".equals(value)){
																	expr.bindInt(qname, 0, null);
																}else{
																	expr.bindInt(qname, Integer.parseInt(value), null);
																}
															}else
																if( "MaxWidth".equals(name) ){
																	QName qname = new QName("MaxWidth");
																	if	("".equals(value)){
																		expr.bindInt(qname, Integer.MAX_VALUE, null);
																	}else{
																		expr.bindInt(qname, Integer.parseInt(value), null);
																	}
																}else
																	if( "MinDepth".equals(name) ){
																		QName qname = new QName("MinDepth");
																		if	("".equals(value)){
																			expr.bindInt(qname, 0, null);
																		}else{
																			expr.bindInt(qname, Integer.parseInt(value), null);
																		}
																	}else
																		if( "MaxDepth".equals(name) ){
																			QName qname = new QName("MaxDepth");
																			if	("".equals(value)){
																				expr.bindInt(qname, Integer.MAX_VALUE, null);
																			}else{
																				expr.bindInt(qname, Integer.parseInt(value), null);
																			}
																		}else
																			if( "MinDiameter".equals(name) ){
																				QName qname = new QName("MinDiameter");
																				if	("".equals(value)){
																					expr.bindInt(qname, 0, null);
																				}else{
																					expr.bindInt(qname, Integer.parseInt(value), null);
																				}
																			}else
																				if( "MaxDiameter".equals(name) ){
																					QName qname = new QName("MaxDiameter");
																					if	("".equals(value)){
																						expr.bindInt(qname, Integer.MAX_VALUE, null);
																					}else{
																						expr.bindInt(qname, Integer.parseInt(value), null);
																					}
																				}else

																					//part : String
																					if( "part1Type".equals(name) ){
																						QName qname = new QName("part1Type");
																						if	("".equals(value)){
																							expr.bindAtomicValue(qname, ".*", it);
																						}else{
																							expr.bindAtomicValue(qname,value.toLowerCase(), it);
																						}
																					}else
																						if( "part1Material".equals(name) ){
																							QName qname = new QName("part1Material");
																							if	("".equals(value)){
																								expr.bindAtomicValue(qname, ".*", it);
																							}else{
																								expr.bindAtomicValue(qname, value.toLowerCase(), it);
																							}
																						}else
																							if( "part1Colour".equals(name) ){
																								QName qname = new QName("part1Colour");
																								if	("".equals(value)){
																									expr.bindAtomicValue(qname, ".*", it);
																								}else{
																									expr.bindAtomicValue(qname, value.toLowerCase(), it);
																								}
																							}else
																								//part : String
																								if( "part2Type".equals(name) ){
																									QName qname = new QName("part2Type");
																									if	("".equals(value)){
																										expr.bindAtomicValue(qname, ".*", it);
																									}else{
																										expr.bindAtomicValue(qname, value.toLowerCase(), it);
																									}
																								}else
																									if( "part2Material".equals(name) ){
																										QName qname = new QName("part2Material");
																										if	("".equals(value)){
																											expr.bindAtomicValue(qname, ".*", it);
																										}else{
																											expr.bindAtomicValue(qname, value.toLowerCase(), it);
																										}
																									}else
																										if( "part2Colour".equals(name) ){
																											QName qname = new QName("part2Colour");
																											if	("".equals(value)){
																												expr.bindAtomicValue(qname, ".*", it);
																											}else{
																												expr.bindAtomicValue(qname, value.toLowerCase(), it);
																											}
																										}else
																											//part : String
																											if( "part3Type".equals(name) ){
																												QName qname = new QName("part3Type");
																												if	("".equals(value)){
																													expr.bindAtomicValue(qname, ".*", it);
																												}else{
																													expr.bindAtomicValue(qname, value.toLowerCase(), it);
																												}
																											}else
																												if( "part3Material".equals(name) ){
																													QName qname = new QName("part3Material");
																													if	("".equals(value)){
																														expr.bindAtomicValue(qname, ".*", it);
																													}else{
																														expr.bindAtomicValue(qname, value.toLowerCase(), it);
																													}
																												}else
																													if( "part3Colour".equals(name) ){
																														QName qname = new QName("part3Colour");
																														if	("".equals(value)){
																															expr.bindAtomicValue(qname, ".*", it);
																														}else{
																															expr.bindAtomicValue(qname, value.toLowerCase(), it);
																														}
																													}else
																														System.err.println(name + " non trovato!");

	}



	/**
	 * 
	 * @param in0
	 * @return
	 * @throws RemoteException
	 */

	public String evaluateXQueryToHTML(String in0) throws RemoteException {
		// TODO Auto-generated method stub
		StringBuffer products = new StringBuffer(/*"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"*/);
		//System.err.println("in0: " + in0);
		String formInput = in0.replaceAll("\\+", " ");
		//System.err.println("formInput: " + formInput);
		try {
			//PATH DEL FILE CONTENENTE IL RISULTATO DEL RUN DI VISTA con smartOutput
			String xmlfile="/usr/share/tomcat/webapps/axis/WEB-INF/lib/vista/ikea-product.xml";
			XQDataSource xqds = new SaxonXQDataSource();
//			establish a connection to the XQuery engine
			XQConnection conn = xqds.getConnection();

			XQExpression expr = parsingAttributes(formInput,conn);

			String es = 
				"declare variable $name as xs:string external;" +
				"declare variable $type as xs:string external;" +
				"declare variable $category as xs:string external;" +
				"declare variable $material as xs:string external;" +
				"declare variable $colour as xs:string external;" +
				//interi
				"declare variable $MinPrice as xs:integer external;" +
				"declare variable $MaxPrice as xs:integer external;" +
				"declare variable $MinHeight as xs:integer external;" +
				"declare variable $MaxHeight as xs:integer external;" +
				"declare variable $MinWidth as xs:integer external;" +
				"declare variable $MaxWidth as xs:integer external;" +
				"declare variable $MinDepth as xs:integer external;" +
				"declare variable $MaxDepth as xs:integer external;" +
				"declare variable $MinDiameter as xs:integer external;" +
				"declare variable $MaxDiameter as xs:integer external;" +

				//parte 1
				"declare variable $part1Type as xs:string external;" +
				"declare variable $part1Material as xs:string external;" +
				"declare variable $part1Colour as xs:string external;" +
				//parte 2
				"declare variable $part2Type as xs:string external;" +
				"declare variable $part2Material as xs:string external;" +
				"declare variable $part2Colour as xs:string external;" +
				//parte 3
				"declare variable $part3Type as xs:string external;" +
				"declare variable $part3Material as xs:string external;" +
				"declare variable $part3Colour as xs:string external;" +

				"for $n in fn:doc('"+xmlfile+"')//product" +

				" where ( " + 
				" fn:matches(fn:upper-case($n/name), fn:upper-case($name)) and " +
				" fn:starts-with($n/type, $type) and " +
				" fn:matches($n/category, $category) and " +
				" fn:matches($n/material, $material) and " +
				" fn:matches($n/colour, $colour) )" +
				" and " +
				" (if((fn:index-of($n/price,'price'))>0 or $MinPrice>0 or $MaxPrice<10000) "+
				"then ($n/price >= $MinPrice       and $n/price <= $MaxPrice)  else (fn:true()) ) and " +  
//				" ($n/price >= $MinPrice       and $n/price <= $MaxPrice) and " +  
				" (if((fn:index-of($n/height,'height'))>0 or $MinHeight>0 or $MaxHeight<10000) "+
				"then ($n/height >= $MinHeight     and $n/height <= $MaxHeight)  else ( fn:true()) ) and " + 
//				" ($n/height >= $MinHeight     and $n/height <= $MaxHeight) and " + 
				" (if((fn:index-of($n/width,'width'))>0 or $MinWidth>0 or $MaxWidth<10000)"+
				" then ($n/width >= $MinWidth and $n/width <= $MaxWidth) else ( fn:true()) ) and " + 
//				" (if(fn:matches($n/width,'width')) then ($n/width >= $MinWidth and $n/width <= $MaxWidth) else (fn:true()) )and " +
				//" ($n/depth >= $MinDepth       and $n/depth <= $MaxDepth) and " + 
				" (if((fn:index-of($n/diameter,'diameter'))>0 or $MinDiameter>0 or $MaxDiameter<10000)" +
				" then ($n/diameter >= $MinDiameter and $n/diameter <= $MaxDiameter) else (fn:true()) )" + 
				" and "+
//				" fn:matches($n/part/value, $part1Type) and " +
//				" fn:matches($n/part/material, $part1Material) and " +
//				" fn:matches($n/part/colour, $part1Colour) " +

				//type della parte
				" (if((fn:index-of($n/part/value, $part1Type)>0) or (fn:index-of($n/part/value, $part2Type)>0) or (fn:index-of($n/part/value, $part3Type)>0) or" +
				" ( fn:compare($part1Type,\".*\")=0 and fn:compare($part2Type,\".*\")=0 and fn:compare($part3Type,\".*\")=0 ) )" +
				"then (fn:true()) else (fn:false())) and " +

				//material della parte
				" (if((fn:index-of($n/part/material, $part1Material)>0) or (fn:index-of($n/part/material, $part2Material)>0) or (fn:index-of($n/part/material, $part2Material)>0) or" +
				" ( fn:compare($part1Material,\".*\")=0 and fn:compare($part2Material,\".*\")=0 and fn:compare($part3Material,\".*\")=0 ) )" +
				"then (fn:true()) else (fn:false())) and " +

				//colour della parte
				" (if((fn:index-of($n/part/colour, $part1Colour)>0) or (fn:index-of($n/part/colour, $part2Colour)>0) or (fn:index-of($n/part/colour, $part3Colour)>0) or" +
				" ( fn:compare($part1Colour,\".*\")=0 and fn:compare($part2Colour,\".*\")=0 and fn:compare($part3Colour,\".*\")=0 ) )" +
				"then (fn:true()) else (fn:false()))  " +
				"order by $n/name" +
				" return <tr>" +
				"<td>{data($n/name)}</td>" +
				"<td>{data($n/type)}</td>"   +
				"<td>{data($n/category)}</td>" +
				"<td>{data($n/material)}</td>"  +
				"<td>{data($n/colour)}</td>"      +
				"<td>{data($n/price)}</td>"         +
				"<td>{data($n/width)}</td>"         +
				"<td>{data($n/height)}</td>"         +
				"<td>{data($n/depth)}</td>"         +
				"<td>{data($n/part/value)}</td>" +
				"<td>{data($n/part/material)}</td>" +
				"<td>{data($n/part/colour)}</td>" +
				"</tr>";

//			System.err.println(es);
//			execute the XQuery expression
			XQResultSequence result = expr.executeQuery(es);
//			process the result (sequence) iteratively

			products.append("<html>\n<body>\n<table border = '1'>\n<tr>" +
					"<td>Name</td>" +
					"<td>Type</td>" +
					"<td>Category</td>" +
					"<td>material</td>" +
					"<td>Colour</td>" + 
					"<td>Price</td>" + 
					"<td>Width</td>" + 
					"<td>Height</td>" + 
					"<td>Depth</td>" + 
					"<td colspan=3>Part</td>"  /*+
					"<td colspan=3>Part</td>"  +
					"<td colspan=3>Part</td>" */
			);

			while ( result.next() ){
//				retrieve the current item of the sequence as a String
				String str = result.getItemAsString();
//				System.err.println("str: "+str);
				products.append(str.substring(38));
				products.append("\n");

			}
			products.append("</tr></table>\n</body>\n</html>");

//			free all resources allocated for the result
			result.close();
//			free all resources allocated for the expression
			expr.close();
//			free all resources allocated for the connection
			conn.close();
			Utils.writeFile(products.toString(), "/home/simone/p.html");
		} catch (Exception e) {
			e.printStackTrace();
			products.append("</html>");
		}
		//System.err.println(products.toString());
		return products.toString();
	}



}
