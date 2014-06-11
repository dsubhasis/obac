/**
 * 
 */
package isi.ecsu.view;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * @author subhasis
 *
 */
public class OntologyConcept {
	
	private String conceptName;
	private String uri;
	private List<ArrayList> parentConcept;
	private List childConcept;
	private List documentClass;
	/**
	 * @return the conceptName
	 */
	
	
	public OntologyConcept(String conceptName, String uri) {
		super();
		this.conceptName = conceptName;
		this.uri = uri;
	}
	
	public Model getNodeDetails(String conceptName){
		
		Model md = null;
		return md;
		
	}
	
	public final String getConceptName() {
		return conceptName;
	}
	/**
	 * @param conceptName the conceptName to set
	 */
	public final void setConceptName(String conceptName) {
		this.conceptName = conceptName;
	}
	
	
	/**
	 * @return the parentConcept
	 */
	public final List<ArrayList> getParentConcept() {
		return parentConcept;
	}
	/**
	 * @return the uri
	 */
	public final String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public final void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @param parentConcept the parentConcept to set
	 */
	public final void setParentConcept(List<ArrayList> parentConcept) {
		this.parentConcept = parentConcept;
	}
	/**
	 * @return the childConcept
	 */
	public final List getChildConcept() {
		
		return childConcept;
	}
	/**
	 * @param childConcept the childConcept to set
	 */
	public final void setChildConcept(List childConcept) {
		this.childConcept = childConcept;
	}
	/**
	 * @return the documentClass
	 */
	public final List getDocumentClass() {
		return documentClass;
	}
	/**
	 * @param documentClass the documentClass to set
	 */
	public final void setDocumentClass(List documentClass) {
		this.documentClass = documentClass;
	}
	

}
