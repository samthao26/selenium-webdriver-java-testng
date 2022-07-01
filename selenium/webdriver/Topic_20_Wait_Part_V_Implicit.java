package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Part_V_Implicit {
    WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	By loadingIcon = By.xpath("//div[@id='loading']");
	By wordIcon = By.xpath("//div[@id='finish']/h4");
	String fileNameLESO2863 = "LESO2863.jpg"; 
	String fileNameLESO2887 = "LESO2887.jpg";
	String fileNameLESO3231 = "LESO3231.jpg";
	String uploadFilePath = projectPath + "/uploadFiles/";
	String lESO2863Path = uploadFilePath + fileNameLESO2863;
	String lESO2887Path = uploadFilePath + fileNameLESO2887;
	String lESO3231Path = uploadFilePath + fileNameLESO3231;
	
	@BeforeClass
	public void beforeClass() {
;		System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
		driver = new FirefoxDriver();
		
	   
	   
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_Enough_Time() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		
		// load icon bien mat trong 15s
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
		
		
		
		
	}

	
	public void TC_02_Less_Than_5s() {
		explicitWait = new WebDriverWait(driver, 3);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
		
	
		
		
	
	}

	
	public void TC_03_Greater() {
		explicitWait = new WebDriverWait(driver, 30);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
		
		
	}
	
	public void TC_04_Invisible_Start_Button() {
		explicitWait = new WebDriverWait(driver, 30);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
		
	}

	public void TC_05_visible_Word() {
		explicitWait = new WebDriverWait(driver,30);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.xpath("//div[@id='start']/button")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(wordIcon));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}

	
	public void TC_06_Ajiax_Loading() {
		explicitWait = new WebDriverWait(driver, 30);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		// wait until date time show up
		WebElement datetime =  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")));
		Assert.assertEquals(datetime.getText(), "No Selected Dates to display.");
		// click random day ( wait cho ngay random co the click va sau do click 
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='8']"))).click();
		
		
		// wait loading icon bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));
		// sau khi clic vao so * thi text dc cap nhat lai 
		// neu dung lai eleement o tren roi get text lai la sai 
		WebElement selectedDataText = driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(selectedDataText.getText(), "Friday, July 8, 2022" );
		
		
		// verify ngay dc update 
		
		WebElement selectedDataNumber = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']//a[text()='8']")));
		Assert.assertEquals(selectedDataNumber.getText(), "8");
		// verify selected date 
		
		
	}
	
	@Test 
	public void TC_07_Upload_File() {
		explicitWait = new WebDriverWait(driver, 110);
		driver.get("https://gofile.io/uploadFiles");
		By uploadFileBy = By.xpath("//input[@type='file']");
		driver.findElement(uploadFileBy).sendKeys(lESO2863Path + "\n" + lESO2887Path + "\n" + lESO3231Path);
		// wait cho upload file thanh cong 
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
		
		//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='row'] // div[@class='col-xs-auto']")));
		// wait de cho text dc visible 
		
		By wordIcon = By.xpath("//div[@class='callout callout-success']//h5");
		 explicitWait.until(ExpectedConditions.visibilityOfElementLocated(wordIcon));
		Assert.assertEquals(driver.findElement(wordIcon).getText(), "Your files have been successfully uploaded");
		String uploadLink = driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).getAttribute("href");
		driver.get(uploadLink);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='"+fileNameLESO2863+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='"+fileNameLESO2887+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='"+fileNameLESO3231+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='LESO2863.jpg']/parent::a/parent::div/following-sibling::div//span[text()='Download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='LESO2887.jpg']/parent::a/parent::div/following-sibling::div//span[text()='Download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='LESO3231.jpg']/parent::a/parent::div/following-sibling::div//span[text()='Download']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='LESO3231.jpg']/parent::a/parent::div/following-sibling::div//span[text()='Play']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='LESO2887.jpg']/parent::a/parent::div/following-sibling::div//span[text()='Play']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='LESO2863.jpg']/parent::a/parent::div/following-sibling::div//span[text()='Play']")).isDisplayed());
		
		
		
		
		
	}
	@AfterClass
	public void afterClass() {
	driver.quit();
	}
	
  
}