/**
 * 
 */
package isi.ecsu.view.struct;

import java.util.List;

/**
 * @author subhasis
 *
 */
public class DocumentClass extends OntologyConcept{
	public DocumentClass(String conceptName, String uri) {
		super(conceptName, uri);
		// TODO Auto-generated constructor stub
	}
	private String documentClassName;
	
	private List listOfConcept;

	/**
	 * @return the documentClassName
	 */
	public final String getDocumentClassName() {
		return documentClassName;
	}

	/**
	 * @param documentClassName the documentClassName to set
	 */
	public final void setDocumentClassName(String documentClassName) {
		this.documentClassName = documentClassName;
	}

	/**
	 * @return the listOfConcept
	 */
	public final List getListOfConcept() {
		return listOfConcept;
	}

	/**
	 * @param listOfConcept the listOfConcept to set
	 */
	public final void setListOfConcept(List listOfConcept) {
		this.listOfConcept = listOfConcept;
	}
	

}
