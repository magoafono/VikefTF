/* $Id: GroupSchema.java,v 1.3 2006/05/09 08:35:01 simone Exp $ */
package ilc.vikef;


/**
 * 
 * 
 */
public class GroupSchema {

/**
 * <p>Represents ...</p>
 * 
 */
    private String groupName;

/**
 * <p>Represents ...</p>
 * 
 */
    private int groupIndex;

/**
 * <p>Represents ...</p>
 * 
 */
    private boolean isNlpProcessable;
    
    
    public GroupSchema(String groupName, int groupIndex, boolean isNlpProcessable) {
    	this.groupName = groupName;
    	this.groupIndex = groupIndex;
    	this.isNlpProcessable = isNlpProcessable;
    }


	/**
	 * @return Returns the groupIndex.
	 */
	public int getGroupIndex() {
		return groupIndex;
	}


	/**
	 * @param groupIndex The groupIndex to set.
	 */
	public void setGroupIndex(int groupIndex) {
		this.groupIndex = groupIndex;
	}


	/**
	 * @return Returns the groupName.
	 */
	public String getGroupName() {
		return groupName;
	}


	/**
	 * @param groupName The groupName to set.
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	/**
	 * @return Returns the isNlpProcessable.
	 */
	public boolean isNlpProcessable() {
		return isNlpProcessable;
	}


	/**
	 * @param isNlpProcessable The isNlpProcessable to set.
	 */
	public void setNlpProcessable(boolean isNlpProcessable) {
		this.isNlpProcessable = isNlpProcessable;
	}
    
  
    
 }
