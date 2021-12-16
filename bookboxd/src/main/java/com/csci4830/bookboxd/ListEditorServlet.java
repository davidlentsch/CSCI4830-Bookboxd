package com.csci4830.bookboxd;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csci4830.datamodel.Lists;
import com.csci4830.datamodel.User;

/**
 * Servlet implementation class ListEditorServlet
 */
@WebServlet("/ListEditor")
public class ListEditorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListEditorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			try {	
				response.setContentType("text/html");
				
				// Get the user's lists
				User currentUser = (User) request.getSession().getAttribute("user");
				String list_id = request.getParameter("list_id");
				Lists list = null;
				
				try {
					list = Utility.getListByID(Integer.valueOf(list_id));					
				} catch (Exception e) {
					response.sendRedirect("ListOverview");
				}
				
				if (list == null) {
					response.sendRedirect("ListOverview");
				} else if (currentUser.getUser_id() != list.getUser_id()) {
					response.sendRedirect("ListOverview");
				}
				
	            String destination = "editList.jsp";
	            request.setAttribute("list", list);
	            
	            // Forward request
				RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
	            dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
