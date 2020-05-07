package in.sandbox.ontrack.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import in.sandbox.ontrack.base.BaseClass;
import in.sandbox.ontrack.pages.DashboardPage;
import in.sandbox.ontrack.pages.LoginPage;
import in.sandbox.ontrack.pages.ManageClientsPage;
import utilities.ExcelUtility;

public class ManageClientsPageTest extends BaseClass {

	LoginPage loginPage;
	DashboardPage dashBoardPage;
	ManageClientsPage manageClientsPage;

	public ManageClientsPageTest() {
		super();
	}

	@BeforeClass
	public void Setup() {
		initialization();
		loginPage = new LoginPage();
		dashBoardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		manageClientsPage = new ManageClientsPage();
		DashboardPage.navigateToManageClientPage();
	}

	@Test(enabled = false /* priority = 1 */)
	public void VerifyPage() {
		String PageTitle = manageClientsPage.VerifyPage();
		Assert.assertEquals(PageTitle, "Manage Clients", "This is not Manage Clients Page");
	}

	@Test(enabled = false /* priority = 3 */)
	public void AddNewClient() {
		try {
			ManageClientsPage.AddClient("Sujit", "Jana", "sujitjana@tulieservices.com", "Female", "23/05/2013",
					"1234567890", "9876543210");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(enabled = false /* priority = 1 */)
	public void VisitClientProfileTest() {
		try {
			ManageClientsPage.SearchClientProfile("cbillanyb@wunderground.com");
			ManageClientsPage.VisitClientProfile();
			manageClientsPage.uploadFile("Test", "Test", "C:/Users/DELL/Desktop/xpath_syntax.txt");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(enabled = false /* priority = 4 */)
	public void VerifyClient() {
		ExcelUtility excelUtil = new ExcelUtility(
				"E:/WorkSpace/SandboxOntrackTest/src/main/java/TestData/Userinfo.xlsx");
		int Userrow = excelUtil.getRowCount("Users");
		for (int i = 2; i <= Userrow; i++) {
			String Email = excelUtil.getCellData("Users", "Email Address", i);
			System.out.println("Email Address is: " + Email);
			ManageClientsPage.VerifyClient(Email);
		}
	}

	@Test
	public void ClientAccountStatement() {
		ExcelUtility excelUtil = new ExcelUtility(
				"E:/WorkSpace/SandboxOntrackTest/src/main/java/TestData/Userinfo.xlsx");
		int Userrow = excelUtil.getRowCount("Users");
		for (int i = 2; i <= Userrow; i++) {
			String FirstName = excelUtil.getCellData("Users", "First Name", i);
			System.out.println("First Name is: " + FirstName);
			String LastName = excelUtil.getCellData("Users", "Last Name", i);
			System.out.println("Last Name is: " + LastName);
			String Email = excelUtil.getCellData("Users", "Email Address", i);
			System.out.println("Email Address is: " + Email);
			String Gender = excelUtil.getCellData("Users", "Gender", i);
			System.out.println("Gender is: " + Gender);
			String DateofBirth = excelUtil.getCellData("Users", "Date Of Birth", i);
			System.out.println("Date of Birth is: " + DateofBirth);
			String PhoneNo = excelUtil.getCellData("Users", "Phone No", i);
			System.out.println("PhoneNo is: " + PhoneNo);
			try {
				ManageClientsPage.SearchClientProfile(Email);
				ManageClientsPage.VisitClientProfile();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@AfterClass
	public void TearDown() {
		driver.quit();
	}

}
