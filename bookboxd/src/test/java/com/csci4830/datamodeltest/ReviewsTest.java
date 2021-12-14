package com.csci4830.datamodeltest;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Before;
import org.junit.Test;

import com.csci4830.datamodel.Reviews;

//TODO
public class ReviewsTest {
	Reviews r = new Reviews(1, 2, 5.0, "This was a good book", 1);
	Reviews r2 = new Reviews();
	
	@Before
	public void init() {
		r.setReview_id(1);
	}
	
	@Test
	public void testGetBook_id() {
		assertEquals((Integer) 2, r.getBook_id());
	}

	@Test
	public void testGetComments() {
		assertEquals("This was a good book", r.getComments());
	}

	@Test
	public void testGetPrivacy_setting() {
		assertEquals((Integer) 1, r.getPrivacy_setting());
	}

	@Test
	public void testGetRating() {
		assertEquals((Double) 5.0, r.getRating());
	}

	@Test
	public void testGetReview_id() {
		assertEquals((Integer) 1, r.getReview_id());
	}

	@Test
	public void testGetUser_id() {
		assertEquals((Integer) 1, r.getUser_id());
	}

	@Test
	public void testSetBook_id() {
		r2.setBook_id(2);
		assertEquals((Integer) 2, r2.getBook_id());
	}

	@Test
	public void testSetComments() {
		r2.setComments("way cool");
		assertEquals("way cool", r2.getComments());
	}

	@Test
	public void testSetPrivacy_setting() {
		r2.setPrivacy_setting(1);
		assertEquals((Integer) 1, r2.getPrivacy_setting());
	}

	@Test
	public void testSetRating() {
		r2.setRating(5.0);
		assertEquals((Double) 5.0, r2.getRating());
	}

	@Test
	public void testSetReview_id() {
		r2.setReview_id(1);
		assertEquals((Integer) 1, r2.getReview_id());
	}

	@Test
	public void testSetUser_id() {
		r2.setUser_id(1);
		assertEquals((Integer) 1, r2.getUser_id());
	}
}