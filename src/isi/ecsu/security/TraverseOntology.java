/**
 * 
 */
package isi.ecsu.security;

import isi.ecsu.Util.CommonConstant;
import isi.ecsu.Util.commonUtil;
import isi.ecsu.view.struct.OntologyObject;
import isi.ecsu.view.struct.ViewObject;
import isi.ecsu.view.struct.impl.StorageAccess;
import isi.ecsu.view.struct.impl.VirtDataAccess;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

/**
 * @author subhasis
 * 
 */

public class TraverseOntology {

	private Logger slf4jLogger = LoggerFactory
			.getLogger(TraverseOntology.class);
	private OntologyObject OntoObj;

	public void intiateAccess(String rootElement, String graphName,
			String parentURI) throws Throwable {

		slf4jLogger.info("View Details " + rootElement + "Graph Name"
				+ graphName + parentURI);
		//this.getUserView(rootElement, graphName, parentURI);

	}

	public OntologyObject getUserView(String rootElement, String graphName,
			String parentURI, String prefix, String ontologyRelation) throws Throwable {
		Map<String, List<String>> roleNodeSet = new HashMap<String, List<String>>();
		String lparentURI = parentURI+rootElement;
		List<String> unVisited = new LinkedList<String>();
		int unvisitedCount = 0;
		RoleAccess raccess = new RoleAccess();
		StorageAccess virt = new VirtDataAccess();
		OntoObj = new OntologyObject();
		//String prefix = CommonConstant.prefix01;
		OntoObj.getNodeElement().put(lparentURI, 1);
		OntoObj.getNodeList().add(lparentURI);
		do {
			// System.out.println("\n Executing Query On  : " +lparentURI+
			// " Concept" );
			String query = commonUtil.queryListSubClassNode(graphName,
					lparentURI, ontologyRelation, prefix);
			// System.out.println("\nQuery \t" + query);
			slf4jLogger.info("\nQuery \t" + query);
			ResultSet subClasses = virt.executeQuery(query);
			while (subClasses.hasNext()) {
				QuerySolution row = subClasses.next();
				RDFNode x = row.get("cls");

				query = commonUtil.queryListParentClassNode(graphName,
						x.toString(), ontologyRelation, prefix);
				ResultSet parentList = virt.executeQuery(query);
				while (parentList.hasNext()) {
					QuerySolution parentRow = parentList.next();
					RDFNode p = parentRow.get("cls");
				}
				if (parentList.getRowNumber() > 1) {
					OntoObj.getMultipleParent().add(x.toString());
				}
				System.out.println("\n For the Concept  " + x.toString()
						+ "  Number of Paernt Found "
						+ parentList.getRowNumber());

				OntoObj.getNodeList().add(x.toString());
				OntoObj.getNodeElement().put(x.toString(), 1);

				if (!unVisited.contains(x.toString())) {
					unVisited.add(x.toString());
				} else {
					if (OntoObj.getMultipleParent().contains(x.toString())) {
						unVisited.add(x.toString());
					}
				}
			}
			unvisitedCount = unVisited.size();
			if (unvisitedCount > 0) {
				lparentURI = unVisited.get(unVisited.size() - 1);
				unVisited.remove(lparentURI);
			}
		} while (unvisitedCount > 0);
		System.out.println("\n Analysis Done Generating View for the Role ");
		return OntoObj;
	}

	/**
	 * 
	 */
	public TraverseOntology() {

		slf4jLogger.info("Traverse started ");
	}

}
