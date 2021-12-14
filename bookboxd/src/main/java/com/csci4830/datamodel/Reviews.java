package com.csci4830.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8 CREATE TABLE reviews ( 
 * 			review_id INT NOT NULL AUTO_INCREMENT,
 * 			user_id INT NOT NULL,
 * 			book_id INT NOT NULL,
 * 			rating DOUBLE NOT NULL,
 * 			comments VARCHAR(255),
 * 			privacy_setting INT,
 * 			PRIMARY KEY (review_id),
 * 			FOREIGN KEY (user_id) REFERENCES User(user_id),
 * 			FOREIGN KEY (book_id) REFERENCES Book(book_id)
 * 		  )
 */		  

@Entity
@Table(name = "reviews")
public class Reviews {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="review_id")
	private Integer review_id;
	
	@Column(name="user_id")
	private Integer user_id;
	
	@Column(name="book_id")
	private Integer book_id;
	
	@Column(name="rating")
	private Double rating;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="privacy_setting")
	private Integer privacy_setting;
	
	public Reviews() {
		
	}

	public Reviews(Integer user_id, Integer book_id, Double rating, String comments,
			Integer privacy_setting) {
		this.user_id = user_id;
		this.book_id = book_id;
		this.rating = rating;
		this.comments = comments;
		this.privacy_setting = privacy_setting;
	}

	public Integer getReview_id() {
		return review_id;
	}

	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getPrivacy_setting() {
		return privacy_setting;
	}

	public void setPrivacy_setting(Integer privacy_setting) {
		this.privacy_setting = privacy_setting;
	}

	@Override
	public String toString() {
		return "Reviews [review_id=" + review_id + ", user_id=" + user_id + ", book_id=" + book_id + ", rating="
				+ rating + ", comments=" + comments + ", privacy_setting=" + privacy_setting + "]";
	}
}
