package com.csci4830.datamodel;

public class ReviewsUser {
	
	private Integer review_id;
	private Integer user_id;
	private Integer book_id;
	private Double rating;
	private String comments;
	private Integer privacy_setting;
	private User user;
	
	public ReviewsUser() {
		
	}

	public ReviewsUser(Integer user_id, Integer book_id, Double rating, String comments,
			Integer privacy_setting, User user) {
		this.user_id = user_id;
		this.book_id = book_id;
		this.rating = rating;
		this.comments = comments;
		this.privacy_setting = privacy_setting;
		this.user = user;
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
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

}
