/**
 * 
 */
package isi.ecsu.security;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

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

	public void rendomPolicyGen(OntologyObject oo, int positiveChance) {
		List lNodeList = oo.getNodeList();
		Map lelement = oo.getNodeElement();
		int SizeOfData = lNodeList.size();
		ListIterator<Integer> litr = null;
		litr = lNodeList.listIterator();
		while (litr.hasNext()) {
			int randno = (int) (Math.random() * SizeOfData);
			if (randno <= positiveChance * SizeOfData / 100) {
				oo.getNodeElement().put(litr.next(), 1);
			} else {

				oo.getNodeElement().put(litr.next(), 0);
			}
		}

	}

}
