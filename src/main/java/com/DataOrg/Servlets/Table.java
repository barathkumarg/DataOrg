package com.DataOrg.Servlets;


import com.DataOrg.DAO.InsertTableData;
import com.DataOrg.DAO.UserDataOperation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Table
 */
@WebServlet("/table/*")
public class Table extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Table() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		
		//get the table name
        String tableName = request.getPathInfo().substring(1); // Remove the leading '/'
        System.out.println(tableName);
        
       
        
        //get the the table detail column and complete data
        List<String> column = UserDataOperation.getUserTableColumn(tableName, "Db_"+ UserDataOperation.getUsername((String) session.getAttribute("email")));
		List<Map<String, Object>> rs = UserDataOperation.getUserTable(tableName, "Db_"+ UserDataOperation.getUsername((String) session.getAttribute("email")));
        

		//setting the attribute
		 request.setAttribute("email", email);
	     request.setAttribute("username", UserDataOperation.getUsername(email));
	     request.setAttribute("tableName", tableName);
	        
		request.setAttribute("columnNames", column);
        request.setAttribute("dataList", rs);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/table.jsp");
	     dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		
		//get the value from form
		String tableName = request.getParameter("tableName");
		String[] dynamicData = request.getParameterValues("fieldValue");

		List<String> dynamicDataList = new ArrayList<>(Arrays.asList(dynamicData));
		
		//get the column Name
        List<String> column = UserDataOperation.getUserTableColumn(tableName, "Db_"+ UserDataOperation.getUsername((String) session.getAttribute("email")));


		
		//insert data logic 
		if (InsertTableData.insertData("Db_"+ UserDataOperation.getUsername((String) session.getAttribute("email")), tableName, column, dynamicDataList)) {
	    	response.getWriter().write("success");
	    	
	    }
	    else {
	    	response.getWriter().write("failure");
	    }
        
		
		
		
	}

}
