/**
 * 
 */
package isi.ecsu.view.struct;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author subhasis
 *
 */
public class OntologyObject {
	
	 private List<String> NodeList = new LinkedList<String>();
	    
	    private List<String> multipleParent = new LinkedList<String>();
	    private Map nodeElement = new HashMap();
	   
	    /**
		 * @return the nodeElement
		 */
		public final Map getNodeElement() {
			return nodeElement;
		}
		/**
		 * @param nodeElement the nodeElement to set
		 */
		public final void setNodeElement(Map nodeElement) {
			this.nodeElement = nodeElement;
		}
		/**
	     * 
	     */
	    public OntologyObject() {
	    }
	    /**
	     * @return the positiveList
	     */
	   
	    public final List<String> getMultipleParent() {
	        return multipleParent;
	    }
	    /**
		 * @return the nodeList
		 */
		public final List<String> getNodeList() {
			return NodeList;
		}
		/**
		 * @param nodeList the nodeList to set
		 */
		public final void setNodeList(List<String> nodeList) {
			NodeList = nodeList;
		}
		/**
	     * @param multipleParent the multipleParent to set
	     */
	    public final void setMultipleParent(List<String> multipleParent) {
	        this.multipleParent = multipleParent;
	    }
	    /**
	     * @return the nodeElement
	     */
	    
	    
	    

}
