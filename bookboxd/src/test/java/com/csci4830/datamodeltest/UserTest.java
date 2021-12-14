package com.csci4830.datamodeltest;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import com.csci4830.datamodel.Reviews;
import com.csci4830.datamodel.User;

//TODO
public class UserTest {
	User u = new User();
	User u2 = new User();
	
	@Before
	public void init() {
		u.setAbout_desc("hi i'm maya and i'm very gay");
		u.setPassword("12345");
		u.setPrivacy_setting(1);
		u.setUser_id(8);
		u.setUsername("maya");
	}
	
	
	@Test
	public void testGetAbout_desc() {
		assertEquals("hi i'm maya and i'm very gay", u.getAbout_desc());
	}
	
	@Test
	public void testGetPassword() {
		assertEquals(DigestUtils.sha1Hex("12345"), u.getPassword());
	}
	
	@Test
	public void testGetPrivacy_setting() {
		assertEquals((Integer) 1 , u.getPrivacy_setting());
	}
	
	@Test
	public void testGetUser_id() {
		assertEquals((Integer) 8, u.getUser_id());
	}
	
	@Test
	public void testGetUsername() {
		assertEquals("maya", u.getUsername());
	}
	
	@Test
	public void testSetAbout_desc() {
		u2.setAbout_desc("hi i'm maya and i'm very gay");
		assertEquals("hi i'm maya and i'm very gay", u2.getAbout_desc());
	}
	
	@Test
	public void testSetPassword() {
		u2.setPassword("12345");
		assertEquals(DigestUtils.sha1Hex("12345"), u2.getPassword());
	}
	
	@Test
	public void testSetPrivacy_setting() {
		u2.setPrivacy_setting(1);
		assertEquals((Integer) 1 , u2.getPrivacy_setting());
	}
	
	@Test
	public void testSetUser_id() {
		u2.setUser_id(8);
		assertEquals((Integer) 8, u2.getUser_id());
	}
	
	@Test
	public void testSetUsername() {
		u2.setUsername("maya");
		assertEquals("maya", u.getUsername());
	}
}