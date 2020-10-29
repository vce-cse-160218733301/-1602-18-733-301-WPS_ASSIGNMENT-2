package payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class mysqlbean {
	private Connection con;
  public Connection authenticate(String uid) {
	  try {
		  String url="jdbc:mysql://localhost:3306/test";
		  String dbusername="root";
		  String dbpassword="";
		  Class.forName("com.mysql.cj.jdbc.Driver");
	      con=DriverManager.getConnection(url,dbusername,dbpassword);  
	  }
	  catch(Exception e) {
		  e.printStackTrace();
		  
	  }
	return con;
	
 
  }
}
