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
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("Login");
        } else {
			String action = request.getParameter("action");
			String user_id = request.getParameter("user_id");
			
			User currentUser = (User) request.getSession().getAttribute("user");
			
			System.out.printf("action = %s, user_id = %s, currentUser = %d\n", action, user_id, currentUser.getUser_id());
			
			// make sure we got the params we need
			if (action == null || user_id == null) {
				response.sendRedirect("ViewProfile?user_id=" + ((User) request.getSession().getAttribute("user")).getUser_id());
			}
			
			switch (action) {
			case "accept":
				try {
					System.out.println("accept");
					if (FriendsUtility.getSentFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id)) == 1) {
						// boot them to our own profile
						System.out.println("is own request");
						response.sendRedirect("ViewProfile?user_id=" + ((User) request.getSession().getAttribute("user")).getUser_id());
						break;
					} else {
						System.out.println("calling accept");
						FriendsUtility.acceptFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id));
					}
				} catch (Exception e) {
					// do nothing
					e.printStackTrace();
				}
				response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
				break;
			case "deny":
				try {
					System.out.println("decline");
					if (FriendsUtility.getSentFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id)) == 1) {
						// boot them to our own profile
						System.out.println("is own request");
						response.sendRedirect("ViewProfile?user_id=" + ((User) request.getSession().getAttribute("user")).getUser_id());
						break;
					} else {
						System.out.println("calling decline");
						FriendsUtility.denyFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id));
					}					
				} catch (Exception e) {
					// do nothing
					e.printStackTrace();
				}
				response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
				break;
			case "send":
				// check if there is an existing request, and create one if not
				try {
					System.out.println("send");
					if (FriendsUtility.getSentFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id)) != 1) {
						System.out.println("did not find existing request, calling send");
						FriendsUtility.sendFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id));
					}					
				} catch (Exception e) {
					// do nothing
					e.printStackTrace();
				}
				response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
				break;
			case "remove":
				// check if there is an existing request, and create one if not
				try {
					System.out.println("remove");
					int f1 = FriendsUtility.getSentFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id));
					int f2 = FriendsUtility.getSentFriendRequest(Integer.valueOf(user_id), currentUser.getUser_id());
					System.out.printf("f1 = %d, f2 = %d\n", f1, f2);
					
					if (f1 == 1) {
						System.out.println("f1 calling deny");
						FriendsUtility.denyFriendRequest(Integer.valueOf(user_id), currentUser.getUser_id());
					} else if (f2 == 1) {
						System.out.println("f2 calling deny");
						FriendsUtility.denyFriendRequest(currentUser.getUser_id(), Integer.valueOf(user_id));
					}
				} catch (Exception e) {
					// do nothing
					e.printStackTrace();
				}			
				response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
				break;
			default:
				System.out.println("default");
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
