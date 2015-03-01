/**
 * 
 */
package isi.ecsu.security;

import isi.ecsu.Util.CommonConstant;
import isi.ecsu.Util.commonUtil;
import isi.ecsu.view.struct.accessibleChildren;
import isi.ecsu.view.struct.impl.StorageAccess;
import isi.ecsu.view.struct.impl.MysqlDataAccess;
import isi.ecsu.view.struct.impl.VirtDataAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

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
	private accessibleChildren asc ;
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
	public final void setRoleName(final String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the roleId
	 */
	public final String getRoleId() {
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
	public ArrayList<?> getRoleName(String user) throws SQLException
	{
		StorageAccess st = new MysqlDataAccess();
		String query ="SELECT roleName from roleTable where user=\"" + user + "\";";
		ArrayList<?> lroleName = st.getResultArrayList("roleName", query);
		return lroleName;
		
	}
	
	public int getPermission(String objectName, String roleName) throws Exception
	{
		
		//StorageAccess st = new mysqlDataAccess();
		//String query ="SELECT permission from roleTable where user=\"" + objectName + "\";";
		//ArrayList lroleName = st.getResultArrayList("roleName", query);
		
		asc = new accessibleChildren();
		int numberOfActiveSubject = HierarchyActiveComponentList(asc);
		Iterator itl = asc.getChildren().iterator();
		while(itl.hasNext())
		{
		   String subject = (String) itl.next();
		   System.out.print(subject);
		   
			
		}
		
		Random rand=new Random(); 
		int x=rand.nextInt(100); 
		int y = x % 2;
		
		return  y;
		
		
	}
	public  int HierarchyActiveComponentList(accessibleChildren asc) throws Exception
	{
		StorageAccess virt = new VirtDataAccess();
		String graphName = CommonConstant.userHierarchy;
		String lgraphUrl = CommonConstant.userGraphURL;
		String prefix = CommonConstant.prefix01;
		List<String> lchild = new LinkedList<String>();
		
		
		do{
			
		String query = commonUtil.queryListSubClassNode(graphName, lgraphUrl, CommonConstant.relation00, prefix);
		ResultSet subClasses = virt.executeQuery(query);
		
		while (subClasses.hasNext()) {
			RDFNode x = subClasses.next().get("cls");
			lchild.add(x.toString());
		}
		
		
		String temp = lchild.get(lchild.size() - 1 );
		asc.setChildrenInput(temp);
		lchild.remove(temp);
		lgraphUrl = temp;
		
		}while(lchild.size() > 0);
		//asc.printNode();
		return asc.getCountChildren();
		
		
	}

}
