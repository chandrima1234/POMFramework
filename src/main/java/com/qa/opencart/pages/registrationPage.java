package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.appConstant;
import com.qa.opencart.utils.ElementUtil;

public class registrationPage {
	
	public WebDriver driver;
	public ElementUtil eleutil;
	
	By firstName = By.id("input-firstname");
	By lastName = By.id("input-lastname");
	By email = By.id("input-email");
	By telephone = By.id("input-telephone");
	By password = By.id("input-password");
	By confirm = By.id("input-confirm");
	By subscribe = By.name("newsletter");
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1' and @type='radio']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value='0' and @type='radio']");

	private By registerSuccessMesg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	
	
	
	public registrationPage(WebDriver driver) {
		this.driver= driver;
		eleutil = new ElementUtil(driver);
	}
	
	
	public String doRegistration(String firstName, String lastName, String email, String telephone,String password,String subscribe) {
		
		eleutil.doSendkeyswithWait(this.firstName, appConstant.DEFAULT_LARGE_TIMEOUT, firstName);
		eleutil.doSendkeys(this.lastName, lastName);
		eleutil.doSendkeys(this.email, email);
		eleutil.doSendkeys(this.telephone, telephone);
		eleutil.doSendkeys(this.password, password);
		eleutil.doSendkeys(this.confirm, password);
		if(subscribe.equals("Yes")) {
			eleutil.doClick(this.subscribeYes);
			
		}
		if(subscribe.equals("No")) {
			eleutil.doClick(this.subscribeNo);
			
		}
		eleutil.doClick(this.agreeCheckBox);
		eleutil.doClick(this.continueButton);
		String successmsg= eleutil.waitForElementPresence(registerSuccessMesg, appConstant.DEFAULT_LARGE_TIMEOUT).getText();
		eleutil.doClick(logoutLink);
		eleutil.doClick(registerLink);
		return successmsg;
	}
	
	
	

}
