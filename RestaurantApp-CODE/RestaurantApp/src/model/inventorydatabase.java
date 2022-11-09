package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;

//A class that connects to the Inventory table and does queries on it
public class InventoryDatabase extends Database{

	private static InventoryDatabase instance=null;
	
	private InventoryDatabase() {
		
	}
	
	public static InventoryDatabase getInstance()//use singleton design pattern
	{
		if(instance==null) 
		{
			instance=new InventoryDatabase();
		}
		return instance;
	}
	
    
    public boolean insertSupplyOrder(HashMap<String,Integer> supply)//Updated inventory by supply order
    {

    	String sql = "UPDATE inventory SET quantity = quantity+? WHERE productName=?  ";
    	
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	
        	for(Entry<String,Integer> e : supply.entrySet())
        	{
        		pstmt.setInt(1, e.getValue());
        		pstmt.setString(2, e.getKey());
        		pstmt.executeUpdate();
        	}

 
            // set the corresponding param
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean takeFromInventory(String productName,int quantity)//decreases from inventory the quantity
    {
    	String sql = "UPDATE inventory SET quantity = quantity-? WHERE productName=?  ";
    	
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
        	pstmt.setInt(1, quantity);
            pstmt.setString(2, productName); 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public HashMap<String,Integer> getInventory()//returns hashmap of all inventory in db,where key is the product and value is the quantity
    {
    	HashMap<String,Integer> inventory=new HashMap<String,Integer>();
    	String sql = "SELECT * FROM inventory";
    	
    	try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
              ResultSet rs    = stmt.executeQuery(sql)){
         	
         	while (rs.next()) 
         	{
         		inventory.put(rs.getString("productName"), rs.getInt("quantity"));
         	}

         	return inventory;
         	
    	}
    	
       catch (SQLException e) {
          System.out.println(e.getMessage());
      }
		return null;
    }
    public boolean insertProduct(String productName,int quantity)//add a product to db
    {
    	if(getInventory().containsKey(productName))
    	{
    		return false;
    	}
    	String sql = "INSERT INTO inventory(productName,quantity) VALUES(?,?)";
    	
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, productName);
            pstmt.setInt(2, quantity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    

    
	
}
