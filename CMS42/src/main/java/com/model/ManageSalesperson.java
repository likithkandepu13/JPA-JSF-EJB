package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ManageSalesperson extends Salesperson {

    public void addSalesperson() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms42", "root", "daddy12345")) {
            String query = "INSERT INTO Salesperson (salespersonid, name, dateofjoining, salary) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, getSalespersonid());
                ps.setString(2, getName());
                ps.setDate(3, getDateofjoining());
                ps.setLong(4, getSalary());
                
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Salesperson details inserted successfully");
                } else {
                    throw new Exception("Failed to insert salesperson details");
                }
            }
        }
    }

    public boolean SalespersonidExists(int salespersonid) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms42", "root", "daddy12345")) {
            String query = "SELECT COUNT(*) FROM Salesperson WHERE salespersonid = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, salespersonid);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            }
        }
        return false;
    }
}
