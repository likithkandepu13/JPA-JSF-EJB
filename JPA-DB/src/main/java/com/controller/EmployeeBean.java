package com.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.entity.EmployeeEntity;
import com.model.EmployeeRemote;

@ManagedBean(name="emp" ,eager=true)
public class EmployeeBean {
	int empid;
	String name;
	double salary;
	String ack;
	@EJB(mappedName ="java:global/JPA-DB/EmployeeModel!com.model.EmployeeRemote")
	EmployeeRemote ER;
	public void callinsert() {
		try {
			
		EmployeeEntity E= new EmployeeEntity();
		E.setEmpid(empid);
		E.setName(name);
		E.setSalary(salary);
		ack= ER.insertData(E);
		
		
		}
		catch (Exception e){
			ack = e.getMessage();
		}
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getAck() {
		return ack;
	}
	public void setAck(String ack) {
		this.ack = ack;
	}
	
	

}

