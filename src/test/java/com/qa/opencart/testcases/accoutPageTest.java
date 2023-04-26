package com.qa.opencart.testcases;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.baseTest;
import com.qa.opencart.constants.appConstant;

public class accoutPageTest extends baseTest{
	
	
	
	@BeforeClass
	public void accSetup() {
	accpage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	@Test
	  public void loginPagetest() {
	      String title = accpage.getAccPageTitle();
	      Assert.assertEquals(title, appConstant.ACC_PAGE_TITLE);
	 }
 @Test
	public void getPageurltest() {
		 Assert.assertTrue(accpage.getAccPageurl());
	
	}
	@Test
	public void islogoutlinkExist() {
		Assert.assertTrue(accpage.logoutlinkExist());
	}
	@Test
	public void getAccHeaderSecList() {
		ArrayList<String> accheadersec= accpage.isHeaderExist();
	    System.out.println("Actual headers are" +accheadersec);
	    Assert.assertEquals(accheadersec, appConstant.ACC_PAGE_HEADER_SECTION);
	}
	@DataProvider
	public Object[][] getProductKey() {
		return new Object[][] {
				{ "Macbook"},
				{ "iMac"},
				{"Samsung"}
				};
	}
	@Test(dataProvider = "getProductKey")
	public void searchCheckTest(String productKey) {
		searchResultsPage = accpage.performSearch(productKey);
		Assert.assertTrue(searchResultsPage.isSearchSuccessful());
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
				{ "Macbook", "MacBook Pro" },
				{ "Macbook", "MacBook Air" },
				{ "iMac", "iMac" },
				{"Samsung", "Samsung SyncMaster 941BW"},
				{"Samsung", "Samsung Galaxy Tab 10.1"}
				};
	}
	
	
	@Test(dataProvider = "getProductData")
	public void searchTest(String searchKey, String mainProductName) {
		searchResultsPage = accpage.performSearch(searchKey);
		if (searchResultsPage.isSearchSuccessful()) {
			productInfoPage = searchResultsPage.selectProduct(mainProductName);
			String actualProductHeader = productInfoPage.getProductHeader(mainProductName);
			Assert.assertEquals(actualProductHeader, mainProductName);
		}
	}
	}
	
	


