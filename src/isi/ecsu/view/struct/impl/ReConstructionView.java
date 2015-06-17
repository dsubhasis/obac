/**
 * 
 */
package isi.ecsu.view.struct.impl;



import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.rmi.server.UID;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import isi.ecsu.Util.commonUtil;
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


	public JSONObject rebuiltOntology(visitLog vl) throws MalformedURLException{
		
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
		dlo.put("rootNode", new URL(rootNode));
		for(Map.Entry<String, Map> entryObj : listNode.entrySet()){
		Map<String, Integer> parentList = new HashMap();
		parentList = entryObj.getValue();
		String concept = entryObj.getKey();
	   // List parentStruct = new LinkedList();
	    Map parentMap = new HashMap();
	    JSONObject parentJSON = new JSONObject();
	    JSONArray parentConceptJSON = new JSONArray();
	    List docClass = new LinkedList<String>();
		for(Map.Entry<String, Integer> entryConcept : parentList.entrySet())
		{
			
			List documentClass;
			List parentConcept = new LinkedList();
			String parentNode = entryConcept.getKey();
		   Integer permV = entryConcept.getValue();
		   parentConcept.add(new URL(parentNode));
		   parentConcept.add(permV);
		   //parentStruct.add(parentConcept);
		   parentConceptJSON.put(parentConcept);
		   if(permV == 0)
		   {
			   
			   String[] documentSrc = parentNode.split("#");
			   
			   docClass.add(documentSrc[documentSrc.length -1 ]);
		   }
		   
		}
		parentJSON.put("docClasses", commonUtil.powerset(docClass));
		URL conceptURL = new URL(concept);
		parentJSON.put("Parents", parentConceptJSON);
		String spath = conceptURL.getPath();
		String host= conceptURL.getHost();
		String delims = "#";
		String[] nodeName = concept.split(delims);
		int n = nodeName.length;
		String name = nodeName[n-1];
		parentJSON.put("doi", concept);
		dlo.put(name+"@"+host+spath, parentJSON);
		
	}
	
	System.out.println(dlo.toString());
	return dlo;
	
}
}
