/**
 * 
 */
package isi.ecsu.view;

import isi.ecsu.Util.virtuosoData;

import java.util.List;

/**
 * @author subhasis
 *
 */
public class Subject {
	/**
	 * @param name
	 * @param roleName
	 */
	public Subject(String name, List roleName) {
		this.name = name;
		this.roleName = roleName;
	}
	private String name;
	private List roleName;
	private String type;
	private DataAceess vd ;
	/**
	 * @return the name
	 */
	public final String getName() {
		vd = new virtuosoData(type, name);
		
		
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the roleName
	 */
	public final List getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public final void setRoleName(List roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the type
	 */
	public final String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public final void setType(String type) {
		this.type = type;
	}
	

}
