/**
 * 
 */
package isi.ecsu.Util;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author subhasis
 *
 */
public class mysqlJava {

	static final String JDBC_DRIVER = CommonConstant.JDRIVER;  
	static final String DB_URL = CommonConstant.DB_URL;

	//  Database credentials
	static final String USER = CommonConstant.USER;
	static final String PASS = CommonConstant.PASS;
	/**
	 * 
	 */

	private String charT;
	private int result;
	private final static Logger LOGGER = Logger.getLogger(mysqlJava.class.getName());

	/**
	 * @param charT
	 * @throws SQLException 
	 */
	public  mysqlJava(String charT) throws SQLException {

		
		result = 0;
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			LOGGER.info("Database Operation version 1.0");
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			LOGGER.info("Connecting to database .........");
			stmt = conn.createStatement();
			//String sql;
			//sql = "show databases";
			ResultSet rs = stmt.executeQuery(charT);
			writeResultSet(rs);
			
			

		} catch (ClassNotFoundException e) {

           LOGGER.log(Level.SEVERE, e.getMessage(), e);
			
		}




	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		
		System.out.println("The columns in the table are: ");
	    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
	    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
	      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
	      System.out.println(resultSet.getMetaData().getColumnCount());
	    }
		
	}







}
