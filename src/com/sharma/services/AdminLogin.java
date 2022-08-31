package com.sharma.services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AdminLogin() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String em = request.getParameter("uemail");
		String pass = request.getParameter("upassword");
		
		if(em.equals("admin@gmail.com") && pass.equals("admin")){
			response.sendRedirect("CreateAccount.html");
		}else{
			response.sendRedirect("index.html");
		}
	}

}
