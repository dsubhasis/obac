/**
 * 
 */
package isi.ecsu.view.struct.impl;

import isi.ecsu.Util.CommonConstant;
import isi.ecsu.Util.commonUtil;
import isi.ecsu.security.RoleAccess;
import isi.ecsu.security.TraverseOntology;
import isi.ecsu.view.struct.ViewObject;
import isi.ecsu.view.struct.visitLog;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private List<String> unVisited = new LinkedList<String>();
	private Logger slf4jLogger = LoggerFactory
			.getLogger(UserRootView.class);

	public UserRootView() {

	}

	public ViewObject getUserView(ViewObject vo, String parent, String child,
			String user, String rootElement, String requestFile,
			String policyFile[], String graphName, String parentURI)
			throws Throwable {
		Map<String, List<String>> roleNodeSet = new HashMap<String, List<String>>();
		String lparentURI = parentURI;
		visitLog vLog = new visitLog();
		int unvisitedCount = 0;
		int permValue = 0;
		RoleAccess raccess = new RoleAccess();
		StorageAccess virt = new VirtDataAccess();
		String prefix = CommonConstant.prefix01;
		vo.getNodeElement().put(lparentURI, 1);
		vo.getPositiveList().add(lparentURI);
		do {
			String query = commonUtil.queryListSubClassNode(graphName,
					lparentURI, CommonConstant.relation00, prefix);
            slf4jLogger.info("\nQuery \t" + query);
			ResultSet subClasses = virt.executeQuery(query);
			
			
			while (subClasses.hasNext()) {
				QuerySolution row = subClasses.next();
				RDFNode x = row.get("cls");
				int permRoot = raccess.getPermission(x.toString(), user);
 				if(permRoot == 3)
				{
 					//add the node to negative list
					permValue = permRoot;
					vLog.addPermission(x.toString(), lparentURI, permValue);
					vo.getNegativeList().add(x.toString());
				}
 				else{
 					//add the node to postive list
 					vo.getPositiveList().add(x.toString());
 					
 				}
 				vLog.addPermission(x.toString(), lparentURI, permValue);
				
				if (!vLog.getUnVisited().contains(x.toString())) {
					vLog.getUnVisited().add(x.toString());
					
				} else {
					if (vo.getMultipleParent().contains(x.toString())) {
						vLog.getUnVisited().add(x.toString());
					}
				}
			}
			unvisitedCount = vLog.getUnVisited().size();
			if (unvisitedCount > 0) {
				lparentURI = vLog.getUnVisited().get(vLog.getUnVisited().size() -1);
				vLog.getUnVisited().remove(lparentURI);
			}
		} while (unvisitedCount > 0);
		System.out.println("\n Analysis Done Generating View for the Role ");
		return vo;
	}

	public void nodeVisitor(RoleAccess raccess, int permRoot)
			throws SQLException {
	}
}
