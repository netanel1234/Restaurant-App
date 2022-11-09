package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//A father class to all database classes
public abstract class Database {
	//connects the database
    protected Connection connect()
    {
        // SQLite connection string
        String url = "jdbc:sqlite:Resturant_Database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
