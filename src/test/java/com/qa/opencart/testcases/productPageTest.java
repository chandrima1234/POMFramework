package com.qa.opencart.testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.baseTest;
import com.qa.opencart.constants.appConstant;

public class productPageTest extends baseTest{
	
	
	@BeforeClass
	public void accSetup() {
		accpage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		}

	@Test
	public void productHeaderTest() {
		searchResultsPage = accpage.performSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook");
		String accProdHeader = productInfoPage.getProductHeader("MacBook");
		Assert.assertEquals(accProdHeader, "MacBook");
		
		
	}
	@DataProvider
	public Object[][] getProductImagecount() {
		return new Object[][] {
				{ "MacBook","MacBook", appConstant.MACBOOK_IMAGE_COUNT },
				{ "MacBook Pro","MacBook Pro", appConstant.MACBOOK__PRO_IMAGE_COUNT },
				{ "iMac","iMac", appConstant.IMAC_IMAGE_COUNT },
				};
	}
	
	@Test(dataProvider = "getProductImagecount")
	   public void productImagecountTest(String searchkey, String productName, int imagecount) {
		searchResultsPage = accpage.performSearch(searchkey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		int totalImage = productInfoPage.getProductImagecount();
		Assert.assertEquals(totalImage, imagecount);
		
	}
	
	
	//Brand: Apple
	//Product Code: Product 14
	//Availability: Out Of Stock
	@Test
	   public void  MetaDataTest() {
		searchResultsPage = accpage.performSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actMetaDataMap = productInfoPage.getProductMetaData();
		Assert.assertEquals(actMetaDataMap.get("Brand"), "Apple");
		Assert.assertEquals(actMetaDataMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actMetaDataMap.get("Reward Points"), "800");
		Assert.assertEquals(actMetaDataMap.get("Availability"), "In Stock");
		
		
	}
	
	
	
	
}
