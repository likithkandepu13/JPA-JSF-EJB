package com.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.math.BigDecimal;

public class AddItemsServlet extends HttpServlet {

    // JDBC URL, username, and password of Oracle server
    private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String dbUser = "system";
    private static final String dbPassword = "1634";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Forward GET requests to doPost
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle both GET (display form) and POST (process form submission)
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (request.getParameter("item_id") == null) {
            // Display the form if item_id parameter is not present
            displayForm(out);
        } else {
            // Process form submission if item_id parameter is present
            processForm(request, out);
        }
    }

    private void displayForm(PrintWriter out) {
        out.println("<html><body>");
        out.println("<h1>Add Items</h1>");
        out.println("<form action=\"AddItemsServlet\" method=\"post\">");
        out.println("Item ID: <input type=\"text\" name=\"item_id\" required><br><br>");
        out.println("Item Name: <input type=\"text\" name=\"item_name\" required><br><br>");
        out.println("Buyer Price: <input type=\"text\" name=\"buyer_price\" required><br><br>");
        out.println("Seller Price: <input type=\"text\" name=\"seller_price\" required><br><br>");
        out.println("Date of Expiry: <input type=\"date\" name=\"expiry_date\" required><br><br>");
        out.println("Number of Stocks Available: <input type=\"number\" name=\"no_of_stocks\" required><br><br>");
        out.println("<input type=\"submit\" value=\"Add Item\">");
        out.println("</form>");
        out.println("</body></html>");
    }

    private void processForm(HttpServletRequest request, PrintWriter out) throws ServletException {
        // Handle form submission and database insertion
        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Getting data from the form
            int item_id = Integer.parseInt(request.getParameter("item_id"));
            String item_name = request.getParameter("item_name");
            BigDecimal buyer_price = new BigDecimal(request.getParameter("buyer_price"));
            BigDecimal seller_price = new BigDecimal(request.getParameter("seller_price"));
            Date expiry_date = Date.valueOf(request.getParameter("expiry_date"));
            int no_of_stocks = Integer.parseInt(request.getParameter("no_of_stocks"));

            // Opening database connection
            con = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // Creating SQL query to insert data into STOCK table
            String sql = "INSERT INTO STOCK (item_id, item_name, buyer_price, seller_price, expiry_date, no_of_stocks) VALUES (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, item_id);
            ps.setString(2, item_name);
            ps.setBigDecimal(3, buyer_price);
            ps.setBigDecimal(4, seller_price);
            ps.setDate(5, expiry_date);
            ps.setInt(6, no_of_stocks);

            // Executing SQL query to insert data
            int rowsAffected = ps.executeUpdate();

            // Providing feedback to user
            out.println("<html><body>");
            out.println("<h2>Item added successfully!</h2>");
            out.println("<p>Rows affected: " + rowsAffected + "</p>");
            out.println("</body></html>");

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ServletException(ex);
        } finally {
            // Closing JDBC objects
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new ServletException(ex);
            }
        }
    }
}
