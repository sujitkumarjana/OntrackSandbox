package in.sandbox.ontrack.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import in.sandbox.ontrack.base.BaseClass;
import in.sandbox.ontrack.pages.DashboardPage;

public class DashBoardPageTest extends BaseClass{
	
	DashboardPage dashBoardPage;
	
	public DashBoardPageTest(){
		super();
	}
	
	@BeforeMethod
	public void Setup(){
		initialization();
		dashBoardPage = new DashboardPage();
	}
	
	@Test(priority = 1)
	public void VerifyPageTitleTest(){
		String dashboardpagetitletext = dashBoardPage.VarifyPageTitle();
		Assert.assertEquals(dashboardpagetitletext, "Voonix Fitness - Dashboard", "Dashboard Page Title Not Matched");
	}
	
	
	@Test(priority = 2)
	public void VerifyUserNameTest(){
		Assert.assertTrue(dashBoardPage.VerifyUserName());
	}
	

	@AfterMethod
	public void TearDown(){
		driver.quit();
	}
}
