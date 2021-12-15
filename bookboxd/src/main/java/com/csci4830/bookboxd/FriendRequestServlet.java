package com.csci4830.bookboxd;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csci4830.datamodel.User;

/**
 * Servlet implementation class FriendRequestServlet
 */
@WebServlet(name = "FriendRequest", urlPatterns = { "/FriendRequest" })
public class FriendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") != null) {
			response.sendRedirect("dashboard.jsp");
        } else {
			String action = request.getParameter("action");
			String user_id = request.getParameter("user_id");
			
			User currentUser = (User) request.getSession().getAttribute("user");
			
			// make sure we got the params we need
			if (action == null || user_id == null) {
				response.sendRedirect("ViewProfile?user_id=" + ((User) request.getSession().getAttribute("user")).getUser_id());
			}
			
			switch (action) {
			case "accept":
				try {
					if (FriendsUtility.getSentFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id)) == 1) {
						// boot them to our own profile
						response.sendRedirect("ViewProfile?user_id=" + ((User) request.getSession().getAttribute("user")).getUser_id());
						break;
					} else {
						FriendsUtility.acceptFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id));
					}
				} catch (Exception e) {
					// do nothing
					e.printStackTrace();
				}
			case "decline":
				try {
					if (FriendsUtility.getSentFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id)) == 1) {
						// boot them to our own profile
						response.sendRedirect("ViewProfile?user_id=" + ((User) request.getSession().getAttribute("user")).getUser_id());
						break;
					} else {
						FriendsUtility.denyFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id));
					}					
				} catch (Exception e) {
					// do nothing
					e.printStackTrace();
				}
			case "send":
				// check if there is an existing request, and create one if not
				try {
					if (FriendsUtility.getSentFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id)) != 1) {
						FriendsUtility.sendFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id));
					}					
				} catch (Exception e) {
					// do nothing
					e.printStackTrace();
				}
			case "remove":
				// check if there is an existing request, and create one if not
				try {
					int f1 = FriendsUtility.getSentFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id));
					int f2 = FriendsUtility.getSentFriendRequest(Integer.valueOf(user_id), currentUser.getUser_id());
					
					if (f1 == 1) {
						FriendsUtility.denyFriendRequest(Integer.valueOf(user_id), currentUser.getUser_id());
					} else if (f2 == 1) {
						FriendsUtility.denyFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id));
					}
				} catch (Exception e) {
					// do nothing
					e.printStackTrace();
				}			
				
			default:
				response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
			}			
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
