package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.io.Files;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;


@Epic("Автотест для проверки повторной заявки пользователя")
public class RepeatedApp {

	AndroidDriver<MobileElement> driver;

	String ASSERT_ACCEPTED = "Мы оцениваем вашу\r\n" + 
			"		платежеспособность";


	@Test
	public void RepeatedApptest() throws MalformedURLException{

		appiumInit();		



		afterReset();

		//login

		login();

		//take
		take();

	}


	@Step("Инициализация")
	private void appiumInit() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("deviceName", "My Phone");
		desiredCapabilities.setCapability("udid", "emulator-5554"); //Give Device ID of your mobile phone
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "5.1");
		desiredCapabilities.setCapability("appPackage", "com.oneclickmoney.ocm");
		desiredCapabilities.setCapability("appActivity", ".PreloadActivity");
		desiredCapabilities.setCapability("noReset", "true");
		desiredCapabilities.setCapability("unicodeKeyboard", "true");
		desiredCapabilities.setCapability("automationName", "UiAutomator2");
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

	}


	@Step("Шаги после первого запуска")
	private void afterReset() {
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


	@Step("Вход в систему через почтовый адрес")
	private void login() {
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


	@Step("Оформление повторной заявки")
	private void take() {
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

	@Step("Нажатие кнопки оформления заявки")
	private void takeApp() {
		MobileElement el3 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/takeText");
		el3.click();
		MobileElement el4 = (MobileElement) driver.findElementById("com.oneclickmoney.ocm:id/take");
		el4.click();
	}

	@Step("Нажатие кнопки выбора фото")
	private void choosePhoto() {
		MobileElement el5 = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"Выбрать\"]");
		el5.click();
	}

	@Step("Выбор файла из галереи")
	private void chooseFromGalary() {


		try {
			MobileElement el1 = (MobileElement)  driver.findElementById("com.android.documentsui:id/date");;
			el1.click();
		} catch (Exception e) {

			try {
				MobileElement el1 = (MobileElement) driver.findElementById("com.android.documentsui:id/icon_mime");
				el1.click();
			} catch (Exception e1) {
				MobileElement el1 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.GridView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ImageView\r\n");
				el1.click();


			}
		} 	

	}

	@Step("Продолжить оформление")
	private void continueApp() {
		MobileElement el7 = (MobileElement) driver.findElementByXPath("//android.widget.Button[@content-desc=\"Продолжить\"]");
		el7.click();
	}

	@Step("Открыто окно оценки платежеспособности")
	private void titleIsExpected() {
		MobileElement e18 = (MobileElement) driver.findElement(By.id("com.oneclickmoney.ocm:id/tvTitle"));
		assert cleanStr(e18.getText()).equals(cleanStr(ASSERT_ACCEPTED));
	}

	private static String cleanStr(String str) {
		str = str.replaceAll("[\\W]", "");
		str = str.replaceAll("[\\S]", "");
		return str;
	}
	@After
	public void addAttachment() {
		Allure.addAttachment("Logs", "");


		try {
			File initialFile = new File("path-to-my-attachment-contnet");
			InputStream is = new FileInputStream(initialFile);
			Allure.addAttachment("My attachment", is);
		}catch (Exception e1) {
		}
	}
}