package com.csci4830.bookboxdtest;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Test;

import com.csci4830.bookboxd.UserProfileUtility;
import com.csci4830.bookboxd.Utility;
import com.csci4830.datamodel.*;

//TODO
public class UserProfileUtilityTest {

	@Test
	public void testChangeListPrivacy() {
		List<Lists> lists = Utility.getListsByUserID(1);
		Lists l = lists.get(0);
		l = UserProfileUtility.changeListPrivacy(1, l.getList_id(), 1);
		assertEquals((Integer) 1, l.getPrivacy_setting());
	}
	
	@Test
	public void testChangeProfilePrivacy() {
		User u = UserProfileUtility.changeProfilePrivacy(1, 1);
		assertEquals((Integer) 1, u.getPrivacy_setting());
	}
	
	@Test
	public void testGetProfilePrivacy() {
		User u = UserProfileUtility.changeProfilePrivacy(1, 1);
		assertEquals((Integer) 1, UserProfileUtility.getProfilePrivacy(1));
	}
	
	@Test
	public void testUpdateAboutMe() {
		User u = UserProfileUtility.updateAboutMe(1, "this is my new about me!");
		assertEquals("this is my new about me!", u.getAbout_desc());
	}
}