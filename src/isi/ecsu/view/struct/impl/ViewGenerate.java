/**
 * 
 */
package isi.ecsu.view.struct.impl;

import isi.ecsu.Util.CommonConstant;

import com.hp.hpl.jena.ontology.OntModel;

/**
 * @author subhasis
 *
 */
public class ViewGenerate {
	public void executeView(String rootObject, String userGroupName)
	{
		
		

		View vw = new ViewGenerationModule();
		String domainName = "www.semanticweb.org.subhasis.ontologies.2014.6.untitled-ontology-15";
		//String roleName = CommonConstant.userGraphURL, rootObject = "DL-Concept19";
		OntModel lmodel = null;
		try {
			System.out.println("Version 1.0");
			OntModel om = vw.roleView(lmodel, userGroupName, rootObject);
			System.out.println("Version 1.0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	}


