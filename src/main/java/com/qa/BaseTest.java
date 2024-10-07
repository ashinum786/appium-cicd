package com.qa;



import com.google.common.collect.ImmutableMap;
import com.qa.utils.TestUtils;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;
import java.net.URI;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	protected static AppiumDriver driver;
	protected static Properties props;
	InputStream inputStream;
	protected static String dateTime;
	TestUtils utils;
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("super before method");
		((CanRecordScreen)driver).startRecordingScreen();
	}
	@AfterMethod
	public void afterMethod(ITestResult result) {
		System.out.println("super after method");
		String media=((CanRecordScreen)driver).stopRecordingScreen();
		if(result.getStatus()==1) {
			Map<String,String> params=result.getTestContext().getCurrentXmlTest().getAllParameters();
			
			String dir="videos"+File.separator+ params.get("platformName")+"_"+ params.get("platformVersion")+"_"
					+ params.get("deviceName")+File.separator +dateTime+File.separator+result.getTestClass().getRealClass().getSimpleName();
		
			File videoDir=new File(dir);
			if(!videoDir.exists()) {
				videoDir.mkdirs();
			}
			try {
				FileOutputStream stream=new FileOutputStream(videoDir+File.separator+result.getName()+".mp4");
				stream.write(org.apache.commons.codec.binary.Base64.decodeBase64(media));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	}
	
	
	
	
	
  @Parameters({"platformName","platformVersion","deviceName","deviceUdid"})
  @BeforeTest
  public void beforeTest(String platformName,String platformVersion,String deviceName,String deviceUdid)  {
	  try {
		  utils=new TestUtils();
		  dateTime=utils.getDateTime();
		  props=new Properties();
		  String propFileName="config.properties";
		
		  inputStream =getClass().getClassLoader().getResourceAsStream(propFileName);
		  props.load(inputStream);
		  
		  DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
		  desiredCapabilities.setCapability("platformName",platformName );	
		   desiredCapabilities.setCapability("appium:platformVersion",platformVersion );
		  desiredCapabilities.setCapability("appium:deviceName", deviceName);
		  desiredCapabilities.setCapability("appium:udid", deviceUdid);
		  desiredCapabilities.setCapability("appium:automationName", props.getProperty("androidAutomationName"));	
		  desiredCapabilities.setCapability("appium:appPackage", props.getProperty("androidappPackage"));
		  desiredCapabilities.setCapability("appium:appActivity", props.getProperty("androidappActivity"));
		  String s=System.getProperty("user.dir")+(props.getProperty("androidAppLocation"));
		  desiredCapabilities.setCapability("appium:app", s);
		  driver = new AndroidDriver (new URI( props.getProperty("appiumURL")).toURL() ,desiredCapabilities);
		 // String sessionId=driver.getSessionId().toString();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	  }catch(Exception e)
	  {
		  e.printStackTrace();
		  
	  }
	  
    
  }
public void waitForVisibility(WebElement e) {
	
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(TestUtils.WAIT));
	wait.until(ExpectedConditions.visibilityOf(e));
}

public void click(WebElement e) {
	waitForVisibility(e);
	e.click();
}
public void sendKeys(WebElement e,String txt) {
	
	waitForVisibility(e);
	e.clear();
	e.sendKeys(txt);
	
}

public String  getAttribute(WebElement e,String attribute) {
	waitForVisibility(e);
	return e.getAttribute(attribute);
}

public AppiumDriver getDriver() {
	return driver;
}
public String getDateTime() {
	return dateTime;
}
public void swipeGesture(){
	 

    WebElement element = driver.findElement(AppiumBy.
            xpath("//*[@resource-id=\"io.appium.android.apis:id/gallery\"]/android.widget.ImageView[1]"));

    driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
//            "left", 100, "top", 100, "width", 600, "height", 600,
            "elementId", ((RemoteWebElement) element).getId(),
            "direction", "left",
            "percent", 0.75
    ));}
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
