package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Handle_Frame_IFrame {
    WebDriver driver;
    Alert alert;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	public void TC_01_() {
		driver.get("https://kyna.vn/");
		// switch vao fram/iframe trc roi moi thao tac tren element thuoc frame/iframe do 
		//driver.switchTo().frame(0);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'facebook.com/kyna.vn')]")));
		
		// verify so luong like tren face book 
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div ")).getText(), "167K likes");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
		driver.findElement(By.xpath("//div[@class='meshim_widget_Widget']")).click();
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Tim Cook");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("123456");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("IFrame");
		sleepInSecond(3);
		driver.switchTo().defaultContent();
		String keyword = "Excel";
		
		driver.findElement(By.xpath("//div[@class='input-group']//input[@id='live-search-bar']")).sendKeys(keyword);
		driver.findElement(By.xpath("//button[@class='search-button']")).click();
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
	
		for (WebElement course : courseName) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().contains(keyword));
			
		}

	}

	public void TC_02_Automation_Blog() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@loading='lazy']")));
		driver.findElement(By.cssSelector("button.ytp-large-play-button")).click();
		sleepInSecond(3);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='fb:page Facebook Social Plugin']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Automation FC']/parent::div/following-sibling::div/div")).getText().contains("3.3K likes"));
		
	}

	@Test
	public void TC_03_netBanking() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("");
		driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "Customer ID  cannot be left blank.");
		alert.accept();
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("AutomationFC");
		driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='fldPasswordDispId']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='fs12']")).isDisplayed());
		 By checkBox = By.xpath("//input[@id='chksecmod']");
		 
		Assert.assertTrue(driver.findElement(checkBox).isDisplayed());
		driver.findElement(checkBox).click();
		Assert.assertTrue(driver.findElement(checkBox).isSelected());
		driver.findElement(checkBox).click();
		alert=driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"Use of Security Keyboard is recommended to protect your Password. Are you sure you choose NOT to use the Security Keyboard?");
		alert.accept();
		
	
		
		
		

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