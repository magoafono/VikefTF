/* $Id: Paragraph.java,v 1.7 2006/10/25 08:04:30 simone Exp $ */
package ilc.vikef;


/**
 * 
 * 
 */
public class Paragraph {

	/**
	 * <p>Represents ...</p>
	 * 
	 */
	private String characters;

	/**
	 * <p>Represents ...</p>
	 * 
	 */
	private String entryLevelIds;

	private String entryLevelURL;

	private String category;

	private String paragraphId;



	/**
	 * @return the characters
	 */
	public String getCharacters() {
		return characters;
	}

	/**
	 * @param characters the characters to set
	 */
	public void setCharacters(String characters) {
		this.characters = characters;
	}

	/**
	 * @return the entryLevelIds
	 */
	public String getEntryLevelIds() {
		return entryLevelIds;
	}

	/**
	 * @param entryLevelIds the entryLevelIds to set
	 */
	public void setEntryLevelIds(String entryLevelIds) {
		this.entryLevelIds = entryLevelIds;
	}

	public String getEntryLevelURL() {
		return entryLevelURL;
	}

	/**
	 * @param entryLevelURL the entryLevelURL to set
	 */
	public void setEntryLevelURL(String entryLevelURL) {
		this.entryLevelURL = entryLevelURL;
	}

	/**
	 * @return Returns the category.
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category The category to set.
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return Returns the paragraphId.
	 */
	public String getParagraphId() {
		return paragraphId;
	}

	/**
	 * @param paragraphId The paragraphId to set.
	 */
	public void setParagraphId(String paragraphId) {
		this.paragraphId = paragraphId;
	}

	/**
	 * 
	 * @return il numero di pagina del paragraph
	 */
	public String getPage() {
		return paragraphId.substring(0, paragraphId.indexOf('_'));
	}

}
