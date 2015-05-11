/**
 * 
 */
package isi.ecsu.view.struct.impl;

import isi.ecsu.Util.CommonConstant;
import isi.ecsu.Util.commonUtil;
import isi.ecsu.security.RoleAccess;
import isi.ecsu.view.struct.ViewObject;
import isi.ecsu.view.struct.visitLog;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import virtuoso.jena.driver.VirtGraph;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;

/**
 * @author subhasis
 * @version beta
 * 
 */
public class UserRootView {
	private Model md;
	private String rootElement;
	private String userId;
	private visitLog vLog;
	private List<String> unVisited = new LinkedList<String>();
	private Logger slf4jLogger = LoggerFactory.getLogger(UserRootView.class);

	public visitLog getUserView(ViewObject vo, String parent, String child,
			String user, String rootElement, String requestFile,
			String policyFile[], String graphName, String parentURI)
			throws Throwable {
		String lparentURI = parentURI;
		vLog = new visitLog();
		int unvisitedCount = 0;
		int permValue = 0;
		RoleAccess raccess = new RoleAccess();
		StorageAccess virt = new VirtDataAccess();
		String prefix = CommonConstant.prefix01;
		vo.getNodeElement().put(lparentURI, 1);
		vo.getPositiveList().add(lparentURI);
		do {
			VirtGraph vt = virt.virtConnect();
			String query = commonUtil.queryListSubClassNode(graphName,
					lparentURI, CommonConstant.relation00, prefix);
			slf4jLogger.info("\nQuery \t" + query);
			ResultSet subClasses = virt.executeQuery(query, vt);
			while (subClasses.hasNext()) {
				QuerySolution row = subClasses.next();
				RDFNode x = row.get("cls");
				int permRoot = raccess.getPermission(x.toString(), user);
				if (permRoot == 1) {
					// In case of Deny
					permValue = permRoot;
					vo.getNegativeList().add(x.toString());
				}
				/*
				 * Permit, Deny, Indeterminate (an error occurred or some
				 * required value was missing, so a decision cannot be made) or
				 * Not Applicable (the request can't be answered by this
				 * service).
				 */
				if (permRoot != 2) {
					vLog.addPermission(x.toString(), lparentURI, permValue);
				}
				if (permRoot == 2) {
					slf4jLogger.info("\n We have found error in Policy:");
				}
				if (!vLog.getUnVisited().contains(x.toString())) {
					vLog.getUnVisited().add(x.toString());
				}
			}
			unvisitedCount = vLog.getUnVisited().size();
			if (unvisitedCount > 0) {
				lparentURI = vLog.getUnVisited().get(
						vLog.getUnVisited().size() - 1);
				vLog.getUnVisited().remove(lparentURI);
			}
			vt.close();
		} while (unvisitedCount > 0);
		slf4jLogger.info("\n Analysis Done Generating View for the Role ");
		vo.setNodeVisitLog(vLog);
		slf4jLogger
				.info("\n View Generation Complete updated visit Log and view Object for "
						+ parentURI + "\t Target group is : \t" + user);
		return vLog;
	}
}
