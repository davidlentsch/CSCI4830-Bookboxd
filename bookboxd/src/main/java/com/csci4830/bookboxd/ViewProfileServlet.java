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
 * Servlet implementation class ViewProfileServlet
 */
@WebServlet("/ViewProfile")
public class ViewProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		
		if (request.getSession().getAttribute("user") != null) {
			User currentUser = (User) request.getSession().getAttribute("user");			
			
			try {
				request.removeAttribute("userProfile");
							
				// Catch empty query
				if (user_id == null) {
					response.sendRedirect("UserProfile.jsp");
				}
				
				response.setContentType("text/html");
				
				// Perform the search
				User userProfile = Utility.getUserByUserID(Integer.valueOf(user_id));		
				
				// Catch if user doesn't exist
				if (userProfile == null) {
					response.sendRedirect("UserProfile.jsp");
				}
				
				List<Friends> friends = Utility.getFriendsByUserID(Integer.valueOf(user_id));
				List<User> userFriends = new ArrayList<User>();
				boolean isFriendsWithLoggedInUser = false;
				boolean friendRequestPending = false;
				
				for (Friends f : friends) {
					// check if we are friends with them
					if (f.getUser_id_1() == currentUser.getUser_id() || f.getUser_id_2() == currentUser.getUser_id()) {
						isFriendsWithLoggedInUser = true;
					} 

					
					// enumerate friend list
					if (f.getUser_id_1() != userProfile.getUser_id()) {
						userFriends.add(Utility.getUserByUserID(f.getUser_id_1()));
					} else if (f.getUser_id_2() != userProfile.getUser_id()) {
						userFriends.add(Utility.getUserByUserID(f.getUser_id_2()));
					}
				}
				
				if (Utility.getPendingFriendRequest(currentUser.getUser_id(), userProfile.getUser_id()) != null) {
					friendRequestPending = true;
				}
				
				List<Lists> userLists = Utility.getListsByUserID(Integer.valueOf(user_id));
				
				// Store the results and then send the user the page
				request.setAttribute("userProfile", userProfile);
				request.setAttribute("userLists", userLists);
				request.setAttribute("userFriends", userFriends);
				request.setAttribute("isFriendsWithLoggedInUser", isFriendsWithLoggedInUser);
				request.setAttribute("friendRequestPending", friendRequestPending);
				request.removeAttribute("errorMessage");
	            String destination = "viewProfile.jsp";
	            
	            
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
