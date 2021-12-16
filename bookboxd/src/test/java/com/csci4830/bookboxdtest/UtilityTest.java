package com.csci4830.bookboxdtest;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.csci4830.bookboxd.ReviewUtility;
import com.csci4830.bookboxd.UserProfileUtility;
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

	@Test
	public void testGetBooksByGenre() {
		List<Books> books = Utility.getBooksByGenre("fantasy");
		assertTrue(books.size() > 0);
		
		for (Books b : books) {
			assertEquals(b.getGenre(), "fantasy");
		}
	}

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
		List<Books> books = Utility.getBooksByAuthorSearch("J.");
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
		assertTrue(books.size() >= 4);
		
		for (int i = 0; i < books.size(); i++)
		{
			Books b = books.get(i);
			assertTrue(b.getAverage_rating() >= 4 && b.getAverage_rating() <= 5);
		}
	}

	@Test
	public void testGetBooksByListID() {
		List<Books> books = Utility.getBooksByListID(26);
		
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
		String encrypted = Utility.encryptSHA1("Bob, but backwards");
		assertEquals(encrypted, u.getPassword());
	}
	
	@Test
	public void testGetFriendsByUserID() {
		List<Friends> l = Utility.getFriendsByUserID(1);
		assertTrue(l.size() > 0);
		
		for (Friends f : l) {
			assertTrue(f.getUser_id_1() == 1 || f.getUser_id_2() == 1);
		}
	}
		
	@Test
	public void testCreateCustomList() {
		Lists l = Utility.createCustomList(1, "my new list", 1);
		
		Session session = Utility.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Lists list = (Lists) session.get(Lists.class, l.getList_id());
			assertEquals((Integer) 1, list.getUser_id());
			assertEquals("my new list", list.getList_name());
			assertEquals((Integer) 1, list.getPrivacy_setting());
			
			Utility.deleteCustomList(list);
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testDeleteCustomList() {
		Lists l = Utility.createCustomList(1, "my new list", 1);
		Utility.deleteCustomList(l);
		
		Session session = Utility.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Lists list = (Lists) session.get(Lists.class, l.getList_id());
			assertNull(list);
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testCreateBook() {
		Books b = Utility.createBook("https://upload.wikimedia.org/wikipedia/commons/thumb/0/06/Iceland_Dettifoss_1972-4.jpg/348px-Iceland_Dettifoss_1972-4.jpg", "name", "author", "genre", "description");
		
		Session session = Utility.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Books book = (Books) session.get(Books.class, b.getBook_id());
			assertEquals("https://upload.wikimedia.org/wikipedia/commons/thumb/0/06/Iceland_Dettifoss_1972-4.jpg/348px-Iceland_Dettifoss_1972-4.jpg", book.getImage_url());
			assertEquals("name", book.getBook_name());
			assertEquals("author", book.getAuthor());
			assertEquals("genre", book.getGenre());
			assertEquals("description", book.getDescription());
			
			Utility.deleteBook(book);
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testDeleteBook() {
		Books b = Utility.createBook("https://upload.wikimedia.org/wikipedia/commons/thumb/0/06/Iceland_Dettifoss_1972-4.jpg/348px-Iceland_Dettifoss_1972-4.jpg", "name", "author", "genre", "description");
		Utility.deleteBook(b);
		
		Session session = Utility.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Books book = (Books) session.get(Books.class, b.getBook_id());
			assertNull(book);
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testGetPublicListsByUserID() {
		List<Lists> lists = Utility.getListsByUserID(1);
		for (Lists l: lists) {
			UserProfileUtility.changeListPrivacy(1, l.getList_id(), 0);
		}
		
		lists = Utility.getPublicListsByUserID(1);
		for (Lists l: lists) {
			assertEquals((Integer) 1, l.getUser_id());
			assertEquals((Integer) 0, l.getPrivacy_setting());
		}
		
	}
	
	@Test
	public void testGetAverageReview() {
		Double avg = Utility.getAverageReview(4);
		assertEquals((Double) 3.5, avg);
	}
}
