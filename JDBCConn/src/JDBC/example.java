package JDBC;

import java.sql.*;
public class example {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Driver drv = new com.mysql.cj.jdbc.Driver();
	    DriverManager.registerDriver(drv);
	    
	    Connection con = DriverManager.getConnection(" jdbc:mysql://localhost:3306/db ","root","password");
	    if(con != null)
	    {
	      System.out.println("Connection is Successful");
	    }
	    else
	    {
	      System.out.println("Connection is not successful");
	    }
	    con.close();

	}

}
