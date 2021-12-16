package com.csci4830.bookboxdtest;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTest {
	private WebDriver driver;
	private final String USERNAME = "Selenium2";
	private final String PASSWORD = "TesterR0b0reaDs2";
	private final String FAKE_USERNAME = "Selenium";
	private final String FAKE_PASSWORD = "Banana";
	
	private final int WAIT = 250;

	@Before
	public void setUp() throws Exception {
		//String fileName = System.getProperty("user.dir") + "/chromedriver.exe";
		
		//If running this make sure the chrome driver is in the directory. Current version driver is 96
		//System.setProperty("webdriver.chrome.driver", fileName);
		WebDriverManager.chromedriver().browserVersion("96.0.4664.110").setup();
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("start-maximized"); 
		options.addArguments("enable-automation"); 
		options.addArguments("--no-sandbox"); 
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-browser-side-navigation"); 
		options.addArguments("--disable-gpu"); 
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	}

	@Test
	public void testACreate() throws InterruptedException {
		driver.get("http://localhost:8080/bookboxd/index.html");
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//a[@href='register.jsp'][@data-upgraded=',MaterialButton,MaterialRipple']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.name("username")).sendKeys(USERNAME);
		Thread.sleep(WAIT);
		driver.findElement(By.name("password")).sendKeys("Banana");
		Thread.sleep(WAIT);
		driver.findElement(By.name("passwordconf")).sendKeys("FourScoreAndSevenYearsAgo");
		Thread.sleep(WAIT*2);
		driver.findElement(By.xpath("//*[text()='Register'][@type='submit']")).click();
		//Sad sleep
		Thread.sleep(WAIT*2);
		driver.findElement(By.name("username")).sendKeys("Selenium");
		Thread.sleep(WAIT);
		driver.findElement(By.name("password")).sendKeys(PASSWORD);
		Thread.sleep(WAIT);
		driver.findElement(By.name("passwordconf")).sendKeys(PASSWORD);
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//*[text()='Register'][@type='submit']")).click();
		//Sad sleep again
		Thread.sleep(WAIT*2);
		Thread.sleep(WAIT);
		driver.findElement(By.name("username")).sendKeys(USERNAME);
		Thread.sleep(WAIT);
		driver.findElement(By.name("password")).sendKeys(PASSWORD);
		Thread.sleep(WAIT);
		driver.findElement(By.name("passwordconf")).sendKeys(PASSWORD);
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//*[text()='Register'][@type='submit']")).click();
		Thread.sleep(WAIT*2);
		
	}
	
	
	@Test
	public void testBLogin() throws InterruptedException {
		//driver.get("http://google.com");
		driver.get("http://localhost:8080/bookboxd/index.html");
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//i[@class='material-icons']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.name("username")).sendKeys("Selenium, but the account doesn't exist");
		Thread.sleep(WAIT);
		driver.findElement(By.name("password")).sendKeys("PASSWORD");
		Thread.sleep(WAIT*5);
		driver.findElement(By.xpath("//*[text()='Login'][@type='submit']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.name("username")).sendKeys(USERNAME);
		Thread.sleep(WAIT);
		driver.findElement(By.name("password")).sendKeys(PASSWORD);
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//*[text()='Login'][@type='submit']")).click();
	}

	@After
	public void close() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
}
