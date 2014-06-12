/**
 * 
 */
package isi.ecsu.security;

import com.sun.xacml.ctx.RequestCtx;

/**
 * @author subhasis
 *
 */
public class XACMLCredentials {
	
	private String Responce;
	private String[] policyFile;
	private RequestCtx requestUser;
	private String subjectName;
	private String objectName;
	public XACMLCredentials(String[] policyFile, String subjectName,
			String objectName) {
		
		this.policyFile = policyFile;
		this.subjectName = subjectName;
		this.objectName = objectName;
		
		
	}
	
	public String getSecurityResponse(final String subjectName, final String objectName, final String[] policyFile)
	{
		String response = null;
		
		xacmlRequest xR = new xacmlRequest(objectName, policyFile);
		response = xR.getPermissionReply();
	
		
		return response; 
		
		
	}
	
	

}
