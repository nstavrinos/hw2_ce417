/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class ProductView extends HttpServlet {
 private static final long serialVersionUID = 1L;
    
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    String DB_URL="jdbc:mysql://localhost/new_database";
    String USER = "root";
    String PASS = "rootpassword";
    
   int check;
 public  ProductView(){
 super();
 }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
      response.setContentType("text/html");
      PrintWriter out=response.getWriter();
      
        try {
            Class.forName(JDBC_DRIVER);
            Connection con =  DriverManager.getConnection(DB_URL, USER, PASS);

            String sql;
            sql="select * from products";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
      
             out.println("<html>");
             out.println("<head>");
             out.println("<meta charset=\"UTF-8\">");
             out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
             out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">");
             out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>");			
             out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>");		
             out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>");
             out.println("</head>");            
             out.println("<body>"); 
             out.println("<div class=\"container\">");
             out.println("<h3 class=\"text-center\">List of Products</h3>");
             out.println("<hr>");
             out.println("<div class=\"container text-left\">");
             out.println("<a href=\"<%=request.getContextPath()%>/new\" class=\"btn btn-success\">Add New Product</a>");
             out.println("</div>");			
             out.println("<br>");		
             out.println("<table class=\"table table-bordered\">");
             out.println("<thead>");
             out.println("<tr>");
             out.println("<th>Name</th>");
             out.println("<th>Barcode</th>");
             out.println("<th>Color</th>");
             out.println("<th>Description</th>");
             out.println("</tr>");
             out.println("</thead>");
             
             out.println("<tbody>");
             
             while(rs.next()){
                    out.println("<tr>");
                    out.println("<td>" + rs.getString("name")+"</td>");
                    out.println("<td>" + rs.getString("barcode")+"</td>");
                    out.println("<td>" + rs.getString("color")+"</td>");
                    out.println("<td>" + rs.getString("description")+"</td>");
                    out.println("<tr>");
             }

             out.println("</tbody>");
             out.println("</div>");
             out.println("</body>");
             out.println("</html>");
            
        } catch (ClassNotFoundException ex) {
            
            Logger.getLogger(ProductSub.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
           
            Logger.getLogger(ProductSub.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
