package com.controller;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteExpiredItems")
public class DeleteExpiredItemsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Forward GET requests to doPost
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1634");

            // SQL query to delete expired items
            String deleteSQL = "DELETE FROM STOCK WHERE expiry_date <= TO_DATE('30/03/2023', 'DD/MM/YYYY')";
            ps = con.prepareStatement(deleteSQL);

            // Execute the delete statement
            int rowsDeleted = ps.executeUpdate();
            response.getWriter().println(rowsDeleted + " expired items deleted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error deleting expired items: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
