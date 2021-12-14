package com.csci4830.datamodeltest;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Test;

import com.csci4830.datamodel.Books;

//TODO
public class BooksTest {
	Books b = new Books("A Test Book", "Maya", "Fantasy", "A super cool book about some awesome lesbians who get married");
	Books bEmpty = new Books();
	
	@Test
	public void testGetAuthor() {
		assertEquals("Maya", b.getAuthor());
	}
	
	@Test
	public void testGetAverage_rating() {
		assertEquals((Double) 0.0, b.getAverage_rating());
	}
	
	@Test
	public void testGetBook_id() {
		b.setBook_id(1);
		assertEquals((Integer) 1, b.getBook_id());
	}
	
	@Test
	public void testGetBook_name() {
		assertEquals("A Test Book", b.getBook_name());
	}
	
	@Test
	public void testGetDescription() {
		assertEquals("A super cool book about some awesome lesbians who get married", b.getDescription());
	}
	
	@Test
	public void testGetGenre() {
		assertEquals("Fantasy", b.getGenre());
	}
	
	@Test
	public void testSetAuthor() {
		bEmpty.setAuthor("Maya");
		assertEquals("Maya", bEmpty.getAuthor());
	}
	
	@Test
	public void testSetAverage_rating() {
		bEmpty.setAverage_rating(5.0);
		assertTrue(bEmpty.getAverage_rating() == 5.0);
	}
	
	@Test
	public void testSetBook_id() {
		bEmpty.setBook_id(2);
		assertEquals((Integer) 2, bEmpty.getBook_id());
	}
	
	@Test
	public void testSetBook_name() {
		bEmpty.setBook_name("My Cool Book");
		assertEquals("My Cool Book", bEmpty.getBook_name());
	}
	
	@Test
	public void testSetDescription() {
		bEmpty.setDescription("desc");
		assertEquals("desc", bEmpty.getDescription());
	}
	
	@Test
	public void testSetGenre() {
		bEmpty.setGenre("Fiction");
		assertEquals("Fiction", bEmpty.getGenre());
	}
}