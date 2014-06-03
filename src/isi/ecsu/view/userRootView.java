/**
 * 
 */
package isi.ecsu.view;



import isi.ecsu.Util.commonUtil;
import isi.ecsu.security.xacmlRequest;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;


/**
 * @author subhasis 
 * @version beta 
 *
 */
public class userRootView {

	private Model md; 
	private String rootElement; 
	private String userId;
	public userRootView(Model md, String rootElement, String userId) {

		this.md = md;
		this.rootElement = rootElement;
		this.userId = userId;
	}
	private Model getUserView(String user, String virtUser, String virtPass, String virtUrl, String rootElement, String requestFile, String policyFile[], String graphName, String parentURI){
		Model lmodel = ModelFactory.createDefaultModel();
		String lparentAuth = "NULL";
		String lchildAuth = "NULL";
		String lblackNode = "NULL";
		boolean blankNodeDefined=false;
		boolean multiParentClassDefined=false;
		int visit;
		xacmlRequest xc = new xacmlRequest(requestFile, policyFile);
		lchildAuth = xc.getPermissionReply();	
		lparentAuth = xc.getPermissionReply();

		ResultSet subClasses = commonUtil.executeQuery(commonUtil.queryListSubClassNode(graphName, parentURI), commonUtil.virtConnect(virtUser, virtPass, virtUrl)); 
		if(parent==child && parentAuth.equals("default")) {
			parentAuth=new String("positive");
		}
		else if(parentAuth.equals("default")) {
			
			if(m.isClassInGraph(parent, viewGraph)==false)
				parentAuth=new String("negative");
			else
				parentAuth=new String("positive");
	
		}
		
		if(childAuth.equals("default")) {
			
			System.out.print(child+" authorization default changing to: ");
			childAuth=parentAuth;
			System.out.println(childAuth);
		}




		return lmodel;
	}



}
