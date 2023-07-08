/* $Id: RegexManager.java,v 1.22 2006/11/08 08:54:41 simone Exp $ */
package ilc.vikef;

import ilc.nlp.wrapper.NLPTools;
import ilc.nlp.wrapper.T2CMapper;
import ilc.nlp.wrapper.Vista;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 
 * 
 */
public class RegexManager {

	static int j=0;
   	Logger logger = Logger.getLogger("ilc.vikef");    	
static int count =0;
    private Pattern regex;
/**
 * 
 * 
 * 
 * @poseidon-type GroupSchema
 */
    //public java.util.Collection groupSchemata = new java.util.Vector();
    public java.util.Vector groupSchemata = new java.util.Vector();
/**
 * 
 * 
 * 
 * @poseidon-type GroupRelationSchema
 */
    //public java.util.Collection groupRelationSchemata = new java.util.Vector();
    public java.util.Vector groupRelationSchemata = new java.util.Vector();

    
 
    /**
     * <ul type="disc">
     * <li>&nbsp;valuta regex su text, se non matcha si ritorna null,</li>
     * <li>se matcha:</li>
     * <li>1- creo un product</li>
     * <li>
     * 2- per ogni groupSchema:
     * <ul type="disc">
     * <li>se isNlpProcessable: lancio VISTA cercando entit&agrave; e relazioni e creando i relativi oggetti</li>
     * <li>
     * altrimenti creo oggetto Entity e lo riempio con
     * <ul type="disc">
     * <li>groupName --&gt; name</li>
     * <li>group(groupIndex) --&gt; value</li>
     * <li>dataId.getDataId --&gt; data_id</li>
     * </ul>
     * </li>
     * </ul>
     * </li>
     * <li>
     * 3- per ogni GroupRelationSchema:
     * <ul type="disc">
     * <li>
     * costruisco l'oggetto Relation e lo riempio con:
     * <ul type="disc">
     * <li>groupRelationName --&gt; name</li>
     * <li>cerco il data_id dell'entity con nome groupSubjectName --&gt; subject</li>
     * <li>cerco il data_id dell'entity con nome groupObjectName --&gt; object</li>
     * <li>dataId.getDataId --&gt; data_id</li>
     * </ul>
     * </li>
     * </ul>
     * </li>
     * </ul>
     * <p>&nbsp;&nbsp;&nbsp;</p>
     * 
     * 
     * @param text		 
     * @return 
     */
    
    public void setPattern(String regularExpression) {
    	logger.warning("compilo regola: "+regularExpression);
    	regex = Pattern.compile(regularExpression,Pattern.COMMENTS); //messo COMMENTS per ignorare gli whitespace
    	/*
    	String text = Utils.readFromFile("testo.txt");
    	logger.info("text: "+text);
    	Matcher matcher = regex.matcher(text);
    	logger.info("matcher: "+matcher);
    	while (matcher.find()) {
    		System.err.println("stampo gruppo 0: "+matcher.group(0));
    	}
    	*/
    	
    }
    
    public void setGroupSchemata(java.util.Vector groupSchemataVector) {
    	groupSchemata = groupSchemataVector;
    }
    
    public void setGroupRelationSchemata(java.util.Vector groupRelationSchemataVector) {
    	groupRelationSchemata = groupRelationSchemataVector;
    }
    
