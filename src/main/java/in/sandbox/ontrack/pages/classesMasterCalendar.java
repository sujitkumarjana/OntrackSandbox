package in.sandbox.ontrack.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import in.sandbox.ontrack.base.BaseClass;

public class classesMasterCalendar extends BaseClass {

	public classesMasterCalendar() {
		PageFactory.initElements(driver, this);
	}

	Actions action;

	@FindBy(xpath = "//input[@id='btnAddNewEvent_A']")
	WebElement AddClassButton;
	@FindBy(xpath= "//a[@id='btnBookSubmit']")
	WebElement AddClassSubmitBUtton;

	public String VerifyURL() {
		return driver.getCurrentUrl();
	}

	public String VerifyPageText() {
		return driver.findElement(By.xpath("//span[contains(text(),'Master Calendar')]")).getText();
	}

	public void AddClass(String CallDropBookings, String BatchBooking, String Service, String ClassTitle,
			String ClassDateValue, String ClassTimming, String Duration, String DurationMinsHrs, String FreeEvent,
			String PackageBooking, String GroupSize, String ClassDetails, String Staff) {
		AddClassButton.click();
		// ++++++++++++++++++++++++++++++++++++++
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// ++++++++++++++++++++++++++++++++++++++
		if (CallDropBookings.equalsIgnoreCase("Yes")) {
			driver.findElement(By.xpath("//label[@id='lblOffline']/span")).click();
		}
		if (BatchBooking.equalsIgnoreCase("Yes")) {
			driver.findElement(By.xpath("//label[@id='lblBatch']/span")).click();
		}
		driver.findElement(By.xpath("//span[contains(text(),'Select Service')]")).click();
		List<WebElement> ServiceType = driver
				.findElements(By.xpath("//*[@id='ddlAppoService_chosen']//li[@class='active-result group-option']"));
		for (WebElement ServiceTypes : ServiceType) {
			String ST = ServiceTypes.getText();
			if (ST.equals(Service)) {
				ServiceTypes.click();
				break;
			}
		}
		driver.findElement(By.xpath("//input[@id='txtSessionTitle']")).sendKeys(ClassTitle);

		// Select Date
		WebElement ClassDateElement = driver.findElement(By.xpath("//input[@id='txtSessionStartDate']"));
		jsSendKeys(ClassDateElement, ClassDateValue);

		// Select Time
		WebElement Selecttime = driver.findElement(By.xpath("//div[@id='DDLSessionStartTime_chosen']"));
		Selecttime.click();
		/*// ++++++++++++++++++++++++++++++++++++++
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// ++++++++++++++++++++++++++++++++++++++
*/		WebElement Timescroll = driver
				.findElement(By.xpath("//div[@id='DDLSessionStartTime_chosen']//li[text()='" + ClassTimming + "']"));
		jsScrollTillElementFound(Timescroll);
		Timescroll.click();
		
		//Select Duration
		driver.findElement(By.xpath("//input[@id='txtDuration']")).clear();
		driver.findElement(By.xpath("//input[@id='txtDuration']")).sendKeys(Duration);
		driver.findElement(By.xpath("//select[@id='ddlDurationType']//option[text()='"+ DurationMinsHrs +"']")).click();
		if(FreeEvent.equalsIgnoreCase("Yes")){
			driver.findElement(By.xpath("//label[@id='lblFree']")).click();
		}
		if(PackageBooking.equalsIgnoreCase("Yes")){
			driver.findElement(By.xpath("//span[@id='chkBookPackages']")).click();
		}
		driver.findElement(By.xpath("//input[@id='txtGroupSize']")).sendKeys(GroupSize);
		driver.findElement(By.xpath("//textarea[@id='txtSessionDescription']")).sendKeys(ClassDetails);
		jsScrollDown(driver);
		driver.findElement(By.xpath("//div[@id='ddlStaff_chosen']//span[text()='Select Trainer']")).click();
		driver.findElement(By.xpath("//div[@id='ddlStaff_chosen']//li[text()='"+ Staff +"']")).click();
		AddClassSubmitBUtton.click();
	}
}
