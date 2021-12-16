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
 * Servlet implementation class FriendListServlet
 */
@WebServlet("/FriendsList")
public class FriendListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		
		if (request.getSession().getAttribute("user") != null) {
			try {
				request.removeAttribute("friendListUser");
				request.removeAttribute("userFriends");
				
				
				response.setContentType("text/html");
				
				// Get the user's lists
				User u;
				if (user_id == null) {
					u = (User) request.getSession().getAttribute("user");
				}
				else {
					u = Utility.getUserByUserID(Integer.valueOf(user_id));
				}
				List<Friends> friends = Utility.getFriendsByUserID(u.getUser_id());
				List<User> userFriends = new ArrayList<User>(friends.size());
				
				for (Friends f: friends) {
					if (f.getConfirmed() == 1) {
						userFriends.add(
								(f.getUser_id_1()==u.getUser_id())?
										Utility.getUserByUserID(f.getUser_id_2())://User is in slot 1
										Utility.getUserByUserID(f.getUser_id_1()));//in slot 2
					}
				}
				//System.out.println(u.getUsername());
				request.setAttribute("friendListUser", u);
				request.setAttribute("userFriends", userFriends);
				request.removeAttribute("errorMessage");
	            String destination = "friendsList.jsp";
	            
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
