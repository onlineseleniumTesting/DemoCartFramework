package testCases;

import java.io.FileReader;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import utilities.DataProviders;

public class TC03_LoginDDT {
	public WebDriver driver;
	public Logger logger;
	public  Properties p;
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void verify_login_mul_data(String username, String pwd, String exp) {
		try {
			//load config. properties file
			FileReader file=new FileReader("./src//test//resources//config.properties");
			p=new Properties();
			p.load(file);
			
			driver=new ChromeDriver();
			
			driver.get(p.getProperty("appURL"));
			driver.manage().window().maximize();
		LoginPage lp=new LoginPage(driver);
		System.out.println(username);
		lp.setUserName(username);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		String confmsg=lp.getConfirmationMsg();		
		if(exp.equalsIgnoreCase("Valid")) {
			if(confmsg =="Dashboard") {
				Assert.assertEquals(confmsg, "Dashboard");
				Assert.assertTrue(true);
				
			}else {
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid")) {
			if(confmsg =="Dashboard") {
				Assert.assertEquals(confmsg, "Dashboard");
				Assert.assertTrue(false);
				
			}else {
				Assert.assertTrue(true);
			}
		}
		
		
		}
		catch(Exception e) {
			Assert.fail();
			
		}
		
		driver.quit();
		
	}
}

