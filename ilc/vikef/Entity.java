/* $Id: Entity.java,v 1.5 2006/10/27 15:49:42 simone Exp $ */
package ilc.vikef;


/**
 * 
 * 
 */
public class Entity {
	
	/**
	 * <p>Represents ...</p>
	 * 
	 */
	private String name; 
	
	/**
	 * <p>Represents ...</p>
	 * 
	 */
	private String value = ""; //forzato x evitare il NullPointer sulla risposta
	
	/**
	 * <p>Represents ...</p>
	 * 
	 */
	private int data_id;
	
	private int leftOffSet;

	private int rightOffSet;
	/**
	 * <p>Does ...</p>
	 * 
	 * 
	 * @return 
	 */
	public String getName() {        
		// your code here
		return name;
	}
	
	/**
	 * @return Returns the data_id.
	 */
	public int getData_id() {
		return data_id;
	}
	
	/**
	 * @param data_id The data_id to set.
	 */
	public void setData_id(int data_id) {
		this.data_id = data_id;
	}
	
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * @param value The value to set.
	 */
	public void setValue(String _value) {
	//	System.err.println("value: "+ _value);
		this.value = _value.replaceAll("([0-9]+),([0-9]+)","$1.$2"); //per cambiare la virgola in punto nei prezzi
		value = value.replaceAll("([0-9]+)\u2013([0-9]+)","$1");// x roba tipo 68-80 si prende solo 68
		
	}
	
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	} 
	
	public Entity(String entityName, String entityValue, int dataId) {
		this.name = entityName;
		if(entityValue != null){
			this.value = entityValue.replaceAll("([0-9]+),([0-9]+)","$1.$2");
			this.value = this.value.replaceAll("([0-9]+)\u2013([0-9]+)","$1");// x roba tipo 68-80 si prende solo 68
		}else{
			this.value = null;
		}
		this.data_id = dataId;
	}

	/**
	 * @return Returns the leftOffSet.
	 */
	public int getLeftOffSet() {
		return leftOffSet;
	}

	/**
	 * @param leftOffSet The leftOffSet to set.
	 */
	public void setLeftOffSet(int leftOffSet) {
		this.leftOffSet = leftOffSet;
	}

	/**
	 * @return Returns the rightOffSet.
	 */
	public int getRightOffSet() {
		return rightOffSet;
	}

	/**
	 * @param rightOffSet The rightOffSet to set.
	 */
	public void setRightOffSet(int rightOffSet) {
		this.rightOffSet = rightOffSet;
	}
	
}
