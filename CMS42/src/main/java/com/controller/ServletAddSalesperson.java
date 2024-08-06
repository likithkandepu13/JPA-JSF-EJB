package com.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.ManageSalesperson;

public class ServletAddSalesperson extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletAddSalesperson() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This method can be left empty or you can add code to handle GET requests if needed.
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        int salespersonid = Integer.parseInt(request.getParameter("salespersonid"));
        String name = request.getParameter("name");
        Date dateofjoining = Date.valueOf(request.getParameter("dateofjoining")); // Correctly parse the date
        long salary = Long.parseLong(request.getParameter("salary"));

        ManageSalesperson ms = new ManageSalesperson();
        ms.setSalespersonid(salespersonid);
        ms.setName(name);
        ms.setDateofjoining(dateofjoining);
        ms.setSalary(salary);

        try {
            if (ms.SalespersonidExists(salespersonid)) {
                request.setAttribute("message", "Salesperson ID already exists!");
                request.getRequestDispatcher("/addsalesperson.jsp").forward(request, response);
            } else {
                ms.addSalesperson();
                RequestDispatcher rd = request.getRequestDispatcher("/viewsalesperson.jsp");
                rd.forward(request, response); 
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: " + e.getMessage());
            request.getRequestDispatcher("/addsalesperson.jsp").forward(request, response);
        }
    }
}
