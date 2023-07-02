package com.DataOrg.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import com.DataOrg.DAO.UserDataOperation;
import com.DataOrg.Bean.*;
import com.DataOrg.DAO.InsertTableMeta;
import com.DataOrg.DAO.InsertTableData;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// logic to rendering the initaite function jsp page
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		
		//pass data to the home page
		request.setAttribute("email", email);
        request.setAttribute("username", UserDataOperation.getUsername(email));
        
        //get the usetable meta Data
        
        request.setAttribute("userList", UserDataOperation.getUserTableMeta(email));

		
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/home.jsp");
	     dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		//Intialize the bean
		UserTable usertable = new UserTable();
		UserTableMeta usermeta = new UserTableMeta();
		
		
		//get the post data
		String tablename = request.getParameter("tablename");
		String primarykey = request.getParameter("primarykey");
		String[] columnames = request.getParameterValues("columnnames");
		
		System.out.println(tablename + ' '+ primarykey);
		
		ArrayList<String> columnNameList = new ArrayList<>();
		
		columnNameList.add(primarykey);
		
		if (columnames!=null) {
		for (String name: columnames) {
			System.out.println(name);
			columnNameList.add(name);
		    }
		}
		
		
		//set the usermeta bean
		usermeta.setUserEmail((String) session.getAttribute("email"));
		usermeta.setUserTableName(tablename);
		usermeta.setDbName("Db_"+ UserDataOperation.getUsername((String) session.getAttribute("email")));
		
		//set th4 e usertable bean
		usertable.setTableName(tablename);
		usertable.setDbName("Db_"+ UserDataOperation.getUsername((String) session.getAttribute("email")));
		usertable.setColumnNames(columnNameList);
		
		//insert the meta and create table
	    if(InsertTableMeta.insertUserMeta(usermeta) && InsertTableData.insertTable(usertable)) {
	    	//execution successfull
	    	
	    	response.getWriter().write("success");
	    	
	    }
	    else {
	    	response.getWriter().write("failure");
	    }
		
		
		
	}

}
