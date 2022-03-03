package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_xpath {
    WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// set gockodriver : giao tiếp giữa brower và code 
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
		// bật firefox 
		driver = new FirefoxDriver();
		// bật thời gian chờ tìm 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// bat brower to len 
		driver.manage().window().maximize();
		// mo app ra
		driver.get("");
	}

	@Test
	public void TC_01_() {

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