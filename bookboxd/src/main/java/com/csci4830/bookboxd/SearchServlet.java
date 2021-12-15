package com.csci4830.bookboxd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csci4830.datamodel.Books;
import com.csci4830.datamodel.Lists;
import com.csci4830.datamodel.User;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(name = "Search", urlPatterns = { "/Search" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("query");
		
		if (request.getSession().getAttribute("user") != null) {
			try {
				request.removeAttribute("searchresults");
							
				// Catch empty query
				if (query == null) {
					response.sendRedirect("searchPage.jsp");
				}
				
				response.setContentType("text/html");
				
				// Perform the search
				List<Books> authorSearch = Utility.getBooksByAuthorSearch(query);
				List<Books> nameSearch = Utility.getBooksByNameSearch(query);
				List<Books> deduped = new ArrayList<Books>();
				
				// Get the user's lists
				User u = (User) request.getSession().getAttribute("user");
				List<Lists> userLists = Utility.getListsByUserID(u.getUser_id());			
				
				// Deduplicate the results
				deduped.addAll(nameSearch);
				
				for (Books b : authorSearch) {
					if (!deduped.contains(b)) {
						deduped.add(b);
					}
			 	} 
				
				// Store the results and then send the user the page
				request.setAttribute("query", query);
				request.setAttribute("searchresults", deduped);
				request.setAttribute("userLists", userLists);
				request.removeAttribute("errorMessage");
	            String destination = "searchresults.jsp";
	            
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
	
	
	
	protected String printBookCard(Books book, User user) {
		StringBuilder sb = new StringBuilder();
		List<Lists> userLists = Utility.getListsByUserID(user.getUser_id());
		
		sb.append("<div class=\"mdl-cell mdl-cell--3-col mdl-cell--4-col-tablet mdl-cell--4-col-phone mdl-card mdl-shadow--3dp\">\n"
				+ "<div class=\"mdl-card__media\">\n"
				+ "<img src=\"https://i.redd.it/y7vxbs14igr71.jpg\">\n"
				+ "</div>\n"
				+ "<div class=\"mdl-card__title\">");
		sb.append("<h4 class=\"mdl-card__title-text\">" + book.getBook_name() + "</h4>\n");
		sb.append("</div>\n"
				+ "<div class=\"mdl-card__title\">\n"
				+ "<p class=\"mdl-card__title-text mdl-typography--subhead\">");
		
		// add stars
		if (book.getAverage_rating() < 1.0) {
			sb.append("\n"
					+ "<span class=\"fa fa-star\"></span>\n"
					+ "<span class=\"fa fa-star\"></span>\n"
					+ "<span class=\"fa fa-star\"></span>\n"
					+ "<span class=\"fa fa-star\"></span>\n"
					+ "<span class=\"fa fa-star\"></span>");
		} else if (book.getAverage_rating() >= 1.0 && book.getAverage_rating() < 2.0) {
			sb.append("\n"
					+ "<span class=\"fa fa-star checked\"></span>\n"
					+ "<span class=\"fa fa-star\"></span>\n"
					+ "<span class=\"fa fa-star\"></span>\n"
					+ "<span class=\"fa fa-star\"></span>\n"
					+ "<span class=\"fa fa-star\"></span>");
		} else if (book.getAverage_rating() >= 2.0 && book.getAverage_rating() < 3.0) {
			sb.append("\n"
					+ "<span class=\"fa fa-star checked\"></span>\n"
					+ "<span class=\"fa fa-star checked\"></span>\n"
					+ "<span class=\"fa fa-star\"></span>\n"
					+ "<span class=\"fa fa-star\"></span>\n"
					+ "<span class=\"fa fa-star\"></span>");
		} else if (book.getAverage_rating() >= 3.0 && book.getAverage_rating() < 4.0) {
			sb.append("\n"
					+ "<span class=\"fa fa-star checked\"></span>\n"
					+ "<span class=\"fa fa-star checked\"></span>\n"
					+ "<span class=\"fa fa-star checked\"></span>\n"
					+ "<span class=\"fa fa-star\"></span>\n"
					+ "<span class=\"fa fa-star\"></span>");
		} else if (book.getAverage_rating() >= 4.0 && book.getAverage_rating() < 5.0) {
			sb.append("\n"
					+ "<span class=\"fa fa-star checked\"></span>\n"
					+ "<span class=\"fa fa-star checked\"></span>\n"
					+ "<span class=\"fa fa-star checked\"></span>\n"
					+ "<span class=\"fa fa-star checked\"></span>\n"
					+ "<span class=\"fa fa-star\"></span>");
		} else if (book.getAverage_rating() >= 5.0) {
			sb.append("\n"
					+ "<span class=\"fa fa-star checked\"></span>\n"
					+ "<span class=\"fa fa-star checked\"></span>\n"
					+ "<span class=\"fa fa-star checked\"></span>\n"
					+ "<span class=\"fa fa-star checked\"></span>\n"
					+ "<span class=\"fa fa-star checked\"></span>");
		}
		
		sb.append(" &nbsp; " + book.getAverage_rating() + "\n"
				+ "</p>\n"
				+ "</div>\n"
				+ "<div class=\"mdl-card__supporting-text\">");
		
		sb.append("<span class=\"mdl-typography--font-light mdl-typography--subhead\">" + book.getDescription() + "</span></div>");
		sb.append("<div class=\"mdl-card__actions\"><ul class=\"mdl-menu mdl-js-menu mdl-menu--top-left mdl-js-ripple-effect\" for=\"add-book-to-list\">");
		
		for (Lists l : userLists)
		{
			sb.append("<a class=\"mdl-menu__item\" href=\"Lists?action=add&list_id="
					+ l.getList_id() + "&user_id=" + user.getUser_id() + "\">" + l.getList_name() + "</a>");
		}
		
		sb.append("</ul>\n"
				+ "                            <a class=\"android-link mdl-button mdl-js-button android-link-menu mdl-typography--text-uppercase\" id=\"add-book-to-list\">Add Book To List<i class=\"material-icons\">chevron_right</i></a>\n"
				+ "                        </div>\n"
				+ "                        <div>\n"
				+ "                            </a>\n"
				+ "                        </div>\n"
				+ "                    </div>");
		
		return sb.toString();
	}
}
