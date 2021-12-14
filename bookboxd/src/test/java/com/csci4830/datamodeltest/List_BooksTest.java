package com.csci4830.datamodeltest;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Before;
import org.junit.Test;

import com.csci4830.datamodel.List_Books;

//TODO
public class List_BooksTest {
	List_Books lb = new List_Books();
	
	@Before
	public void init() {
		lb.setBook_id(2);
		lb.setList_id(1);
	}
	
	@Test
	public void testGetBook_id() {
		assertEquals((Integer) 2, lb.getBook_id());
	}
	
	@Test
	public void testGetList_id() {
		assertEquals((Integer) 1, lb.getList_id());
	}
	
	@Test
	public void testSetBook_id() {
		lb.setBook_id(3);
		assertEquals((Integer) 3, lb.getBook_id());
	}
	
	@Test
	public void testSetList_id() {
		lb.setList_id(4);
		assertEquals((Integer) 4, lb.getList_id());
	}
	
	@Test
	public void testEquals() {
		List_Books lb2 = new List_Books();
		List_Books lb3 = new List_Books();
		
		lb2.setBook_id(1);
		lb2.setList_id(2);
		
		lb3.setBook_id(1);
		lb3.setList_id(2);
		
		assertTrue(lb2.equals(lb3));
		
		lb3.setBook_id(3);
		lb3.setList_id(4);
		assertFalse(lb2.equals(lb3));
	}
}