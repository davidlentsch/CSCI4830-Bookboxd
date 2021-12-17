package com.csci4830.bookboxd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.NoResultException;
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
 * Servlet implementation class SearchServlet
 */
@WebServlet(name = "Search", urlPatterns = { "/Search" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");
		
		if (request.getSession().getAttribute("user") != null) {
			try {
				request.removeAttribute("searchresults");
							
				// Catch empty query
				if (query == null) {
					response.sendRedirect("searchPage.jsp");
				}
				
				response.setContentType("text/html");
				
				// Perform the search
				List<Books> authorSearch = Utility.getBooksByAuthorSearch(query);
				List<Books> nameSearch = Utility.getBooksByNameSearch(query);
				List<Books> deduped = new ArrayList<Books>();
				
				// Get the user's lists
				User u = (User) request.getSession().getAttribute("user");
				List<Lists> userLists = Utility.getListsByUserID(u.getUser_id());			
				
				// Deduplicate the results
				deduped.addAll(nameSearch);
				for (Books b : nameSearch) {
					try {
						b.setAverage_rating(Math.ceil((Utility.getAverageReview(b.getBook_id()))*10)/10.0);		
					} catch (NoResultException nre) {
						b.setAverage_rating(0.0);
					}
				}
				for (Books b : authorSearch) {
					if (!deduped.contains(b)) {
						deduped.add(b);
						try {
							b.setAverage_rating(Math.ceil((Utility.getAverageReview(b.getBook_id()))*10)/10.0);
						} catch (NoResultException nre) {
							b.setAverage_rating(0.0);
						}
					}
			 	}
				
				// Next search for users
				List<User> users = Utility.getUsersByNameSearch(query);
				
				// TODO: Filter out users with privacy level
				
				// Store the results and then send the user the page
				request.setAttribute("query", query);
				request.setAttribute("searchresults", deduped);
				request.setAttribute("searchresultsUsers", users);
				request.setAttribute("userLists", userLists);
				request.removeAttribute("errorMessage");
	            String destination = "searchresults.jsp";
	            
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
