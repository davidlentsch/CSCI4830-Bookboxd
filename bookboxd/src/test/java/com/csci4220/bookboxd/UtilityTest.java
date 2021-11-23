package com.csci4220.bookboxd;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Test;

import com.csci4830.bookboxd.Utility;
import com.csci4830.datamodel.*;

public class UtilityTest {

	@Test
	public void testGetUsers() {
		List<User> users = Utility.getUsers();
		assertTrue(users.size() > 0);
		
		for (int i = 0; i < users.size(); i++)
		{
			assertTrue(!users.get(i).getUsername().isEmpty());
			assertTrue(users.get(i).getUser_id() > 0);
		}
	}

	@Test
	public void testGetUserByUserID() {
		User u = Utility.getUserByUserID(1);
		assertTrue(u.getUsername().equals("ichiban"));
		assertTrue(u.getUser_id() == 1);
	}

	@Test
	public void testGetUserByUsername() {
		User u = Utility.getUserByUsername("ichiban");
		assertTrue(u.getUsername().equals("ichiban"));
		assertTrue(u.getUser_id() == 1);
	}

	@Test
	public void testGetBooks() {
		List<Books> books = Utility.getBooks();
		assertTrue(books.size() > 0);
		
		for (int i = 0; i < books.size(); i++)
		{
			Books b = books.get(i);
			assertTrue(b.getBook_id() > 0);
			assertTrue(!b.getBook_name().isEmpty());
			assertTrue(!b.getAuthor().isEmpty());
		}
	}

	@Test
	public void testGetBookByBookID() {
		Books b = Utility.getBookByBookID(1);
		assertTrue(b.getBook_name().equals("To Kill a Mockingbird"));
		assertTrue(b.getBook_id() == 1);
		assertTrue(b.getAuthor().equals("Harper Lee"));
	}

	@Test
	public void testGetBookByName() {		
		Books b = Utility.getBookByName("To Kill a Mockingbird");
		assertTrue(b.getBook_name().equals("To Kill a Mockingbird"));
		assertTrue(b.getBook_id() == 1);
		assertTrue(b.getAuthor().equals("Harper Lee"));
	}

	@Test
	public void testGetBooksByAuthor() {
		List<Books> books = Utility.getBooksByAuthor("Harper Lee");
		assertTrue(books.size() > 0);
		
		for (int i = 0; i < books.size(); i++)
		{
			Books b = books.get(i);
			assertTrue(b.getAuthor().equals("Harper Lee"));
		}
	}

//	Currently, all books have a null genre, so this test is disabled.
//	@Test
//	public void testGetBooksByGenre() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testGetBooksByNameSearch() {
		List<Books> books = Utility.getBooksByNameSearch("Mocking");
		assertTrue(books.size() > 0);
		
		for (int i = 0; i < books.size(); i++)
		{
			Books b = books.get(i);
			assertTrue(b.getBook_name().contains("Mocking"));
		}
	}

	@Test
	public void testGetBooksByAuthorSearch() {
		List<Books> books = Utility.getBooksByNameSearch("J.");
		assertTrue(books.size() > 0);
		
		for (int i = 0; i < books.size(); i++)
		{
			Books b = books.get(i);
			assertTrue(b.getAuthor().contains("J."));
		}
	}

	@Test
	public void testGetBooksByAverageRating() {
		List<Books> books = Utility.getBooksByAverageRating(4, 5);
		assertTrue(books.size() > 4);
		
		for (int i = 0; i < books.size(); i++)
		{
			Books b = books.get(i);
			assertTrue(b.getAverage_rating() >= 4 && b.getAverage_rating() <= 5);
		}
	}

	@Test
	public void testGetBooksByListID() {
		List<Books> books = Utility.getBooksByListID(26);
		assertTrue(books.size() > 0);
		
		for (int i = 0; i < books.size(); i++)
		{
			Books b = books.get(i);
			assertTrue(b.getBook_id() > 0);
			assertTrue(!b.getBook_name().isEmpty());
			assertTrue(!b.getAuthor().isEmpty());
		}
	}

	@Test
	public void testGetLists() {
		List<Lists> lists = Utility.getLists();
		assertTrue(lists.size() > 0);
		
		for (int i = 0; i < lists.size(); i++)
		{
			Lists l = lists.get(i);
			assertTrue(l.getList_id() > 0);
			assertTrue(!l.getList_name().isEmpty());
			assertTrue(l.getUser_id() != null);
		}
	}

	@Test
	public void testGetListsByUserID() {
		List<Lists> lists = Utility.getListsByUserID(1);
		assertTrue(lists.size() > 0);
		
		for (int i = 0; i < lists.size(); i++)
		{
			Lists l = lists.get(i);
			assertTrue(l.getList_id() > 0);
			assertTrue(!l.getList_name().isEmpty());
			assertTrue(l.getUser_id() == 1);
		}
	}

	@Test
	public void testGetListByID() {
		Lists l = Utility.getListByID(1);
		
		assertTrue(l.getList_id() == 1);
		assertTrue(l.getList_name().equals("Favorites"));
		assertTrue(l.getUser_id() == 1);
	}

	@Test
	public void testGetReviews() {
		List<Reviews> reviews = Utility.getReviews();
		assertTrue(reviews.size() > 0);
		
		for (int i = 0; i < reviews.size(); i++)
		{
			Reviews r = reviews.get(i);
			assertTrue(r.getBook_id() > 0);
			assertTrue(r.getUser_id() > 0);
			assertTrue(r.getRating() > 0);
			assertTrue(r.getReview_id() > 0);
		}
	}

	@Test
	public void testGetReviewByReviewID() {
		Reviews r = Utility.getReviewByReviewID(1);
		
		assertTrue(r.getBook_id() == 1);
		assertTrue(r.getRating() == 5);
		assertTrue(r.getUser_id() == 1);
		assertTrue(r.getReview_id() == 1);
	}

	@Test
	public void testGetReviewsByUserID() {
		List<Reviews> reviews = Utility.getReviewsByUserID(1);
		assertTrue(reviews.size() > 0);
		
		for (int i = 0; i < reviews.size(); i++)
		{
			Reviews r = reviews.get(i);
			assertTrue(r.getUser_id() == 1);
		}
	}

	@Test
	public void testGetReviewsByBookID() {
		List<Reviews> reviews = Utility.getReviewsByBookID(10);
		assertTrue(reviews.size() > 0);
		
		for (int i = 0; i < reviews.size(); i++)
		{
			Reviews r = reviews.get(i);
			assertTrue(r.getBook_id() == 10);
		}
	}
	
	@Test
	public void testCheckLogin() {
		User u = Utility.checkLogin("ichiban", "12345");
		assertTrue(u.getUsername().equals("ichiban"));
	}
	
	@Test
	public void testCreateUser() {
		User u = Utility.createUser("TestBob", "Bob, but backwards");
		assertTrue(u.getUsername().equals("TestBob"));
		assertTrue(u.getPassword().equals("Bob, but backwards"));
	}
	
	@Test
	public void testCreatedUserFromDatabase() {
		User u = Utility.createUser("TestMaya1", "12345");
		try {
		User o = Utility.getUserByUserID(u.getUser_id());
		assertTrue(u.getPassword().equals(o.getPassword()));
		} catch (NoResultException e) {
			assertTrue(false);
		}
		
		// Test if the default lists were created
		List<Lists> l = Utility.getListsByUserID(u.getUser_id());
		for (Lists list : l) {
			assertTrue(list.getUser_id() == u.getUser_id());
		}
	}
		
}
