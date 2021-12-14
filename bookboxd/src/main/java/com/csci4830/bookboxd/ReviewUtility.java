package com.csci4830.bookboxd;

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
import com.csci4830.datamodel.Reviews;

public class ReviewUtility {
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
	 * @param user_id
	 * @param book_id
	 * @param rating
	 * @param comments
	 * @param privacy_setting
	 * @return the created review
	 */
	public static Reviews createReview(Integer user_id, Integer book_id, Double rating, String comments, Integer privacy_setting) {
		Reviews newReview = null;
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			newReview = new Reviews(user_id, book_id, rating, comments, privacy_setting);
			session.save(newReview);
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return newReview;
	}
	
	/**
	 * @param review The review to be deleted
	 * @return deleted review
	 */
	public static Reviews deleteReview(Reviews review) {
		Reviews result = null;
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			result = (Reviews) session.get(Reviews.class, review.getReview_id());
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
	
	/**
	 * 
	 * @param review_id
	 * @param new_privacy_setting
	 * @return updated review
	 */
	public static Reviews changeReviewPrivacy(Integer review_id, Integer new_privacy_setting) {
		Reviews updatedReview = null;
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			updatedReview = (Reviews) session.get(Reviews.class, review_id);
			updatedReview.setPrivacy_setting(new_privacy_setting);
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return updatedReview;
	}
	
	/**
	 * 
	 * @param review_id
	 * @return privacy setting
	 */
	public static Integer getReviewPrivacy(Integer review_id) {
		Reviews review = null;
		Integer privacy_setting = null;
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			review = (Reviews) session.get(Reviews.class, review_id);
			privacy_setting = review.getPrivacy_setting();
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return privacy_setting;
	}
	
	/**
	 * TODO
	 * @param review_id
	 * @param updated_rating
	 * @param updated_comments
	 * @return
	 */
	public static Reviews updateReview(Integer review_id, Double updated_rating, String updated_comments) {
		Reviews updatedReview = null;
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			updatedReview = (Reviews) session.get(Reviews.class, review_id);
			updatedReview.setRating(updated_rating);
			updatedReview.setComments(updated_comments);
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return updatedReview;
	}
}