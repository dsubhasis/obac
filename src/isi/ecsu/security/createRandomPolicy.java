/**
 * 
 */
package isi.ecsu.security;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.combine.OrderedPermitOverridesRuleAlg;
import com.sun.xacml.cond.FunctionTypeException;

import isi.ecsu.Util.CommonConstant;
import isi.ecsu.view.struct.OntologyObject;
import isi.ecsu.view.struct.ViewObject;

/**
 * @author subhasis
 * 
 */
public class createRandomPolicy {

	/**
	 * 
	 */
	public createRandomPolicy() {

		// TODO Auto-generated constructor stub
	}

	public void rendomPolicyGen(OntologyObject oo, int positiveChance,
			String objectName, String xacmlStore, String actionValue)
			throws FileNotFoundException, UnknownIdentifierException, URISyntaxException, FunctionTypeException {
		List lNodeList = oo.getNodeList();
		Map lelement = oo.getNodeElement();
		XACMLCreatePolicy xcpl = new XACMLCreatePolicy();
		
		URI combiningAlgId = new URI(OrderedPermitOverridesRuleAlg.algId);
		int SizeOfData = lNodeList.size();
		ListIterator<String> litr = null;
		litr = lNodeList.listIterator();
		while (litr.hasNext()) {
			int randno = (int) (Math.random() * SizeOfData);
			if (randno <= positiveChance * SizeOfData / 100) {
				URI policyId = new URI(objectName+litr.next());
				
				
				oo.getNodeElement().put(litr.next(), 1);
				
				xcpl.XACMLPolicyUpdate(policyId, combiningAlgId, actionValue, objectName, litr.next(), xacmlStore);
				
				
			} else {

				oo.getNodeElement().put(litr.next(), 0);
			}
		}

	}

	public void XacmlPolicyGen(String objectRoot, String objectGraph,
			String objectUri, String subjectRoot, String subjectGraph,
			String subjectUri) throws FileNotFoundException, UnknownIdentifierException, URISyntaxException, FunctionTypeException {
		OntologyObject lObject = new OntologyObject();
		OntologyObject lSubject = new OntologyObject();

		TraverseOntology tOnto = new TraverseOntology();
		

		try {
			lObject = tOnto.getUserView(objectRoot, objectGraph, objectUri);
			lSubject = tOnto.getUserView(subjectRoot, subjectGraph, subjectUri);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListIterator<String> litrSubj = null;
		ListIterator<String> litrObj = null;
		String lobject;
		
		while(litrObj.hasNext())
		{
			lobject = litrObj.next();
			
			this.rendomPolicyGen(lObject, CommonConstant.POLICY_DENSITY, lobject, CommonConstant.XACML_POLICY_STORE, CommonConstant.PERM_DEFAULT_READ);
			
			
		}

	}

}
