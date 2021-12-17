package com.csci4830.datamodel;

import java.util.Objects;

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
	
	@Column(name = "image_url")
	private String image_url;

	public static final Double DEFAULT_AVERAGE = 0.0;
	public Books() {
	}

	public Books(String url, String name, String author, String genre, String description) {
		this.image_url = url;
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
	
	public String getImage_url() {
		if (image_url != null) {
			if (!image_url.equals("")) {
				return image_url;
			}
			else {
				return "https://upload.wikimedia.org/wikipedia/commons/thumb/0/06/Iceland_Dettifoss_1972-4.jpg/348px-Iceland_Dettifoss_1972-4.jpg";
			}
		} else {
			return "https://upload.wikimedia.org/wikipedia/commons/thumb/0/06/Iceland_Dettifoss_1972-4.jpg/348px-Iceland_Dettifoss_1972-4.jpg";
		}
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, average_rating, book_id, book_name, description, genre, image_url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Books other = (Books) obj;
		return Objects.equals(author, other.author)
				&& Objects.equals(book_id, other.book_id) && Objects.equals(book_name, other.book_name)
				&& Objects.equals(description, other.description) && Objects.equals(genre, other.genre)
				&& Objects.equals(image_url, other.image_url);
	}
	
}
