package in.sandbox.ontrack.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import in.sandbox.ontrack.base.BaseClass;
import in.sandbox.ontrack.pages.DashboardPage;
import in.sandbox.ontrack.pages.LoginPage;

public class LoginPageTest extends BaseClass {

	LoginPage loginPage;
	DashboardPage dashboardPage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	public void LoginPageTitleTest() {

		String title = loginPage.VarifyPageTitle();
		Assert.assertEquals(title, "Voonix Fitness");
	}

	@Test(priority = 2)
	public void LoginTest() {
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		String PageTitle = dashboardPage.VarifyPageTitle();
		Assert.assertEquals(PageTitle, "Voonix Fitness - Dashboard", "Page Title for Dashboard Page Not Matched");
	}

	/*
	 * @AfterMethod public void tearDown(){ driver.quit(); }
	 */

}
