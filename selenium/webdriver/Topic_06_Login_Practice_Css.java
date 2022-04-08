package webdriver;

import static org.testng.Assert.assertEquals;

import java.sql.DriverManager;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Topic_06_Login_Practice_Css {
    WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, middName, lastName, fullName, emailAdress, passWord;
	
	


	@BeforeClass
	public void beforeClass() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	    firstName = "Automation25";
	    middName = " ";
	    lastName = "CSS";
	    fullName = "Automation25" + " " + "CSS";
	    emailAdress= "fc" + generateRandomNumber() + "@gmail.com";
	    passWord = "123456";
	    
	    


	}

	@Test
	public void TC_01_Login_Empty_Data() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		driver.findElement(By.cssSelector("input#email")).sendKeys("");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("");
		driver.findElement(By.cssSelector("button#send2")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");
			
		
	}

	

	@Test
	public void TC_02_Login_Invalid_Email() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		driver.findElement(By.cssSelector("input#email")).sendKeys("123456@123");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#send2")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}

	@Test
	public void TC_03_Login_Password_less_Than_6_Characters() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("12345");
		driver.findElement(By.cssSelector("button#send2")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");

	}

	
	@Test
	public void TC_04_Login_Incorrect_Password() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("123123123");
		driver.findElement(By.cssSelector("button#send2")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
	}
	
	@Test
	public void TC_05_Login_Create_New_Account() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		driver.findElement(By.cssSelector("div.buttons-set a.button")).click();
		driver.findElement(By.cssSelector("#firstname")).sendKeys(firstName);
		driver.findElement(By.cssSelector("#middlename")).sendKeys(middName);
		driver.findElement(By.cssSelector("#lastname")).sendKeys( lastName);
		driver.findElement(By.cssSelector("#email_address")).sendKeys(emailAdress);
		driver.findElement(By.cssSelector("#password")).sendKeys( passWord);
		driver.findElement(By.cssSelector("#confirmation")).sendKeys(passWord);
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		String contactInforText = driver.findElement(By.xpath("//h3[text()='Contact Information']//parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInforText.contains(fullName));
		Assert.assertTrue(contactInforText.contains(emailAdress));
		driver.findElement(By.xpath("//span[text()='Account']")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
			
		
	}
	@Test
	public void TC_06_Login_Valid_Email_Password() {
		driver.get("http://live.techpanda.org/index.php/");
		driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
		driver.findElement(By.cssSelector("input#email")).sendKeys(emailAdress);
		driver.findElement(By.cssSelector("input#pass")).sendKeys(passWord);
		driver.findElement(By.cssSelector("button#send2")).click();
		String contactInforText = driver.findElement(By.xpath("//h3[text()='Contact Information']//parent::div/following-sibling::div/p")).getText();
		Assert.assertTrue(contactInforText.contains(fullName));
		Assert.assertTrue(contactInforText.contains(emailAdress));
		driver.findElement(By.xpath("//span[text()='Account']")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
		
		public int generateRandomNumber() {
			Random rand = new Random();
			return rand.nextInt(99999);
		
		}
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
	}

}