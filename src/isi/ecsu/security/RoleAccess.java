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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
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
	Map permValue;
	
	private OntologyObject subjectDAGMember;
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	private Logger slf4jLogger = LoggerFactory.getLogger(RoleAccess.class);
    

	public int getPermission(String objectName, String roleName)
			throws Throwable {
		int perm = 0;
		int returnPerm = 5;
		permValue = new HashMap<String, Integer>();
		permValue = this.HierarchyActiveComponentList(roleName, objectName);
		Iterator pv = null;
		pv = permValue.entrySet().iterator();
		while(pv.hasNext()){
			Map.Entry pair = (Map.Entry)pv.next();
			if(pair.getValue() == (Object) perm)
			{
				slf4jLogger.info("One Postive Permisiion "+pair.getKey());
				returnPerm = 0;
				return returnPerm;
			}
			
		}
		
		
	return returnPerm;	
		


	}

	public Map HierarchyActiveComponentList(String subjectName, String objectName) throws Throwable {
		subjectDAGMember = new OntologyObject();
		XacmlRequestGenerator xrg = new XacmlRequestGenerator();
		String subGraphName = CommonConstant.SubjectOntologyStorage;
		String lsubGraphUrl = CommonConstant.SubjectCommonURI;
		String lsubRelation = CommonConstant.SubjectRelation00;
		String sPrefix = CommonConstant.SubjectPrefix;

		String objGraphName = CommonConstant.ObjectOntologyStorage;
		String lobjGraphUrl = CommonConstant.ObjectCommonURI;
		String lobjRelation = CommonConstant.ObjectRelation00;
		String oPrefix = CommonConstant.ObjectPrefix;
		int option = 3;
		slf4jLogger.info("Configuration of Logger for Group access ");
		TraverseOntology objAccess = new TraverseOntology();
		subjectDAGMember = objAccess.getUserView(subjectName, subGraphName,
				lsubGraphUrl, lsubRelation, sPrefix);
		Iterator<String> lsub = null;
		lsub = subjectDAGMember.getNodeList().iterator();
		while (lsub.hasNext()) {
			String lsubject = lsub.next();
				ResponseCtx ct = xrg.getPermissionValue(lsubject, objectName,
						null, null);
				Set<Result> results = ct.getResults();
				Result result = results.iterator().next();
				option = result.getDecision();
				while(option == 0 || option == 1)
				{
					permValue.put(lsubject, option);
					slf4jLogger.info("\n One value found" +lsubject+ "\t Group \t"+objectName);
				}
			
		}
		return permValue;
	}

}
