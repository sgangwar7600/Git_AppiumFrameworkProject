package tests;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import junit.framework.Assert;
import pageObjectPages.AccountCreation;
import pageObjectPages.indexPage;
import pageObjectPages.myAccount;
import pageObjectPages.registerUserClass;
import utilities.ReadExcelFile;

public class TC_MyAccountPageTestDataDriven extends BaseClass {

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


	@Test(dataProvider = "LoginDataProvider")
	public void VerifyLogin(String userEmail, String userPwd, String expectedUsernameDD)   throws IOException {

		Logger.info(".................verify login execution stared...................");

		myAccount myAcpg = new myAccount(driver);
		registerUserClass regUser = new registerUserClass(driver); 
		indexPage pg = new indexPage(driver);

		pg.clickOnSignIn();
		Logger.info("click on sign In link");
		myAcpg.enterEmailAddress(userEmail);
		Logger.info("enter email");
		myAcpg.enterPassword(userPwd);
		Logger.info("enter password");
		myAcpg.clickOnSubmit();
		Logger.info("click on submit login button");

		String userNamee = regUser.getUserName();
		if (userNamee.equals(expectedUsernameDD)) 
		{
			Logger.info("VerifyLogin - Passed");
			regUser.clickOnSignOut();
			Assert.assertTrue(true);
		}
		else
		{
			Logger.info("VerifyLogin - Failed");
			captureScreenShot(driver,"VerifyLogin");
			Assert.assertTrue(false);
		}

		Logger.info(".........................TestCase Verify Login ends................................."); 
	}
	
	@DataProvider(name = "LoginDataProvider")
	
		public String[][] LoginDataProvider  ()
		{
			//System.out.println(System.getProperty("user.dir"));
		
			String fileName = System.getProperty("user.dir") + "\\TestData\\AppiumTestData.xlsx";


			int ttlRows = ReadExcelFile.getRowCount(fileName, "LoginTestData");  //getRowCount is a static method so we can call them without making their object
			System.out.println(ttlRows);
		
			int ttlColumns = ReadExcelFile.getColCount(fileName, "LoginTestData"); //getColCount is a static method so we can call them without making their object
	

			String data[][]=new String[ttlRows-1][ttlColumns]; //create two dimensional array object with type of String

			for(int i=1;i<ttlRows; i++)//rows =1,2
			{
				for(int j=0;j<ttlColumns;j++)//col=0, 1,2
				{

				data[i-1][j]=ReadExcelFile.getCellValue(fileName,"LoginTestData", i,j);
				}

				}
		return data;
		}
}
