/**
 * 
 */
package isi.ecsu.security;

import isi.ecsu.Util.mysqlJava;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author subhasis
 *
 */
public class RoleAccess {
	
	/**
	 * @param roleName
	 * @param roleId
	 * @param userName
	 * @param userId
	 */
	public RoleAccess() {
		
	}
	private String roleName;
	private String roleId;
	private String userName;
	private String userId;
	private String perMission;
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	private final static Logger LOGGER = Logger.getLogger(RoleAccess.class.getName());
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the perMission
	 */
	public String getPerMission() {
		return perMission;
	}
	/**
	 * @param perMission the perMission to set
	 */
	public void setPerMission(String perMission) {
		this.perMission = perMission;
	}
	public ArrayList getRoleName(String user) throws SQLException
	{
		StorageAccess st = new mysqlDataAccess();
		String query ="SELECT roleName from roleTable where user=\"" + user + "\";";
		ArrayList lroleName = st.getResultArrayList("roleName", query);
		return lroleName;
		
	}
	
	public int getPermission(String objectName, String roleName) throws SQLException
	{
		
		//StorageAccess st = new mysqlDataAccess();
		//String query ="SELECT permission from roleTable where user=\"" + objectName + "\";";
		//ArrayList lroleName = st.getResultArrayList("roleName", query);
		
		Random rand=new Random(); 
		int x=rand.nextInt(100); 
		int y = x % 2;
		
		return  y;
		
		
	}

}
