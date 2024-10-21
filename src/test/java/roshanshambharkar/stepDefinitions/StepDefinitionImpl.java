package roshanshambharkar.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import roshanshambharkar.TestComponents.BaseTest;
import roshanshambharkar.pageobjects.CartPage;
import roshanshambharkar.pageobjects.CheckoutPage;
import roshanshambharkar.pageobjects.ConfirmationPage;
import roshanshambharkar.pageobjects.LandingPage;
import roshanshambharkar.pageobjects.ProductCatalog;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalog productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_passsword(String username, String password) {
		
		productCatalogue = landingpage.loginApplication(username, password);
	}
	@When("^I add (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();

	}
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	@Then("^\"([^\"]*)\" message is displayed$")
	public void aomwthing_message_is_displayed(String strArg1)  throws Throwable{
		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}
	
	
}
