package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage extends BasePage {

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css="[name=\"username\"]")
	WebElement txtusername;
	
	@FindBy(xpath="//button[normalize-space()='Reset Password']")
	WebElement btnResetButton;
	
	@FindBy(xpath="//h6[normalize-space()=\"Reset Password link sent successfully\"]")
	WebElement txtForgotPasswordSuccessfullMsg	;
	
	public void setUserName(String username) {
		txtusername.sendKeys(username);
	}
	
	public void clickResetButton() {
		btnResetButton.click();
	}
	
	public String getSuccessfullMsg() {
		return txtForgotPasswordSuccessfullMsg.getText();
		
	}

}
