/**
 * 
 */
package isi.ecsu.security;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.server.UID;
import java.util.Iterator;
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

	public String rendomPolicyGen(OntologyObject oo, int positiveChance,
			String subjectName, String xacmlStore, String actionValue)
			throws FileNotFoundException, UnknownIdentifierException,
			URISyntaxException, FunctionTypeException {
		List lNodeList = oo.getNodeList();
		Map lelement = oo.getNodeElement();
		XACMLCreatePolicy xcpl = new XACMLCreatePolicy();
        String output = null;
		URI combiningAlgId = new URI(OrderedPermitOverridesRuleAlg.algId);
		int SizeOfData = lNodeList.size();
		ListIterator<String> litr = null;
		litr = lNodeList.listIterator();
		while (litr.hasNext()) {
			int randno = (int) (Math.random() * SizeOfData);
		/*	if (randno <= positiveChance * SizeOfData / 100) {
				URI policyId = new URI(litr.next());

				oo.getNodeElement().put(litr.next(), 1);

				xcpl.XACMLPolicyUpdate(policyId, combiningAlgId, actionValue,
						objectName, litr.next(), xacmlStore);

			} else {

				oo.getNodeElement().put(litr.next(), 0);
			}*/
			
			
			UID userId = new UID();
			URI policyId = new URI("Policy"+userId.toString());
			String xacmlStoreFile = xacmlStore+userId+".xml";

			//oo.getNodeElement().put(litr.next(), 1);

			output = xcpl.XACMLPolicyUpdate(policyId, combiningAlgId, actionValue,
					subjectName, litr.next(), xacmlStoreFile);
			
		}
		return output;

	}

	public void XacmlPolicyGen(String objectRoot, String objectGraph,
			String objectUri, String objectPrefix,
			String objectOntologyRelation, String subjectRoot,
			String subjectGraph, String subjectUri, String subjectPrefix,
			String subjectOntologyRelation) throws FileNotFoundException,
			UnknownIdentifierException, URISyntaxException,
			FunctionTypeException {
		OntologyObject lObject = new OntologyObject();
		OntologyObject lSubject = new OntologyObject();

		TraverseOntology tOnto = new TraverseOntology();
		String xacmlStore = CommonConstant.XACML_POLICY_STORE+"dummy-Policy-file";

		try {
			lObject = tOnto.getUserView(objectRoot, objectGraph, objectUri, objectPrefix, objectOntologyRelation);
			lSubject = tOnto.getUserView(subjectRoot, subjectGraph, subjectUri, subjectPrefix, subjectOntologyRelation);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<String> litrSubj = null;
		//ListIterator<String> litrObj = null;
		String lobject ;
		litrSubj = lSubject.getNodeList().listIterator();

		while (litrSubj.hasNext()) {
			lobject = litrSubj.next();
			//System.out.println(lobject);
			

			String outPut = rendomPolicyGen(lObject, CommonConstant.POLICY_DENSITY,
				lobject, xacmlStore,
					CommonConstant.PERM_DEFAULT_READ);
			System.out.println(outPut);

		}

	}

}
