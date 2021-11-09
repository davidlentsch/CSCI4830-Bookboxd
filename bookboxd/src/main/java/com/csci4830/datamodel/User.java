package com.csci4830.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8 CREATE TABLE user ( 
 * 			user_id INT NOT NULL AUTO_INCREMENT,
 * 			username VARCHAR(255) NOT NULL,
 * 			password VARCHAR(255) NOT NULL,
 * 			about_desc VARCHAR(255),
 * 			privacy_setting INT,
 * 			PRIMARY KEY (user_id)
 * 		  )
 */		  

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer user_id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="about_desc")
	private String about_desc;
	
	@Column(name="privacy_setting")
	private Integer privacy_setting;
	
	public User() {
		
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout_desc() {
		return about_desc;
	}

	public void setAbout_desc(String about_desc) {
		this.about_desc = about_desc;
	}

	public Integer getPrivacy_setting() {
		return privacy_setting;
	}

	public void setPrivacy_setting(Integer privacy_setting) {
		this.privacy_setting = privacy_setting;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", about_desc="
				+ about_desc + ", privacy_setting=" + privacy_setting + "]";
	}
}
