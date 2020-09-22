package com.wipro.candidate.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static Connection conn = null;
	
	public static Connection getDBConn() {	
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521", "B202016073920", "B202016073920");
			return conn;
		} catch (Exception e) {
			System.out.println("Connection could not be estanlished");
			e.printStackTrace();
			return null;
		}
	}
}
