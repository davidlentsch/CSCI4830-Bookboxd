package com.csci4830.datamodel;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
public class List_Books implements Serializable {
	private static final long serialVersionUID = 1L;

	@AttributeOverrides({
		@AttributeOverride(name="list_id", column = @Column(name="list_id")),
		@AttributeOverride(name="book_id", column = @Column(name="book_id"))
	})
	@EmbeddedId
	List_Books_Id id;
	
	@MapsId("list_id")
	@ManyToOne
	Lists list;

	@MapsId("book_id")
	@ManyToOne
	Books book;
	
	public List_Books() {

	}

	public Lists getList() {
		return list;
	}

	public void setList_id(Lists list) {
		this.list= list;
	}

	public Books getBook() {
		return book;
	}

	public void setBook_id(Books book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "List_Books [list_id=" + list.getList_id() + ", book_id=" + book.getBook_id() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book.getBook_id() == null) ? 0 : book.getBook_id().hashCode());
		result = prime * result + ((list.getList_id() == null) ? 0 : list.getList_id().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
		//TODO update back later, crunch time now
		/*if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		List_Books other = (List_Books) obj;
		if (book.getBook_id() == null) {
			if (other.book_id != null)
				return false;
		} else if (!book_id.equals(other.book_id))
			return false;
		if (list_id == null) {
			if (other.list_id != null)
				return false;
		} else if (!list_id.equals(other.list_id))
			return false;
		return true;*/
	}
}

@Embeddable class List_Books_Id implements Serializable {
	public Integer listId;
	public Integer bookId;
}

