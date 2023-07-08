package ilc.vikef;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Logger;

public class VistaOutputParser {
	
   	static Logger logger = Logger.getLogger("ilc.vikef");  
	private static Vector entitiesV ;
	private static Vector relationsV ;
	private static Vector unlinkedEntitiesV ;
   	
   public static void parseVistaResult(String vistaResult){
	   try{
	   entitiesV = new Vector();
	   relationsV = new Vector();
	   unlinkedEntitiesV = new Vector();
    	//per prima cosa si spezza la stringa sul pipe ottenendo tutte le entita' e le relazioni
    	String[] entities = vistaResult.split(";");
    	logger.fine("*"+vistaResult+"*");
    	//logger.info("*"+entities[0]+"*");
    	HashMap entitiesCounter = new HashMap();
    	//per ogni elemento di result controllo se e' una entita' o una relazione
    	for (int i = 0; i < entities.length; i++) {
			String ent = entities[i];
			if (ent.startsWith("E:")){
				ent = ent.substring(2); //tolgo E:
				String[] splitEntity = ent.split("=");
				String entityName = (splitEntity[0]).replaceFirst("product_part", "part");
				String entityValue = splitEntity[1];
				if(entityValue.contains("__")){
					entityValue = entityValue.replaceAll("__", " ");
					entityValue = entityValue.replaceFirst("VERNICIARE","VERNICIATO");
					entityValue = entityValue.replaceFirst("RIVESTIRE","RIVESTITO");
					entityValue = entityValue.replaceFirst("TEMPRARE","TEMPRATO");
					entityValue = entityValue.replaceFirst("ESPANDERE","ESPANSO");
					entityValue = entityValue.replaceFirst("CROMARE","CROMATO");
					entityValue = entityValue.replaceFirst("GALVANIZZARE","GALVANIZZATO");
				}
				Entity entity = new Entity ( entityName, entityValue.toLowerCase(), DataId.getDataId() );
				logger.fine("E: " + entityName + ", " + entityValue + ", " + entity.getData_id());
				entitiesV.add(entity);
				Integer howmany = ( Integer ) entitiesCounter.get(entityValue.toLowerCase());
				//logger.info("entityValue : "+entityValue.toLowerCase());
				if(null != howmany){
					entitiesCounter.put(entityValue.toLowerCase(),new Integer(howmany.intValue() + 1 ) );
				}else{
					entitiesCounter.put(entityValue.toLowerCase(),new Integer(1));
				}
			}else if (ent.startsWith("R:")){
				ent = ent.substring(2); //tolgo R:
				if(ent.contains("__")){
					ent = ent.replaceAll("__", " ");
					ent = ent.replaceFirst("VERNICIARE","VERNICIATO");
					ent = ent.replaceFirst("RIVESTIRE","RIVESTITO");
					ent = ent.replaceFirst("TEMPRARE","TEMPRATO");
					ent = ent.replaceFirst("ESPANDERE","ESPANSO");
					ent = ent.replaceFirst("CROMARE","CROMATO");
					ent = ent.replaceFirst("GALVANIZZARE","GALVANIZZATO");
				}

				String[] splitEntity = ent.split("=");
				String[] domains = splitEntity[1].split(",");
				String relationName = new String(splitEntity[0]);
				String nameDomain = (new String(domains[0])).toLowerCase();
				String nameRange  = (new String(domains[1])).toLowerCase();
//				int domainId = findEntityId(entitiesV,nameDomain);
				logger.fine("domain : "+nameDomain + ", " +( ( Integer ) entitiesCounter.get(nameDomain)));
				int domainId = findEntityId(entitiesV,nameDomain, ( ( Integer ) entitiesCounter.get(nameDomain)).intValue());
				int rangeId = findEntityId(entitiesV,nameRange);
				Relation relation = new Relation(relationName, domainId, nameDomain ,rangeId, nameRange, DataId.getDataId());
				logger.info("R: "+relationName+", "+domainId+"-"+nameDomain+", "+rangeId+"-"+nameRange+" R: "+relation.getData_id());
				relationsV.add(relation);
			}
		}
    	//cerco le entita' che non fanno parte di relazioni
    	findUnlinkedEntities();
	   }catch (Exception e) {
		   e.printStackTrace();
	}
    }
    
    private static int findEntityId(Vector entities, String name, int i) {
		for (Iterator iter = entities.iterator(); iter.hasNext();) {
			Entity element = (Entity) iter.next();
			//System.out.println("*"+element.getValue()+"* vs. *"+name+"*");
			if(name.equals(element.getValue())){
				i--;
				if(0 == i){
					logger.info("Questo e' quello giusto : " + element.getData_id());
					return element.getData_id();
				}
			}
		}
		logger.severe("*non trovato id per: *"+name+"*");
		return -1;
}

	/*
     * Dato un vector di Entity e il nome della entity restituisce il suo id, se esiste, altrimenti -1
     */
	public static int findEntityId (Vector entities, String name){
		for (Iterator iter = entities.iterator(); iter.hasNext();) {
			Entity element = (Entity) iter.next();
			//System.out.println("*"+element.getValue()+"* vs. *"+name+"*");
			if(name.equals(element.getValue()))
				return element.getData_id();
		}
		logger.severe("*non trovato id per: *"+name+"*");
		return -1;
	}

	private static void findUnlinkedEntities(){
		//per ogni entita' in entitiesV
		boolean found;
		for (Iterator iter = entitiesV.iterator(); iter.hasNext();) {
			Entity entity = (Entity) iter.next();
			int elementId = entity.getData_id();
			found = false;
			//logger.fine("findUnlinkedEntities(): "+entity.getName()+", "+entity.getValue()+", "+entity.getData_id());
			if(!entity.getName().equals("part")){
				for (Iterator iterV = relationsV.iterator(); iterV.hasNext();) {
					Relation relation = (Relation) iterV.next();
					if((relation.getObject() == elementId) || (relation.getSubject() == elementId)){
						found = true;
					}else{
						//logger.warning("Parte? "+entity.getName()+"  "+entity.getValue());
					}
				}
			}/*else{
				logger.warning("Parte? "+entity.getName()+"  "+entity.getValue());
			}*/
			if (!found){
				unlinkedEntitiesV.add(entity);
			}
			
		}
	}
	/**
	 * @return Returns the entitiesV.
	 */
	public static Vector getEntitiesV() {
		return entitiesV;
	}

	/**
	 * @param entitiesV The entitiesV to set.
	 */
	public static void setEntitiesV(Vector entitiesV) {
		VistaOutputParser.entitiesV = entitiesV;
	}

	/**
	 * @return Returns the relationsV.
	 */
	public static Vector getRelationsV() {
		return relationsV;
	}

	/**
	 * @param relationsV The relationsV to set.
	 */
	public static void setRelationsV(Vector relationsV) {
		VistaOutputParser.relationsV = relationsV;
	}

	/**
	 * @return Returns the unlinkedEntitiesV.
	 */
	public static Vector getUnlinkedEntitiesV() {
		return unlinkedEntitiesV;
	}

	/**
	 * @param unlinkedEntitiesV The unlinkedEntitiesV to set.
	 */
	public static void setUnlinkedEntitiesV(Vector unlinkedEntitiesV) {
		VistaOutputParser.unlinkedEntitiesV = unlinkedEntitiesV;
	}


}
