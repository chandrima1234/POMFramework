package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.appConstant;
import com.qa.opencart.utils.ElementUtil;

public class accountPage {
	
	private WebDriver driver;
	private ElementUtil eleutil ;
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By headersections= By.cssSelector("div#content h2");
	
	public accountPage(WebDriver driver) { // creating const and passing ref driver so that page actions can
		//be performed
		this.driver =driver;
		eleutil = new ElementUtil(driver);
	}
	
	
	public String getAccPageTitle() {
		String title = eleutil.waitForTitleIs(appConstant.ACC_PAGE_TITLE,appConstant.DEFAULT_TIMEOUT);
		System.out.println("Account page title " +title);
		return title;
	}
	
	public boolean getAccPageurl() {
		String url =eleutil.waitForUrlContains(appConstant.DEFAULT_TIMEOUT,appConstant.ACC_PAGE_URL_PARAM);
		System.out.println("Page title" + url);
		if(url.contains(appConstant.ACC_PAGE_URL_PARAM)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean logoutlinkExist() {
		return eleutil.isdisplayed(logoutLink);
	    
	}
	public boolean searchbuttonExist() {
		return eleutil.isdisplayed(search);
	    
	}
	
	public searchResultPage performSearch(String productKey) {
		System.out.println("Product key is " +productKey);
		if(searchbuttonExist()) {
			eleutil.doSendkeys(search, productKey);
			eleutil.doClick(searchIcon);
			return new searchResultPage(driver); //test driven approach , code is driven to create the page
		}
		else {
			System.out.println("Search box is not present");
			return null;
		}
		
		
	}
	 public ArrayList<String> isHeaderExist() {
		 List<WebElement> header = eleutil.waitForElementsToBeVisible(headersections, appConstant.DEFAULT_LARGE_TIMEOUT);
		 ArrayList<String> accheaderSection = new ArrayList<String>();
		 System.out.println(header.size());
		 for(WebElement e: header) {
			 String text = e.getText();
			 accheaderSection.add(text);
		 }
		 return accheaderSection;
		 
	 }

}
