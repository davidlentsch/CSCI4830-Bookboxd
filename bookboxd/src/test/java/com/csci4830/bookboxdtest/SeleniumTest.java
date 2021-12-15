package com.csci4830.bookboxdtest;

import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		String fileName = System.getProperty("user.dir") + "/chromedriver.exe";
		
		//If running this make sure the chrome driver is in the directory. Current version driver is 96
		System.setProperty("webdriver.chrome.driver", fileName);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void testTest() {
		driver.get("http://google.com");
		
		//To get to dashboard, needs servlet running
		//driver.get("http://localhost:8080/bookboxd/dashboard.jsp");
	}

}
