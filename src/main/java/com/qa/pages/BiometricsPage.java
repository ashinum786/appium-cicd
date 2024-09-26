package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class BiometricsPage extends LoginPage {
	
	@AndroidFindBy (xpath="//android.widget.LinearLayout[@content-desc=\"Turn on biometrics, button\"]")private WebElement biometricsBtn;

	public BiometricsPage() {
		PageFactory.initElements(new AppiumFieldDecorator(driver),this); 
	}
	
	public String getTitle() {
	return getAttribute(biometricsBtn, "text");
	 
}
}
