package com.example.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ProductSub extends HttpServlet {
  private static final long serialVersionUID = 1L;
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    String DB_URL="jdbc:mysql://localhost/new_database";
    String USER = "root";
    String PASS = "rootpassword";
   
    int check;
    public  ProductSub(){
        super();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
      response.setContentType("text/html");
      PrintWriter out=response.getWriter();
    
        try {
            Class.forName(JDBC_DRIVER);
            Connection con =  DriverManager.getConnection(DB_URL, USER, PASS);
            String name= request.getParameter("name");
            String barcode= request.getParameter("barcode");
            String color= request.getParameter("color");
            String description= request.getParameter("description");
          
            PreparedStatement pst=con.prepareStatement("insert into products(name,barcode,color,description)values(?,?,?,?) ");
            pst.setString(1, name);
            pst.setString(2, barcode);
            pst.setString(3, color);
            pst.setString(4, description);
            check= pst.executeUpdate();
            
            if(check>0){
                out.println("Submiton done succefully");
            }else{
                out.println("Submiton done unsuccefully");
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSub.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductSub.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }



}
