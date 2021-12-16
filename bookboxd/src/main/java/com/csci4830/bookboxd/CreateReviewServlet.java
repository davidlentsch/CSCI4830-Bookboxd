package com.csci4830.bookboxd;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csci4830.datamodel.Books;
import com.csci4830.datamodel.Friends;
import com.csci4830.datamodel.Lists;
import com.csci4830.datamodel.Reviews;
import com.csci4830.datamodel.ReviewsUser;
import com.csci4830.datamodel.User;

/**
 * Servlet implementation class CreateReviewServlet
 */
@WebServlet("/CreateReviewServlet")
public class CreateReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String book_id = request.getParameter("book_id");
		
		if (request.getSession().getAttribute("user") != null) {
				
			try {
				// Catch empty query
				if (book_id == null) {
					response.sendRedirect("Search?query=");
				}
				
				int id = (int) Integer.valueOf(book_id);
				
				response.setContentType("text/html");
				
				// Get the data we need
				User currentUser = (User) request.getSession().getAttribute("user");
				Books book = Utility.getBookByBookID(id);
				
				// Store the results and then send the user the page
				request.setAttribute("book", book);
				request.removeAttribute("errorMessage");
	            String destination = "createReview.jsp";
	            
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
		if (request.getSession().getAttribute("user") != null) {
			try {
				String newReview = request.getParameter("review");
				String newPrivacySetting = request.getParameter("reviewPrivacy");
				Double newRating = Double.valueOf(request.getParameter("rating"));
				
				Integer book_id = Integer.valueOf((String) request.getParameter("book_id"));
				User currentUser = (User) request.getSession().getAttribute("user");
				
				int newPrivacy = -1;
				
				switch (newPrivacySetting) {
				case "public":
					newPrivacy = 0;
					break;
				case "private":
					newPrivacy = 1;
					break;
				case "friends":
					newPrivacy = 2;
					break;
				default:
					break;
				}
				
				ReviewUtility.createReview(currentUser.getUser_id(), book_id, newRating, newReview, newPrivacy);
	            String destination = "Book?book_id=" + book_id;
	            
	            response.sendRedirect(destination);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("login.jsp");
		}		
	}

}
