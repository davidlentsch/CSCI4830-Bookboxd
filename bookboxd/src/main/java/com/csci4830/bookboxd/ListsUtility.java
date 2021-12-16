package com.csci4830.bookboxd;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.csci4830.datamodel.Books;
import com.csci4830.datamodel.List_Books;
import com.csci4830.datamodel.Lists;
import com.csci4830.datamodel.User;

public class ListsUtility {
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
	 * Update the parameters of a list
	 * 
	 * @param list_id  List that is being updated
	 * @param list_name The new list name
	 * @param privacy_setting The new privacy setting
	 * @return The list that was updated
	 */
	public static Lists updateList(Integer list_id, String list_name, Integer privacy_setting) {
		Lists updatedList = null;
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			if (list_name.length() <= 255) {
				tx = session.beginTransaction();
				updatedList = (Lists) session.get(Lists.class, list_id);
				updatedList.setPrivacy_setting(privacy_setting);
				updatedList.setList_name(list_name);
				tx.commit();
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			session.getSessionFactory().close();
		}
		return updatedList;

	}
	
	/**
	 * Adds a book to a list. Does not perform validation to see if it is
	 * already on the list.
	 * @param list_id The list ID
	 * @param book_id The book ID
	 * @return The List_Books object that was saved to the DB
	 */
	public static List_Books addBookToList(Integer list_id, Integer book_id) {
	    Session session = getSessionFactory().openSession();
	    Transaction tx = null;
	    List_Books output = null;
	    try {
	        tx = session.beginTransaction();
	        output = new List_Books();
	        output.setList_id(list_id);
	        output.setBook_id(book_id);
	        session.save(output);
	        tx.commit();
	    } catch (HibernateException e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	    	session.close();
			session.getSessionFactory().close();
	    }
	    
	    return output;
	}
	
	
	/**
	 * Removes a List_Books object from the database
	 * @param lb The object to remove
	 * @return The object that was removed from the DB
	 */
	public static List_Books removeBookFromList(List_Books lb) {
	    Session session = getSessionFactory().openSession();
	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	        session.delete(lb);
	        tx.commit();
	    } catch (HibernateException e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	    	session.close();
			session.getSessionFactory().close();
	    }
	    
	    return lb;
	}
	
	/**
	 * Returns the matching List_Books object.
	 * @param list_id The list ID
	 * @param book_id The book ID
	 * @throws NoResultException if the result wasn't found
	 * @return The List_Books object that was found.
	 */
	public static List_Books getListBooksDataObject(Integer list_id, Integer book_id) throws NoResultException {
		return (List_Books) Utility.getDataObject("FROM List_Books WHERE book_id = " + book_id 
				+ " AND list_id = " + list_id, List_Books.class);
	}
	
	
	/**
	 * Creates a list. Does not validate if it already exists.
	 * @param list_name The list name
	 * @param user_id The user ID
	 * @param privacy_level The privacy level of the list
	 * @return The list object that was created.
	 */
	public static Lists createList(String list_name, Integer user_id, Integer privacy_level) {
	    Session session = getSessionFactory().openSession();
	    Transaction tx = null;
	    Lists output = null;
	    try {
	        tx = session.beginTransaction();
	        output = new Lists(user_id, list_name, privacy_level);
	        session.save(output);
	        tx.commit();
	    } catch (HibernateException e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	    	session.close();
			session.getSessionFactory().close();
	    }
	    
	    return output;
	}

	/**
	 * Deletes an entire list. Also deletes all of the books from List_Books.
	 * @param list The list that should be deleted
	 * @return The list that was deleted.
	 */
	public static Lists deleteList(Lists list) {
	    Session session = getSessionFactory().openSession();
	    Transaction tx = null;
	    try {
	        tx = session.beginTransaction();
	        // We need to delete all of the List_Books too
	        List<List_Books> lb = getListBooksByListID(list.getList_id());
	        for (List_Books listBooks : lb) {
	        	session.delete(listBooks);
	        }
	        session.delete(list);
	        tx.commit();
	    } catch (HibernateException e) {
	        if (tx != null) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	    	session.close();
			session.getSessionFactory().close();
	    }

	    return list;
	}
	
	/**
	 * Returns a List of List_Books with the corresponding list ID.
	 * @param listID The list ID.
	 * @return The List of List_Books that were found.
	 */
	public static List<List_Books> getListBooksByListID(Integer listID) {
		return (List<List_Books>) Utility.getDataList("FROM List_Books WHERE list_id = '" + listID + "'", List_Books.class);
	}

}