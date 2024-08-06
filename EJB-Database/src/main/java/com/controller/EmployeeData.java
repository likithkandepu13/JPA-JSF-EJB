package com.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.entity.Employee;
import com.model.EmployeeRemote;

@ManagedBean(name="e" ,eager=true)
public class EmployeeData {
    int eid;
    String name;
    int sal;

    String response;
    List<Employee> list;

    @EJB(lookup="java:global/EJB-Database/EmployeeManager!com.model.EmployeeRemote")
    EmployeeRemote ER;

    public void save() {
        try {
            Employee E = new Employee();
            E.setEid(eid);
            E.setName(name);
            E.setSal(sal);
            response = ER.saveData(E);
        } catch (Exception e) {
            response = e.getMessage();
        }
    }

    public void update() {
        try {
            Employee E = new Employee();
            E.setEid(eid);
            E.setName(name);
            E.setSal(sal);
            response = ER.updateData(E);
        } catch (Exception e) {
            response = e.getMessage();
        }
    }

    public void delete() {
        try {
            Employee E = new Employee();
            E.setEid(eid);
            E.setName(name);
            E.setSal(sal);
            response = ER.deleteData(E);
        } catch (Exception e) {
            response = e.getMessage();
        }
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<Employee> getList() {
        try {
            list = ER.getData();
        } catch (Exception e) {
            response = e.getMessage();
        }
        return list;
    }

    public void setList(List<Employee> list) {
        this.list = list;
    }
}
