package com.csci4830.bookboxd;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csci4830.datamodel.Lists;
import com.csci4830.datamodel.Reviews;
import com.csci4830.datamodel.User;

/**
 * Servlet implementation class DeleteReviewServlet
 */
@WebServlet("/DeleteReviewServlet")
public class DeleteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReviewServlet() {
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
				
				//get current user
				User currentUser = (User) request.getSession().getAttribute("user");
				String review_id = request.getParameter("review_id");
				Reviews toDelete = Utility.getReviewByReviewID(Integer.valueOf(review_id));
				ReviewUtility.deleteReview(toDelete);
				
				//back to profile page
	            String destination = "ViewProfile?user_id=" + currentUser.getUser_id();
	            
	            // Forward request
				response.sendRedirect(destination);
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
