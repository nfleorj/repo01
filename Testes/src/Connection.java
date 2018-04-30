import java.sql.DriverManager;
import java.sql.SQLException;


public class Connection {
	
	private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	
	/**
	 * 
	 * @param jdbcDriver
	 * @param dbUurl
	 * @param user
	 * @param pass
	 * @return
	 * @throws SQLException
	 */
	public static java.sql.Connection connOracle(String dbUurl, String user, String pass) throws SQLException {
			
		java.sql.Connection conn = null;
	    
	    try {
	    	   
	      //STEP 2: Register JDBC driver
	      Class.forName(JDBC_DRIVER);

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(dbUurl, user, pass);
	      

	    } catch (Exception e) {
			e.printStackTrace();
		} 
	    
	    return conn;		
	}
}
