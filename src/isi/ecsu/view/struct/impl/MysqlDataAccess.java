/**
 * 
 */
package isi.ecsu.view.struct.impl;

import isi.ecsu.Util.CommonConstant;
import isi.ecsu.Util.mysqlJava;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import virtuoso.jena.driver.VirtGraph;

/**
 * @author subhasis
 *
 */
public class MysqlDataAccess implements StorageAccess {

	static final String JDBC_DRIVER = CommonConstant.JDRIVER;  
	static final String DB_URL = CommonConstant.DB_URL;

	//  Database credentials
	static final String USER = CommonConstant.USER;
	static final String PASS = CommonConstant.PASS;
	/* 
	 * 
	 */

	private String charT;
	private int result;
	private ResultSet resultSQL;
	
	private ArrayList<Array> ResultArray;
	private final static Logger LOGGER = Logger.getLogger(mysqlJava.class.getName());

	public MysqlDataAccess()
	{
             
	}

	public ResultSet executeQuerySQL(String charT) throws SQLException {
		result = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			LOGGER.info("Database Operation version 1.0");

			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			LOGGER.info("Connecting to database .........");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(charT);
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return rs;
	}

	private void showResultSet(ResultSet resultSet) throws SQLException {
		System.out.println("The columns in the table are: ");
		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
			System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
			System.out.println(resultSet.getMetaData().getColumnCount());
		}
	}
	/*
	 * @param 
	 * @ 
	 */
	public ArrayList<Array> getResultArrayList(String name, String query) throws SQLException{
		
		ResultArray = new ArrayList<Array>();
		resultSQL = this.executeQuerySQL(query);
		ResultArray.add(resultSQL.getArray(name));
		this.showResultSet(resultSQL);
		return ResultArray;
	}

	/* (non-Javadoc)
	 * @see isi.ecsu.view.struct.impl.StorageAccess#executeQuery(java.lang.String)
	 */
	public com.hp.hpl.jena.query.ResultSet executeQuery(String queryString)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see isi.ecsu.view.struct.impl.StorageAccess#displyResult(com.hp.hpl.jena.query.ResultSet)
	 */
	public void displyResult(com.hp.hpl.jena.query.ResultSet results) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see isi.ecsu.view.struct.impl.StorageAccess#displyResult(java.sql.ResultSet)
	 */
	

}



