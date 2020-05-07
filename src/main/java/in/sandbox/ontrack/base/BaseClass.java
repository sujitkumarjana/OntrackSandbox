package in.sandbox.ontrack.base;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import in.sandbox.ontrack.utilities.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	
	public static WebDriver driver;
	public static Properties prop;
	static JavascriptExecutor js;
	
	public BaseClass(){
		
		try {
			prop = new Properties();
			FileInputStream propertyfile = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/in/sandbox/ontrack/config/Config.properties");
			prop.load(propertyfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization (){
		
		String BrowserName = prop.getProperty("browser");
		if(BrowserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
	else if(BrowserName.equalsIgnoreCase("IE"))
		{
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
	else if(BrowserName.equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_Load_Time, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.Implicit_Time, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}
	
	public static void jsSendKeys(WebElement element, String value){
		js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].value='"+ value +"';", element);
	}
	
	public static void jsClick(WebElement element){
		js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click", element);
	}
	
	public static void jsScrollDown(WebDriver driver){		
		js = ((JavascriptExecutor)driver);
		js.executeScript("window.scrollBy(0,500)");
	}
	
	public static void jsScrollTillElementFound(WebElement element){
		js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
}

