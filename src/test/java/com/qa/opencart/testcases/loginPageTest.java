package com.qa.opencart.testcases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.baseTest;
import com.qa.opencart.constants.appConstant;
import com.qa.opencart.listeners.ExtentReportListener;
@Listeners(ExtentReportListener.class)
public class loginPageTest extends baseTest{
	
	@Test(priority =1)
	  public void loginPagetest() {
	      String title = loginpage.getLoginPageTitle();
	      Assert.assertEquals(title, appConstant.LOGIN_PAGE_TITLE);
	 }
   @Test(priority =2)
	public void getPageurltest() {
		 Assert.assertTrue(loginpage.getPageurl());
	
	}
	@Test(priority =3)
	public void isForgotPasswordExist() {
		Assert.assertTrue(loginpage.forgotPwdLink());
	}
	
	@Test(priority =4)
	public void loginTest() {
		accpage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accpage.logoutlinkExist());
	}
		
	}