package com.csci4830.bookboxd;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.csci4830.datamodel.Lists;
import com.csci4830.datamodel.User;

public class UserProfileUtility {
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
	 * update the about me section
	 * 
	 * @param user_id  User whose about me is being updated
	 * @param comments New about me section note that the about me can only be as
	 *                 long as mysql can hold
	 * @return success code
	 */
	public static User updateAboutMe(Integer user_id, String comments) {
		User updatedUser = null;
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			if (comments.length() <= 255) {
				tx = session.beginTransaction();
				updatedUser = (User) session.get(User.class, user_id);
				updatedUser.setAbout_desc(comments);
				
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
		return updatedUser;

	}

	/**
	 * change a user's profile privacy
	 * 
	 * @param user_id             User making changes
	 * @param new_privacy_setting
	 * @return updated user
	 */
	public static User changeProfilePrivacy(Integer user_id, Integer new_privacy_setting) {
		User updatedUser = null;
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			updatedUser = (User) session.get(User.class, user_id);
			updatedUser.setPrivacy_setting(new_privacy_setting);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			session.getSessionFactory().close();
		}
		return updatedUser;
	}

	/**
	 * change a list's privacy setting
	 * 
	 * @param user_id
	 * @param list_id
	 * @param new_privacy_setting
	 * @return
	 */
	public static Lists changeListPrivacy(Integer user_id, Integer list_id, Integer new_privacy_setting) {
		Lists updatedList = null;
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			updatedList = (Lists) session.get(Lists.class, list_id);
			updatedList.setPrivacy_setting(new_privacy_setting);
			
			tx.commit();
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
	 * get the privacy setting of a user's profile
	 * 
	 * @param user_id
	 * @return The current privacy setting of a user's profile
	 */
	public static Integer getProfilePrivacy(Integer user_id) {
		User user = null;
		Integer privacy = null;
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			user = (User) session.get(User.class, user_id);
			privacy = user.getPrivacy_setting();
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			session.getSessionFactory().close();
		}
		
		return privacy;
	}

}