package com.csci4830.bookboxd;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csci4830.datamodel.Books;
import com.csci4830.datamodel.Lists;
import com.csci4830.datamodel.User;

/**
 * Servlet implementation class ListsServlet
 */
@WebServlet(name = "Lists", urlPatterns = { "/Lists" })
public class ListsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListsServlet() {
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
			String book_id = request.getParameter("book_id");
			String list_id = request.getParameter("list_id");
			String user_id = request.getParameter("user_id");
			String list_name = request.getParameter("list_name");
			String privacy_level = request.getParameter("privacy_setting");			
			User currentUser = (User) request.getSession().getAttribute("user");
			
			// make sure we got the params we need
			if (action == null) {
				response.sendRedirect("ViewProfile?user_id=" + ((User) request.getSession().getAttribute("user")).getUser_id());
			}
			
			switch (action) {
			case "add":
				// Add Book action
				
				// make sure we got the params we need
				if (list_id == null || book_id == null) {
					response.sendRedirect("ViewProfile?user_id=" + ((User) request.getSession().getAttribute("user")).getUser_id());
				}
				
				
				boolean alreadyOnList = false;
				
				try {
					// First check if the list exists
					Lists l = Utility.getListByID(Integer.valueOf(list_id));
					
					// Check to see if this list doesn't belongs to the user
					if (l.getUser_id() != currentUser.getUser_id()) {
						response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
					}
					
					// Check to see if the book already exists on the list
					List<Books> bookList = Utility.getBooksByListID(Integer.valueOf(list_id));
					
					for (Books b : bookList) {
						if (b.getBook_id() == Integer.valueOf(book_id).intValue()) {
							alreadyOnList = true;
						}
					}
					
					if (!alreadyOnList) {
						ListsUtility.addBookToList(Integer.valueOf("list_id"), Integer.valueOf(book_id));
					}
					
					response.sendRedirect("ListViewer?list_id=" + Integer.valueOf(list_id));
				} catch (Exception e) {
					// do nothing
					e.printStackTrace();
					response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
				}
				break;
			case "remove":
				// Remove book action
				
				// make sure we got the params we need
				if (list_id == null || book_id == null) {
					response.sendRedirect("ViewProfile?user_id=" + ((User) request.getSession().getAttribute("user")).getUser_id());
				}
				
				boolean removeIsOnList = false;
				try {
					// First check if the list exists
					Lists l = Utility.getListByID(Integer.valueOf(list_id));
					
					// Check to see if this list doesn't belongs to the user
					if (l.getUser_id() != currentUser.getUser_id()) {
						response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
					}
					
					// Check to see if the book already exists on the list
					List<Books> bookList = Utility.getBooksByListID(Integer.valueOf(list_id));
					
					for (Books b : bookList) {
						if (b.getBook_id() == Integer.valueOf(book_id).intValue()) {
							removeIsOnList = true;
						}
					}
					
					if (!removeIsOnList) {
						ListsUtility.removeBookFromList(ListsUtility.getListBooksDataObject(Integer.valueOf(list_id), Integer.valueOf(user_id)));
					}
					
					response.sendRedirect("ListViewer?list_id=" + Integer.valueOf(list_id));
				} catch (Exception e) {
					// do nothing
					e.printStackTrace();
					response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
				}
				break;
			case "editList":
				// Edit list action

				// make sure we got the params we need
				if (list_id == null || list_name == null || privacy_level == null) {
					response.sendRedirect("ViewProfile?user_id=" + ((User) request.getSession().getAttribute("user")).getUser_id());
				}
				
				
				try {
					// First check if the list exists
					Lists l = Utility.getListByID(Integer.valueOf(list_id));
					
					// Check if it's a default list
					if (l.getList_name().equals("Favorites") || 
							l.getList_name().equals("To Read") ||
							l.getList_name().equals("Finished") ||
							l.getList_name().equals("Reviewed")) {
						response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
					}
					
					// Check to see if this list doesn't belongs to the user
					if (l.getUser_id() != currentUser.getUser_id()) {
						response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
					}
					
					ListsUtility.updateList(Integer.valueOf(list_id), list_name, Integer.valueOf(privacy_level));
					response.sendRedirect("ListViewer?list_id=" + Integer.valueOf(list_id));
				} catch (Exception e) {
					// do nothing
					e.printStackTrace();
					response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
				}
				break;
			case "addList":
				// Add List action
				
				// make sure we got the params we need
				if (list_name == null || privacy_level == null) {
					response.sendRedirect("ViewProfile?user_id=" + ((User) request.getSession().getAttribute("user")).getUser_id());
				}
				
				try {
					// Check if the user wants to make a default list name
					if (list_name.equals("Favorites") || 
							list_name.equals("To Read") ||
							list_name.equals("Finished") ||
							list_name.equals("Reviewed")) {
						response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
					}
					
					// First check if the list already exists
					List<Lists> lists = Utility.getListsByUserID(currentUser.getUser_id());
					
					for (Lists l : lists) {
						if (l.equals(list_name)) {
							response.sendRedirect("ListViewer?list_id=" + l.getList_id());
						}
					}
					
					// Add the list (default privacy level is 0)
					ListsUtility.createList(list_name, currentUser.getUser_id(), 0);
					
					// Go get the new list because we don't know the actual ID
					List<Lists> newUserLists = Utility.getListsByUserID(currentUser.getUser_id());
					
					for (Lists l : newUserLists)
					{
						if (l.getList_name().equals(list_name)) {
							response.sendRedirect("ListViewer?list_id=" + Integer.valueOf(list_id));
						}
					}
					
					
				} catch (Exception e) {
					// do nothing
					e.printStackTrace();
				}
				response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
				break;
			case "deleteList":
				// Delete List action

				// make sure we got the params we need
				if (list_id == null) {
					response.sendRedirect("ViewProfile?user_id=" + ((User) request.getSession().getAttribute("user")).getUser_id());
				}
				
				try {
					// Check if the user wants to make a default list name
					if (list_name.equals("Favorites") || 
							list_name.equals("To Read") ||
							list_name.equals("Finished") ||
							list_name.equals("Reviewed")) {
						response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
					}
					
					// First check if the list already exists
					List<Lists> lists = Utility.getListsByUserID(currentUser.getUser_id());
					
					for (Lists l : lists) {
						if (l.equals(list_name)) {
							response.sendRedirect("ListViewer?list_id=" + l.getList_id());
						}
					}
					
					// Delete the list
					ListsUtility.deleteList(Utility.getListByID(Integer.valueOf(list_id)));					
				} catch (Exception e) {
					// do nothing
					e.printStackTrace();
				}
				response.sendRedirect("ViewProfile?user_id=" + Integer.valueOf(user_id));
				break;
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