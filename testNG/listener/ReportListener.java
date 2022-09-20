package listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.IIOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.server.handler.CaptureScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import com.github.dockerjava.api.model.Driver;

public class ReportListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Object testClass = result.getInstance();
		WebDriver webDriver = ((Register)testClass).getDriver();
		CaptureScreenshot(webDriver, result.getName());	
		
	}

	private String CaptureScreenshot(WebDriver webDriver, String screenshotName) {
		try {
			Calendar calender = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			TakesScreenshot driver = null;
			File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String projectPath = System.getProperty("user.dir");
			String screenPath = projectPath + "\\" + screenshotName + "_" + formater.format(calender.getTime()) + ".png";
			FileUtils.copyFile(source, new File(screenPath));
			return screenPath;
					
		} catch (IOException e) {
			System.out.println(" Exception while taking screenshot: " + e.getMessage());
			return e.getMessage();
			
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

}
