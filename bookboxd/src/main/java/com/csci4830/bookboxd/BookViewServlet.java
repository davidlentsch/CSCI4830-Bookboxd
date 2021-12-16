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
 * Servlet implementation class BookViewServlet
 */
@WebServlet(name = "Book", urlPatterns = { "/Book" })
public class BookViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookViewServlet() {
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
				List<Lists> userLists = Utility.getListsByUserID(currentUser.getUser_id());
				List<Reviews> reviews = Utility.getReviewsByBookID(id);
				Books book = Utility.getBookByBookID(id);
				
				// We need to attach a user object to each review and check the privacy level
				// Even if the review can't be seen, it still should count towards the final overall
				// rating score.
				List<ReviewsUser> reviewList = new ArrayList<ReviewsUser>();
				List<Friends> currentUserFriends = Utility.getFriendsByUserID(currentUser.getUser_id());
				ReviewsUser temp;
				boolean addReview = false;
				
				for (Reviews r : reviews) {
					temp = new ReviewsUser(r.getUser_id(), 
							r.getBook_id(), 
							r.getRating(), 
							r.getComments(), 
							r.getPrivacy_setting(), 
							Utility.getUserByUserID(r.getUser_id()));
					
					addReview = false;
					
					if (r.getPrivacy_setting() == 0) {
						// public
						addReview = true;
					} else if (r.getPrivacy_setting() == 1) {
						// private, add only if currentUser = review creator
						if (r.getUser_id() == currentUser.getUser_id()) {
							addReview = true;
						}
					} else if (r.getPrivacy_setting() == 2) {
						// friends only
						
						for (Friends f : currentUserFriends) {
							if (f.getUser_id_1() == currentUser.getUser_id() && f.getConfirmed() == 1) {
								addReview = true;
							} else if (f.getUser_id_2() == currentUser.getUser_id() && f.getConfirmed() == 1) {
								addReview = true;
							}
						}
					}
					
					if (addReview) {
						reviewList.add(temp);
					}
				}
				
				DecimalFormat df = new DecimalFormat("0.0");
				df.setRoundingMode(RoundingMode.UP);
				
				// Store the results and then send the user the page
				request.setAttribute("userLists", userLists);
				request.setAttribute("reviewList", reviewList);
				try {
					request.setAttribute("averageRating", df.format(Utility.getAverageReview(id)));					
				} catch (NoResultException nre) {
					request.setAttribute("averageRating", 0.0);	
				}
				request.setAttribute("book", book);
				request.removeAttribute("errorMessage");
	            String destination = "viewBook.jsp";
	            
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
