package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Locator {
	WebDriver driver;
    //khai báo 1 biến đại diện cho WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
		// khởi tạo brower lên : firefox, chrome 
		driver = new FirefoxDriver();
		// set time chờ để tìm đc 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Mở trang Facebook lên 
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");
	}

	@Test
	public void TC_01_() {
		// selenium Locator (By class) class là file, trong class có thể là biến hoặc hàm
		//id : 
        driver.findElement(By.id("firstname")).sendKeys("sam");
        sleepInSecond(3);
        // Name 
        driver.findElement(By.name("lastname")).sendKeys("yenthao");
        sleepInSecond(3);
        //class
        driver.findElement(By.className("customer-name-middlename"));
        sleepInSecond(3);
        //Tagname: count how many 
        Dimension inputNumber = driver.findElement(By.tagName("button")).getSize();
        System.out.println(inputNumber); 
        //Linktext
        driver.findElement(By.linkText("SITE MAP")).click();
        sleepInSecond(2);
        // Partial LinkText
        driver.findElement(By.partialLinkText("ADVANCED")).click();
        sleepInSecond(2);
        //cSS
        driver.findElement(By.cssSelector("input[id='name']")).sendKeys("Iphone");
        sleepInSecond(2);
        driver.findElement(By.cssSelector("input[name='name']")).clear();
        driver.findElement(By.cssSelector("input[name='name']")).sendKeys("samsung note2"); 
        sleepInSecond(2); 
        driver.findElement(By.cssSelector("input[title='Name']")).clear();
        driver.findElement(By.cssSelector("input[title='Name']")).sendKeys("nokia");
        sleepInSecond(2);
        // Xphath
        driver.findElement(By.xpath("//input[@name='description']")).sendKeys("My");
        sleepInSecond(2);
        driver.findElement(By.xpath("//Input[@id='description']")).clear();
        driver.findElement(By.xpath("//Input[@id='description']")).sendKeys("US");
        sleepInSecond(2);
        driver.findElement(By.xpath("//label[text()='Description']/following-sibling::div/input")).clear();
        driver.findElement(By.xpath("//label[text()='Description']/following-sibling::div/input")).sendKeys("VN");
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