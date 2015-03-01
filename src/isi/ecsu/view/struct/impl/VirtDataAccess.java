/**
 * 
 */
package isi.ecsu.view.struct.impl;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import isi.ecsu.Util.CommonConstant;
import isi.ecsu.Util.commonUtil;


/**
 * @author subhasis
 *
 */
public class VirtDataAccess implements StorageAccess{
	
	/**
	 * @param dataConnectionURL
	 * @param virtUserName
	 */
	private String dataConnectionURL;
	private String virtUserName;
	private String password;
	
	
	public VirtDataAccess(String dataConnectionURL, String virtUserName) {
		this.dataConnectionURL = dataConnectionURL;
		this.virtUserName = virtUserName;
	}
	
	/**
	 * 
	 */
	public VirtDataAccess() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the dataConnectionURL
	 */
	public String getDataConnectionURL() {
		return dataConnectionURL;
	}
	/**
	 * @param dataConnectionURL the dataConnectionURL to set
	 */
	public void setDataConnectionURL(String dataConnectionURL) {
		this.dataConnectionURL = dataConnectionURL;
	}
	/**
	 * @return the virtUserName
	 */
	public String getVirtUserName() {
		return virtUserName;
	}
	/**
	 * @param virtUserName the virtUserName to set
	 */
	public void setVirtUserName(String virtUserName) {
		this.virtUserName = virtUserName;
	}
	
	private VirtGraph virtConnect(){ 
		String userName = CommonConstant.virtUser;
		String passWord = CommonConstant.virtPassword; 
		String virtUrl = CommonConstant.virtUrl;
		VirtGraph vt = new VirtGraph(virtUrl, userName, passWord);
		return vt ;
	}
	public com.hp.hpl.jena.query.ResultSet executeQuery(String queryString) throws Exception{
		VirtGraph vt = this.virtConnect();
		Query query = QueryFactory.create(queryString);
		QueryExecution qet = VirtuosoQueryExecutionFactory.create(query, vt);
		//System.out.println(qet.getQuery());
		ResultSet results = qet.execSelect();
		Model model = ModelFactory.createDefaultModel();
		return results;
		
	}
	public void displyResult(ResultSet results)
	{
		
		Model model = ModelFactory.createDefaultModel();
		//ResultSetFormatter.out(results); //.outputAsTSV(System.out, results);
		ResultSetFormatter.asRDF(model, results);
		model.write(System.out, "RDF/XML-ABBREV");
		
		
		
	}
	public static String queryListSubClassNode(String graphName , String parentURI){
		
		String query;
		
		query = "SELECT ?cls FROM <"+ graphName +"> WHERE { ?cls a owl:Class . ?cls rdfs:subClassOf <"+ parentURI +"> }";
		return query;
	}
	public static String queryListSuperClassNode(String graphName ){
		
		String query; 
		
		
		return graphName;
		
	}

	/* (non-Javadoc)
	 * @see isi.ecsu.view.struct.impl.StorageAccess#executeQuerySQL(java.lang.String)
	 */
	public java.sql.ResultSet executeQuerySQL(String charT) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see isi.ecsu.view.struct.impl.StorageAccess#getResultArrayList(java.lang.String, java.lang.String)
	 */
	public ArrayList<Array> getResultArrayList(String name, String query)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see isi.ecsu.view.struct.impl.StorageAccess#displyResult(java.sql.ResultSet)
	 */
	

	
	

}
