package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.BaseTest;
import com.qa.pages.BiometricsPage;
import com.qa.pages.LoginPage;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginTests extends BaseTest {
	LoginPage loginPage;
	BiometricsPage biometricsPage;
	
	  @BeforeMethod
	  public void beforeMethod(Method m) {
			System.out.println("Login test before method");
		  loginPage=new LoginPage();
		  System.out.println("\n"+"*********** starting test:"+m.getName()+"*********"+"\n");
	  }

	  @AfterMethod
	  public void afterMethod() {
			System.out.println("Login test after method");
	  }

	  @BeforeClass
	  public void beforeClass() {
	  }

	  @AfterClass
	  public void afterClass() {
	  }
  @Test
  public void invalidUserName() {
	  loginPage.enterUserName("invalidusername");
	  loginPage.enterPassword("secret_sauce");
	  loginPage.pressLoginButton();
	  String actualErrText=loginPage.getErrText();
	  String expectedErrText="Login Error";
	  System.out.println("actual error text="+actualErrText+"\nexpected error text="+expectedErrText );
	  Assert.assertEquals(actualErrText, expectedErrText);
	  loginPage.clickOkButtonInErrorPopup();
	  
  }
  
  @Test
  public void validLogin() throws InterruptedException {
	  loginPage.enterUserName("ashn156");
	  loginPage.enterPassword("nyu12346");
	  biometricsPage=loginPage.pressLoginButton();
	  loginPage.clickOkTurnOnBiometrics();
	  
	  
  }


}
