package com.DataOrg.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DataOrg.DAO.DeleteTableData;
import com.DataOrg.DAO.UserDataOperation;

/**
 * Servlet implementation class DeleteData
 */
@WebServlet("/deletedata")
public class DeleteData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteData() {
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
		
		//http session

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		
		//fetch the table name, coulnName and data
      String tableName = request.getParameter("tableName");
      String columnName = request.getParameter("columnName");

      String primaryKeyValue = request.getParameter("primarykey");
        
      //Delete Action
      if (DeleteTableData.DeleteData("Db_"+ UserDataOperation.getUsername((String) session.getAttribute("email")), tableName, columnName, primaryKeyValue)) {
    	  response.getWriter().write("success");
      }
      else {
    	  response.getWriter().write("failure");
      }
		
	}

}
