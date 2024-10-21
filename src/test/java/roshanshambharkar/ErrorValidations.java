package roshanshambharkar;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import roshanshambharkar.TestComponents.BaseTest;
import roshanshambharkar.TestComponents.Retry;
import roshanshambharkar.pageobjects.CartPage;
import roshanshambharkar.pageobjects.CheckoutPage;
import roshanshambharkar.pageobjects.ConfirmationPage;
import roshanshambharkar.pageobjects.LandingPage;
import roshanshambharkar.pageobjects.ProductCatalog;

public class ErrorValidations extends BaseTest {
	private static FluentWait<WebDriver> wait;

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void Login() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		landingpage.loginApplication("roshana100kaar@gmail.com", "Roshan@1234");
		Assert.assertEquals("Incorrect email password", landingpage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalogue = landingpage.loginApplication("roshana100kar@gmail.com", "Roshan@123");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertFalse(match);
		
	}

}
