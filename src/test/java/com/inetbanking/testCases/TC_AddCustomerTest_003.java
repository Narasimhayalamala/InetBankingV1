package com.inetbanking.testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("User name is provided");
		lp.setPassword(password);
		logger.info("Passsword is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		AddCustomerPage addCust=new AddCustomerPage(driver);
		addCust.clickAddNewCustomer();
		logger.info("providing customer details....");
		addCust.custName("Naresh");
		addCust.custgender("male");
		addCust.custdob("10","15","1992");
		Thread.sleep(3000);
		addCust.custaddress("INDIA");
		addCust.custcity("HYD");
		addCust.custstate("TELANGANA");
		addCust.custpinno("5000074");
		addCust.custtelephoneno("9901995902");
		String email=randomString()+"@gmail.com";
		addCust.custemailid(email);
		addCust.custpassword("abcdef");
		addCust.custsubmit();
		
		Thread.sleep(3000);
		logger.info("validation started....");
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		if(res==true) {
			Assert.assertTrue(true);
			logger.info("test case passed....");
		}else {
			logger.info("test case failed....");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
	 }
}
