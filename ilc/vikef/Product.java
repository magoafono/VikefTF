/* $Id: Product.java,v 1.17 2006/11/08 08:54:41 simone Exp $ */
package ilc.vikef;

import java.util.Vector;
import java.util.logging.Logger;
import java.io.*;

/**
 * 
 * 
 */
public class Product {
	Logger logger = Logger.getLogger("ilc.vikef");    	

	/**
	 * <p>description rappresenta il testo matchato, che il main dovr&agrave; togliere dal paragraph</p>
	 * 
	 */
	private String description;
	/**
	 * 
	 * 
	 * 
	 * @poseidon-type Entity
	 */


	private String paragraphId;

	private String entryLevelUrl;

	private String entryLevelIds;

	private String category;


	public Vector entities = new java.util.Vector();
	/**
	 * 
	 * 
	 * 
	 * @poseidon-type Relation
	 */
	public Vector relations = new java.util.Vector();

	/**
	 * <p>Represents ...</p>
	 * 
	 * 
	 * @param relation 
	 */
	public void addRelation(Relation relation) {        
		this.relations.add(relation);
	} 


	public void addEntities(Vector entities) {        
		this.entities.addAll(entities);

	} 

	public void addRelations(Vector relations) {        
		this.relations.addAll(relations);
	} 

	/**
	 * <p>Does ...</p>
	 * 
	 * 
	 * @param entity 
	 */
	public void addEntity(Entity entity) {        
		this.entities.add(entity);

	} 

	/**
	 * 
	 * @return 
	 */
	public String getDescription() {        
		return description;
	} 

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getParagraphId() {
		return paragraphId;
	}

	public void setParagraphId(String paragraphId) {
		this.paragraphId = paragraphId;
	}

	public String getEntryLevelURL() {
		return entryLevelUrl;
	}

	public void setEntryLevelURL(String entryLevelUrl) {
		this.entryLevelUrl = entryLevelUrl;
	}

	public String getEntryLevelIds() {
		return entryLevelIds;
	}

	public void setEntryLevelIds(String entryLevelIds) {
		this.entryLevelIds = entryLevelIds;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}




	public int getNumberOfEntities() {
		return this.entities.size();
	}



	/**
	 * <p>Does ...</p>
	 * 
	 * 
	 * @return 
	 */
	 public String printXML(BufferedWriter bw) {        
		 int i;
		 Entity e;
		 Relation r;
		 try {

			 //stampa delle entità presenti nella descrizione del prodotto
			 for (i=0; i<this.entities.size();i++) {
				 e = (Entity) this.entities.elementAt(i);

				 //caso category
				 if ("category".equals(e.getName())) {
					 if(null != category)
						 e.setValue(category.replaceAll("&","&amp;"));
				 }
				 /*	
	    		System.err.println("<ExtractedData>");
	    	    System.err.println("\t<entity data_id=\""+e.getData_id()+"\">");
	    		System.err.println("\t\t<"+e.getName()+">"+e.getValue()+"</"+e.getName()+">");
	    		System.err.println("\t</entity>");
	    		System.err.println("</ExtractedData>");
				  */
				 bw.write("\n<ExtractedData");
				 bw.write(" Id=\""+this.getParagraphId()+"\" ");
				 bw.write("Entry_level_url=\""+this.getEntryLevelURL().replaceAll("&","&amp;")+"\" ");
				 bw.write("loffset=\"\" ");
				 bw.write("roffset=\"\" ");
				 bw.write("Entry_level_ids=\""+this.getEntryLevelIds()+"\">");
				 bw.write("\n\t<entity data_id=\""+e.getData_id()+"\">");
				 bw.write("\n\t\t<"+e.getName()+">"+e.getValue()+"</"+e.getName()+">");
				 bw.write("\n\t</entity>");
				 bw.write("\n</ExtractedData>");
			 }

			 //stampa delle relazioni presenti nella descrizione del prodotto
			 for (i=0; i<this.relations.size();i++) {
				 r = (Relation) this.relations.elementAt(i);
				 /*
	    		System.err.println("<ExtractedData>");
	    		System.err.println("\t<relation data_id=\""+r.getData_id()+"\">");
	    		System.err.println("\t\t<reltype>"+r.getName()+"</reltype>");
	    		System.err.println("\t\t<subject>"+r.getSubject()+"</subject>");
	    		System.err.println("\t\t<object>"+r.getObject()+"</object>");
	    		System.err.println("\t</relation>");
	    		System.err.println("</ExtractedData>");
				  */
				 bw.write("\n<ExtractedData");
				 bw.write(" Id=\""+this.getParagraphId()+"\" ");
				 bw.write("Entry_level_url=\""+this.getEntryLevelURL().replaceAll("&","&amp;")+"\" ");
				 bw.write("Entry_level_ids=\""+this.getEntryLevelIds()+"\">");
				 bw.write("\n\t<relation data_id=\""+r.getData_id()+"\">");
				 bw.write("\n\t\t<reltype>"+r.getName()+"</reltype>");
				 bw.write("\n\t\t<subject>"+r.getSubject()+"</subject>");
				 bw.write("\n\t\t<object>"+r.getObject()+"</object>");
				 bw.write("\n\t</relation>");
				 bw.write("\n</ExtractedData>");	    		

			 }

		 }
		 catch (IOException ioe) {
			 ioe.printStackTrace();
		 }

		 return null;
	 } 

