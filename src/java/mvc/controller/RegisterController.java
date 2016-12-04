/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akshaya
 */
public class RegisterController extends HttpServlet {
    
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
        final String DB_URL="jdbc:mysql://localhost:3306/trivialpursuit";

      //  Database credentials
        final String USER = "root";
        final String PASS = "";
   
        Statement stmt=null;
        Connection conn =null;
        String title = "Register Here.";
//        String docType =
//        "<!doctype html public \"-//w3c//dtd html 4.0 " +
//         "transitional//en\">\n";
//         out.println(docType +
//         "<html>\n" +
//         "<head><title>Exam</title></head>\n" +
//         "<body bgcolor=\"#f0f0f0\">\n" +
//         "<h1 align=\"center\">" + title + "</h1>\n");
         
         
         String name=request.getParameter("name"); 
         String handle=request.getParameter("handle"); 
         String email=request.getParameter("email"); 
         String password=request.getParameter("password"); 
         String age=request.getParameter("age"); 
         
         try{
         
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
      
         stmt = conn.createStatement();
         String sql;
         sql="insert into userDB values('"+name+"','"+handle+"','"+email+"','"+password+"','"+age+"')";
         stmt.executeUpdate(sql);
//         out.print("<input type='submit' value='Register'>");
//         out.println("</form>");
         stmt.close();
         conn.close();
    
        }
         
        catch (ClassNotFoundException ex) {
          Logger.getLogger(examController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) {
          Logger.getLogger(examController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("redirect.jsp").forward(request,response);
  }
}
