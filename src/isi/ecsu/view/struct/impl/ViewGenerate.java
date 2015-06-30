/**
 * 
 */
package isi.ecsu.view.struct.impl;

import org.json.JSONObject;

import isi.ecsu.Util.CommonConstant;

import com.hp.hpl.jena.ontology.OntModel;

/**
 * @author subhasis
 *
 */
public class ViewGenerate {
	public JSONObject executeView(String rootObject, String userGroupName)
	{
		
		JSONObject om = null;

		View vw = new ViewGenerationModule();
		String domainName = "www.semanticweb.org.subhasis.ontologies.2014.6.untitled-ontology-15";
		//String roleName = CommonConstant.userGraphURL, rootObject = "DL-Concept19";
		OntModel lmodel = null;
		try {
			System.out.println("Version 1.0");
			om = vw.rView(lmodel, userGroupName, rootObject);
			//System.out.println("Version 1.0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return om;
		
	}
		
	}


