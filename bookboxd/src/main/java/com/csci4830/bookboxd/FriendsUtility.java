package com.csci4830.bookboxd;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
	 * TODO: Send a friend request
	 * @param sending_user User_id of the user sending the friend request
	 * @param receiving_user User_id of the user receiving the friend request
	 * @return success code
	 */
	public static Integer sendFriendRequest(Integer sending_user, Integer receiving_user) {
		//our datamodel will need to be edited in some way to handle this
		//simplest way of doing it imo would be adding a third column to friends named "confirmed" that's a 0 or a 1, and a pending friend request will be a 0
		//alternatively have a friend request table
		return null;
	}
	
	/**
	 * TODO: Respond to a friend request
	 * @param responding_user User_id of the person accepting/denying the friend request
	 * @param responding_to User_id of the person who had sent the friend request
	 * @return success code
	 */
	public static Integer respondFriendRequest(Integer responding_user, Integer responding_to) {
		return null;
	}
}