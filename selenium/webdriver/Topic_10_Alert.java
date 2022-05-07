package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Topic_10_Alert {
    WebDriver driver;
    Alert alert;
    
	String projectPath = System.getProperty("user.dir");
	String authenChrome = projectPath + "\\autoITScripts\\authen_chrome.exe";
	String authenFirefox = projectPath + "\\autoITScripts\\authen_firefox.exe";
	

	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		// switch to alert thanh cong
		
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		sleepInSecond(3);
		
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		
		
		

	}

	
	public void TC_02_Cancel_Alert() {
    driver.get("https://automationfc.github.io/basic-form/");
    driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
    alert = driver.switchTo().alert();
    Assert.assertEquals(alert.getText(), "I am a JS Confirm");
    alert.dismiss();
    Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
    
    
    
    
	}

	
	public void TC_03_Promt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		String prontSendkey = "Automation FC";
		
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(prontSendkey);
		alert.accept();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: "+ prontSendkey);
		
		
		
		

	}

	public void TC_04_Authentication_Alert_I() {
		// thu vien selenium ko support authentication
		String username = "admin";
		String password = "admin";
		driver.get("http://" + username + ":" + password  + "@" + "the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
		
		
	}
	
	public void TC_04_Authentication_Alert_II() {
		String username = "admin";
		String password = "admin";
		driver.get("http://the-internet.herokuapp.com/");
		
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		basicAuthenLink = getAuthenticateLink(basicAuthenLink, username, password);
		driver.get(basicAuthenLink);
		
		
		
		// http://the-internet.herokuapp.com/basic_auth
		// tach ma "//" : kieu string: ham split
		
		// http:
		//the-internet.herokuapp.com/basic_auth : gan duong link bang 1 gia tri moi
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
		
	}
	
	@Test
	public void TC_04_Authentication_Alert_AutoIT() throws IOException {
		String username = "admin";
		String password = "admin";
		driver.get("http://the-internet.herokuapp.com/");
		// Script se chay trc de cho AUthen bat len sau 
		if(driver.toString().contains("firefox")) {
		Runtime.getRuntime().exec(new String[] {authenFirefox, username, password});
		} else if(driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] {authenChrome, username, password});
		}
		
		 
		driver.findElement(By.xpath("//a[text()='Basic Auth']")).click();
		sleepInSecond(8);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
			
		
	}
	public String getAuthenticateLink(String url, String username, String password) {
		String[] links = url.split("//");
		url = links[0] + "//" + "//" + username + ":" + password + "@" + links[1];
		return url;
		
		
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
	}

}