package com.csci4830.bookboxdtest;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.csci4830.bookboxd.Utility;
import com.csci4830.datamodel.User;

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
		//
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
		driver.findElement(By.name("username")).sendKeys(FAKE_USERNAME);
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
	
	@Test
	public void testCAccountSettings() throws InterruptedException {
		driver.get("http://localhost:8080/bookboxd/index.html");
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//a[@href='login.jsp'][@data-upgraded=',MaterialButton,MaterialRipple']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.name("username")).sendKeys(USERNAME);
		driver.findElement(By.name("password")).sendKeys(PASSWORD);
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//*[text()='Login'][@type='submit']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//i[@class='material-icons']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//*[text()=\"Edit Profile\"]")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.name("comments")).sendKeys("Im a robot,");
		Thread.sleep(WAIT);
		driver.findElement(By.name("comments")).sendKeys(" Yarr");
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//*[text()=\"Private\"]")).click();
		Thread.sleep(WAIT*2);
		driver.findElement(By.xpath("//*[text()='Save Changes'][@type='submit']")).click();
	}
	
	
	@Test
	public void testDSummonFriend() throws InterruptedException {
		driver.get("http://localhost:8080/bookboxd/index.html");
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//a[@href='login.jsp'][@data-upgraded=',MaterialButton,MaterialRipple']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.name("username")).sendKeys(USERNAME);
		driver.findElement(By.name("password")).sendKeys(PASSWORD);
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//*[text()='Login'][@type='submit']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//form[@action='Search']")).click();
		Thread.sleep(WAIT);
		WebElement toEnter = driver.findElement(By.id("searchBox"));
		toEnter.sendKeys(FAKE_USERNAME);
		Thread.sleep(WAIT);
		toEnter.sendKeys(Keys.RETURN);
		Thread.sleep(WAIT*2);
		driver.findElement(By.linkText(FAKE_USERNAME)).click();
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//i[@class='material-icons']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//*[text()=\"Send Friend Request\"]")).click();
	}
	
	@Test
	public void testEAcceptFriend() throws InterruptedException {
		driver.get("http://localhost:8080/bookboxd/index.html");
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//a[@href='login.jsp'][@data-upgraded=',MaterialButton,MaterialRipple']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.name("username")).sendKeys(FAKE_USERNAME);
		driver.findElement(By.name("password")).sendKeys(FAKE_PASSWORD);
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//*[text()='Login'][@type='submit']")).click();
		
		driver.findElement(By.xpath("//a[@href='FriendRequests']")).click();
		
		//Add quick decline later
		
		driver.findElement(By.linkText(USERNAME)).click();
		Thread.sleep(WAIT);
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//i[@class='material-icons']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//*[text()=\"Accept Friend Request\"]")).click();

		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//i[@class='material-icons']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//a[text()=\"Profile\"]")).click();
		driver.findElement(By.xpath("//a[text()=\"" + FAKE_USERNAME +"'s friends\"]")).click();
	}
	
	
	@Test
	public void testFDestroyFriend() throws InterruptedException {
		driver.get("http://localhost:8080/bookboxd/index.html");
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//a[@href='login.jsp'][@data-upgraded=',MaterialButton,MaterialRipple']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.name("username")).sendKeys(USERNAME);
		driver.findElement(By.name("password")).sendKeys(PASSWORD);
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//*[text()='Login'][@type='submit']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//a[text()=\""+USERNAME+"'s friends\"]")).click();
		Thread.sleep(WAIT*2);
		driver.findElement(By.linkText(FAKE_USERNAME)).click();
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//i[@class='material-icons']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//*[text()=\"Remove Friend\"]")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//i[@class='material-icons']")).click();
		Thread.sleep(WAIT);
		driver.findElement(By.xpath("//a[text()=\"Profile\"]")).click();
		driver.findElement(By.xpath("//a[text()=\"" + USERNAME +"'s friends\"]")).click();
	}
	
	@After
	public void close() throws InterruptedException {
		Thread.sleep(1500);
		//Utility.deleteDataObject(Utility.getUserByUsername(USERNAME));
		driver.quit();
	}
}
