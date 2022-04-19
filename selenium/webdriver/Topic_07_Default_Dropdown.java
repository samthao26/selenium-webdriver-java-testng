package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindActiveElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;
	Select select;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
		driver = new FirefoxDriver();
		// khởi tạo lên cùng với driver trong beforeclass
		// JavascriptExecutor/ WebdriverWait/Actions/...
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
   
	@Test
	public void TC_01_Rode() {
		driver.get("https://rode.com/en/support/where-to-buy");
		// Select đc khơi tạo khi bắt đầu dự dụng(element xuất hiện)
		// khởi tạo select để thao tác vs element (country dropdowm)
		select = new Select(driver.findElement(By.xpath("//select[@id='country']")));
		//  không support multiple 
		Assert.assertFalse(select.isMultiple());
		//Select giá trị Việt Nam 
		select.selectByVisibleText("Vietnam");
		sleepInSecond(5);
		
		// kiểm tra giá trị đã chọn thành công : verify vietname selected success
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
	    driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
	    List<WebElement> storeName = driver.findElements(By.xpath("//div[@class='my-3']// div[@class='d-flex flex-wrap']"));
	    for (WebElement store : storeName) {
	    	System.out.print(store.getText()); 
		}
	
	}

	@Test
	public void TC_02_NopCommerce() {
		String firstName = "John";
		String lastName = "Wick";
		String date = "10";
		String month = "October";
		String year = "1964";
		String emailAdress  = "keane" + generateRandomNumber() + "@hotemail.com";
		String password = "123456";
		
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))); 
		select.selectByVisibleText(date);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))); 
		select.selectByVisibleText(month);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))); 
		select.selectByVisibleText(year);
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAdress);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='register-button']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		driver.findElement(By.xpath("//div[@class='header']//a[text()='My account']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"), lastName);
		
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))); 
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))); 
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))); 
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"), emailAdress);	
		
		   

	}

	@Test
	public void TC_03_() {

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