	 public StringBuffer printXML(StringBuffer sb) {

		 Entity e;
		 Relation r;
		 //logger.info("1 ");
		 try {

			 for (int i=0; i<this.entities.size();i++) {
				 e = (Entity) this.entities.elementAt(i);

				 //caso category
				 if ("category".equals(e.getName())) {
					 if(null != category)
						 e.setValue(category.replaceAll("&","&amp;"));
				 }

				 sb.append("\n<ExtractedData");
				 sb.append(" Id=\""+this.getParagraphId()+"\" ");
				 sb.append("Entry_level_url=\""+this.getEntryLevelURL().replaceAll("&","&amp;")+"\" ");
				 sb.append("loffset=\"\" ");
				 sb.append("roffset=\"\" ");
				 sb.append("Entry_level_ids=\""+this.getEntryLevelIds()+"\">");
				 sb.append("\n\t<entity data_id=\""+e.getData_id()+"\">");
				 if( e.getValue()!= null){
					 sb.append("\n\t\t<"+e.getName()+">"+e.getValue().replaceAll("£"," ")+"</"+e.getName()+">");
				 }else{
					 logger.fine("entity " + e.getName()+ " ha value nullo!");
				 }
				 sb.append("\n\t</entity>");
				 sb.append("\n</ExtractedData>");
			 }
			 for (int i=0; i<this.relations.size();i++) {
				 r = (Relation) this.relations.elementAt(i);
				 sb.append("\n<ExtractedData");
				 sb.append(" Id=\""+this.getParagraphId()+"\" ");
				 sb.append("Entry_level_url=\""+this.getEntryLevelURL().replaceAll("&","&amp;")+"\" ");
				 sb.append("Entry_level_ids=\""+this.getEntryLevelIds()+"\">");
				 sb.append("\n\t<relation data_id=\""+r.getData_id()+"\">");
				 sb.append("\n\t\t<reltype>"+r.getName()+"</reltype>");
				 sb.append("\n\t\t<subject>"+r.getSubject()+"</subject>");
				 sb.append("\n\t\t<object>"+r.getObject()+"</object>");
				 sb.append("\n\t</relation>");
				 sb.append("\n</ExtractedData>");	    		

			 }
			} catch (Exception ee) {
				logger.severe("Error!  " + ee.getMessage());
			}

		 return sb;

	 }

