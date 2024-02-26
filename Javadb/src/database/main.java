package database;

/*
 1.import package ---->java.sqlb 
 2. load aand register driver
 3. create conection
 4. create a statement
 5. execute the query
 6.process the results
 7.close the connection
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class main {

	public static void main(String[] args) throws Exception{
	
		String url ="jdbc:mysql://localhost:3306/spam";
		String uname ="root";
		String pass="root";
		String query = "select  name from virus where id=2";
		
//Class.forName("com.mysql.cj.jdbc.Driver");
Connection con = DriverManager.getConnection(url,uname,pass);
if (con==null) {
System.out.println("Connection is not established!!");
}else {
	System.out.println("Sucessfull established connection with database!!!!!");	
}
 Statement st = con.createStatement();
 ResultSet rs = st.executeQuery(query);
 rs.next();
 String name = rs.getString("name");
 System.out.println(name);
 st.close();
 con.close();

	
	}
}