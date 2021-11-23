package com.csci4830.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @since J2SE-1.8 CREATE TABLE lists ( 
 * 			user_id INT NOT NULL,
 * 			list_id INT NOT NULL AUTO_INCREMENT,
 * 			list_name VARCHAR(255) NOT NULL,
 * 			privacy_setting INT,
 * 			PRIMARY KEY (list_id),
 * 			FOREIGN KEY (user_id) REFERENCES User(user_id)
 * 		  )
 */		  

@Entity
@Table(name = "lists")
public class Lists {
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="list_id")
	private Integer list_id;
	
	@Column(name="list_name")
	private String list_name;
	
	public Lists() {
		
	}

	public Lists(User user, String listName) {
		this.user = user;
		this.list_name = listName;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getList_id() {
		return list_id;
	}

	public void setList_id(Integer list_id) {
		this.list_id = list_id;
	}

	public String getList_name() {
		return list_name;
	}

	public void setList_name(String list_name) {
		this.list_name = list_name;
	}

	@Override
	public String toString() {
		return "Lists [user_id=" + user.getUser_id() + ", list_id=" + list_id + ", list_name=" + list_name + "]";
	}
	
	
}
