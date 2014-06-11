/**
 * 
 */
package isi.ecsu.view;

import java.util.List;

import sun.util.calendar.BaseCalendar.Date;

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
