/**
 * 
 */
package isi.ecsu.view.struct.impl;



import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.server.UID;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import isi.ecsu.view.struct.visitLog;


/**
 * @author subhasis
 *
 */
public class ReConstructionView {
	private JSONObject dlo;
	
	
	/**
	 * @return the JSON Object of view 
	 */
	public final JSONObject getJSONView() {
		return dlo;
	}


	public void rebuiltOntology(visitLog vl) throws MalformedURLException{
		
		dlo = new JSONObject();

		UID viewId = new UID();
		
		dlo.put("Version", 1.0);
		dlo.put("id", "dlview"+viewId.toString() );
		System.out.println();
		
		Map<String, Map> listNode = vl.getVisitNodeList(); 
		
		for(Map.Entry<String, Map> entry : listNode.entrySet()){
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			String key = entry.getKey();
			Map parentList = listNode.get(key);
		}
		Map<String, Integer> userRoot = listNode.get("userRootNode");
		String rootNode = null;
		for(Map.Entry<String, Integer> entry : userRoot.entrySet()){
			rootNode = entry.getKey();
		}
		listNode.remove("userRootNode");
		Map jsonRoot = new HashMap();
		jsonRoot.put("rootNode", new URL(rootNode));
		for(Map.Entry<String, Map> entryObj : listNode.entrySet()){
		Map<String, Integer> parentList = new HashMap();
		parentList = entryObj.getValue();
		URL concept = new URL(entryObj.getKey());
	    List parentStruct = new LinkedList();
	    Map parentMap = new HashMap();
	   // parentMap.put("Concept", concept);
		for(Map.Entry<String, Integer> entryConcept : parentList.entrySet())
		{
			List parentConcept = new LinkedList();
			String parentNode = entryConcept.getKey();
		   Integer permV = entryConcept.getValue();
		   parentConcept.add(new URL(parentNode));
		   parentConcept.add(permV);
		   parentStruct.add(parentConcept);
		}
		parentMap.put("parent", parentStruct);
		jsonRoot.put(concept, parentMap);
		
		
		
		
		
	}
		
	
		
	
	dlo.putAll(jsonRoot);
	
	System.out.println();
	
}
}
