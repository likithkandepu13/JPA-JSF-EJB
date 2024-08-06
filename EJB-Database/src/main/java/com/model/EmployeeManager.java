package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.entity.Employee;

@Stateless
public class EmployeeManager implements EmployeeRemote{

    public String saveData(Employee E) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kl", "root", "1634");
        PreparedStatement ps = con.prepareStatement("insert into employee values(?,?,?)");
        ps.setInt(1, E.getEid());
        ps.setString(2, E.getName());
        ps.setInt(3, E.getSal());
        ps.execute();
        con.close();        
        return "Saved Successfully";
    }

    public List<Employee> getData() throws Exception {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kl", "root", "1634");
        PreparedStatement ps = con.prepareStatement("select * from employee");
        ResultSet rs = ps.executeQuery();
        List<Employee> L = new ArrayList<Employee>();
        while (rs.next()) {
            Employee E = new Employee();
            E.setEid(rs.getInt(1));
            E.setName(rs.getString(2));
            E.setSal(rs.getInt(3));
            L.add(E);
        }
        con.close();
        return L;
    }

    public String updateData(Employee E) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kl", "root", "1634");
        PreparedStatement ps = con.prepareStatement("update employee set name=?, sal=? where eid=?");
        ps.setString(1, E.getName());
        ps.setInt(2, E.getSal());
        ps.setInt(3, E.getEid());
        ps.execute();
        con.close();
        return "Updated Successfully";
    }

    public String deleteData(Employee E) throws Exception {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kl", "root", "1634");
        PreparedStatement ps = con.prepareStatement("delete from employee where eid=?");
        ps.setInt(1, E.getEid());
        ps.execute();
        con.close();
        return "Record Deleted Successfully";
    }
}
