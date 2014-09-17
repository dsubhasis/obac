/**
 * 
 */
package isi.ecsu.view.struct;

import java.util.Date;
import java.util.List;



/**
 * @author subhasis
 *
 */
public class user extends Subject{

	
	private String passWord;
	private String address;
	private String phoneNumber; 
	private Date dateOfJoin;
	private String informations;
	
	/**
	 * @param name
	 * @param roleName
	 */
	public user(String name, List roleName) {
		super(name, roleName);
		// TODO Auto-generated constructor stub
	}

}
