/**
 * 
 */
package isi.ecsu.view.struct.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import isi.ecsu.view.struct.visitLog;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;

/**
 * @author subhasis
 *
 */
public class ReConstructionView {
	
	
	public void rebuiltOntology(visitLog vl){
		
		OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_DL_MEM);
		String uri = "http://www.iwi-iuk.org/material/RDF/Schema/Class/scf#Property-III";
		m.createProperty("http://ontology.project/#hasContributedto");
		Property hCt = m.getProperty("http://ontology.project/#hasContributedto");
		Map<String, Map> vs = vl.getVisitNodeList();
		OntClass randClass = null;
		for (Map.Entry<String, Map> entry : vs.entrySet())
		{
		    List<String> lParent =  new ArrayList<String>();
		    List<String> lSuper = new ArrayList<String>();
			System.out.println(entry.getKey() + "/" + entry.getValue());
		    Map<String , Integer>  parentClass = entry.getValue();
		    randClass = m.createClass(entry.getKey()); 
		    for ( Map.Entry<String, Integer> pEntry : parentClass.entrySet())
		    {
		    	if(pEntry.getValue() == 0)
		    	{
		    	lParent.add(pEntry.getKey());
		    	if(vl.getVisitNodeList().containsKey(pEntry.getKey()))
		    			{
		    		       lSuper.add(pEntry.getKey());
		    			}
		    	}
		    }
		    
		    randClass = m.createClass(entry.getKey()); 
		    Iterator<String> lPar = null;
		    Iterator<String> lsup = null;
		    
		    lsup = lSuper.iterator();
		    while(lsup.hasNext())
		    {
		    	randClass.addSuperClass(lsup.next());
		    }
		    
		    randClass.addSuperClass();
		    
		}
		
		
		
		
	}

}
