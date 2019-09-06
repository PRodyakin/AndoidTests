package test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;

public class RepitedApp extends BaseTest {

	public RepitedApp() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override

	public void startTesting() throws Exception {
		// TODO Auto-generated method stub
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.context(RepeatedApplication.NATIVE_CONTEXT);
		MobileElement el2 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/menuButton");
		el2.click();
		MobileElement el3 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/takeText");
		el3.click();
		MobileElement el4 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/take");
		el4.click();
		MobileElement el5 = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"Выбрать\"]");
		el5.click();
		MobileElement el6 = (MobileElement) driver.findElement(By.id("com.android.documentsui:id/icon_mime"));
		el6.click();
		MobileElement el7 = (MobileElement) driver.findElement(By.id("Продолжить"));
		el7.click();
		//driver.quit();

	}
	
	
	
	
	public MobileElement searchWebElemByIdOrName(String id, String name) {
		
		try {
			return (MobileElement) driver.findElement(By.name(name));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot find "+name+" by name");
		}
		
		try {
			return (MobileElement) driver.findElement(By.id(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot find "+name+" by id");
		}
		return null;
				
	}

}
