/**
 * 
 */
package isi.ecsu.view.struct.impl;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import virtuoso.jena.driver.VirtGraph;

/**
 * @author subhasis
 *
 */
public interface StorageAccess {
	
	public com.hp.hpl.jena.query.ResultSet executeQuery(String queryString, VirtGraph vt) throws Exception;
	public ResultSet executeQuerySQL(String charT) throws SQLException ;
	public ArrayList<Array> getResultArrayList(String name, String query) throws SQLException;
	public void displyResult(com.hp.hpl.jena.query.ResultSet results);
	public VirtGraph virtConnect();

}
