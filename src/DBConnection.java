import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

	public static Connection getConnection(){
		Connection connection = null;		
		try {
			 
			Class.forName("oracle.jdbc.driver.OracleDriver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("No Oracle JDBC Driver Found");
			e.printStackTrace();
		}
		
		try {
			 
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:orcl", "SYSTEM",
					"Pa55word");
 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
 
		}
		
		return connection;
	}
	
	
	}


