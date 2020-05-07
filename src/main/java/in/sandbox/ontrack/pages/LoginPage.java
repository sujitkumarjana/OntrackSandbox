package in.sandbox.ontrack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.sandbox.ontrack.base.BaseClass;

public class LoginPage extends BaseClass{
	
	//Page Factory or Object Repository
	@FindBy(xpath="//input[@id='txtEmail']")
	WebElement UserName;
	
	@FindBy(xpath="//input[@id='txtPassword']")
	WebElement Password;
	
	@FindBy(xpath="//input[@id='btnLogin']")
	WebElement LoginButton;
	
	@FindBy(xpath="//a[@class='link']")
	WebElement ForgotPasswordLink;
	
	@FindBy(xpath="//a[contains(text(), 'Register Now!')]")
	WebElement RegisterLink;
	
	//Initializing Objects
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	
	//Actions
	public String VarifyPageTitle(){
		return driver.getTitle();
	}
	
	public DashboardPage login(String un, String pwd){
		
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserName.sendKeys(un);
		Password.sendKeys(pwd);
		LoginButton.click();		
		return new DashboardPage();
	}

}
