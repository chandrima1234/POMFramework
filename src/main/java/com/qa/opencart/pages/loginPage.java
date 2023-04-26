package com.qa.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.appConstant;
import com.qa.opencart.factory.driverfactory;
import com.qa.opencart.utils.ElementUtil;

import jdk.internal.org.jline.utils.Log;

public class loginPage {
	
	private WebDriver driver;  // always make it private >> encapsulation so that other methods are not 
	//going to do that
	private ElementUtil eleutil ;
	By emailId = By.id("input-email");
	By password = By.id("input-password");
	By login = By.xpath("//input[@value='Login']");
	By logoImgae =By.cssSelector("img[title='naveenopencart']");
	By forgotLink = By.linkText("Forgotten Password");
	By registerLink = By.linkText("Register");
	
	private static final Logger LOG = Logger.getLogger(loginPage.class);
	
	public loginPage(WebDriver driver) { // creating const and passing ref driver so that page actions can
		//be performed
		this.driver =driver;
		eleutil = new ElementUtil(driver);
	}
	
	
	public String getLoginPageTitle() {
		String title = eleutil.waitForTitleIs(appConstant.LOGIN_PAGE_TITLE,appConstant.DEFAULT_TIMEOUT);
		System.out.println("Login page title " +title);
		LOG.info("Login page title" +title);
		
		return title;
	}
	
	public boolean getPageurl() {
		String url =eleutil.waitForUrlContains(appConstant.DEFAULT_TIMEOUT,appConstant.LOGIN_PAGE_URL_PARAM);
		System.out.println("Page url" + url);
		LOG.info("Login page url" +url);
		if(url.contains(appConstant.LOGIN_PAGE_URL_PARAM)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean forgotPwdLink() {
		return eleutil.isdisplayed(forgotLink);
	   
	}
	
	public accountPage doLogin(String username,String pwd) {
		eleutil.doSendkeyswithWait(emailId, appConstant.DEFAULT_LARGE_TIMEOUT, username);
		eleutil.doSendkeys(password, pwd);
		System.out.println("credentials are "+username +" : "+pwd);
		eleutil.doClick(login);
		return new accountPage(driver);
		}
	

	public registrationPage navigateToRegister() {
		eleutil.waitForElementsToBeVisible(registerLink, appConstant.DEFAULT_TIMEOUT);
		eleutil.doClick(this.registerLink);
		return new  registrationPage(driver);
	}
	
	

}
