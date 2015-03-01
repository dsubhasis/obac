/*
 * 
 */
package isi.ecsu.view.struct.impl;

import java.sql.SQLException;

import com.hp.hpl.jena.ontology.OntModel;

/**
 * 
 */

/**
 * @author subhasis
 *
 */
public interface View {
	
	OntModel viewModel(String user, String role, String rootNode, String relations) throws SQLException, Exception;
	public OntModel roleView(OntModel lmodel, String roleName, String rootObject ) throws Exception;
	
	
	
	

}
