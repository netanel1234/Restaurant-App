package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDatabase extends Database {
	
	private static LoginDatabase instance=null;
	
	private LoginDatabase() {
		
	}
	
	public static LoginDatabase getInstance()//use singleton design pattern
	{
		if(instance==null) {
			instance=new LoginDatabase();
		}
		return instance;
	}
 
	//insert new login info to database-by manager in event of adding a new employee
    public void insertLoginInfo(String userName, String pass)
    {
        String sql = "INSERT INTO login(userName,pass) VALUES(?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, pass);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
  //gets userName and pass strings and checks they got a match in login table
    public boolean loginAuthentication(String userName,String pass)
    {
        String sql = "SELECT userName, pass FROM login";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	if(rs.getString("userName").equals(userName)&&rs.getString("pass").equals(pass))
            		return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return false;
    }
    	
  //deletes login info from table by userName-by manger in event of deleting an employee
    public void delete(String userName)
    {
        String sql = "DELETE FROM login WHERE userName = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, userName);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    

	
	
}
