package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.EmployeeEntity;
import com.model.EmployeeRemote;


@WebServlet("/AddData")
public class AddData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB(mappedName ="java:global/JPA-DB/EmployeeModel!com.model.EmployeeRemote")
	EmployeeRemote ER;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		try {
			EmployeeEntity E = new EmployeeEntity();
			int empid=Integer.parseInt(request.getParameter("t1"));
			String name=request.getParameter("t2");
			double salary=Integer.parseInt(request.getParameter("t3"));
			E.setEmpid(empid);
			E.setName(name);
			E.setSalary(salary);
			String ack =ER.insertData(E);
			pw.print(ack);
		
		}
		catch(Exception e){
			pw.print(e.getMessage());
			
		}
		RequestDispatcher rd=request.getRequestDispatcher("add.jsp");
		rd.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}



