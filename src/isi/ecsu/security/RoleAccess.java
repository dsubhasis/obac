/**
 * 
 */
package isi.ecsu.security;

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
	public RoleAccess(String roleName, String roleId, String userName,
			String userId) {
		this.roleName = roleName;
		this.roleId = roleId;
		this.userName = userName;
		this.userId = userId;
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
	
	public String getPermission(String userName, String roleName)
	{
		
		return perMission; 
	}

}
