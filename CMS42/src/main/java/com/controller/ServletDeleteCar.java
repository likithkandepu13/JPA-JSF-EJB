package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.ManageCar;

public class ServletDeleteCar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletDeleteCar() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		int carid = Integer.parseInt(request.getParameter("carid"));

		ManageCar mc = new ManageCar();
		mc.setCarid(carid);
        try {
			mc.deleteCar();
			pw.print("Car Data Deleted Successfully");
		} catch (Exception e) 
        {
			e.printStackTrace();
			pw.print("Failed to delete: " + e.getMessage());
		}
    }
}
