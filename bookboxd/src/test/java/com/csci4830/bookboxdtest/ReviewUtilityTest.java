package com.csci4830.bookboxdtest;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.csci4830.bookboxd.ReviewUtility;
import com.csci4830.datamodel.*;

public class ReviewUtilityTest {
	@Test
	public void testCreateReview() {
		Reviews r = ReviewUtility.createReview(1, 1, 4.2, "good book!", 0);
		
		Session session = ReviewUtility.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Reviews review = (Reviews) session.get(Reviews.class, r.getReview_id());
			assertEquals((Integer) 1, review.getUser_id());
			assertEquals((Integer) 1, review.getBook_id());
			assertEquals((Double) 4.2, review.getRating());
			assertEquals("good book!", review.getComments());
			assertEquals((Integer) 0, review.getPrivacy_setting());
			
			ReviewUtility.deleteReview(r);
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@Test
	public void testDeleteReview() {
		Reviews r = ReviewUtility.createReview(1, 1, 4.2, "good book!", 0);
		ReviewUtility.deleteReview(r);
		
		Session session = ReviewUtility.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Reviews review = (Reviews) session.get(Reviews.class, r.getReview_id());
			assertNull(review);
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Test
	public void testGetReviewPrivacy() {
		Reviews r = ReviewUtility.createReview(1, 1, 4.2, "good book!", 0);
		assertEquals((Integer) 0, ReviewUtility.getReviewPrivacy(r.getReview_id()));
		ReviewUtility.deleteReview(r);
	}
	
	@Test
	public void testChangeReviewPrivacy() {
		Reviews r = ReviewUtility.createReview(1, 1, 4.2, "good book!", 0);
		assertEquals((Integer) 0, ReviewUtility.getReviewPrivacy(r.getReview_id()));
		r = ReviewUtility.changeReviewPrivacy(r.getReview_id(), 1);
		assertEquals((Integer) 1, ReviewUtility.getReviewPrivacy(r.getReview_id()));
	}
	
	@Test
	public void testUpdateReview() {
		Reviews r = ReviewUtility.createReview(1, 1, 4.2, "good book!", 0);
		r = ReviewUtility.updateReview(r.getReview_id(), 1.6, "this book sucks!");
		assertEquals((Double) 1.6, r.getRating());
		assertEquals((String) "this book sucks!", r.getComments());
		
		ReviewUtility.deleteReview(r);
	}
	
}