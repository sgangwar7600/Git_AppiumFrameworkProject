package tests;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.testng.annotations.Test;
import junit.framework.Assert;
import pageObjectPages.OrderAddressPagee;
import pageObjectPages.OrderConfirmationPagee;
import pageObjectPages.OrderPaymentPagee;
import pageObjectPages.OrderShippingPagee;
import pageObjectPages.OrderSummaryPagee;
import pageObjectPages.ProductPagee;
import pageObjectPages.SearchResultPagee;
import pageObjectPages.homePagee;
import pageObjectPages.indexPage;
import pageObjectPages.myAccount;
import pageObjectPages.registerUserClass;

public class TC_ProductPageTest extends BaseClass {

	@Test (enabled = false)
	//@Test (priority=2)
	public void VerifySearchProduct() throws IOException {
		
		
		String searchKey = "T-shirts";

		
		Logger.info("****************TestCase Search product  Started*********************");

		indexPage pg = new indexPage(driver);
		
		//sign in
		pg.clickOnSignIn();
		Logger.info("click on sign In link");
				
		
		myAccount myAcpg = new myAccount(driver);
		
		//enter account details - email and password
		myAcpg.enterEmailAddress(email);
		Logger.info("enter email");
		
		myAcpg.enterPassword(password);
		Logger.info("enter password");
		
		myAcpg.clickOnSubmit();
		Logger.info("click on submit login button");
		
		
		//enter search key in search box
		registerUserClass productPg = new registerUserClass(driver); 

		productPg.EnterDataInSearchBox(searchKey);
		productPg.ClickOnSearchButton();
		
		//get name of search product
		SearchResultPagee resultpg  = new SearchResultPagee(driver);
		String SearchResultProductName = resultpg.getSearchResultProductName();
	
		//verify the correct product is displaying after search
		if(SearchResultProductName.contains(searchKey))
		{
			
			Assert.assertTrue(true);
			Logger.info("Search Product Testcase - Passed");
			productPg.clickOnSignOut();
			} 
		
		else 
			
		{
			Logger.info("Search Product Testcase - Failed");
			Assert.assertFalse(false);
			captureScreenShot(driver, "verifySearchProduct");

		}	
	}
	
	@Test (enabled = false)

	//@Test (priority=1)
	public void VerifyBuyProduct() throws IOException, InvocationTargetException {
		
		Logger.info("\n***************TestCase Buy Product started*****************"); 
		
		//sign in
		indexPage indexpg = new indexPage(driver);
		indexpg.clickOnSignIn();
		Logger.info("click on sign In link");
				
		//enter account details - email and password
		myAccount pg = new myAccount(driver);
		pg.enterEmailAddress(email);
		Logger.info("enter email");
		
		pg.enterPassword(password);
		Logger.info("enter password");
		
		pg.clickOnSubmit();
		Logger.info("Sign In Link Click");
		
		//enter search key in search box
		registerUserClass prodCatPg = new registerUserClass(driver); 

		prodCatPg.EnterDataInSearchBox("T-shirts");
		Logger.info("T-shirt entered in search box");
		
		prodCatPg.ClickOnSearchButton();
		Logger.info("clicked on search button");

		//get name of search product
		SearchResultPagee searchResultPg  = new SearchResultPagee(driver);
		
		searchResultPg.hoverToMoreLink();
		Logger.info("Clicked on more button");
		searchResultPg.ClickOnMoreLink();
		
		Logger.info("Clicked on more button");
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ProductPagee prodPg = new ProductPagee(driver);
		
		prodPg.setSize("M");
		Logger.info("Size M entered");
		prodPg.setQuantity("2");
		Logger.info("quantity 2 entered");
		prodPg.clickOnAddToCart();
		Logger.info("Click on add to cart");
		prodPg.clickOnProceedToCheckOut();
		Logger.info("click to proceed to checkout on product page");

		
		OrderSummaryPagee orderSumPg = new OrderSummaryPagee(driver);
		orderSumPg.clickOnProceedToCheckout();
		Logger.info("Click on proceed to checkout on order summary page");

		OrderAddressPagee orderAddPg = new OrderAddressPagee(driver);
		orderAddPg.clickOnProceedToCheckout();
		Logger.info("Click on proceed to checkout on order address page");

		OrderShippingPagee orderShippingPg = new OrderShippingPagee(driver);
		orderShippingPg.selectTrmsOfServices();
		Logger.info("Select term of service check box");

		orderShippingPg.clickOnProceedToCheckout();
		Logger.info("Click on proceed to checkout on order shipping page");

		OrderPaymentPagee orderPaymentPg = new OrderPaymentPagee(driver);
		Logger.info(orderPaymentPg.getPageTitle());
		
		orderPaymentPg.clickOnPayByCheque();
		Logger.info("Clicked on pay by cheque");

		OrderConfirmationPagee orderConfirmPg = new OrderConfirmationPagee(driver);
		orderConfirmPg.clickOnConfirmOrder();
		
		Logger.info("Click on confirm order");
		
		String sucessMsg =  orderConfirmPg.getOrderSuccessMessage();
				
		if(sucessMsg.equals("Your order on My Shop is complete."))
		{
			Logger.info("VerifyBuyProduct - Passed");
			Assert.assertTrue(true);
			
			orderConfirmPg.clickOnSignOut();	
		}
		else
		{
			Logger.info("VerifyBuyProduct - Failed");
			captureScreenShot(driver, "VerifyBuyProduct");
			Assert.assertFalse(false);
		}

		Logger.info("************************TestCase BuyProduct ends**********************************");
	}
	
	
	@Test (enabled = true)
	public void verifyTextOnHomePage() throws IOException
	{
		
		
		Logger.info("\n***********verify Text On HomePage started************"); 

		homePagee homePg = new homePagee(driver);
		
		homePg.scrollToViewElement();
		

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	
		String actualAlertMsg = homePg.getTextOfAlertForHomePage();
		String expectedAlertMsg = "No featured products at this time.";

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(actualAlertMsg.equals(expectedAlertMsg))
		{
			Logger.info("verifyTextOnHomePage - passed"); 
			Assert.assertTrue(true);
		}
		else
		{
			Logger.info("verifyTextOnHomePage - failed"); 
			captureScreenShot(driver,"verifyTextOnHomePage");
			Assert.assertTrue(false);
		}
		
	}
	
}





















