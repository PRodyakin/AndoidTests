package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.core.annotation.Order;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Epic;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;


@Epic("�������� ��� �������� ��������� ������ ������������")
public class RepeatedApp {

	AndroidDriver<MobileElement> driver;

	String ASSERT_ACCEPTED = "�� ��������� ����\r\n" + 
			"		������������������";
	
	
	@Test
	public void RepeatedApptest() throws MalformedURLException{
				
		appiumInit();		
		
		//caps.setCapability("automationName", "selendroid");
		// TODO Auto-generated method stub

		afterReset();

		//login

		login();

		//take
		take();

	}
	
	
	@Step("�������������")
	public void appiumInit() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("deviceName", "My Phone");
		desiredCapabilities.setCapability("udid", "emulator-5554"); //Give Device ID of your mobile phone
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "5.1");
		desiredCapabilities.setCapability("appPackage", "com.oneclickmoney.ocm");
		desiredCapabilities.setCapability("appActivity", ".PreloadActivity");
		desiredCapabilities.setCapability("noReset", "true");
		desiredCapabilities.setCapability("unicodeKeyboard", "true");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

	}
	
	
	@Step("���� ����� ������� �������")
	public void afterReset() {
		driver.resetApp();
		//after reset
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		MobileElement el23 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/next");
		el23.click();
		MobileElement el33 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/next");
		el33.click();
		el33.click();
		el33.click();
		el33.click();
		MobileElement el43 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/confirm");
		el43.click();
	}
	
	
	@Step("���� � ������� ����� �������� �����")
	public void login() {
		MobileElement el111 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/menuButton");
		el111.click();
		MobileElement el211 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/loginText");
		el211.click();


		driver.context("WEBVIEW");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.findElement(By.id("reset-email")).click();
		driver.findElement(By.id("reset-email")).sendKeys("");


		driver.findElement(By.id("login-email")).click();
		driver.findElement(By.id("login-email")).sendKeys("D@f.ru");

		driver.findElement(By.id("pass-ent")).click();
		driver.findElement(By.id("pass-ent")).sendKeys("12345678");

		driver.findElement(By.className("btn-yellow")).click();
	}
	
	
	@Step("���������� ��������� ������")
	public void take() {
		driver.context("NATIVE_APP");
		//MobileElement el2 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/menuButton");
		//el2.click();
		takeApp();
		choosePhoto();		
		chooseFromGalary();
		continueApp();
		//driver.quit();
		titleIsExpected();
	}

	@Step("������� ���� ������ ������������������")
	public void titleIsExpected() {
		MobileElement e18 = (MobileElement) driver.findElement(By.id("com.oneclickmoney.ocm:id/tvTitle"));
		assert cleanStr(e18.getText()).equals(cleanStr(ASSERT_ACCEPTED));
	}

	@Step("���������� ����������")
	public void continueApp() {
		MobileElement el7 = (MobileElement) driver.findElement(By.id("����������"));
		el7.click();
	}

	@Step("����� ����� �� �������")
	public void chooseFromGalary() {
		MobileElement el1 = (MobileElement) driver.findElementById("com.android.documentsui:id/icon_mime");
		el1.click();
	}

	@Step("������� ������ ������ ����")
	public void choosePhoto() {
		MobileElement el5 = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"�������\"]");
		el5.click();
	}

	@Step("������� ������ ���������� ������")
	public void takeApp() {
		MobileElement el3 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/takeText");
		el3.click();
		MobileElement el4 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/take");
		el4.click();
	}
	
	public static String cleanStr(String str) {
		str = str.replaceAll("[\\W]", "");
		str = str.replaceAll("[\\S]", "");
		return str;
	}

	
}
