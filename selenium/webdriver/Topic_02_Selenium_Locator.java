package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_() {
		//id
		driver.findElement(By.id("email")).sendKeys("sam@gmail.com");
		sleepInSecond(3);
		// name 
		driver.findElement(By.name("pass")).sendKeys("1234");
		sleepInSecond(3);
		// class
		driver.findElement(By.className("fb_logo")).isDisplayed();
		//tagname
		driver.findElement(By.tagName("button"));
		//linktext
		driver.findElement(By.linkText("Forgotten password?")).click();
		sleepInSecond(2);
		//partial link text
		driver.findElement(By.partialLinkText("Tiếng")).click();
		sleepInSecond(2);
		// xpath
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("samthao@gmail.com");
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("123456");
		sleepInSecond(3);
		driver.findElement(By.xpath("//img[@class='fb_logo _8ilh img']")).isDisplayed();
		driver.findElement(By.xpath("//button"));
		driver.findElement(By.xpath("//a[text()='Forgotten password?']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[contains(text(),'Tiếng')]")).click();
		sleepInSecond(2);
		//css
		driver.findElement(By.cssSelector("input[id='email']")).sendKeys("samyenthao@gmail.com");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input[name='pass']")).sendKeys("1234567");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("img[class='fb_logo _8ilh img']")).isDisplayed();
		driver.findElement(By.cssSelector("button"));
		driver.findElement(By.cssSelector("a[onclick*='vi_VN']")).click();
		driver.findElement(By.cssSelector("a[title='French (France)']")).click();
		sleepInSecond(2);

	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

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