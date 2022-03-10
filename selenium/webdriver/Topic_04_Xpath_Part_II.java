
package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Part_II {
    WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void Register_01_Empty_Data() {
    driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(), "Vui lòng nhập họ tên");
    Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email");
    Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Vui lòng nhập lại địa chỉ email");
    Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
    Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập số điện thoại.");
    
	}

	@Test
	public void Register_02_Invalid_Email() {
    driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("sam yen thao");
    driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("sam@12@com");
    driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("sam@12@com");
    driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
    driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
    driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0986936515");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email hợp lệ");
    Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void Register_03_Incorrect_Confirm_Email() {
	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("sam yen thao");
    driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("sam@gmail.com");
    driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("sam@gmail@com");
	driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
    driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0986936515");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
	}
	@Test
	public void Register_04_Password_Less_Than_6_Characters() {
	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("sam@gmail.com");	
    driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("sam@gmail.com");
    driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345");
	driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345");
    driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0986936515");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");

	}
	@Test
	public void Register_05_Incorrect_Confirm_Password() {
	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("sam@gmail.com");	
	driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("sam@gmail.com");
	driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("122456");
	driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0986936515");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");
	
	}
	@Test
	public void Register_06_01_Phone_Number_Less_Than_10_Number() {
	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("sam@gmail.com");	
	driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("sam@gmail.com");
	driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("098693651");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");
	}
	@Test
	public void Register_06_02_Phone_Number_More_Than_11_Number() {
	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("sam@gmail.com");	
	driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("sam@gmail.com");
	driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("098693651567");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");
	}
	@Test
	public void Register_06_03_Phone_Number_Invalid_Number() {
	driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("sam@gmail.com");	
	driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("sam@gmail.com");
	driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
	driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("986936515");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
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