	 /**
	  * Stampa le informazioni catturate per ogni product in XML.
	  * La struttura XML delle informazioni e' ragguppata per poter essere parsata
	  * con XQuery (o linguaggio simile)
	  *  
	  * @param sb
	  * @return
	  */
	 public StringBuffer printSmartXML(StringBuffer sb) {

		 Entity anEntity;
		 Relation rel;

		 /*
		   <product>
		   		<name>BRATTBY</name>
		   		<type>scaffale</type>
		   		<material>birch</material>
		   		<length>140</length>
		   		<width>23</width>
		   		<height>140</height>
		   		<id>800.993.99</id>
		   		<category>null</category>
		   		...
		   </product>
		  */
		 sb.append("\n<product id = \""+ getParagraphId() +"\" >");
		 int part = 3;
		 boolean productMaterial = false;
		 boolean partMaterial = false;
		 boolean productColour = false;
		 boolean partColour = false;


		 for (int index = 0; index < entities.size(); index++) {
			 anEntity = (Entity) entities.elementAt(index);
			 if( anEntity == null){
//				 logger.severe("entity null");
				 continue;
			 }
//			 logger.severe("entity: "+anEntity.getName()+ " "+anEntity.getValue());
			 //caso category
			 if ("category".equals(anEntity.getName())) {
				 if(null != category){
					 anEntity.setValue(category.replaceAll("&","&amp;"));
					 sb.append("\n\t<"+anEntity.getName()+">"+anEntity.getValue()+"</"+anEntity.getName()+">");
				 }
			 } else {
//				 if(!"product".equals(e.getName())){
				 if(!"part".equals(anEntity.getName())){
					 if ( "material".equals(anEntity.getName()) ){
						 //Devo stampare solo il primo material, altrimenti la Xquery mi da il seguente errore:
						 //XPTY0004: A sequence of more than one item is not allowed as the first argumen  t of
						//  fn:matches() ("acciaio inox", "plastica")
						 if(!productMaterial)
							 sb.append("\n\t<"+anEntity.getName()+">"+anEntity.getValue()+"</"+anEntity.getName()+">");
						 productMaterial = true;
					 }else	
						 if ( "colour".equals(anEntity.getName())){
							 //Devo stampare solo il primo colour altrimenti ho lo stesso errore che per material
							 if( !productColour )
								 sb.append("\n\t<"+anEntity.getName()+">"+anEntity.getValue()+"</"+anEntity.getName()+">");
							 productColour = true;
						 }else
							 if("product".equals(anEntity.getName()))
								 sb.append("\n\t<content>"+anEntity.getValue().replaceAll("£", " ")+"</content>");
							 else{
								 sb.append("\n\t<"+anEntity.getName()+">"+anEntity.getValue()+"</"+anEntity.getName()+">");
							 }
				 }else{
//					 System.err.println(index + " " + anEntity.getName());
					 part--;
					 //// cerca la relazione di cui e.getData_id() e' dominio (es. e.getValue() e' Ripiano
					 //allora prendo e.getData_id() cerco tutte le relazioni di cui
					 sb.append("\n\t<part>");
					 sb.append("\n\t\t<value>"+anEntity.getValue()+"</value>");
					 //rimuovo la parte dalle entities per evitare ripetizioni.
					 int toRemove = index;
					 for (int j=0; j < relations.size();j++) {
						 rel = (Relation) relations.elementAt(j);
						 if ( rel.getSubject() == anEntity.getData_id() ){
							 //ho trovato una relazione che ha questa parte come dominio
	//						 logger.warning("Rel: "+rel.getName() +"(" +rel.getSubjectName() + "," +rel.getObjectName()+ ")  "+rel.getObject());
							 Entity ent = getEntityByID(rel.getObject());
							 if ( ent == null ){
//							 logger.warning("Rel: "+rel.getName() +"(" +rel.getSubjectName() + "," +rel.getObjectName()+ ") => ent non esiste");
								 continue;
							 }
							 //rimuovo la entity trovata dal vettore di entities xche' ci sono
							 //problemi di ripetioni nell'output
//							 System.err.println("Rimuovo2 ("+entities.indexOf(ent)+") "+ent.getName() + " "+ent.getValue());
							 entities.set(entities.indexOf(ent),null);
							 
							 //entities.remove(ent);
							 /* 
							  * tolta una entity devo stare attento che riduco la dimensione del Vector entities, quindi tolgo un passo
							  * all'iteratore 
							  */
							 //index--;
							 if(rel.getObjectName() != null){
								 if ( "material".equals(ent.getName()) ){
									 //Qui, a differenza da
									 if( !partMaterial )
										 sb.append("\n\t\t<"+ent.getName()+">"+rel.getObjectName()+"</"+ent.getName()+">");
									// partMaterial = true;
								 }else
									 if ( "colour".equals(ent.getName())  ){
										 if( !partColour )
											 sb.append("\n\t\t<"+ent.getName()+">"+rel.getObjectName()+"</"+ent.getName()+">");
										// partColour = true;
									 }else
										 sb.append("\n\t\t<"+ent.getName()+">"+rel.getObjectName()+"</"+ent.getName()+">");
							 }
						 }
					 }
					 sb.append("\n\t</part>");
				//	 System.err.println("Rimuovo (" + toRemove + ") " + ((Entity)entities.elementAt(toRemove)).getName() + " " + ((Entity)entities.elementAt(toRemove)).getValue() );
					// entities.remove(anEntity);
					 entities.set(toRemove, null);

				 }
//				 }//!product
			 }
		 }
		 for (int j = 0; j < part; j++){
			 sb.append("\n\t<part>");
			 sb.append("\n\t</part>");
		 }

		 sb.append("\n</product>");

		 return sb;

	 }
	 /**
	  * Restituisce il data_id della entità di nome entityName
	  * 
	  * @param entityName
	  * @return
	  */
	 public int getEntityDataId(String entityName) {

		 Logger logger = Logger.getLogger("ilc.vikef");
		 //logger.info("cerco entityName: "+entityName+" tra "+entities.size()+" elementi");
		 Entity entity;
		 //scorro tutto il vettore delle entità per trovare quella con nome entityName
		 int i = 0;
		 while (i < entities.size()) {
			 entity = (Entity) this.entities.elementAt(i);
			 if ((entity.getName()).equals(entityName)) {
				 //logger.info("trovato id della entità "+entityName+": "+entity.getData_id());
				 return entity.getData_id();
			 }
			 else
				 i++;	
		 }
		 //il nome non è stato trovato: errore!
		 logger.info(entityName+" non trovato tra le entity del product!");
		 return -1;
	 }

