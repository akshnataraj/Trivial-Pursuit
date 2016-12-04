/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class examController extends HttpServlet{
    
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // JDBC driver name and database URL
      final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
      final String DB_URL="jdbc:mysql://localhost:3306/trivilapursuit";

      //  Database credentials
      final String USER = "root";
      final String PASS = "";
   
     Statement stmt=null;
     Connection conn =null;

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Your OWL Examination starts now.";
      String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
         out.println(docType +
         "<html>\n" +
         "<head><title>Exam</title></head>\n" +
         "<body bgcolor=\"bisque\">\n" +
         "<h1 align=\"center\">" + title + "</h1>\n");
         //out.println("<button onlick='setTimeout(myFunction,300000)'>Click to Start the test! You have 5 minutes to complete</button>");
      
     
         
         try{
         
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
      
         stmt = conn.createStatement();
         String sql;
         String i= request.getParameter("id");
         String p= request.getParameter("name");
         
            
             int n=0;
                String x="1";
   
            sql="select ques_id,question,choice_I,choice_II,choice_III,choice_IV from exam";
             ResultSet R=stmt.executeQuery(sql);  
            out.print("Welcome " + p );  
            out.print("<br>");
            out.print("<br>");  
            out.print("<form action='calculate' method='GET'>");
            while(R.next()){
                 String qid=R.getString("ques_id");
                 String q=R.getString("question");
                 String c1=R.getString("choice_I");
                 String c2=R.getString("choice_II"); 
                 String c3=R.getString("choice_III"); 
                 String c4=R.getString("choice_IV");
       
             out.print(qid+".");
             out.println(q);
             out.println("<br><input type='radio' value=\""+c1+"\" name=\""+x+"\">"+c1);
             out.println("<br><input type='radio' value=\""+c2+"\" name=\""+x+"\">"+c2); 
             out.println("<br><input type='radio' value=\""+c3+"\" name=\""+x+"\">"+c3);
             out.println("<br><input type='radio' value=\""+c4+"\" name=\""+x+"\">"+c4);
             //out.println(x);
             Integer j=Integer.parseInt(x);
		j++;
		x=j.toString();
                            
            out.print("<br>"); 
            out.print("<br>"); 

            }        
            request.setAttribute(i,"i");
            request.setAttribute(p,"p");
            
            out.print("<input type='submit' value='Submit answers'>");
            out.print("</form>");
            R.close();
            stmt.close();
            conn.close();
         } catch (ClassNotFoundException ex) {
          Logger.getLogger(examController.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SQLException ex) {
          Logger.getLogger(examController.class.getName()).log(Level.SEVERE, null, ex);
      }
         out.println("</body></html>");
         
         // Clean-up environment
         
      }
} 