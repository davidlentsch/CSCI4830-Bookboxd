package com.csci4830.bookboxd;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csci4830.datamodel.User;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("editProfile.jsp");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newAboutMe = request.getParameter("comments");
		String newPrivacySetting = request.getParameter("userPrivacy");
		
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
		
		
		User u = (User) request.getSession().getAttribute("user");
		
		if (!u.getAbout_desc().equals(newAboutMe)) {
			u.setAbout_desc(newAboutMe);
			UserProfileUtility.updateAboutMe(u.getUser_id(), newAboutMe);
		}
		
		if (u.getPrivacy_setting() != newPrivacy) {
			u.setPrivacy_setting(newPrivacy);
			UserProfileUtility.changeProfilePrivacy(u.getUser_id(), newPrivacy);
		}
		
		request.getSession().setAttribute("user", u);
		
		response.sendRedirect("ViewProfile?user_id=" + u.getUser_id());		
	}

}
