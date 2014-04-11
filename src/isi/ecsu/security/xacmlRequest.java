/**
 * 
 */
package isi.ecsu.security;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import com.sun.xacml.PDP;
import com.sun.xacml.PDPConfig;
import com.sun.xacml.ParsingException;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.finder.PolicyFinder;
import com.sun.xacml.finder.impl.FilePolicyModule;

/**
 * @author subhasis
 *
 */
public class xacmlRequest {

	private String requestFile;
	private String[] policyFile;
	private String permissionReply;
	/**
	 * @param requestFile
	 * @param policyFile
	 */
	public xacmlRequest(String requestFile, String[] policyFile) {

		this.requestFile = requestFile;
		this.policyFile = policyFile;
	}
	/**
	 * @return the requestFile
	 * @throws ParsingException 
	 * @throws FileNotFoundException 
	 */

	private ResponseCtx getPermissionValue(String[] policyFiles, String requestFile) throws FileNotFoundException, ParsingException
	{
		FilePolicyModule filePolicyModule = new FilePolicyModule();
		for (int i = 0; i < policyFiles.length; i++)
			filePolicyModule.addPolicy(policyFiles[i]);

		PolicyFinder policyFinder = new PolicyFinder();
		Set policyModules = new HashSet();
		policyModules.add(filePolicyModule);
		policyFinder.setModules(policyModules);
		PDP pdp = new PDP(new PDPConfig(null, policyFinder, null));
		RequestCtx request =
				RequestCtx.getInstance(new FileInputStream(requestFile));
		ResponseCtx response = pdp.evaluate(request);
		
		
		return response;
	}


	public final String getRequestFile() {
		return requestFile;
	}
	/**
	 * @param requestFile the requestFile to set
	 */
	public final void setRequestFile(String requestFile) {
		this.requestFile = requestFile;
	}
	/**
	 * @return the policyFile
	 */
	public final String[] getPolicyFile() {
		return policyFile;
	}
	/**
	 * @param policyFile the policyFile to set
	 */
	public final void setPolicyFile(String[] policyFile) {
		this.policyFile = policyFile;
	}
	/**
	 * @return the permissionReply
	 */
	public final String getPermissionReply() {
		return permissionReply;
	}
	/**
	 * @param permissionReply the permissionReply to set
	 */
	public final void setPermissionReply(String permissionReply) {
		this.permissionReply = permissionReply;
	} 







}
