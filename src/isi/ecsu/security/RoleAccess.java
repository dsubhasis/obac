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

import java.net.URI;
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
    

	public Integer getPermission(String objectName, String roleName)
			throws Throwable {
		int perm = 0;
		int returnPerm = 5;
		permValue = new HashMap<String, Integer>();
	    returnPerm = this.HierarchyActiveComponentList(roleName, objectName);
		
		slf4jLogger.info(roleName, objectName);
		
		
		
		
	return returnPerm;	
		


	}


	public Integer HierarchyActiveComponentList(String subjectName, String objectName) throws Throwable {
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
				lsubGraphUrl, sPrefix, lsubRelation);
		Iterator<String> lsub = null;
		lsub = subjectDAGMember.getNodeList().iterator();
		while (lsub.hasNext()) {
			option = 3;
			String lsubject = lsub.next();
			URI svalue = new URI(lsubject);
			String spath = svalue.getPath();
			String host= svalue.getHost();
			String delims = "#";
			String[] nodeName = subjectName.split(delims);
			int n = nodeName.length;
			
			String sp = spath.substring(spath.lastIndexOf("/"));
			
			sp = spath.replaceAll("/", ".");
			final String finalSubject = nodeName[n - 1]+"@"+host+sp;
			
				ResponseCtx ct = xrg.getPermissionValue(finalSubject, objectName,
						"admin", "test");
				Set<Result> results = ct.getResults();
				Result result = results.iterator().next();
				option = result.getDecision();
				if(option == 0)
				{
					
					slf4jLogger.info("\n Positive access found" +lsubject+ "\t Group \t"+objectName);
					
					return option;
					
				}
			
		}
		return option;
	}

}
