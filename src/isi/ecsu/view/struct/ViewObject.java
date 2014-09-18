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
public class ViewObject {
    
    private List<String> positiveList = new LinkedList<String>();
    private List<String> negativeList = new LinkedList<String>();
    private List<String> multipleParent = new LinkedList<String>();
    private Map nodeElement = new HashMap();
    /**
     * 
     */
    public ViewObject() {
    }
    /**
     * @return the positiveList
     */
    public final List<String> getPositiveList() {
        return positiveList;
    }
    /**
     * @param positiveList the positiveList to set
     */
    public final void setPositiveList(List<String> positiveList) {
        this.positiveList = positiveList;
    }
    /**
     * @return the negativeList
     */
    public final List<String> getNegativeList() {
        return negativeList;
    }
    /**
     * @param negativeList the negativeList to set
     */
    public final void setNegativeList(List<String> negativeList) {
        this.negativeList = negativeList;
    }
    /**
     * @return the multipleParent
     */
    public final List<String> getMultipleParent() {
        return multipleParent;
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
    public final Map getNodeElement() {
        return nodeElement;
    }
    /**
     * @param nodeElement the nodeElement to set
     */
    public final void setNodeElement(Map nodeElement) {
        this.nodeElement = nodeElement;
    }
    
    
    

}
