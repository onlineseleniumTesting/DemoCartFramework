package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    
    String repName;

    @Override
    public void onStart(ITestContext context) {
    	
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	repName = "Test-Report" + timeStamp +".html";
    	
        String reportPath = System.getProperty("user.dir") + repName;
        sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Functional Testing");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "HRMS");
        extent.setSystemInfo("Module", "Login");
        extent.setSystemInfo("Sub Module", "Forgot password");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        

        // Set system/environment info
        extent.setSystemInfo("Computer Name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester Name", "Natesh");
        extent.setSystemInfo("OS", "Windows 10");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }
	
	@Override
	public void onTestFailure(ITestResult result) {
	    test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
	    test.log(Status.FAIL, result.getThrowable());
	
	    // Capture screenshot
	    WebDriver driver = null;
	
	    try {
	        driver = (WebDriver) result.getTestClass().getRealClass()
	                  .getDeclaredField("driver")
	                  .get(result.getInstance());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	
	    if (driver != null) {
	        String screenshotPath = takeScreenshot(driver, result.getMethod().getMethodName());
	        try {
	            test.addScreenCaptureFromPath(screenshotPath);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public String takeScreenshot(WebDriver driver, String testName) {
	    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    String destPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";

	    try {
	        File dest = new File(destPath);
	        FileUtils.copyFile(src, dest);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return destPath;
	}


    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}