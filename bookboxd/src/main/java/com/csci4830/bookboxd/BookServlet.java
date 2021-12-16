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
import com.csci4830.datamodel.Lists;
import com.csci4830.datamodel.User;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
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
		
		
		String url = request.getParameter("url");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		
		
		
		if (request.getSession().getAttribute("user") != null) {
			try {
				//TODO: logic
				
				User currentUser = (User) request.getSession().getAttribute("user");
	            String destination = "ViewProfile?user_id=" + currentUser.getUser_id();
	            
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

}
