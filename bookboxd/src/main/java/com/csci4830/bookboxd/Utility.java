package com.csci4830.bookboxd;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.csci4830.datamodel.User;
import com.csci4830.datamodel.Books;
import com.csci4830.datamodel.Lists;
import com.csci4830.datamodel.Reviews;

public class Utility {
	static SessionFactory sessionFactory = null;

	/**
	 * Returns the SessionFactory object for this Hibernate configuration.
	 * 
	 * @return The SessionFactory object.
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory != null) {
			return sessionFactory;
		}

		// New versions of Hibernate don't like the code given in class and is
		// considered deprecated.
		// Source for this code:
		// https://stackoverflow.com/questions/33005348/hibernate-5-org-hibernate-mappingexception-unknown-entity

		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

		return metadata.getSessionFactoryBuilder().build();
	}

	/**
	 * Returns a list of all users in the database.
	 * 
	 * @return A List containing all Users.
	 */
	public static List<User> getUsers() {
		List<User> resultList = new ArrayList<User>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> users = session.createQuery("FROM User").list();
			for (Iterator<?> iterator = users.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				resultList.add(user);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return resultList;
	}

	/**
	 * Returns a User object of the user with the corresponding user_id. If the user
	 * is not found, then a NoResultException is thrown.
	 * 
	 * If for whatever reason that more than one is found, then the first result is
	 * returned.
	 * 
	 * @param user_id The ID of the user
	 * @throws NoResultException when there is no result.
	 * @throws NonUniqueResultException when there is more than one result
	 * @return The User object that was found.
	 */
	public static User getUserByUserID(Integer user_id) throws NoResultException, NonUniqueResultException {
		User result = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			result = (User) session.createQuery("FROM User WHERE user_id = " + user_id).getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	/**
	 * Returns a User object of the user with the corresponding username. If the
	 * user is not found, then a NoResultException is thrown.
	 * 
	 * If for whatever reason that more than one is found, then the first result is
	 * returned.
	 * 
	 * @param username The username of the User
     * @throws NoResultException when there is no result.
	 * @throws NonUniqueResultException when there is more than one result
	 * @return The User object that was found.
	 */
	public static User getUserByUsername(String username) throws NoResultException, NonUniqueResultException {
		User result = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			result = (User) session.createQuery("FROM User WHERE username = '" + username + "'").getSingleResult();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	/**
	 * Returns a list of all books from the database.
	 * 
	 * @return A List of all Books.
	 */
	public static List<Books> getBooks() {
		List<Books> resultList = new ArrayList<Books>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> books = session.createQuery("FROM Books").list();
			for (Iterator<?> iterator = books.iterator(); iterator.hasNext();) {
				Books book = (Books) iterator.next();
				resultList.add(book);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return resultList;
	}

	/**
	 * Returns a Books object of the book with the corresponding id. If the book is
	 * not found, then a NoResultException is thrown.
	 * 
	 * If for whatever reason that more than one is found, then the first result is
	 * returned.
	 * 
	 * @param id The ID of the book
	 * @throws NoResultException when there is no result.
	 * @throws NonUniqueResultException when there is more than one result
	 * @return The Books object that was found.
	 */
	public static Books getBookByBookID(Integer id) throws NoResultException, NonUniqueResultException {
		Books result = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			result = (Books) session.createQuery("FROM Books WHERE book_id = '" + id + "'").getSingleResult();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}

	/**
	 * Returns a Books object of the book with the corresponding name. If the book
	 * is not found, then a NoResultException is thrown.
	 * 
	 * If for whatever reason that more than one is found, then the first result is
	 * returned.
	 * 
	 * @param name The exact name of the book
	 * @throws NoResultException when there is no result.
	 * @throws NonUniqueResultException when there is more than one result
	 * @return The Books object that was found.
	 */
	public static Books getBookByName(String name) throws NoResultException, NonUniqueResultException {
		Books result = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			result = (Books) session.createQuery("FROM Books WHERE book_name = '" + name + "'").getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}
		
	/**
	 * Returns a List of Books with the corresponding author.
	 * @param name The exact name of the author.
	 * @return The List of books that were found.
	 */
	public static List<Books> getBooksByAuthor(String name) {
		List<Books> resultList = new ArrayList<Books>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> books = session.createQuery("FROM Books WHERE author = '" + name + "'").list();
			for (Iterator<?> iterator = books.iterator(); iterator.hasNext();) {
				Books book = (Books) iterator.next();
				resultList.add(book);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return resultList;
	}

	/**
	 * Returns a List of Books with the corresponding genre.
	 * @param name The exact name of the genre.
	 * @return The List of books that were found.
	 */
	public static List<Books> getBooksByGenre(String genre) {
		List<Books> resultList = new ArrayList<Books>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> books = session.createQuery("FROM Books WHERE genre = '" + genre + "'").list();
			for (Iterator<?> iterator = books.iterator(); iterator.hasNext();) {
				Books book = (Books) iterator.next();
				resultList.add(book);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return resultList;
	}
		
	/**
	 * Returns a List of Books that match the title search query. Uses the LIKE
	 * SQL operator.
	 * @param search The search query for the book.
	 * @return The List of books that were found.
	 */
	public static List<Books> getBooksByNameSearch(String search) {
		List<Books> resultList = new ArrayList<Books>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> books = session.createQuery("FROM Books WHERE book_name IS LIKE '%" + search + "%'").list();
			for (Iterator<?> iterator = books.iterator(); iterator.hasNext();) {
				Books book = (Books) iterator.next();
				resultList.add(book);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return resultList;
	}
	
	/**
	 * Returns a List of Books that match the author search query. Uses the LIKE
	 * SQL operator.
	 * @param search The search query for the book.
	 * @return The List of books that were found.
	 */
	public static List<Books> getBooksByAuthorSearch(String search) {
		List<Books> resultList = new ArrayList<Books>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> books = session.createQuery("FROM Books WHERE author IS LIKE '%" + search + "%'").list();
			for (Iterator<?> iterator = books.iterator(); iterator.hasNext();) {
				Books book = (Books) iterator.next();
				resultList.add(book);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return resultList;
	}
		
	/**
	 * Performs a search for books between the min and max ratings. Returns the
	 * results in a List.
	 * @param min The minimum rating inclusive
	 * @param max The maximum rating inclusive
	 * @return The results found.
	 */
	public static List<Books> getBooksByAverageRating(Integer min, Integer max) {
		List<Books> resultList = new ArrayList<Books>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> books = session.createQuery("FROM Books WHERE average_rating IS BETWEEN " + min + "AND " + max).list();
			for (Iterator<?> iterator = books.iterator(); iterator.hasNext();) {
				Books book = (Books) iterator.next();
				resultList.add(book);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return resultList;
	}
		
	/**
	 * Retrieves a list of books referenced by a list ID.
	 * @param id The ID of the list.
	 * @return The results found.
	 */
	public static List<Books> getBooksByListID(Integer id) {
		List<Books> resultList = new ArrayList<Books>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> books = session.createQuery("SELECT b.book_id,b.book_name,b.author,b.genre,b.description,b.average_rating FROM List_Books l LEFT JOIN Books b ON l.book_id = b.book_id WHERE l.list_id = '" + id + "'").list();
			for (Iterator<?> iterator = books.iterator(); iterator.hasNext();) {
				Books book = (Books) iterator.next();
				resultList.add(book);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return resultList;
	}
		
	/**
	 * Returns a list of all lists in the database.
	 * 
	 * @return A List containing all Lists.
	 */
	public static List<Lists> getLists() {
		List<Lists> resultList = new ArrayList<Lists>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> lists = session.createQuery("FROM Lists").list();
			for (Iterator<?> iterator = lists.iterator(); iterator.hasNext();) {
				Lists list = (Lists) iterator.next();
				resultList.add(list);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return resultList;
	}
		
	/**
	 * Retrieves a list of lists referenced by a user ID.
	 * @param id The ID of the list.
	 * @return The results found.
	 */
	public static List<Lists> getListsByUserID(Integer id) {
		List<Lists> resultList = new ArrayList<Lists>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> lists = session.createQuery("from Lists WHERE user_id = '" + id + "'").list();
			for (Iterator<?> iterator = lists.iterator(); iterator.hasNext();) {
				Lists list = (Lists) iterator.next();
				resultList.add(list);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return resultList;
	}
	
	/**
	 * Returns a Lists object of the list with the corresponding list_id. If the list
	 * is not found, then a NoResultException is thrown.
	 * 
	 * If for whatever reason that more than one is found, then the first result is
	 * returned.
	 * 
	 * @param list_id The ID of the list
	 * @throws NoResultException when there is no result.
	 * @throws NonUniqueResultException when there is more than one result
	 * @return The Lists object that was found.
	 */
	public static Lists getListByID(Integer list_id) throws NoResultException, NonUniqueResultException {
		Lists result = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			result = (Lists) session.createQuery("FROM Lists WHERE list_id = '" + list_id + "'").getSingleResult();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}
	
	/**
	 * Returns a list of all lists in the database.
	 * 
	 * @return A List containing all Lists.
	 */
	public static List<Reviews> getReviews() {
		List<Reviews> resultList = new ArrayList<Reviews>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> reviews = session.createQuery("FROM Reviews").list();
			for (Iterator<?> iterator = reviews.iterator(); iterator.hasNext();) {
				Reviews review = (Reviews) iterator.next();
				resultList.add(review);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return resultList;
	}
		
	/**
	 * Returns a Reviews object of the review with the corresponding review_id. If the review
	 * is not found, then a NullPointerException is thrown.
	 * 
	 * If for whatever reason that more than one is found, then the first result is
	 * returned.
	 * 
	 * @param review_id The ID of the review
	 * @throws NoResultException when there is no result.
	 * @throws NonUniqueResultException when there is more than one result
	 * @return The Reviews object that was found.
	 */
	public static Reviews getReviewByReviewID(Integer review_id) throws NullPointerException, NonUniqueResultException {
		Reviews result = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			result = (Reviews) session.createQuery("FROM Reviews WHERE review_id = '" + review_id + "'").getSingleResult();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return result;
	}
		
	/**
	 * Returns a List of Reviews with the corresponding user ID.
	 * @param user_id The user ID.
	 * @return The List of Reviews that were found.
	 */
	public static List<Reviews> getReviewsByUserID(Integer userID) {
		List<Reviews> resultList = new ArrayList<Reviews>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> books = session.createQuery("FROM Reviews WHERE user_id = '" + userID + "'").list();
			for (Iterator<?> iterator = books.iterator(); iterator.hasNext();) {
				Reviews review = (Reviews) iterator.next();
				resultList.add(review);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return resultList;
	}
	
	/**
	 * Returns a List of Reviews with the corresponding book ID.
	 * @param book_id The book ID.
	 * @return The List of Reviews that were found.
	 */
	public static List<Reviews> getReviewsByBookID(Integer bookID) {
		List<Reviews> resultList = new ArrayList<Reviews>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List<?> books = session.createQuery("FROM Reviews WHERE book_id = '" + bookID + "'").list();
			for (Iterator<?> iterator = books.iterator(); iterator.hasNext();) {
				Reviews review = (Reviews) iterator.next();
				resultList.add(review);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return resultList;
	}
	
	
	/**
	 * Checks the login username and password in the database.
	 * 
	 * @param username The username to check
	 * @param password The password to check
	 * @return A User object if the username and password matches
	 * @throws NoResultException If they do not match
	 * @throws NonUniqueResultException If more than one matches... this should never happen.
	 */
	public static User checkLogin(String username, String password) throws NoResultException, NonUniqueResultException {
		User u = null;

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			u = (User) session.createQuery("FROM User WHERE username = '" + username +
					"' AND password = '" + password + "'").getSingleResult();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return u;
	}
}
