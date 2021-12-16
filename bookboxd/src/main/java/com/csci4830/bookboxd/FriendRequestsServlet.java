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

import com.csci4830.datamodel.Friends;
import com.csci4830.datamodel.User;

/**
 * Servlet implementation class FriendRequestsServlet
 */
@WebServlet("/FriendRequests")
public class FriendRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendRequestsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u;
		
		if ((u = (User) request.getSession().getAttribute("user")) != null) {

			String action = request.getParameter("action");
			String user_id = request.getParameter("user_id");
			try {
				request.removeAttribute("friendRequests");
				if (action != null && user_id!=null) {
					switch (action) {
					case "accept":
						try {
							System.out.println("accept");
							if (FriendsUtility.getSentFriendRequest(u.getUser_id(), Integer.valueOf(user_id)) == 1) {
								// boot them to our own profile
								System.out.println("is own request");
								response.sendRedirect("ViewProfile?user_id=" + u.getUser_id());
								break;
							} else {
								System.out.println("calling accept");
								FriendsUtility.acceptFriendRequest(u.getUser_id(), Integer.valueOf(user_id));
							}
						} catch (Exception e) {
							// do nothing
							e.printStackTrace();
						}
						//response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
						break;
					case "deny":
						try {
							System.out.println("decline");
							if (FriendsUtility.getSentFriendRequest(u.getUser_id(), Integer.valueOf(user_id)) == 1) {
								// boot them to our own profile
								System.out.println("is own request");
								response.sendRedirect("ViewProfile?user_id=" + u.getUser_id());
								break;
							} else {
								System.out.println("calling decline");
								FriendsUtility.denyFriendRequest(u.getUser_id(), Integer.valueOf(user_id));
							}					
						} catch (Exception e) {
							// do nothing
							e.printStackTrace();
						}
						//response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
						break;
					}
				}
				request.removeAttribute("userFriends");
				
				
				response.setContentType("text/html");

				List<Friends> friendRequests = FriendsUtility.getReceivedFriendRequests(u.getUser_id());
				
				List<User> incomingFriendRequests = new ArrayList<User>();
				
				for (Friends f : friendRequests) {
					incomingFriendRequests.add(Utility.getUserByUserID(f.getUser_id_1()));
				}
				
				//System.out.println(u.getUsername());
				request.setAttribute("friendRequests", incomingFriendRequests);
				request.removeAttribute("errorMessage");
	            String destination = "friendRequests.jsp";
	            
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
