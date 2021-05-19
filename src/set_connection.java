import java.sql.*;


public class set_connection {

	Connection connection;
	PreparedStatement ps;
	
	public set_connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com/sql12384814","sql12384814","3DKnzyHUUx");
		}
		catch(Exception e) {
			System.out.print("Databse bc"+e);
		}
	}
	

}
