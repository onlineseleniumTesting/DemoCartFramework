package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.ForgotPasswordPage;
import pageObjects.LoginPage;

public class TC02_ForgotPasswordTest {
public WebDriver driver;
	
	@BeforeClass
	public void setup() {
		driver=new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	@Test(groups= {"Sanity","Master"})
	public void verify_forgot_password() {
		LoginPage lp=new LoginPage(driver);
		ForgotPasswordPage fpp=new ForgotPasswordPage(driver);
		lp.clickForgotPassword();
		fpp.setUserName("Admin");
		fpp.clickResetButton();
		
		String confmsg=fpp.getSuccessfullMsg();
		Assert.assertEquals(confmsg, "Reset Password link sent successfully");
		
		
	}


}
