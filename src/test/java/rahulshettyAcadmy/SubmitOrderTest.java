package rahulshettyAcadmy;

import java.io.IOException;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.implementation.bytecode.Duplication;
import rahulShettyAcadmey.pageobjects.CartPage;
import rahulShettyAcadmey.pageobjects.CheckOutPage;
import rahulShettyAcadmey.pageobjects.conformationPage;
import rahulShettyAcadmey.pageobjects.landingPage;
import rahulShettyAcadmey.pageobjects.productCatlog;
import rahulshettyacademy.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{

	@Test
	public void submitOrder() throws IOException, InterruptedException
	{

		//String proDuctName = "IPHONE 13 PRO";

		
		landingPage landingPage=LaunchApplication();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		productCatlog productCatLogue = landingPage.loginApplication("dnyaneshwarghate1010@gmail.com", "Dghate@2025");

		List<WebElement> products = productCatLogue.getProductList();
		productCatLogue.addProductToCart(proDuctName);

		CartPage cartpage = productCatLogue.gotoCartPage();

		Boolean match = cartpage.VeryFyProductDisplay(proDuctName);
		Assert.assertTrue(match);
		
		
		
		CheckOutPage checkoutPage = cartpage.goToCheckOut();
		checkoutPage.selectCountry("india");
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		jse.executeScript("window.scrollBy(0,1500)");
		
		conformationPage conformMessgae = checkoutPage.submitOrder();
		
		
		String Messge = conformMessgae.verfyConformationPage();
		Assert.assertTrue(Messge.equalsIgnoreCase("Thankyou for the order."));

		

	}

}
