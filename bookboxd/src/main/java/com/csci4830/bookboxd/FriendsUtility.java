package com.csci4830.bookboxd;

import java.util.ArrayList;
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

import com.csci4830.datamodel.Friends;
import com.csci4830.datamodel.FriendsPK;

public class FriendsUtility {
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
	 * Checks to see if there is a friends relationship existing, pending or not, from user A to user B
	 * note this does not check both ways (ie user B to user A)
	 * 
	 * @param sending_user
	 * @param receiving_user
	 * @throws NoResultException when there is not a sent friend request
	 * @return 0 if there is no friend request pending, 1 if there is
	 */
	public static Integer getSentFriendRequest(Integer sending_user, Integer receiving_user)
			throws NoResultException {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.createQuery("FROM Friends WHERE user_id_1 = " + sending_user + " AND user_id_2 = " + receiving_user)
					.getSingleResult();

			tx.commit();
		} catch (NoResultException e) {
			return 0;
		} finally {
			session.close();
		}

		return 1;
	}
	
	/**
	 * Checks to see if there is an existing friend request
	 * 
	 * @param receiving_user
	 * @return 0 if there is no friend request pending, 1 if there is
	 */
	public static List<Friends> getReceivedFriendRequests(Integer receiving_user) {
		List<Friends> resultList = new ArrayList<Friends>();

		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			resultList = session.createQuery("FROM Friends WHERE confirmed = 0 AND user_id_2 = " + receiving_user).list();

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
	 * @param sending_user   User_id of the user sending the friend request
	 * @param receiving_user User_id of the user receiving the friend request
	 * @return newly created friends object
	 */
	public static Friends sendFriendRequest(Integer sending_user, Integer receiving_user) {
		Friends newRequest = null;
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			newRequest = new Friends(sending_user, receiving_user);
			session.save(newRequest);
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return newRequest;
	}

	/**
	 * Accept a friend request
	 * 
	 * @param responding_user User_id of the person accepting/denying the friend
	 *                        request
	 * @param responding_to   User_id of the person who had sent the friend request
	 * @return updated friend request
	 */
	public static Friends acceptFriendRequest(Integer responding_user, Integer responding_to) {
		Friends result = null;
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			result = (Friends) session.get(Friends.class, new FriendsPK(responding_to, responding_user));
			result.setConfirmed(1);
			
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
	 * Deny a friend request
	 * also works to delete a friend
	 * 
	 * @param responding_user User_id of the person accepting/denying the friend
	 *                        request
	 * @param responding_to   User_id of the person who had sent the friend request
	 * @return updated friend request
	 */
	public static Friends denyFriendRequest(Integer responding_user, Integer responding_to) {
		Friends result = null;
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			result = (Friends) session.get(Friends.class, new FriendsPK(responding_to, responding_user));
			session.delete(result);
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
}