	 public String getEntityValue(String entityName) {

		 Logger logger = Logger.getLogger("ilc.vikef");
		 //logger.info("cerco entityName: "+entityName+" tra "+entities.size()+" elementi");
		 Entity entity;
		 //scorro tutto il vettore delle entità per trovare quella con nome entityName
		 int i = 0;
		 while (i < entities.size()) {
			 entity = (Entity) this.entities.elementAt(i);
			 if ((entity.getName()).equals(entityName)) {
				 //logger.info("trovato value della entità "+entityName+": "+entity.getValue());
				 return entity.getValue();
			 }
			 else
				 i++;	
		 }
		 //il nome non è stato trovato: errore!
		 logger.info(entityName+" non trovato tra le entity del product!");
		 return null;
	 }

	 public Entity getEntityByID(int id) {

		 Logger logger = Logger.getLogger("ilc.vikef");
		 //logger.info("cerco entityName: "+entityName+" tra "+entities.size()+" elementi");
		 Entity entity;
		 //scorro tutto il vettore delle entità per trovare quella con nome entityName
		 int i = 0;
		// logger.warning("Entrato con "+ id);
		 while (i < entities.size()) {
			 entity = (Entity) this.entities.elementAt(i);
			 if(entity == null) {
				// logger.warning("\t trovato null");
				i++;
				continue;
			 }
			 if (entity.getData_id() == id) {
				// logger.warning("trovato value della entità "+ id +": "+entity.getValue());
				 return entity;
			 }
			 else{
					//   logger.warning("\t entità "+ id +": "+entity.getData_id() +" " + entity.getValue());
				 i++;
			 }
		 }
		 //il nome non è stato trovato: errore!
		 logger.fine(id + " non trovato tra le entity del product!");
		 return null;
	 }

}
