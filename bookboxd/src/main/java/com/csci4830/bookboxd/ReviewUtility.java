package com.csci4830.bookboxd;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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
	 * TODO
	 * @param review_id
	 * @param new_privacy_setting
	 * @return
	 */
	public static Reviews changeReviewPrivacy(Integer review_id, Integer new_privacy_setting) {
		return null;
	}
	
	/**
	 * TODO
	 * @param review_id
	 * @return
	 */
	public static Integer getReviewPrivacy(Integer review_id) {
		return null;
	}
	
	/**
	 * TODO
	 * @param review_id
	 * @param updated_rating
	 * @param updated_comments
	 * @return
	 */
	public static Reviews updateReview(Integer review_id, Double updated_rating, String updated_comments) {
		return null;
	}
}