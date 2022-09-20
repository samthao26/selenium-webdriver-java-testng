 package listener;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

@Listeners(ReportListener.class)
public class Register {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	By emailTexbox = By.xpath("//input[@id='email']");
	By passwordTextBox = By.xpath("//input[@id='pass']");
	By loginButton = By.xpath("//button[@id='send2']");
	@BeforeClass 
	
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserdriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}
		@Test() 
		public void Register_01(){ 
		
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(emailTexbox).sendKeys("afc@hotmail.vn");
		driver.findElement(passwordTextBox).sendKeys("12345678");
		driver.findElement(loginButton).click();
		String contactInfor = driver.findElement(By.xpath("//div[@class='col-1']//p")).getText();
		Assert.assertFalse(contactInfor.contains("afc@hotmail.vn"));
		
		}
			
	
	public WebDriver getDriver() {
		
		return this.driver;
	}
	
	@AfterClass 
	public void afterClass() {
		driver.quit();
		
	}
}


