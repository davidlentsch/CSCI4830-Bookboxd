package com.csci4830.datamodeltest;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Before;
import org.junit.Test;

import com.csci4830.datamodel.Lists;

//TODO
public class ListsTest {
	Lists l = new Lists();
	Lists l2 = new Lists();
	
	@Before
	public void init() {
		l.setList_id(1);
		l.setList_name("Test List");
		l.setPrivacy_setting(0);
		l.setUser_id(5);
	}
	
	@Test
	public void testGetList_id() {
		assertEquals((Integer) 1, l.getList_id());
	}
	
	@Test
	public void testGetList_name() {
		assertEquals("Test List", l.getList_name());
	}
	
	@Test
	public void testGetPrivacy_setting() {
		assertEquals((Integer) 0, l.getPrivacy_setting());
	}
	
	@Test
	public void testGetUser_id() {
		assertEquals((Integer) 5, l.getUser_id());
	}
	
	@Test
	public void testSetList_id() {
		l2.setList_id(4);
		assertEquals((Integer) 4, l2.getList_id());
	}
	
	@Test
	public void testSetList_name() {
		l2.setList_name("TestName2");
		assertEquals("TestName2", l2.getList_name());
	}
	
	@Test
	public void testSetPrivacy_setting() {
		l2.setPrivacy_setting(3);
		assertEquals((Integer) 3, l2.getPrivacy_setting());
	}
	
	@Test
	public void testSetUser_id() {
		l2.setUser_id(6);
		assertEquals((Integer) 6, l2.getUser_id());
	}
	
}