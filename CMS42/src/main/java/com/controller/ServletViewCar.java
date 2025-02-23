package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Car;
import com.model.ManageCar;

public class ServletViewCar extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletViewCar() 
    {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ManageCar mc = new ManageCar();
        List<Car> cars = null;
        try {
            cars = mc.retrieve();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("list", cars);
        RequestDispatcher rd = request.getRequestDispatcher("viewcar.jsp");
        rd.forward(request, response);
}
}