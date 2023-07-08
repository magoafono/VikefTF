/* $Id: GroupRelationSchema.java,v 1.3 2006/05/09 08:35:01 simone Exp $ */
package ilc.vikef;


/**
 * 
 * 
 */
public class GroupRelationSchema {

/**
 * <p>Represents ...</p>
 * 
 */
    private String groupRelationName;

/**
 * <p>Represents ...</p>
 * 
 */
    private String groupSubjectName;

/**
 * <p>Represents ...</p>
 * 
 */
    private String groupObjectName;

    
    public GroupRelationSchema(String groupRelationName, String groupSubjectName, String groupObjectName) {
    	this.groupRelationName = groupRelationName;
    	this.groupSubjectName = groupSubjectName;
    	this.groupObjectName = groupObjectName;
    }
    
    
/**
 * @return Returns the groupObjectName.
 */
public String getGroupObjectName() {
	return groupObjectName;
}

/**
 * @param groupObjectName The groupObjectName to set.
 */
public void setGroupObjectName(String groupObjectName) {
	this.groupObjectName = groupObjectName;
}

/**
 * @return Returns the groupRelationName.
 */
public String getGroupRelationName() {
	return groupRelationName;
}

/**
 * @param groupRelationName The groupRelationName to set.
 */
public void setGroupRelationName(String groupRelationName) {
	this.groupRelationName = groupRelationName;
}

/**
 * @return Returns the groupSubjectName.
 */
public String getGroupSubjectName() {
	return groupSubjectName;
}

/**
 * @param groupSubjectName The groupSubjectName to set.
 */
public void setGroupSubjectName(String groupSubjectName) {
	this.groupSubjectName = groupSubjectName;
}
 }
