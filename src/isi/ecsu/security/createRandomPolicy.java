/**
 * 
 */
package isi.ecsu.security;

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
	
	public rendomPolicyGen(int SizeOfDatta, int positiveChance, ViewObject vo)
	{
		
		
    int n = 0, m = 0;
	for(int i = 0; i< SizeOfDatta; i++)
	{
	int randno =  (int)(Math.random()*SizeOfDatta);
	if (randno <= positiveChance*SizeOfDatta/100)
	{
            ++m; 
    
	}
	else{
		++n;
	}
    
	
}
	System.out.println("A "+m + " B "+n);

}
		
	}
	

}
