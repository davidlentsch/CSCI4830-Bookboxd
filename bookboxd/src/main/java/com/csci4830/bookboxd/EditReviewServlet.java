package com.csci4830.bookboxd;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csci4830.datamodel.Reviews;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newReview = request.getParameter("review");
		String newPrivacySetting = request.getParameter("reviewPrivacy");
		String newRating = request.getParameter("rating");
		
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
		
		Reviews r = (Reviews) request.getSession().getAttribute("reviews");
		
		if (!r.getComments().equals(newReview))
		{
			r.setComments(newReview);
			ReviewUtility.updateReview(r.getReview_id(), useThisRating, newReview);
		}
		if (r.getPrivacy_setting() != newPrivacy) {
			r.setPrivacy_setting(newPrivacy);
			ReviewUtility.changeReviewPrivacy(r.getUser_id(), newPrivacy);
		}
		
		
	}

}
