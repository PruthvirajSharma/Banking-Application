package com.sharma.services;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sharma.dbconnect.ConnectDB;


public class AmountTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AmountTransfer() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		

				
		int sacc_no = Integer.parseInt(request.getParameter("sano"));
		int racc_no = Integer.parseInt(request.getParameter("rano"));
		float a_amount = Float.parseFloat(request.getParameter("amt"));
		float prevBal =0;
		if(a_amount<=0){
			response.sendRedirect("invalid.html");
		}else
		{
			try 
			{
				Connection con = ConnectDB.getConnet();
				PreparedStatement ps1 = con.prepareStatement("select * from accounts where accno=?");
				ps1.setInt(1, sacc_no);
				ResultSet rs1 = ps1.executeQuery();
				while(rs1.next()){
					prevBal = rs1.getFloat("abal");
				}
				if(prevBal<a_amount)
				{
					response.sendRedirect("invalid.html");
				}
				else{

					float amountTransfer = a_amount;
					float newBal = prevBal - a_amount;
					
					
					PreparedStatement ps2 = con.prepareStatement("update accounts set abal=? where accno=?");
					ps2.setFloat(1, newBal);
					ps2.setInt(2, sacc_no);
					int p = ps2.executeUpdate();
					
					
					PreparedStatement ps3 = con.prepareStatement("select * from accounts where accno=?");
					ps3.setInt(1, racc_no);
					ResultSet rs3 = ps3.executeQuery();
					while(rs3.next()){
						prevBal = rs3.getFloat("abal");
					}
					newBal = prevBal + amountTransfer;
					
					PreparedStatement ps4 = con.prepareStatement("update accounts set abal=? where accno=?");
					ps4.setFloat(1, newBal);
					ps4.setInt(2, racc_no);
					int i = ps4.executeUpdate();
					if(i>0){
						response.sendRedirect("success.html");
					}
					else{
						response.sendRedirect("failed.html");
					}
				}
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
	}

}
