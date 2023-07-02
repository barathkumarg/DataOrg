package com.DataOrg.Servlets;

import  com.DataOrg.Bean.User;
import com.DataOrg.DAO.InsertUser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the User bean 
		User userbean = new User();
		
		//put the values to the bean
		userbean.setUsername((String)request.getParameter("username"));
		userbean.setEmail((String)request.getParameter("email"));
		userbean.setPassword((String)request.getParameter("password"));
		
		InsertUser insert = new InsertUser();
		if (insert.insertuser(userbean)) {
			//user details inserted successfully
			request.setAttribute("register", "valid");
            request.getRequestDispatcher("/").forward(request, response);
		}
		else {
			//user already exists
			request.setAttribute("register", "invalid");
            request.getRequestDispatcher("/").forward(request, response);
		}


		
		
	}

}
