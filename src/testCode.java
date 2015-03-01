/*
 * 
 */
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import com.sun.xacml.Target;
import com.sun.xacml.UnknownIdentifierException;
import com.sun.xacml.combine.OrderedPermitOverridesRuleAlg;
import com.sun.xacml.cond.FunctionTypeException;

import isi.ecsu.Util.mysqlJava;
import isi.ecsu.security.XACMLCreatePolicy;

/**
 * 
 */

/**
 * @author subhasis
 *
 */
public class testCode {

	/**
	 * @param args
	 * @throws FunctionTypeException 
	 * @throws URISyntaxException 
	 * @throws UnknownIdentifierException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws UnknownIdentifierException, URISyntaxException, FunctionTypeException, FileNotFoundException {
		// TODO Auto-generated method stub
 
		//System.out.println("Hello World !");
		URI combiningAlgId = new URI(OrderedPermitOverridesRuleAlg.algId);
		URI policyId = new URI("GeneratedPolicy");
		String xacmlStore = "/Users/subhasis/xacmlStore.xml";
		
		
		XACMLCreatePolicy xc = new XACMLCreatePolicy(policyId, combiningAlgId, "ram", "www", "ram", xacmlStore);
	}

}
