package isi.ecsu.view.struct;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class visitLog {
	List<String> UnVisited;
	
	Map<String, Integer> ParentPermissionMap;
	Map<String, Map> VisitNodeList;
	public visitLog() {
		UnVisited = new LinkedList();
		
		ParentPermissionMap = new HashMap<String, Integer>();
		VisitNodeList = new HashMap<String, Map>();
	}
	
	/**
	 * @return the unVisited
	 */
	public final List<String> getUnVisited() {
		return UnVisited;
	}

	/**
	 * @param unVisited the unVisited to set
	 */
	public final void setUnVisited(List<String> unVisited) {
		UnVisited = unVisited;
	}

	public Map<String, Map> addPermission(String node, String parentNode, int perM)
	{
		
		
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

}
