package com.csci4830.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8 CREATE TABLE list_books (
 * 			list_id INT NOT NULL,
 * 			book_id INT NOT NULL,
 * 			PRIMARY KEY (list_id, book_id),
 * 			FOREIGN KEY (list_id) REFERENCES Lists(list_id),
 * 			FOREIGN KEY (book_id) REFERENCES Books(book_id) )
 */

@Entity
@Table(name = "list_books")
public class List_Books {
	@Id
	@Column(name = "list_id")
	private Integer list_id;

	@Id
	@Column(name = "book_id")
	private Integer book_id;

	public List_Books() {

	}

	public Integer getList_id() {
		return list_id;
	}

	public void setList_id(Integer list_id) {
		this.list_id = list_id;
	}

	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	@Override
	public String toString() {
		return "List_Books [list_id=" + list_id + ", book_id=" + book_id + "]";
	}

}
