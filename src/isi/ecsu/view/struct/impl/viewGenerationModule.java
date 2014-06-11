/**
 * 
 */
package isi.ecsu.view.struct.impl;

import com.hp.hpl.jena.rdf.model.Model;

import isi.ecsu.view.struct.OntologyConcept;

/**
 * @author subhasis
 *
 */
public class viewGenerationModule implements View {
	
	/**
	 * @param user
	 */
	public viewGenerationModule(String user) {
		this.user = user;
	}
	private String user;
	private String roleUser;
	private OntologyConcept oc;
	private String ontoDatabase;
	
	public Model viewModel(String user, String role){
		
		
		
		return null;
		
		
	}
	
	
	

}
