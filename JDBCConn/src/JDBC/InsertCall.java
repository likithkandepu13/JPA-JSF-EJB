package JDBC;
import java.sql.*;
	

public class InsertCall {

	public static void main(String[] args) throws Exception
	{
		Driver drv = new oracle.jdbc.driver.OracleDriver();
		DriverManager.registerDriver(drv);
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin");
		
		Statement stmt = con.prepareStatement("CREATE OR REPLACE PROCEDURE INSERT_EMP \r\n"
				+ "(edi IN NUMBER, ename IN VARCHAR2, esal IN NUMBER)\r\n"
				+ "IS\r\n"
				+ "BEGIN\r\n"
				+ "    INSERT INTO employee VALUES(edi, ename, esal);\r\n"
				+ "END INSERT_EMP;");
		CallableStatement csmt = con.prepareCall("{CALL INSERT_EMP(?, ?, ?)}");
		
		csmt.setInt(1, 134);
		csmt.setString(2, "RAMARAO");
		csmt.setDouble(3, 7000);
		csmt.execute();
		con.close();
		
		
		// TODO Auto-generated method stub

	}

}