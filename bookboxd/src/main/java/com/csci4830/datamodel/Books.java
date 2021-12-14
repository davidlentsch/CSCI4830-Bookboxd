package com.csci4830.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8 CREATE TABLE books (
 * 			book_id INT NOT NULL AUTO_INCREMENT,
 * 			book_name VARCHAR(255) NOT NULL,
 * 			author VARCHAR(255) NOT NULL,
 * 			genre VARCHAR(255),
 * 			description VARCHAR(255),
 * 			average_rating INT,
 * 			PRIMARY KEY (book_id) )
 */

@Entity
@Table(name = "books")
public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Integer book_id;

	@Column(name = "book_name")
	private String book_name;

	@Column(name = "author")
	private String author;

	@Column(name = "genre")
	private String genre;

	@Column(name = "description")
	private String description;

	@Column(name = "average_rating")
	private Double average_rating;

	public static final Double DEFAULT_AVERAGE = 0.0;
	public Books() {
	}

	public Books(String name, String author, String genre, String description) {
		this.book_name = name;
		this.author = author;
		this.genre = genre;
		this.description = description;
		this.average_rating = DEFAULT_AVERAGE;
	}
	
	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAverage_rating() {
		return average_rating;
	}

	public void setAverage_rating(Double average_rating) {
		this.average_rating = average_rating;
	}
}
