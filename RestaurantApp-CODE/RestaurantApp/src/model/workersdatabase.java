package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Employee;
import model.Manager;
import model.Waiter;

//A class that connects to the Workers table and does queries on it
public class WorkersDatabase extends Database
{
	
	private static WorkersDatabase instance=null;
	
	private WorkersDatabase() 
	{
		
	}
	
	public static WorkersDatabase getInstance() //use singleton design pattern
	{
		if(instance==null) {
			instance=new WorkersDatabase();
		}
		return instance;
	}
	
	
	//insert new worker info to database-by manager
    public void insertWorkerInfo(Employee worker)
    {
        String sql = "INSERT INTO workers(userName,isManager,salarySum,salaryPerHour) VALUES(?,?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, worker.getUserName());
            if(worker.getClass().toString().equalsIgnoreCase("class model.Manager"))
            	pstmt.setBoolean(2, true);
            else
            	pstmt.setBoolean(2, false);
            pstmt.setDouble(3, 0);
            pstmt.setDouble(4, worker.getsalaryPerHour());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
  //deletes worker info from table by userName-by manger
    public boolean delete(String userName)
    {
    	if(getEmployee(userName)==null)//if employee userName is in DB
    	{
    		return false;
    	}
    	
        String sql = "DELETE FROM workers WHERE userName = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, userName);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    
    //Updates a waiter to become manager
    public boolean updateToManager(String userName,double newSalary) {
    	
        String sql = "UPDATE workers SET isManager = ? ,salaryPerHour=? "
                + "WHERE userName = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setBoolean(1, true);
            pstmt.setDouble(2, newSalary);
            pstmt.setString(3, userName);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    //Updates an employee salary-by manager
    public boolean updateSalaryToEmployee(String userName,double newSalary)
    {
    	
        String sql = "UPDATE workers SET salaryPerHour=? "
                + "WHERE userName = ?";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setDouble(1, newSalary);
            pstmt.setString(2, userName);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    //After an employee logged out,this will update their total income
    public boolean updateTotalSalary(String userName,double todayIncome)
    {
        String sql = "UPDATE workers SET salarySum =salarySum+ ?  "
                + "WHERE userName = ?";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setDouble(1, todayIncome);
            pstmt.setString(2, userName);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
  //gets a userName and returns an object of an employee,for log in or to check if an employee userName is already taken 
    public User getEmployee(String userName) 
    {
    	String sql = "SELECT userName,isManager,salarySum,salaryPerHour FROM workers";
    	
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
        	
        	while (rs.next()) {
        		if(rs.getString("userName").equals(userName))
		        	if(rs.getBoolean("isManager")) {
		        		return new Manager(rs.getString("userName"),rs.getDouble("salaryPerHour"));
		        	}
		        	else {
		        		return new Waiter(rs.getString("userName"),rs.getDouble("salaryPerHour"));
		        	}
        	}
               

           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
        return null;
       
    }
    public double getSalaryCount(String userName)
    {
    	String sql = "SELECT userName,isManager,salarySum,salaryPerHour FROM workers";
    	
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
        	
        	while (rs.next()) {
        		if(rs.getString("userName").equals(userName))
        		{
        			return rs.getDouble("salarySum");
        		}

        	}
               

           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
        return -1;
    	
    }
    

}
