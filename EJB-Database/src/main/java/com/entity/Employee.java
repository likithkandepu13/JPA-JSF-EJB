package com.entity;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	int Eid;
	String Name;
	int Sal;
	public int getEid() {
		return Eid;
	}
	public void setEid(int eid) {
		this.Eid = eid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public int getSal() {
		return Sal;
	}
	public void setSal(int sal) {
		this.Sal = sal;
	}
	
	
	

	
}
