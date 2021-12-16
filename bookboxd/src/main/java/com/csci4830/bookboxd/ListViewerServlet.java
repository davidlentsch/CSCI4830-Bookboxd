package com.csci4830.bookboxd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csci4830.datamodel.Books;
import com.csci4830.datamodel.Friends;
import com.csci4830.datamodel.Lists;
import com.csci4830.datamodel.User;

/**
 * Servlet implementation class ListViewerServlet
 */
@WebServlet(name = "ListViewer", urlPatterns = { "/ListViewer" })
public class ListViewerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListViewerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String list_id = request.getParameter("list_id");
		
		if (request.getSession().getAttribute("user") != null) {
				
			try {
				// Catch empty query
				if (list_id == null) {
					response.sendRedirect("UserProfile.jsp");
				}
				
				int id = (int) Integer.valueOf(list_id);
				
				response.setContentType("text/html");
				
				// Perform the search
				List<Books> userListBooks = Utility.getBooksByListID(id);
				List<Lists> currentUserLists = Utility.getListsByUserID(((User) request.getSession().getAttribute("user")).getUser_id());
				Lists userList = Utility.getListByID(id);
				User userListOwner = Utility.getUserByUserID(userList.getUser_id());
				boolean isNotDefaultList = true;
				
				// Check if it's a default list
				if (userList.getList_name().equals("Favorites") || 
						userList.getList_name().equals("To Read") ||
						userList.getList_name().equals("Finished") ||
						userList.getList_name().equals("Reviewed")) {
					isNotDefaultList = false;
				}
				
				
				// Store the results and then send the user the page
				request.setAttribute("userListBooks", userListBooks);
				request.setAttribute("userList", userList);
				request.setAttribute("userListOwner", userListOwner);
				request.setAttribute("currentUserLists", currentUserLists);
				request.setAttribute("isNotDefaultList", isNotDefaultList);
				request.removeAttribute("errorMessage");
	            String destination = "viewList.jsp";
	            
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
