/* $Id: Relation.java,v 1.4 2006/10/25 08:04:30 simone Exp $ */
package ilc.vikef;


/**
 * 
 * 
 */
public class Relation {

/**
 * <p>Represents ...</p>
 * 
 */
    private String name;

/**
 * <p>Represents ...</p>
 * 
 */
    private int subject;
    
    private String subjectName;

/**
 * <p>Represents ...</p>
 * 
 */
    private int object;
    private String objectName;

/**
 * <p>Represents ...</p>
 * 
 */
    private int data_id;

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
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return Returns the object.
	 */
	public int getObject() {
		return object;
	}
	
	/**
	 * @param object The object to set.
	 */
	public void setObject(int object) {
		this.object = object;
	}
	
	/**
	 * @return Returns the subject.
	 */
	public int getSubject() {
		return subject;
	}
	
	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(int subject) {
		this.subject = subject;
	}

public Relation(String relationName, int relationSubjectDataId, 
		String relationSubjectName, int relationObjectDataId, String relationObjectName, int dataId) {
	
	this.name = relationName;
	this.subject = relationSubjectDataId;
	this.object = relationObjectDataId;
	this.subjectName = relationSubjectName;
	this.objectName = relationObjectName;
	this.data_id = dataId;
}

/**
 * @return the objectName
 */
public String getObjectName() {
	return objectName;
}

/**
 * @param objectName the objectName to set
 */
public void setObjectName(String objectName) {
	this.objectName = objectName;
}

/**
 * @return the subjectName
 */
public String getSubjectName() {
	return subjectName;
}

/**
 * @param subjectName the subjectName to set
 */
public void setSubjectName(String subjectName) {
	this.subjectName = subjectName;
}
 }
