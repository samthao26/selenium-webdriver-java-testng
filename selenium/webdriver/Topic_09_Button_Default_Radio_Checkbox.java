package webdriver;

import java.util.concurrent.TimeUnit;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Default_Radio_Checkbox {
    WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-item")).click();
		By loginButtonBy = By.cssSelector("button.fhs-btn-login");
		
		// verify is disabled or not
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
		// isEnables: neu 1 element ma enabled => true
		// isEnables: neu 1 element ma disabled => false
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("1234567");
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());	
		// verify background color = red 
		String loginButtonBackgroundColorRbg = driver.findElement(loginButtonBy).getCssValue("background-color");
		System.out.println("GRB color = " + loginButtonBackgroundColorRbg);
		Assert.assertEquals(loginButtonBackgroundColorRbg, "rgb(201, 33, 39)");
		
		// convert to Hexa
		String loginButtonBackgroundColorHexa = Color.fromString(loginButtonBackgroundColorRbg).asHex();
		Assert.assertEquals(loginButtonBackgroundColorHexa.toUpperCase(),"#C92127");
				// tai lai trang sau do navigate qua tab dang nhap
				
		driver.navigate().refresh();
		driver.findElement(By.cssSelector("li.popup-login-tab-item")).click();
				// remove disabled atribute
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(loginButtonBy));
				sleepInSecond(5);
				
		driver.findElement(loginButtonBy).click();
				
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");

		
		
			
		

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