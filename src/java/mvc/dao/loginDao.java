/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.dao;

/**
 *
 * @author Akshaya
 */
import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import mvc.model.login;



public class loginDao {
        

    public int Studentauth(login l) {
     //To change body of generated methods, choose Tools | Templates.
        final String JDBC_DRIVER="com.mysql.jbc.Driver";
        final String DB_URL="jdbc:mysql://localhost:3306/trivialpursuit";
        final String USER = "root";
        final String PASS = "";
        Connection  conn = null;
        Statement stmt = null;
        String handle = l.getUserName();
        String pwd = l.getPassword();
        try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         // Open a connection
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         // Execute SQL query
          stmt = conn.createStatement();
         String sql;
         sql = "SELECT handle,password FROM userDB";
         ResultSet rs = stmt.executeQuery(sql);
         // Extract data from result set
         while(rs.next())
         {
            //Retrieve by column name
            //int id  = rs.getInt("id");
            //int age = rs.getInt("age");
            String first = rs.getString("handle");
            String last = rs.getString("password");                              
           if(handle.equals(first)&& pwd.equals(last))            
              return 1;         
        }
       }
       catch(SQLException se)
       {
         //Handle errors for JDBC
          se.printStackTrace();
       }
       catch(Exception e)
       {
         //Handle errors for Class.forName
         e.printStackTrace();
       }         
       return 0;
    }
    }
    
    