    public Product evaluate(String text) {        
 count++;
        /*
    	//text = text.replaceAll("\r\n","");
    	//text = text.trim();
    	logger.info("text: "+text);
    	
    	logger.info("matcher: "+matcher);
    	while (matcher.find()) {
    		System.err.println("stampo gruppo 0: "+matcher.group(0));
    	}
    	*/
    	Product product = null;
    	
    	//logger.info("text: "+text);
    	//text = text.replaceAll("\r\n","");
    	//text = text.trim();
    	//System.err.println(text);
    	Matcher matcher = this.regex.matcher(text);
    	
    	if (matcher.find()) {

    		/*logger.warning("espressione regolare matchata: "+this.regex);
    		logger.warning("pattern matchato: "+matcher.group(0));*/ 
    		
    		//il pattern matcha: creo un product
    		product = new Product();
    		//assegno al product la sua description, cioe' l'intero match
    		product.setDescription(matcher.group());
    		//verifico che il numero dei GroupSchema sia uguale al numero dei gruppi del match
    		/*
    		if (this.groupSchemata.size() != matcher.groupCount()+1) {
    			logger.severe("ERRORE: il numero dei GroupSchema non e' uguale al numero dei gruppi del match");
    			return null;
    		}
    		*/
    		
    		//CREAZIONE DELLE ENTITY DEL PRODOTTO
    		//scorro tutto il vettore groupSchemata
    		//logger.info("vettore groupSchemata lungo: "+this.groupSchemata.size());
    		GroupSchema groupSchema;
    		String groupName;
    		String groupValue;
    		String textNlp;
    		for (int i=0; i<this.groupSchemata.size(); i++) {
    			//estraggo il groupSchema di indice i
    			groupSchema = (GroupSchema)this.groupSchemata.elementAt(i);
    			//leggo tutte le informazioni relative al GroupSchema estratto
    			int groupIndex = groupSchema.getGroupIndex();
    			//logger.info("groupIndex: "+groupIndex);
    			groupName = groupSchema.getGroupName();
    			//logger.warning("groupName: "+groupName);
    			boolean isNlpProcessable = groupSchema.isNlpProcessable();
    			//logger.info("isNlpProcessable: "+isNlpProcessable);
    			//se il gruppo non e' NlpProcessable procedo con pattern matching estraendo il suo valore dal match
    			if (!isNlpProcessable) {
    				//logger.info("caso not NLP..., i = "+i);
    				//prendo il contenuto del gruppo numero groupIndex dal match
    				groupValue = matcher.group(groupIndex);
    				if(null == groupValue){
    					continue;
    				}
    				groupValue = groupValue.replaceAll("\r\n","");
    				groupValue = groupValue.trim();
    				//genero un nuovo dataId
    				int dataId = DataId.getDataId();
    				
    				//costruisco la entity da aggiungere al prodotto
    				//logger.warning("new Entity: "+groupName +"  " +groupValue + " "+dataId);
    				Entity entity = new Entity(groupName, groupValue, dataId);
    				//aggiungo la Entity al prodotto
    				//logger.warning("aggiungo entity: "+entity.getValue() +" id: " +entity.getData_id());
    				//aggiungo all'entity gli offset
    				//to do...
    				product.addEntity(entity);
    				//logger.info("numero entita' nel prodotto: "+product.getNumberOfEntities());
    			}
    			else {
    				//logger.info("nlp...");
    				groupValue = matcher.group(groupIndex);
    				if(null == groupValue){
    					continue;
    				}
    				//logger.info("to NLP: "+groupIndex+": "+groupValue);
    				try {

    					Utils.deleteDirectory(Config.tempOutDir);
    					textNlp = new String(groupValue.getBytes(),"ISO-8859-1");
    					//               testo da parsare, nome file output
    					//textNlp.concat(".");
    					logger.fine("A VISTA: "+textNlp);
/*    					
    					NLPTools.runTools(textNlp.concat("."),Config.tempOutDir+"output"+"_"+count+".txt");
    					//System.err.println("T2CMapper.runTools "+Config.tempOutDir+"output.txt.chug.ide,  "+Config.tempOutDir+"output.txt");
    					T2CMapper.runTools(Config.tempOutDir+"output"+"_"+count+".txt.chug.ide",Config.tempOutDir+"output"+"_"+count+".txt");
    					String resultVista = Vista.run(Config.tempOutDir+"output.txt.t2c",0);
 */
    					NLPTools.runTools(textNlp.concat("."),Config.tempOutDir+"output.txt");
    					//System.err.println("T2CMapper.runTools "+Config.tempOutDir+"output.txt.chug.ide,  "+Config.tempOutDir+"output.txt");
    					T2CMapper.runTools(Config.tempOutDir+"output.txt.chug.ide",Config.tempOutDir+"output.txt");
    					String resultVista = Vista.run(Config.tempOutDir+"output.txt.t2c",0);
	    				//System.err.println("da Vista: *"+resultVista+"*");
	    				if(null != resultVista){
	    					if(resultVista.trim().length()>0){
	    						VistaOutputParser.parseVistaResult(resultVista);
	    						product.addEntities(VistaOutputParser.getEntitiesV());
	    						product.addRelations(VistaOutputParser.getRelationsV());
	    						//mancano le relazioni che legano il product con eventuali entity
	    						//individuate da vista.
	    						//Per ogni entity che NON e' in relazione con altre entita' individuate da Vista
	    						//si deve creare una relazione tra il product e la relazione stessa.
	    						product.addRelations(findAdditionalRelation(VistaOutputParser.getUnlinkedEntitiesV(),product.getEntityDataId("product")));
	    					}
	    				}
	    				textNlp = null;
	    				j++;
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				//TO DO!!!!
    			}
    		}
    		//creazione della entity "category"
			int dataId = DataId.getDataId();
    		Entity entity = new Entity("category", null, dataId);
    		product.addEntity(entity);
    		
    		
    		//CREAZIONE DELLE RELAZIONI DEL PRODOTTO
    		//scorro tutto il vettore groupRelationSchemata
    		//logger.info("vettore groupRelationSchemata lungo: "+this.groupRelationSchemata.size());
    		for (int i=0; i<this.groupRelationSchemata.size(); i++) {
    			//estraggo il groupRelationSchema di indice i
    			GroupRelationSchema groupRelationSchema = (GroupRelationSchema)this.groupRelationSchemata.elementAt(i);
    			//leggo tutte le informazioni relative al GroupRelationSchema estratto
    			String relationName = groupRelationSchema.getGroupRelationName();
    			String relationSubject = groupRelationSchema.getGroupSubjectName();
    			String relationObject = groupRelationSchema.getGroupObjectName();
    			
    			int relationSubjectDataId = product.getEntityDataId(relationSubject);
    			int relationObjectDataId = product.getEntityDataId(relationObject);
    			String relationSubjectValue = product.getEntityValue(relationSubject);
    			String relationObjectValue = product.getEntityValue(relationObject);
    		    dataId = DataId.getDataId();
    		    //costruisco la relation da aggiungere al prodotto
    		    //logger.warning("relationName: "+relationName+" relationSubjectValue: " + relationSubjectValue + " relationObjectValue: "+relationObjectValue);
    		    Relation relation = new Relation(relationName, relationSubjectDataId, 
    		    		relationSubjectValue, relationObjectDataId,relationObjectValue, dataId);
	    	    //aggiungo la Relation al prodotto
		        product.addRelation(relation);
    			
    			
    		}
    		
    		
    		//aggiunta delle relazioni individuate da VISTA
    		//TO DO!!!
    
		
        }
    	else {
    		//logger.info("espressione NON MATCHATA: "+this.regex);
    	}
    	
	   	//logger.info("ritorno product: "+product.getDescription());        
		return product;
    }

    /*
     * dato un vettore di Entity che non sono linkate a relazioni, crea un vettore di relazioni
     * che legano le entity date con il prodotto. E' necessario avere la hashmap type->relation 
     * (es. material->made_of) per poter creare la relazione tra l'entita' e il prodotto
     */
	private Vector findAdditionalRelation(Vector unlinkedEntities, int productId) {
		Vector additionalRelations = new Vector();
		for (Iterator iter = unlinkedEntities.iterator(); iter.hasNext();) {
			Entity element = (Entity) iter.next();
			String type = element.getName();//prendo il type della entity
			int entityId = element.getData_id();
			String relName = (String) Config.relTypeAssoc.get(type); //prendo il nome della relazione associata
			if(null == relName){
				//logger.warning("Relation "+type+" not found!");
				continue;//relazione non trovata!
			}
			Relation rel = new Relation(relName, entityId, element.getName(), 
											productId, null,DataId.getDataId());
			additionalRelations.add(rel);
		}
		return additionalRelations;
		
	} 
    
    

    
 }
