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
		String url = request.getParameter("url"); //can be null
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String genre = request.getParameter("genre"); //can be null
		String description = request.getParameter("description"); //can be null
		
		
		
		if (request.getSession().getAttribute("user") != null) {
			try {
				if ((title != null && !title.isBlank() && !title.isEmpty()) && (author != null && !author.isBlank() && !author.isEmpty())) {
					//insert book
					Utility.createBook(url, title, author, genre, description);
					
					User currentUser = (User) request.getSession().getAttribute("user");
		            String destination = "ViewProfile?user_id=" + currentUser.getUser_id();
		            
		            // Forward request
					RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
		            dispatcher.forward(request, response);
				} else if (title == null || title.isBlank() || title.isEmpty()) {
					String destination = "addBook.jsp";
					String message = "Please add the title of the book.";
					request.setAttribute("errorMessage", message);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
		            dispatcher.forward(request, response);
				}  else if (author == null || author.isBlank() || author.isEmpty()) {
					String destination = "addBook.jsp";
					String message = "Please add the author of the book.";
					request.setAttribute("errorMessage", message);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
		            dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("login.jsp");
		}
	}

}
