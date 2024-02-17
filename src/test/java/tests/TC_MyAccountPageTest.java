package tests;

import java.io.IOException;
import org.testng.annotations.Test;
import junit.framework.Assert;
import pageObjectPages.AccountCreation;
import pageObjectPages.indexPage;
import pageObjectPages.myAccount;
import pageObjectPages.registerUserClass;

public class TC_MyAccountPageTest extends BaseClass {

	@Test(enabled = false)
	public void verifyRegisterationAndLogin() throws InterruptedException {

		Thread.sleep(3000);
		indexPage pg = new indexPage(driver);  //object of indexPage page
		pg.clickOnSignIn();
		Logger.info("click on sign in button");

		//Thread.sleep(3000);

		myAccount myAcpg = new myAccount(driver);  //object of myAccount page
		myAcpg.enterCreateEmailAddress(email);
		Logger.info("email address and create account section");

		//Thread.sleep(3000);

		myAcpg.clickSubmitCreate();
		Logger.info("click on create an account button ");

		Thread.sleep(2000);


		AccountCreation accCreation = new AccountCreation(driver);
		accCreation.selectTitleMrs();
		Logger.info("selecting the title");
		accCreation.enterCustomerFirstName(firstName);
		Logger.info("inserting first name successfully");
		accCreation.enterLastName(lastName);
		Logger.info("inserting last name successfully");
		accCreation.enterCustomerPassword(password);
		Logger.info("entering password successfully");
		Thread.sleep(10000);
		accCreation.clickOnRegisterButton();
		Thread.sleep(3000);
		accCreation.clickOnRegisterButton();
		Logger.info("clicking on register button");



		registerUserClass regUser = new registerUserClass(driver);

		String	actualUserName = regUser.getUserName();
		Assert.assertEquals(expectedUserName, actualUserName);
		Logger.info("expected and actual username are same");


	}


	@Test(enabled = true)
	public void VerifyLogin()   throws IOException {

		Logger.info(".................verify login execution stared...................");

		myAccount myAcpg = new myAccount(driver);
		registerUserClass regUser = new registerUserClass(driver); 
		indexPage pg = new indexPage(driver);

		pg.clickOnSignIn();
		Logger.info("click on sign In link");
		myAcpg.enterEmailAddress(email);
		Logger.info("enter email");
		myAcpg.enterPassword(password);
		Logger.info("enter password");
		myAcpg.clickOnSubmit();
		Logger.info("click on submit login button");

		String userNamee = regUser.getUserName();
		if (userNamee.equals(expectedUserName)) 
		{
			Logger.info("VerifyLogin - Passed");
			Assert.assertTrue(true);
			regUser.clickOnSignOut();

		}
		else
		{
			Logger.info("VerifyLogin - Failed");
			captureScreenShot(driver,"VerifyLogin");
			Assert.assertTrue(false);
		}

		Logger.info(".........................TestCase Verify Login ends................................."); 
	}
}
