package in.sandbox.ontrack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.sandbox.ontrack.base.BaseClass;

public class DashboardPage extends BaseClass{
	
	static Actions action;

	//Initializing Objects
		public DashboardPage(){
			PageFactory.initElements(driver, this);
		}
		
	@FindBy (xpath = "//a[contains(text(), 'Staff')]") static WebElement StaffPageLink;
	@FindBy (xpath = "//a[contains(text(), 'Appointments')]") static WebElement AppointmentsPageLink;
	@FindBy (xpath = "//a[contains(text(), 'Client')]") static WebElement ClientPageLink;
	@FindBy (xpath = "//li[@id='liClients']//a[text()='Manage ']") static WebElement ManageClientPageLink;
	@FindBy (xpath = "//a[contains(text(),'CLASSES')]") static WebElement ClassesLink;
	@FindBy (xpath = "//div[@class='sMenuGroup']/following::a[contains(text(),'Master Calendar')]") static WebElement ClassesManageCalendarLink;
		
		//Actions
		public String VarifyPageTitle(){
			return driver.getTitle();			
		}
		
		public boolean VerifyUserName(){
			return driver.findElement(By.xpath("//label[contains(text(),'"+ prop.getProperty("OwnerFirstName") +"')]")).isDisplayed();
		}
		
		public static ManageClientsPage navigateToManageClientPage(){
			action = new Actions(driver);
			action.moveToElement(ClientPageLink).moveToElement(ManageClientPageLink).click().build().perform();
			return new ManageClientsPage();
		}
		
		public static ManageClientsPage navigateToMasterCalendarPage(){
			action = new Actions(driver);
			action.moveToElement(ClassesLink).moveToElement(ClassesManageCalendarLink).click().build().perform();
			return new ManageClientsPage();
		}
		
}
