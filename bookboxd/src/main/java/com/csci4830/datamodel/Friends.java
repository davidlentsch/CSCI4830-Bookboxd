package com.csci4830.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8 CREATE TABLE friends (
 * 			user_id_1 INT NOT NULL,
 * 			user_id_2 INT NOT NULL,
 * 			PRIMARY KEY (user_id_1, user_id_2),
 * 			FOREIGN KEY (user_id_1) REFERENCES User(user_id),
 * 			FOREIGN KEY (user_id_2) REFERENCES User(user_id) )
 */

@Entity
@Table(name = "friends")
public class Friends {
	@Id
	@Column(name = "user_id_1")
	private String user_id_1;

	@Id
	@Column(name = "user_id_2")
	private String user_id_2;

	public Friends() {

	}

	public String getUser_id_1() {
		return user_id_1;
	}

	public void setUser_id_1(String user_id_1) {
		this.user_id_1 = user_id_1;
	}

	public String getUser_id_2() {
		return user_id_2;
	}

	public void setUser_id_2(String user_id_2) {
		this.user_id_2 = user_id_2;
	}

	@Override
	public String toString() {
		return "Friends [user_id_1=" + user_id_1 + ", user_id_2=" + user_id_2 + "]";
	}

}
