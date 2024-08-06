package com.model;

import javax.ejb.Stateless;

@Stateless
public class Calculator implements CalculatorRemote {

	public int add(int a, int b) {
		
		return a+b;
	}

	public int sub(int a, int b) {
		
		return a-b;
	}
	

}
