package in.sandbox.ontrack.pages;

import java.util.List;
import java.util.Set;

import org.apache.poi.hslf.record.ParentAwareRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import in.sandbox.ontrack.base.BaseClass;

public class ManageClientsPage extends BaseClass{
	
	static Actions action;
	
	public ManageClientsPage(){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//input[@id='txtSearchClient']")
	static WebElement Searchclient;
	@FindBy (xpath = "//input[@id='btnSearch']")
	static WebElement SearchclientButton;
	@FindBy (xpath = "//input[@value='Add New']")
	static WebElement AddClientButton;

	public String VerifyPage(){
		return driver.findElement(By.xpath("//span[text()='Manage Clients']")).getText();
	}
	
	public static void AddClient(String FirstName, String LastName, String EmailAddress, String Gender, 
			String DOB, String PhoneNumber, String EmergencyContactNo) throws InterruptedException{
		AddClientButton.click();
		driver.findElement(By.xpath("//input[@id='txtFirstName_AddNew']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//input[@id='txtLastName_AddNew']")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@id='txtEmail_AddNew']")).sendKeys(EmailAddress);
		driver.findElement(By.xpath("//div[@id='divGender']//i[@data-value='"+ Gender +"']")).click();
		WebElement DateofBirth = driver.findElement(By.xpath("//input[@id='DateOfBirth']"));
		jsSendKeys(DateofBirth, DOB);
		driver.findElement(By.xpath("//input[@id='txtPhoneNo_AddNew']")).sendKeys(PhoneNumber);
		driver.findElement(By.xpath("//input[@id='txtEmergencyNo_AddNew']")).sendKeys(EmergencyContactNo);
		driver.findElement(By.xpath("//div[@id='ddlAcquisitionType_chosen']/a/span")).click();
		
	}
	
	public static boolean VerifyClient(String ClientEmailID){
		Searchclient.sendKeys(ClientEmailID);
		String Flag = "F";
		List<WebElement> SearchResults = driver.findElements(By.xpath("//ul[@id='ui-id-1']/li/a/span[3]"));
		for(WebElement SearchResult : SearchResults){
			String SR = SearchResult.getText();
			if(SR.equals(ClientEmailID)){
				return SearchResult.isDisplayed();
			}
		}
		if(Flag.equals("F")){
			System.out.println("No profile found.");
		}
		return false;
	}
	
	public static void SearchClientProfile(String ClientEmailID) throws InterruptedException{
		Searchclient.sendKeys(ClientEmailID);
		String Flag = "F";
		List<WebElement> SearchResults = driver.findElements(By.xpath("//ul[@id='ui-id-1']/li/a/span[3]"));
		for(WebElement SearchResult : SearchResults){
			String SR = SearchResult.getText();
			if(SR.equals(ClientEmailID)){
				SearchResult.click();
				SearchclientButton.click();
				Flag = "T";
				break;
			}
		}
		if(Flag.equals("F")){
			System.out.println("No profile found.");
		}
	}
	
	public static void VisitClientProfile() throws InterruptedException{
		
		String ParentWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='View Profile']")).click();
		Set<String> AllCliendWindows = driver.getWindowHandles();
		for(String ChildWindow : AllCliendWindows){
			if(!ParentWindow.equals(ChildWindow)){
				driver.switchTo().window(ChildWindow);
				
			}
		}
	}

	public void VerifyClientProfilePage(){
		String ProfileName = driver.findElement(By.xpath("//h2[@id='ClientName']")).getText();
		System.out.println(ProfileName);
	}

	public void uploadFile(String FileTitle, String FileDescription, String FilePath){
		jsScrollDown(driver);
		driver.findElement(By.xpath("//h4[@id='h4Document']")).click();
		driver.findElement(By.xpath("//i[@class='fa ctrl-add-document']")).click();
		driver.findElement(By.xpath("//input[@id='txtDocumentTitle']")).sendKeys(FileTitle);
		driver.findElement(By.xpath("//textarea[@id='txtDocumentdesc']")).sendKeys(FileDescription);
		WebElement UploadElement = driver.findElement(By.xpath("//input[@name='fileChooseFile']"));
		UploadElement.sendKeys(FilePath);
		driver.findElement(By.xpath("//input[@id='btnUpload']")).click();
	}
}
