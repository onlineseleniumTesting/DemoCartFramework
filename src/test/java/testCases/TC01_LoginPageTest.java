package testCases;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC01_LoginPageTest extends BaseClass {
	
//	public WebDriver driver;
//	public Logger logger;
//	public  Properties p;
	
//	@BeforeClass
//	@Parameters({"os","browser"})
//	public void setup(String os, String br) throws IOException {
//		
//		//load config. properties file
//		FileReader file=new FileReader("./src//test//resources//config.properties");
//		p=new Properties();
//		p.load(file);
//		
//		
//		logger = LogManager.getLogger(this.getClass());
//		
//		switch(br)
//		{
//		case "chrome" :driver=new ChromeDriver();	break;
//		case "edge" :driver=new EdgeDriver();	break;
//		default :logger.info("invalid browser");return;
//		
//		}
//		
//		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		//driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//		driver.get(p.getProperty("appURL"));
//		driver.manage().window().maximize();
//		
//	}
//	
//	@AfterClass
//	public void tearDown() {
//		driver.quit();
//	}
	
	@Test(groups= {"Regression","Master"})
	public void verify_login() {
		try {
			//load config. properties file
			
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(p.getProperty("username"));
		logger.info("Entered user name");
		lp.setPassword(p.getProperty("password"));
		logger.info("Entered password");
		lp.clickLogin();
		logger.info("Clicked on Login Button");
		logger.info("test is sucessfull");
		
		String confmsg=lp.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Dashboard");
		}
		catch(Exception e) {
			logger.error("Test Failed");
			logger.error("There is an error the login script", e);
			Assert.fail();	
		}
	}
	

}
