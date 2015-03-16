/**
 * 
 */
package isi.ecsu.security;

import isi.ecsu.Util.CommonConstant;
import isi.ecsu.Util.commonUtil;
import isi.ecsu.view.struct.OntologyObject;
import isi.ecsu.view.struct.accessibleChildren;
import isi.ecsu.view.struct.impl.StorageAccess;
import isi.ecsu.view.struct.impl.MysqlDataAccess;
import isi.ecsu.view.struct.impl.VirtDataAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.ctx.Result;

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
	private OntologyObject subjectDAGMember;
	private OntologyObject objectDAGMember;
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	private Logger slf4jLogger = LoggerFactory.getLogger(RoleAccess.class);
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
		
		
		//String 
		
		
		
		
		//StorageAccess st = new mysqlDataAccess();
		//String query ="SELECT permission from roleTable where user=\"" + objectName + "\";";
		//ArrayList lroleName = st.getResultArrayList("roleName", query);
		
		asc = new accessibleChildren();
		//int numberOfActiveSubject = HierarchyActiveComponentList(asc);
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
	public  void HierarchyActiveComponentList(accessibleChildren asc, String subjectName, String objectName) throws Throwable 
	{
		subjectDAGMember = new OntologyObject();
		XacmlRequestGenerator xrg = new XacmlRequestGenerator();
		String graphName = CommonConstant.userHierarchy;
		String lgraphUrl = CommonConstant.userGraphURL;
		String prefix = CommonConstant.prefix01;
		String lrelation =  CommonConstant.SubjectRelation00;
		String Prefix = CommonConstant.SubjectPrefix;
		
		
		String subGraphName = CommonConstant.SubjectOntologyStorage;
		String lsubGraphUrl = CommonConstant.SubjectCommonURI;
		String lsubRelation = CommonConstant.SubjectRelation00;
		String sPrefix = CommonConstant.SubjectPrefix;
		
		String objGraphName = CommonConstant.ObjectOntologyStorage;
		String lobjGraphUrl = CommonConstant.ObjectCommonURI;
		String lobjRelation = CommonConstant.ObjectRelation00;
		String oPrefix = CommonConstant.ObjectPrefix;
		
		
		List<String> lchild = new LinkedList<String>();
		
		slf4jLogger.info("Configuration of Logger for Group access ");
		
		TraverseOntology objAccess = new TraverseOntology();
		subjectDAGMember = objAccess.getUserView(subjectName, subGraphName, lsubGraphUrl, lsubRelation, sPrefix);
		objectDAGMember = objAccess.getParentListRecursive(objectName, objGraphName, lobjGraphUrl, lobjRelation, oPrefix);
		
		Iterator<String> lsub = null;
		Iterator<String> lobj = null;
		lsub = subjectDAGMember.getNodeList().iterator();
		
		while(lsub.hasNext())
		{
			String lsubject = lsub.next();
			lobj = objectDAGMember.getNodeList().iterator();
			while(lobj.hasNext())
			{
				String lobject = lobj.next();
			ResponseCtx ct	= xrg.getPermissionValue(lsubject, lobject, null, null);

			Set<Result> results  = ct.getResults();
			Result result = results.iterator().next();
			int option = result.getDecision();
			switch(option)
			{
			case 0: {
				
			}
			case 1: {
				
			}
			case 2 :{
				slf4jLogger.info("XACML Plolicy Error");
			}
			case 3 : {
				slf4jLogger.info("Systems Error");
			}
			}
			if(result.getDecision() == 0)
			{
				//result.getDecision(); Yes
			}
			if(result.getDecision() == 1)
			{
				// no
			}
				
			}
		}
		
		
		
		
		
	}

}
