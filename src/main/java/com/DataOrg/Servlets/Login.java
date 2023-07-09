package com.DataOrg.Servlets;

import com.DataOrg.DAO.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        HttpSession session = request.getSession();

		if (session.getAttribute("email") != null) {
			response.sendRedirect("/DataOrg/home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		logger.info("Inside the dopost of login");
		//authorization of user and jsp redirection
		UserAuth auth = new UserAuth(email,password);
		
		if(auth.checkuser()) {
			//set the session
	        HttpSession session = request.getSession();
	        session.setAttribute("email", email);
	        
	        //redirect to home servlet  by setting the request servlet
	        response.sendRedirect("/DataOrg/home");
			
		}else {
			request.setAttribute("login", "invalid");
            request.getRequestDispatcher("/").forward(request, response);

		}
		

	}

}
