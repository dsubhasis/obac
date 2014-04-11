import java.sql.SQLException;

import isi.ecsu.Util.mysqlJava;

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
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
		System.out.println("Hello World !");
		
		try {
			mysqlJava ms = new mysqlJava("Select * from hosts");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
