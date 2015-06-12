package isi.ecsu.view.struct;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class visitLog {
	/**
	 * @return the visitNodeList
	 */
	public final Map<String, Map> getVisitNodeList() {
		return VisitNodeList;
	}

	/**
	 * @param visitNodeList the visitNodeList to set
	 */
	public final void setVisitNodeList(Map<String, Map> visitNodeList) {
		VisitNodeList = visitNodeList;
	}

	List<Map> UnVisited;
	
	
	
	
	
	
	
	Map<String, Integer> ParentPermissionMap;
	Map<String, Map> VisitNodeList;
	public visitLog() {
		UnVisited = new LinkedList();
		
		
		VisitNodeList = new HashMap<String, Map>();
	}


	public Map<String, Map> addPermission(String node, String parentNode, int perM)
	{
		ParentPermissionMap = new HashMap<String, Integer>();
		
		if(!VisitNodeList.containsValue(node))
		{
			ParentPermissionMap.put(parentNode, perM);
			VisitNodeList.put(node, ParentPermissionMap);
		}
		else{
			Map<String, Integer> tempParentPerm = VisitNodeList.get(node);
			if(!tempParentPerm.containsKey(parentNode))
			{
				
				tempParentPerm.put(parentNode, perM);
				
			}
		}
		return VisitNodeList;
		
	}
	public final void setUnVisitedNodeName(String node, Integer perm)
	{
		
		boolean duplicate = false;
		for(int i = 0; i < UnVisited.size(); i++ )
		{
		
		if(UnVisited.get(i).containsKey(node)){
		 duplicate = true;
		
		}
		}
		if(!duplicate)
		{
			Map unVisitedNode = new HashMap();
			unVisitedNode.put("node", node);
			unVisitedNode.put("value", perm);
			UnVisited.add(unVisitedNode);
		}
		
	}

	/**
	 * @return the unVisited
	 */
	public final List<Map> getUnVisited() {
		return UnVisited;
	}
	

}
