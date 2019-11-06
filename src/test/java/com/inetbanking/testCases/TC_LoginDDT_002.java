package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.Utilities.XLUtils;
import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginDDT_002 extends BaseClass {
	
	@Test(dataProvider = "LoginData")
	public void loginDDT(String user,String pwd) throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		logger.info("username provided");
		lp.setPassword(pwd);
		logger.info("password provided");
		lp.clickSubmit();
		Thread.sleep(3000);
		
	if(isAlertPresent()==true) {
			driver.switchTo().alert().accept();//Close Alert
		driver.switchTo().defaultContent();//Focus(return) on main page
		Assert.assertTrue(false);
		logger.warn("Login failed");
	} else {
		Assert.assertTrue(true);
		logger.warn("Login passed");
		lp.clickLogout();
		Thread.sleep(3000);

		driver.switchTo().alert().accept();//Close logout alert
		driver.switchTo().defaultContent();
	}
}
	public boolean isAlertPresent() {//User defined method created for whether alert present or not
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			// TODO: handle exception
			return false;
		}
	}

	@DataProvider(name="LoginData")//If you have multiple Data provider methods then we have to give Unique name ex:name=LoginData
	String [][] getData() throws IOException{
	
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		int rowNumber=XLUtils.getRowCount(path, "Sheet1");
		int colCount=XLUtils.getCellCount(path, "Sheet1", 1);
		String logindata[][]=new String[rowNumber][colCount];
		for (int i = 1; i <=rowNumber; i++) {
			
			for (int j = 0; j <colCount; j++) {
				
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i,j);//1,0
			}
		}
		return logindata;		
	}
}
	