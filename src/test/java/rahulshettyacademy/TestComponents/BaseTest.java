package rahulshettyacademy.TestComponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulShettyAcadmey.pageobjects.landingPage;

public class BaseTest {

	public WebDriver driver;

	public WebDriver initDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Lenovo\\eclipse-workspace\\dg-automation\\src\\main\\java\\rahulshettyacademy\\Resources\\GlobalData.properties");
		prop.load(fis);

		String BrowserName = prop.getProperty("browser");
		if (BrowserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		
		}
		if (BrowserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}
		if (BrowserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		}
		if (BrowserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;

	}

	public landingPage LaunchApplication() throws IOException
	{
		driver=initDriver();
		landingPage landingPage = new landingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
}
