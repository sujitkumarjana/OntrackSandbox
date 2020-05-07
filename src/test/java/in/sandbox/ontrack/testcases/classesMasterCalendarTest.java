package in.sandbox.ontrack.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import in.sandbox.ontrack.base.BaseClass;
import in.sandbox.ontrack.pages.DashboardPage;
import in.sandbox.ontrack.pages.LoginPage;
import in.sandbox.ontrack.pages.classesMasterCalendar;
import utilities.ExcelUtility;

public class classesMasterCalendarTest extends BaseClass {

	LoginPage loginPage;
	DashboardPage dashboardPage;
	classesMasterCalendar classMasterCalPage;

	public classesMasterCalendarTest() {
		super();
	}

	@BeforeMethod
	public void Setup() {
		initialization();
		loginPage = new LoginPage();
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		classMasterCalPage = new classesMasterCalendar();
		DashboardPage.navigateToMasterCalendarPage();
	}

	@Test(enabled = false)
	public void VerifyPageURLTest() {
		Assert.assertEquals(classMasterCalPage.VerifyURL(),
				"http://sandbox.ontrackstudio.in/studio/appointment/set-classes.aspx", "Page URL is wrong");
	}

	@Test(enabled = false)
	public void VerifyPageTextTest() {
		Assert.assertEquals(classMasterCalPage.VerifyPageText(), "Master Calendar", "Page text is wrong");
	}

	@Test
	public void AddClassTest() {
		ExcelUtility exceldata = new ExcelUtility(
				"E:/WorkSpace/SandboxOntrackTest/src/main/java/TestData/Classinfo.xlsx");
		int rowCount = exceldata.getRowCount("AddClass");
		for (int i = 2; i <= rowCount; i++) {
			String CallDropBooking = exceldata.getCellData("AddClass", "On CallDrop In Booking", i);
			String BatchBooking = exceldata.getCellData("AddClass", "Batch Booking", i);
			String Service = exceldata.getCellData("AddClass", "Service", i);
			String ClassTitle = exceldata.getCellData("AddClass", "Class Title", i);
			String DateofClass = exceldata.getCellData("AddClass", "Date of Class", i);
			String ClassTiming = exceldata.getCellData("AddClass", "Class Timming", i);
			String ClassDuration = exceldata.getCellData("AddClass", "Duration", i);
			String CD[] = ClassDuration.split(" ");
			String DurationValue = CD[0];
			String DurationMinsHrs = CD[1];
			String freeEvent = exceldata.getCellData("AddClass", "Free Event", i);
			String packageBooking = exceldata.getCellData("AddClass", "Package Booking", i);
			String groupSize = exceldata.getCellData("AddClass", "Group Size", i);
			String classDetails = exceldata.getCellData("AddClass", "Class Details", i);
			String staff = exceldata.getCellData("AddClass", "Staff", i);
			classMasterCalPage.AddClass(CallDropBooking, BatchBooking, Service, ClassTitle, DateofClass, ClassTiming,
					DurationValue, DurationMinsHrs, freeEvent, packageBooking, groupSize, classDetails, staff);
		}
	}

}
