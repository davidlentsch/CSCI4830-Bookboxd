package com.csci4830.datamodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Friends implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "user_id_1")
	private Integer user_id_1;
	
	@Id
	@Column(name = "user_id_2")
	private Integer user_id_2;

	public Friends() {

	}
	public Friends(User user1, User user2) {
		user_id_1 = user1.getUser_id();
		user_id_2 = user2.getUser_id();
	}
	public Friends(Integer userID1, Integer userID2) {
		user_id_1 = userID1;
		user_id_2 = userID2;
	}
	
	public void setFriend_id() {
		
	}
	public Integer getUser_id_1() {
		return user_id_1;
	}

	public void setUser_id_1(Integer user_id_1) {
		this.user_id_1 = user_id_1;
	}

	public Integer getUser_id_2() {
		return user_id_2;
	}

	public void setUser_id_2(Integer user_id_2) {
		this.user_id_2 = user_id_2;
	}

	@Override
	public String toString() {
		return "Friends [user_id_1=" + user_id_1 + ", user_id_2=" + user_id_2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user_id_1 == null) ? 0 : user_id_1.hashCode());
		result = prime * result + ((user_id_2 == null) ? 0 : user_id_2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Friends other = (Friends) obj;
		if (user_id_1 == null) {
			if (other.user_id_1 != null)
				return false;
		} else if (!user_id_1.equals(other.user_id_1))
			return false;
		if (user_id_2 == null) {
			if (other.user_id_2 != null)
				return false;
		} else if (!user_id_2.equals(other.user_id_2))
			return false;
		return true;
	}
}
