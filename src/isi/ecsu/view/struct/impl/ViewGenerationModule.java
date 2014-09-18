/**
 * 
 */
package isi.ecsu.view.struct.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelFactoryBase;
import com.hp.hpl.jena.rdf.model.RDFNode;

import isi.ecsu.Util.CommonConstant;
import isi.ecsu.security.RoleAccess;
import isi.ecsu.view.struct.OntologyConcept;
import isi.ecsu.view.struct.ViewObject;


/**
 * @author subhasis
 *
 */
public class ViewGenerationModule implements View {

	/**
	 * @param user
	 */
	public ViewGenerationModule() {
		//this.user = user;
	}
	private String user;
	private String roleUser;
	private OntologyConcept oc;
	private String ontoDatabase;

	/**
	 * 
	 * @param user : user Name / user identifications 
	 * @param role : name of the role where user belongs to 
	 * @param rootNode : name of root concept node where the user's access starts
	 * @param relationNode : name of the relation by which the DAG formed like A isSubClassof B A and B are the concepts and isSubClassOf is the relations 
	 * @return user specific view in the form of OntModel of
	 * @throws Exception 
	 */
	public OntModel viewModel(String user, String role, String rootNode, String relationNode) throws Exception{
		OntModel lOntModel = ModelFactory.createOntologyModel();
		RoleAccess ras = new RoleAccess();
		ArrayList  roleName = ras.getRoleName(user);
		for(int i =0; i<= roleName.size(); i++)
		{
			 lOntModel = roleView(lOntModel, roleName.get(i).toString(), rootNode);
		}
		
       
		return lOntModel;
	}
	/**
	 * @author subhasis
	 * @category
	 * {@code}
	 * @param OntolModel
	 * @param roleName 
	 * @param rootObject
	 * @return
	 * @throws Exception 
	 * 
	 */
	
    public OntModel roleView(OntModel lmodel, String roleName, String rootObject ) throws Exception {
    	
   
    String policyFile[] = null;
    ViewObject vo = new ViewObject();
    if(rootObject.isEmpty() )
    {
    	rootObject = CommonConstant.topConcept;
    }
    String parentURI = CommonConstant.commonURI + rootObject;
    try {
    	UserRootView urv = new UserRootView();
		urv.getUserView(vo, "parent" , "child" , "userId", "rootElement", "requestFile", policyFile , "http://dltest.org", parentURI);
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	return lmodel;
    	
    }

}
