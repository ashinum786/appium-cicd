package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.BaseTest;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends BaseTest {

	@AndroidFindBy(id="org.nyulmc.clinical.mychart:id/et_username")private WebElement usernameTextField;
	@AndroidFindBy(id="org.nyulmc.clinical.mychart:id/et_password")private WebElement passwordTextField;
	@AndroidFindBy(id="org.nyulmc.clinical.mychart:id/btn_login")private WebElement loginButton;
	@AndroidFindBy(id="org.nyulmc.clinical.mychart:id/alertTitle")private WebElement errText;
	@AndroidFindBy(id="android:id/button1")private WebElement okBtn;
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@content-desc=\"Turn on biometrics, button\"]/android.widget.LinearLayout")private WebElement turnOnBiometricsBtn;
	
	
	public LoginPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver),this); 
	}
	
	public LoginPage enterUserName(String userName) {
		sendKeys(usernameTextField, userName);
		return this;
	}
	public LoginPage enterPassword(String password) {
		sendKeys(passwordTextField, password);
		return this;
	}

	
	public String getErrText() {
		return getAttribute(errText, "text");
	}
	public BiometricsPage pressLoginButton() {
		click(loginButton);
		return new BiometricsPage();
	}
	
	public void clickOkButtonInErrorPopup() {
		click(okBtn);
		
	}
	public void clickOkTurnOnBiometrics() {
		click(turnOnBiometricsBtn);
		
	}
}
