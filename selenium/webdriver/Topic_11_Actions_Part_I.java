package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Actions_Part_I {
    WebDriver driver;
    Actions action;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		
		
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}


	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		WebElement yourAgeTextbox = driver.findElement(By.xpath("//input[@id='age']"));
		
		// how to hold mouse in texbox 
		action.moveToElement(yourAgeTextbox).perform();
		sleepInSecond(7);
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");

	}

	
	public void TC_02_Hover_II() {
		driver.get("https://www.myntra.com/"); 
		//2: goi ham can dung ra
		//3: goi cai perform() cuoi cung 
		action.moveToElement(driver.findElement(By.xpath("//header//a[text()='Kids']"))).perform();
		sleepInSecond(3);
		action.click(driver.findElement(By.xpath("//header//a[text()='Home & Bath']"))).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(), "Kids Home Bath");
		
		
		

	}


	public void TC_03_Click_And_Hover() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		// khai bao va lu tru tat ca 12 elements
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		// chon 1->4
		// cleck and hold vao 1 
		// hover toi 4 
		//  nha chuot trai ra: release()
		// thuc thi cac cau lenh
		action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(3)).release().perform();
		sleepInSecond(4);
		
		List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		
		Assert.assertEquals(allNumberSelected.size(), 4);
		
		
		
		
		

	}

	
	public void TC_04_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		// chon 1, 5 vaf 11
		// nhan phim Ctrl (window)/ command xuong (chua nha ra) => dung cau lenh co the dung dc ca window and mac
		Keys controlKeys;
		if (osName.contains("windows")) {
			controlKeys = Keys.CONTROL;
			
		} else {
			controlKeys = Keys.COMMAND;

		}
		action.keyDown(controlKeys).perform();
		// click vao 1
		// click vao 5
		// click vao 11
		// thuc thi cac lenh
		action.click(allNumber.get(0)).click(allNumber.get(5)).click(allNumber.get(10)).perform();
		action.keyUp(controlKeys).perform();
		sleepInSecond(5);
		// nha phim Ctrl ra
		List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumberSelected.size(), 3);
		
		
		
		
	}
	@Test
	public void TC_05_Click_And_Hold_Random_II() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		Keys controlkeys;
		if (osName.contains("window")) {
			controlkeys = Keys.CONTROL;
			
		} else {
			controlkeys = Keys.COMMAND;

		}
		action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(3)).release().perform();
		action.keyDown(controlkeys).perform();
		action.click(allNumber.get(4)).click(allNumber.get(10)).perform();
		action.keyUp(controlkeys).perform();
		sleepInSecond(5);
		List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumberSelected.size(), 6);
		
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