package com.sharma.dbconnect;

import java.sql.*;

public class ConnectDB {
	static Connection con=null;
	public static Connection getConnet(){
		try {
			if(con==null){
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/swiss_bank","root","");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
