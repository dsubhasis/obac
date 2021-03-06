/**
 * 
 */
package isi.ecsu.Util;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * @author subhasis
 *
 */
public class commonUtil {

	
	public commonUtil(){
		// Constructor 
	}
	
	public static VirtGraph virtConnect(String userName, String passWord, String virtUrl){
		VirtGraph vt = new VirtGraph(virtUrl, userName, passWord);
		return vt ;
	}
	public static ResultSet executeQuery(String queryString, VirtGraph vt){
		Query query = QueryFactory.create(queryString);
		QueryExecution qet = VirtuosoQueryExecutionFactory.create(query, vt);
		//System.out.println(qet.getQuery());
		ResultSet results = qet.execSelect();
		Model model = ModelFactory.createDefaultModel();
		return results;
		
	}
	public static void displyResult(ResultSet results)
	{
		
		Model model = ModelFactory.createDefaultModel();
		//ResultSetFormatter.out(results); //.outputAsTSV(System.out, results);
		ResultSetFormatter.asRDF(model, results);
		model.write(System.out, "RDF/XML-ABBREV");
		
		
		
	}
	public static String queryListSubClassNode(String graphName , String parentURI, String relation00 , String Prefix){
		
		String query;
		
		query = Prefix + " SELECT ?cls FROM <"+ graphName +"> WHERE {  ?cls " + relation00 + "<"+ parentURI +"> }";
		return query;
	}
public static String queryListParentClassNode(String graphName , String childURI, String relation00 , String Prefix){
	
		String query = null;
		query = Prefix + " SELECT ?cls FROM <"+ graphName +"> WHERE { <"  + childURI +">  " +relation00 + "  ?cls}";
		return query;
	}
public static String queryListTopClassNode(String graphName , String parentURI, String relation00, String relation01){
		String query;
		
		query = "SELECT ?cls FROM <"+ graphName +"> WHERE { ?cls a " + relation00  + " }";
		return query;
	}
	
	public static String queryListSuperClassNode(String graphName ){
		return graphName;
		
	}
	
public static String queryListofFirstRelationChild(String graphName , String parentURI){
		
		String query;
		
		query = "SELECT ?cls FROM <"+ graphName +"> WHERE { ?cls a owl:Class . ?cls rdfs:subClassOf <"+ parentURI +"> }";
		return query;
	}
public static String queryListofFirstRelationParants(String graphName , String parentURI){
	
	String query;
	
	query = "SELECT ?cls FROM <"+ graphName +"> WHERE { ?cls a owl:Class . ?cls rdfs:subClassOf <"+ parentURI +"> }";
	return query;
}
public static <T> List<List<T>> powerset(Collection<T> list) {
	  List<List<T>> ps = new ArrayList<List<T>>();
	  ps.add(new ArrayList<T>());   // add the empty set
	 
	  // for every item in the original list
	  for (T item : list) {
	    List<List<T>> newPs = new ArrayList<List<T>>();
	 
	    for (List<T> subset : ps) {
	      // copy all of the current powerset's subsets
	      newPs.add(subset);
	 
	      // plus the subsets appended with the current item
	      List<T> newSubset = new ArrayList<T>(subset);
	      newSubset.add(item);
	      newPs.add(newSubset);
	    }
	 
	    // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
	    ps = newPs;
	  }
	  return ps;
	}

	
}
