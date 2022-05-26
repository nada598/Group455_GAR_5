package DB;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB_Conn {
	String URL = "jdbc:mysql://localhost:3306/pet?useSSL=false";
	String USERNAME = "root";
	String PASSWORD = "Mysql";
	Connection connection;
	String sqlQuery = "";
	PreparedStatement preparedStmt=null;
	ResultSet resultSet= null;

	public DB_Conn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(DB_Conn.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	 public int deleteUser(int ID) {
		 sqlQuery = "Delete from user_pet WHERE idUser_Pet = " + ID;;
		 int i=0;
		 try{
		 preparedStmt = connection.prepareStatement(sqlQuery);
		 i = preparedStmt.executeUpdate(); 
		 }
		 catch(SQLException e){
		 System.out.print(e);
		 e.printStackTrace();
		 }
		 
		 return i;
		 }
	public boolean adopt(String description, String location, String phone,String email) {

		sqlQuery = 	 "insert into adopt (description, location, phone,email) VALUES ('"+description+"', '"+ location+"','"+ phone+"'  , '"+email+ "');";

		try {

			Statement stmt = connection.createStatement();
			stmt.execute(sqlQuery);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	
	}
	 public ResultSet getUser(String email, String pass) throws SQLException {
		 sqlQuery = "SELECT * FROM user_pet WHERE email =? , password=?";
		 PreparedStatement preparedsmt = connection.prepareStatement(sqlQuery);
		 preparedStmt.setString(1, email);
		 preparedStmt.setString(2, pass);
		 resultSet = preparedsmt.executeQuery();
		return resultSet;
	 }
	 
	 public boolean addUser(String fName, String lname, String email, String password) {

			sqlQuery = "insert into user_pet (username,email,password)values('" + fName + "','" + lname + "','"  +email + "','" + password
					+ "');";
			try {

				Statement stmt = connection.createStatement();
				stmt.execute(sqlQuery);

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		
		}
	
	
	 public ResultSet email(String description, String location, String phone,String email) {
		 sqlQuery = "Select * from adopt_pet where email='email'";
		 try{
		 preparedStmt = connection.prepareStatement(sqlQuery);
		 resultSet = preparedStmt.executeQuery();
		
	      
		 }catch(SQLException e){
		 System.out.println(e);
		 }
		 return resultSet;
		 }

	 
	
	 
	 public void close(){
		 try {
		 connection.close();
		 } catch (SQLException ex) {
		 Logger.getLogger(DB_Conn.class.getName()).log(Level.SEVERE, 
		null, ex);
		 }
		}
}
