package rahulshettyAcadmy;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.implementation.bytecode.Duplication;
import rahulShettyAcadmey.pageobjects.CartPage;
import rahulShettyAcadmey.pageobjects.CheckOutPage;
import rahulShettyAcadmey.pageobjects.conformationPage;
import rahulShettyAcadmey.pageobjects.landingPage;
import rahulShettyAcadmey.pageobjects.productCatlog;

public class StandAloneTest {
	public static void main(String[] args) throws InterruptedException {

		String proDuctName = "IPHONE 13 PRO";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		landingPage landingPage = new landingPage(driver);
		landingPage.goTo();
		productCatlog productCatLogue = landingPage.loginApplication("dnyaneshwarghate1010@gmail.com", "Dghate@2025");

		List<WebElement> products = productCatLogue.getProductList();
		productCatLogue.addProductToCart(proDuctName);

		CartPage cartpage = productCatLogue.gotoCartPage();

		Boolean match = cartpage.VeryFyProductDisplay(proDuctName);
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartpage.goToCheckOut();
		checkoutPage.selectCountry("india");

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");

		conformationPage conformMessgae = checkoutPage.submitOrder();

		String Messge = conformMessgae.verfyConformationPage();
		Assert.assertTrue(Messge.equalsIgnoreCase("Thankyou for the order."));

		driver.quit();

	}

}
