package com.csci4830.bookboxd;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csci4830.datamodel.Books;
import com.csci4830.datamodel.Reviews;
import com.csci4830.datamodel.User;

/**
 * Servlet implementation class EditReviewServlet
 */
@WebServlet("/EditReviewServlet")
public class EditReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String book_id = request.getParameter("book_id");
		String review_id = request.getParameter("review_id");
		
		if (request.getSession().getAttribute("user") != null) {
				
			try {
				// Catch empty query
				if (book_id == null || review_id == null) {
					response.sendRedirect("Search?query=");
				}
				
				int bookid = (int) Integer.valueOf(book_id);
				int reviewid = (int) Integer.valueOf(review_id);
				
				response.setContentType("text/html");
				
				// Get the data we need
				User currentUser = (User) request.getSession().getAttribute("user");
				Books book = Utility.getBookByBookID(bookid);
				Reviews review = Utility.getReviewByReviewID(reviewid);
				
				if (review.getUser_id() != currentUser.getUser_id()) {
					response.sendRedirect("Book?book_id=" + review.getBook_id());
				}
				
				// Store the results and then send the user the page
				request.setAttribute("book", book);
				request.setAttribute("review", review);
				request.removeAttribute("errorMessage");
	            String destination = "editReview.jsp";
	            
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
			String newReview = request.getParameter("review");
			String newPrivacySetting = request.getParameter("reviewPrivacy");
			String newRating = request.getParameter("rating");
			String reviewID = request.getParameter("review_id");
			
			
			// Catch not enough input
			if (newReview == null || reviewID == null || newPrivacySetting == null || newRating == null) {
				response.sendRedirect("Search?query=");
			}
			
			Double useThisRating = Double.valueOf(newRating);
			
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
					
			ReviewUtility.updateReview(Integer.valueOf(reviewID), useThisRating, newReview);
			ReviewUtility.changeReviewPrivacy(Integer.valueOf(reviewID), newPrivacy);
			response.sendRedirect("Book?book_id=" + (Utility.getReviewByReviewID(Integer.valueOf(reviewID))).getBook_id());
		} else {
			response.sendRedirect("login.jsp");
		}
		
	}

}
