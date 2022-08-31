package com.sharma.services;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sharma.dbconnect.ConnectDB;

/**
 * Servlet implementation class AccountCreate
 */
public class AccountCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AccountCreate() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		int acc_no;
		float a_balance;
		String a_name,a_mob,a_loc,a_email;
		
		acc_no = Integer.parseInt(request.getParameter("ano"));
		a_balance = Float.parseFloat(request.getParameter("balance"));
		a_name = request.getParameter("uname");
		a_mob = request.getParameter("phone");
		a_loc = request.getParameter("address");
		a_email = request.getParameter("uemail");
		
		try {
			Connection con = ConnectDB.getConnet();
			PreparedStatement ps1 = con.prepareStatement("insert into accounts values(?,?,?,?,?,?)");
			ps1.setInt(1, acc_no);
			ps1.setString(2, a_name);
			ps1.setString(3, a_mob);
			ps1.setString(4, a_loc);
			ps1.setString(5, a_email);
			ps1.setFloat(6, a_balance);
			int i = ps1.executeUpdate();
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
