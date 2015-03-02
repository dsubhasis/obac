/**
 * 
 */
package isi.ecsu.Util;

/**
 * @author subhasis
 *
 */
public class CommonConstant {

	/**
	 * 
	 */
	
	
	public static final String JDRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost/zabbix";
	public static final String USER = "root";
	public static final String PASS = "";
	/*
	 * Virtuoso database common constraints 
	 */
	
	public static final String virtUrl = "jdbc:virtuoso://localhost:1111";
	public static final String virtUser = "dba";
	public static final String virtPassword = "dba";
	public static final String topRelations = "";
	public static final String relation00 = "rdfs:subClassOf";
	public static final String relation01 = "";
	public static final String commonURI ="http://www.iwi-iuk.org/material/RDF/Schema/Class/scf#";
	public static final String masterStorage ="http://science.org";
	public static final String topConcept = "ScienceFields";
	public static final String prefix01 = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>";
	public static final String userHierarchy = "http://isirole.org";
	public static final String userGraphURL = "http://www.semanticweb.org/subhasis/ontologies/2014/6/untitled-ontology-15#Business";
	/*
	 * XACML Constraints or static values 
	 */
	
	public static final String XACML_SUBJECTID = "urn:oasis:names:tc:xacml:1.0:subject:subject-id";
	public static final String XACML_ACTIONID = "urn:oasis:names:tc:xacml:1.0:action:action-id";
	public static final String XACML_POLICY_DIR = "/Users/subhasis/Documents/workspace/Validator/policyFile/";
	public static final String XACML_POLICY_STORE = "/Users/subhasis/Documents/workspace/xacml.xml";
	
	public static final String PERM_DEFAULT_READ ="read";
	public static final int POLICY_DENSITY=10;
	
	
	public CommonConstant() {
		// TODO Auto-generated constructor stub
		  
		//// Prevents instantiation 
		
	}
	
	
	
	
			
	
	
	

}
