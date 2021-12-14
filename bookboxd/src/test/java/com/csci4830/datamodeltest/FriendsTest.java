package com.csci4830.datamodeltest;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Test;

import com.csci4830.datamodel.Friends;

//TODO
public class FriendsTest {
	Friends f = new Friends(2, 4);
	Friends f2 = new Friends();
	
	@Test
	public void testGetUser_id_1() {
		assertEquals((Integer) 2, f.getUser_id_1());
	}
	
	@Test
	public void testGetUser_id_2() {
		assertEquals((Integer) 4, f.getUser_id_2());
	}
	
	@Test
	public void testSetUser_id_1() {
		f2.setUser_id_1(1);
		assertEquals((Integer) 1, f2.getUser_id_1());
	}
	
	@Test
	public void testSetUser_id_2() {
		f2.setUser_id_2(3);
		assertEquals((Integer) 3, f2.getUser_id_2());
	}

	@Test
	public void testSetConfirmed() {
		f2.setConfirmed(1);
		assertEquals((Integer) 1, f2.getConfirmed());
	}
	
	@Test
	public void testGetConfirmed() {
		assertEquals((Integer) 0, f.getConfirmed());
	}
	
	@Test
	public void testEquals() {
		Friends f3 = new Friends(2, 4);
		assertTrue(f.equals(f3));
		assertFalse(f.equals(f2));
	}
}