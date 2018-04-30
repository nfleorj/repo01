import java.sql.Connection;
import java.sql.DriverManager;


public class Conexao {
	
	public static Connection conn()throws Exception {
		
		Connection connection = null;
		
		try {
			
			// connection = DriverManager.getConnection("jdbc:oracle:thin:@desenv_amil:1521:des_amil", "TS_GER", "TS_GER");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@dsisamil.amil.com.br:1521:DTOP_NOVO", "TS", "xpto2000");
			
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:mkyong", "username",
					"password");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
		
	}

}
