package com.sharma.services;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sharma.dbconnect.ConnectDB;


public class AmountDeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AmountDeposit() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		int acc_no = Integer.parseInt(request.getParameter("ano"));
		float deposit = Float.parseFloat(request.getParameter("amt"));
		float prevBal = 0;
		if(deposit<=0){
			response.sendRedirect("invalid.html");
		}
		else
		{
			try {
				Connection con = ConnectDB.getConnet();
				PreparedStatement ps2 = con.prepareStatement("select * from accounts where accno=?");
				ps2.setInt(1, acc_no);
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()){
					prevBal = rs2.getFloat("abal");
				}
				float newBal = prevBal + deposit;
				
				PreparedStatement ps3 = con.prepareStatement("update accounts set abal=? where accno=?");
				ps3.setFloat(1, newBal);
				ps3.setInt(2, acc_no);
				int i = ps3.executeUpdate();
				if(i>0){
					response.sendRedirect("success.html");
				}
				else{
					response.sendRedirect("failed.html");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
