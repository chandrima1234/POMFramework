package com.qa.opencart.testcases;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.baseTest;
import com.qa.opencart.constants.appConstant;
import com.qa.opencart.utils.ExcelUtil;

public class registrationPageText extends baseTest {
	
	
	@BeforeClass
	   public void registerSetup() {
		registrationPage = loginpage.navigateToRegister();
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object regData[][] = ExcelUtil.getTestData(appConstant.REGISTER_SHEET_NAME);
		return regData;
	}
	
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "automationtest"+random.nextInt(10000)+"@gmail.com";
		return email;
	}
	@Test(dataProvider = "getRegTestData")
	public void registrationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		String UserRegisterMsg = registrationPage.doRegistration(firstName, lastName, getRandomEmail(), telephone,password, subscribe);
        Assert.assertEquals(UserRegisterMsg, "Your Account Has Been Created!");
}
	
}
