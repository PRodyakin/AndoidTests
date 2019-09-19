package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import parsers.ParseHTMLUnit;

public class NewApp {


	AndroidDriver<MobileElement> driver;

	String ASSERT_ACCEPTED = "Мы оцениваем вашу\r\n" + 
			"		платежеспособность";


	@Test
	public void NewApptest() throws IOException, Exception{


		appiumInit();		
		afterReset();
		try {
			take();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//			ParseHTMLUnit parser = new ParseHTMLUnit();
//
//			(parser).deleteUser();

		}

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


	@Step("Оформление новой заявки")
	private void take() {
		//driver.context("NATIVE_APP");
		driver.findElementById("com.oneclickmoney.ocm:id/take").click();

		step1();
		step2();
		step3();
		step4();
	}

	@Step("Шаг 1")
	private void step1() {
		//1
		driver.context("WEBVIEW");
		driver.findElement(By.id("usermodel-last_name")).sendKeys("Автотест");

		driver.findElement(By.id("usermodel-first_name")).sendKeys("Автотест");

		driver.findElement(By.id("usermodel-middle_name")).sendKeys("Автотест");

		//+ it looks strange, but it works
		driver.findElement(By.id("usermodel-birth_date")).click();

		setNumberWebView("usermodel-birth_date", "15061990", "15.06.1990");

		//		driver.findElement(By.id("usermodel-birth_date")).sendKeys("15 ");
		//		driver.findElement(By.id("usermodel-birth_date")).sendKeys("06 ");
		//		driver.findElement(By.id("usermodel-birth_date")).sendKeys("19 ");
		//		driver.findElement(By.id("usermodel-birth_date")).sendKeys("90 ");
		//-
		setNumberWebView("usermodel-mobile_phone", "9518266544","+7 (951) 826-65-44");
		//		driver.findElement(By.id("usermodel-mobile_phone")).sendKeys("95 ");
		//		driver.findElement(By.id("usermodel-mobile_phone")).sendKeys("18 ");
		//		driver.findElement(By.id("usermodel-mobile_phone")).sendKeys("26 ");
		//		driver.findElement(By.id("usermodel-mobile_phone")).sendKeys("65 ");
		//		driver.findElement(By.id("usermodel-mobile_phone")).sendKeys("44 ");

		driver.findElement(By.id("usermodel-email")).sendKeys("d@f.ru");

		driver.findElement(By.id("usermodel-password")).sendKeys("12345678");

		driver.findElement(By.id("usermodel-confirm_password")).sendKeys("12345678");

		driver.findElement(By.id("next-step-button")).click();

	}

	public void setNumberWebView(String id, String number, String compare) {

		String[] array= number.split("(?<=\\G.{2})");

		WebElement elem = driver.findElement(By.id(id));
		for (String string : array) {
			elem.sendKeys(string + " ");
		}

		//		if(! elem.getText().equals(compare)) {
		//			System.out.println(elem.getText());
		//			elem.clear();
		//			setNumberWebView(id, number, compare);
		//		}

	}

	@Step("Шаг 2")
	private void step2() {

		//Регистрация
		driver.context("WEBVIEW");
		//driver.findElement(By.id("usermodel-passport_number")).sendKeys("16 51 49 84 65");
		setNumberWebView("usermodel-passport_number", "6554641651", "6554-641651");

		driver.findElement(By.id("usermodel-passport_issuer_name")).sendKeys("Автотест");

		setNumberWebView("usermodel-passport_date","15062005", "15.06.2005");

		driver.findElement(By.id("usermodel-passport_birth_place")).sendKeys("Автотест");


		WebElement elem = driver.findElement(By.id("usermodel-region"));
		elem.sendKeys("г Москва ");


		driver.findElement(By.cssSelector("div[data-index=\"0\"]")).click();


		driver.findElement(By.id("usermodel-city")).sendKeys("поселок Внуково");

		driver.findElement(By.cssSelector("div[data-index=\"0\"]")).click();

		driver.findElement(By.id("usermodel-street")).sendKeys("ул Рейсовая 3-я");
		driver.findElement(By.cssSelector("div[data-index=\"0\"]")).click();

		driver.findElement(By.id("usermodel-house_number")).sendKeys("д 3 ");
		driver.findElement(By.cssSelector("div[data-index=\"0\"]")).click();

		driver.findElement(By.id("usermodel-house_building")).sendKeys("2");
		driver.findElement(By.id("usermodel-flat")).sendKeys("3");

		//driver.findElement(By.id("usermodel-last_name")).sendKeys("Автотест");

		//Прописка
		//driver.findElement(By.id("usermodel-legal_region")).sendKeys("г Москва");
		driver.findElement(By.id("usermodel-legal_region")).sendKeys("г Москва");

		driver.findElement(By.id("usermodel-legal_city")).sendKeys("г Москва");

		driver.findElement(By.id("usermodel-legal_street")).sendKeys("Варшавское шоссе");

		driver.findElement(By.id("usermodel-legal_house_number")).sendKeys("д 3 ");

		driver.findElement(By.id("usermodel-legal_house_building")).sendKeys("2");
		driver.findElement(By.id("usermodel-legal_flat")).sendKeys("3");

	}

	@Step("Шаг 3")
	private void step3() {

		//Регистрация
		driver.context("WEBVIEW");
		//driver.findElement(By.id("usermodel-passport_number")).sendKeys("16 51 49 84 65");


		driver.findElement(By.id("usermodel-skype")).sendKeys("Autotest");

		setNumberWebView("usermodel-cohabitor_phone", "9518266545", "+7 (951) 826-65-45");

		driver.findElement(By.id("usermodel-family_income")).sendKeys("90000");

		driver.findElement(By.id("usermodel-work_place_address")).sendKeys("Автотест");

		setNumberWebView("usermodel-work_phone", "9518266546","+7 (951) 826-65-46");

		driver.findElement(By.linkText("https://static-rc.dev.oneclickmoney.ru/images/ps/contacntNg.jpg")).click();//"Контакт"

		driver.findElement(By.id("select2-contactngform-city-container")).sendKeys("ПЕРВОМАЙСКОЕ (Новая Москва)");	

		driver.findElement(By.id("select2-contactngform-address-container")).sendKeys("УЛ. ЦЕНТРАЛЬНАЯ, 90 | ГЕОБАНК, ДО ПТИЧНОЕ");	

	}

	@Step("Шаг 4")
	private void step4() {

		//Регистрация
		driver.context("WEBVIEW");

		driver.findElementByXPath("//android.view.View[@content-desc=\"Выбрать\"]").click();
		driver.findElementById("com.android.documentsui:id/icon_mime").click();
		//By.cssSelector(cssSelector)


	}






	public void name() {


		//el1.click();

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
