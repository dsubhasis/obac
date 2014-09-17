/**
 * 
 */
package isi.ecsu.view.struct;




import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import isi.ecsu.Util.CommonConstant;
import isi.ecsu.Util.commonUtil;
import isi.ecsu.security.RoleAccess;
import isi.ecsu.security.xacmlRequest;


import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;


/**
 * @author subhasis 
 * @version beta 
 *
 */
public class userRootView {

	private Model md; 
	private String rootElement; 
	private String userId;
	private Map nodeElement = new HashMap();
	private List positiveList = new LinkedList<List>();
	private List negativeList = new LinkedList<List>();
	private List<String> unVisited = new LinkedList<String>();
	private List multipleParent = new LinkedList<String>();
	public userRootView() { }

	
	public Map getUserView(String parent , String child, String user, String rootElement, String requestFile, String policyFile[], String graphName, String parentURI) throws Throwable{
		Map<String, List<String>> roleNodeSet = new HashMap<String, List<String>>();
		String lparentURI = parentURI;
		int unvisitedCount = 0;
		RoleAccess raccess = new RoleAccess();
		StorageAccess virt = new virtDataAccess();	
		String prefix = CommonConstant.prefix01;
		nodeElement.put(parentURI, 1);
		positiveList.add(parentURI);
		do{
		String query = commonUtil.queryListSubClassNode(graphName, lparentURI, CommonConstant.relation00, prefix);
		System.out.println("\nQuery \t" +query );
		ResultSet subClasses = virt.executeQuery(query);
		
		while (subClasses.hasNext()) {
			QuerySolution row= subClasses.next();
			RDFNode x = row.get("cls");
			int permRoot = raccess.getPermission(x.toString(), "roleName");
			query = commonUtil.queryListParentClassNode(graphName, x.toString(), CommonConstant.relation00 , prefix) ;
			ResultSet parentList = virt.executeQuery(query);
			while (parentList.hasNext()) {
				QuerySolution parentRow = parentList.next();
				RDFNode p = parentRow.get("cls");
			}  
			if(parentList.getRowNumber() > 1)
			{
				multipleParent.add(x.toString());
			}
			System.out.println("\n For the Concept  " +x.toString()+ "  Number of Paernt Found " +parentList.getRowNumber() );
			int permChild = raccess.getPermission(x.toString(), "roleName" ); 
			if(permRoot == 1 || permChild == 1)
			{
				nodeElement.put(x.toString(), 1);
				positiveList.add(x.toString());
			}
			if(permRoot == 0 || permChild == 0)
			{
				nodeElement.put(x.toString(), 0);
				negativeList.add(x.toString());
			}
			if(!unVisited.contains(x.toString()) ) 
			{
			unVisited.add(x.toString());
			}
			else
			{
				if(multipleParent.contains(x.toString()))
				{
					unVisited.add(x.toString());
				}
			}
			
			
			
			if(subClasses.getRowNumber() == 1)
			{
				//System.out.println("\n LEAF NODE Found  " +lparentURI);
			}
		}
		
		unvisitedCount = unVisited.size();
		if(unvisitedCount > 0)
		{
		lparentURI = unVisited.get(unVisited.size() - 1);
		unVisited.remove(lparentURI);
		}
		//System.out.println("\n Shifting to Child Concept  " +lparentURI);
		
	}while(unvisitedCount > 0);
		
		System.out.println("\n Analysis Done Generating View for the Role ");
		roleNodeSet.put("Positive", positiveList);
		roleNodeSet.put("negative", negativeList);
		roleNodeSet.put("multiple parents", multipleParent);
		return roleNodeSet;
	}

	public void nodeVisitor(RoleAccess raccess, int permRoot) throws SQLException{

		

	}
}
