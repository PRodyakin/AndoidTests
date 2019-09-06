package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import capabilities.DesiredCapabilitiesTemplate;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public abstract class BaseTest {
	
	AppiumDriver<MobileElement> driver;
	
	BaseTest() throws Exception{
		DesiredCapabilities caps = new DesiredCapabilitiesTemplate();
		//Instantiate Appium Driver
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			startTesting();
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void afterReset() {
		MobileElement el2 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/next");
		el2.click();
		MobileElement el3 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/next");
		el3.click();
		el3.click();
		el3.click();
		el3.click();
		MobileElement el4 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/confirm");
		el4.click();
	}
	
	public abstract void startTesting() throws Exception;

	public static String cleanStr(String str) {
		str = str.replaceAll("[\\W]", "");
		str = str.replaceAll("[\\S]", "");
		return str;
	}
}
