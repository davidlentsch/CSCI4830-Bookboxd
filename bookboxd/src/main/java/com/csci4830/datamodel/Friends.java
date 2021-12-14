package com.csci4830.datamodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@IdClass(FriendsPK.class)
@Table(name = "friends")
public class Friends implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id_1")
	private Integer user_id_1;

	@Id
	@Column(name = "user_id_2")
	private Integer user_id_2;
	
	@Column(name = "confirmed")
	private Integer confirmed;
	
	//confirmed is 0 as default to indicate pending request
	public static final Integer DEFAULT_CONFIRMED = 0;

	public Friends() {

	}
	
	public Friends(Integer user_id_1, Integer user_id_2) {
		this.user_id_1 = user_id_1;
		this.user_id_2 = user_id_2;
		this.confirmed = DEFAULT_CONFIRMED;
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

	public Integer getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Integer confirmed) {
		this.confirmed = confirmed;
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
		if (confirmed == null) {
			if (other.confirmed != null)
				return false;
		} else if (!confirmed.equals(other.confirmed))
			return false;
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
