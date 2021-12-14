package com.csci4830.bookboxdtest;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import com.csci4830.bookboxd.FriendsUtility;
import com.csci4830.datamodel.*;

@TestMethodOrder(OrderAnnotation.class)
public class FriendsUtilityTest {
	@Test
	@Order(1)
	public void testGetSentFriendRequest() {
		Integer res = FriendsUtility.getSentFriendRequest(1, 2);
		assertEquals((Integer) 1, res);
		
		Integer res2 = FriendsUtility.getSentFriendRequest(2, 1);
		assertEquals((Integer) 0, res2);
	}
	
	@Test
	@Order(2)
	public void testSendFriendRequest() {
		Friends f = FriendsUtility.sendFriendRequest(1, 5);
		assertTrue(f.getUser_id_1().equals(1));
		assertTrue(f.getUser_id_2().equals(5));
		assertTrue(f.getConfirmed().equals(0));
		FriendsUtility.denyFriendRequest(5, 1);
		
		//TODO: test exception
	}
	
	@Test
	@Order(3)
	public void testGetReceivedFriendRequests() {
		List<Friends> friends = FriendsUtility.getReceivedFriendRequests(2);
		assertTrue(friends.size() == 0);
		
		FriendsUtility.sendFriendRequest(1, 5);
		List<Friends> friends2 = FriendsUtility.getReceivedFriendRequests(5);
		assertTrue(friends2.size() == 1);
		FriendsUtility.denyFriendRequest(5, 1);
		
		//TODO: test exception
	}

	@Test
	@Order(4)
	public void testAcceptFriendRequest() {
		FriendsUtility.sendFriendRequest(1, 5);
		Friends f = FriendsUtility.acceptFriendRequest(5, 1);
		
		Session session = FriendsUtility.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Friends friend = (Friends) session.get(Friends.class, new FriendsPK(1, 5));
			assertEquals((Integer) 1, friend.getConfirmed());
			
			FriendsUtility.denyFriendRequest(5, 1);
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@Test
	@Order(5)
	public void testDenyFriendRequest() {
		FriendsUtility.sendFriendRequest(1, 5);
		Friends f = FriendsUtility.denyFriendRequest(5, 1);
		Session session = FriendsUtility.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Friends friend = (Friends) session.get(Friends.class, new FriendsPK(1, 5));
			
			assertNull(friend);
		} finally {
			session.close();
		}
	}
}