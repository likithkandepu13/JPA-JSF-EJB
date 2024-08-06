package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateStockTable")
public class CreateStockTableServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Forward GET requests to doPost
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        Statement stmt = null;
        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1634");

            // SQL query to create the STOCK table
            String createTableSQL = "CREATE TABLE STOCK ("
                                  + "item_id VARCHAR(50) PRIMARY KEY, "
                                  + "item_name VARCHAR(100), "
                                  + "buyer_price DECIMAL(10, 2), "
                                  + "seller_price DECIMAL(10, 2), "
                                  + "expiry_date DATE, "
                                  + "no_of_stocks INT)";
            stmt = con.createStatement();
            stmt.executeUpdate(createTableSQL);
            response.getWriter().println("Table 'STOCK' created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error creating table: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
