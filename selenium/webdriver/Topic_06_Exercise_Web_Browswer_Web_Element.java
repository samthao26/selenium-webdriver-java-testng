package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_06_Exercise_Web_Browswer_Web_Element {
    WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By fullNameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confirmPasswordTextboxBy = By.id("txtCPassword");
	By phoneNumberTextboxBy = By.id("txtPhone");
	By submitTextboxBy = By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']");
	By fullNameErrorTextboxBy = By.id("txtFirstname-error");
	By emailErrorTextboxBy = By.id("txtEmail-error");
	By confirmEmailErrorTextboxBy = By.id("txtCEmail-error");
	By passwordErrorTextboxBy = By.id("txtPassword-error");
	By confirmPasswordErrorTextboxBy = By.id("txtCPassword-error");
	By phoneNumberErrorTextboxBy = By.id("txtPhone-error");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@BeforeMethod
	
	public void beforeMethod() { 
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Register_Emty_Data() {
    driver.findElement(fullNameTextboxBy).sendKeys("");
    driver.findElement(emailTextboxBy).sendKeys("");
    driver.findElement(confirmEmailTextboxBy).sendKeys("");
    driver.findElement(passwordTextboxBy).sendKeys("");
    driver.findElement(confirmPasswordTextboxBy).sendKeys("");
    driver.findElement(phoneNumberTextboxBy).sendKeys("");
    driver.findElement(submitTextboxBy).click();
    Assert.assertEquals(driver.findElement(fullNameErrorTextboxBy).getText(), "Vui lòng nhập họ tên");
    Assert.assertEquals(driver.findElement(emailErrorTextboxBy).getText(), "Vui lòng nhập email");
    Assert.assertEquals(driver.findElement(confirmEmailErrorTextboxBy).getText(), "Vui lòng nhập lại địa chỉ email");
    Assert.assertEquals(driver.findElement(passwordErrorTextboxBy).getText(), "Vui lòng nhập mật khẩu");
    Assert.assertEquals(driver.findElement(confirmPasswordErrorTextboxBy).getText(), "Vui lòng nhập lại mật khẩu");
    Assert.assertEquals(driver.findElement(phoneNumberErrorTextboxBy).getText(), "Vui lòng nhập số điện thoại.");
  
    
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.findElement(fullNameTextboxBy).sendKeys("Samthao");
	    driver.findElement(emailTextboxBy).sendKeys("123@345@");
	    driver.findElement(confirmEmailTextboxBy).sendKeys("123@345@");
	    driver.findElement(passwordTextboxBy).sendKeys("123456");
	    driver.findElement(confirmPasswordTextboxBy).sendKeys("123456");
	    driver.findElement(phoneNumberTextboxBy).sendKeys("0986912315");
	    driver.findElement(submitTextboxBy).click();
	    Assert.assertEquals(driver.findElement(emailErrorTextboxBy).getText(), "Vui lòng nhập email hợp lệ");
	    Assert.assertEquals(driver.findElement(confirmEmailErrorTextboxBy).getText(), "Email nhập lại không đúng");
	   
	}

	@Test
	public void TC_03_Register_Incorrect_Confirm_Email() {
		driver.findElement(fullNameTextboxBy).sendKeys("Samthao");
	    driver.findElement(emailTextboxBy).sendKeys("123@gmail.com");
	    driver.findElement(confirmEmailTextboxBy).sendKeys("312@gmail.com");
	    driver.findElement(passwordTextboxBy).sendKeys("123456");
	    driver.findElement(confirmPasswordTextboxBy).sendKeys("123456");
	    driver.findElement(phoneNumberTextboxBy).sendKeys("0986912315");
	    driver.findElement(submitTextboxBy).click();
	    Assert.assertEquals(driver.findElement(confirmEmailErrorTextboxBy).getText(),"Email nhập lại không đúng");
	    
	}

	@Test
	public void TC_04_Register_Password_less_Than_6_Character() {
		driver.findElement(fullNameTextboxBy).sendKeys("Samthao");
	    driver.findElement(emailTextboxBy).sendKeys("123@gmail.com");
	    driver.findElement(confirmEmailTextboxBy).sendKeys("123@gmail.com");
	    driver.findElement(passwordTextboxBy).sendKeys("1234");
	    driver.findElement(confirmPasswordTextboxBy).sendKeys("1234");
	    driver.findElement(phoneNumberTextboxBy).sendKeys("0986912315");
	    driver.findElement(submitTextboxBy).click();
	    Assert.assertEquals(driver.findElement(passwordErrorTextboxBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	    Assert.assertEquals(driver.findElement(confirmPasswordErrorTextboxBy).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
	   
	}
	@Test
	public void TC_05_Register_Incorrect_Confirm_Password() {
		driver.findElement(fullNameTextboxBy).sendKeys("Samthao");
	    driver.findElement(emailTextboxBy).sendKeys("123@gmail.com");
	    driver.findElement(confirmEmailTextboxBy).sendKeys("123@gmail.com");
	    driver.findElement(passwordTextboxBy).sendKeys("123456");
	    driver.findElement(confirmPasswordTextboxBy).sendKeys("123465");
	    driver.findElement(phoneNumberTextboxBy).sendKeys("0986912315");
	    driver.findElement(submitTextboxBy).click();
	    Assert.assertEquals(driver.findElement(confirmPasswordErrorTextboxBy).getText(),"Mật khẩu bạn nhập không khớp");
	}
	@Test
	public void TC_06_Register_Invalid_Phone_Number() {
		driver.findElement(fullNameTextboxBy).sendKeys("Samthao");
	    driver.findElement(emailTextboxBy).sendKeys("123@gmail.com");
	    driver.findElement(confirmEmailTextboxBy).sendKeys("123@gmail.com");
	    driver.findElement(passwordTextboxBy).sendKeys("123456");
	    driver.findElement(confirmPasswordTextboxBy).sendKeys("123465");
	    driver.findElement(phoneNumberTextboxBy).sendKeys("986912315");
	    driver.findElement(submitTextboxBy).click();
	    Assert.assertEquals(driver.findElement(phoneNumberErrorTextboxBy).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	    driver.findElement(phoneNumberTextboxBy).clear();
	    driver.findElement(phoneNumberTextboxBy).sendKeys("0986912315234");
	    Assert.assertEquals(driver.findElement(phoneNumberErrorTextboxBy).getText(), "Số điện thoại phải từ 10-11 số.");
	    
	    
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