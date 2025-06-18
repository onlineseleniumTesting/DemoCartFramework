package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);	
	}
	
	@FindBy(xpath="//input[@name=\"username\"]")
	WebElement txtUserName;
	
	@FindBy(xpath="//input[@name=\"password\"]")
	WebElement txtPassword;
	
	@FindBy(xpath="//button[normalize-space()=\"Login\"]")
	WebElement btnLogin;
	
	@FindBy(xpath="//h6[normalize-space()=\"Dashboard\"]")
	WebElement txtDashboard	;
	
	@FindBy(xpath="//p[normalize-space()=\"Forgot your password?\"]")
	WebElement lnkForgotPassword	;
	
	public void setUserName(String username) {
		txtUserName.sendKeys(username);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
	public void clickForgotPassword() {
		lnkForgotPassword.click();
	}
	
	public String getConfirmationMsg() {
		return txtDashboard.getText();
		
	}
	

}
