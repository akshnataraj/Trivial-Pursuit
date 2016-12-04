/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

/**
 *
 * @author Akshaya
 */
import mvc.model.login;
import mvc.dao.loginDao;
import mvc.controller.loginController;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class loginController extends HttpServlet
{
  public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
  {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String handle=request.getParameter("handle");
      String pwd=request.getParameter("pwd");
      login l=new login();      
      l.setUserName(handle);
      l.setPassword(pwd);
      int flag=0;
      loginDao ld=new loginDao();
      flag=ld.Studentauth(l);    
      if(flag==1)
             request.getRequestDispatcher("options.jsp").forward(request, response);
      else
             request.getRequestDispatcher("index.jsp").include(request, response);     
   }
} 