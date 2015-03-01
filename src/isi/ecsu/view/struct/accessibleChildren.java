/**
 * 
 */
package isi.ecsu.view.struct;

import java.util.LinkedList;
import java.util.List;

/**
 * @author subhasis
 *
 */
public class accessibleChildren {

	private List children = new LinkedList<>();
	private String childrenInput;

	/**
	 * @return the childrenInput
	 */
	public final String getChildrenInput() {
		return childrenInput;
	}

	/**
	 * @param childrenInput the childrenInput to set 
	 */
	public final void setChildrenInput(String childrenInput) {
		this.childrenInput = childrenInput;
		children.add(childrenInput);
	}
	public final int getCountChildren()
	{
		return children.size();

	}
	public void printNode(){

		int n = getCountChildren();
		for(int i =0; i< n; i++)
		{
			System.out.println(children.get(i) + ":\n");
		}
	}

	/**
	 * 
	 */
	public accessibleChildren() {
	}

	/**
	 * @return the children
	 */
	public final List getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public final void setChildren(List children) {
		this.children = children;
	}



}
