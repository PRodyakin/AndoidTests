package test.DS;

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
import org.openqa.selenium.interactions.touch.TouchActions;
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

	@Test
	public void NewApptest() throws IOException, Exception{
		appiumInit();		
		afterReset();
	}

	@Step("Инициализация")
	private void appiumInit() throws MalformedURLException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("deviceName", "My Phone");
		desiredCapabilities.setCapability("udid", "emulator-5554"); //Give Device ID of your mobile phone
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "5.1");
		desiredCapabilities.setCapability("appPackage", "ru.dc");
		desiredCapabilities.setCapability("appActivity", ".ui.activities.MainActivity.MainActivity");
		desiredCapabilities.setCapability("noReset", "true");
		desiredCapabilities.setCapability("unicodeKeyboard", "true");
		desiredCapabilities.setCapability("automationName", "UiAutomator2");
		desiredCapabilities.setCapability("newCommandTimeout", 0);
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}

	@Step("Шаги после первого запуска")
	private void afterReset() {		
		
		MobileElement el1 = (MobileElement) driver.findElementById("ru.dc:id/tvWelcomeSkip");
		el1.click();
		MobileElement el2 = (MobileElement) driver.findElementById("ru.dc:id/btnAccept");
		el2.click();
		takeApp(el2);
		step1();
		step2();

	}

	public void step2() {
		MobileElement el13 = (MobileElement) driver.findElementById("ru.dc:id/edittext_passport");
		el13.sendKeys("64441220564");
		MobileElement el14 = (MobileElement) driver.findElementById("ru.dc:id/edittext_passport_issue");
		el14.sendKeys("Тест");
		MobileElement el16 = (MobileElement) driver.findElementById("ru.dc:id/edittext_passport_code");
		el16.sendKeys("141321314");
		MobileElement el17 = (MobileElement) driver.findElementById("ru.dc:id/edittext_passport_date");
		el17.sendKeys("15062014");
		MobileElement el18 = (MobileElement) driver.findElementById("ru.dc:id/edittext_birthplace");
		el18.sendKeys("Тест");
		MobileElement el19 = (MobileElement) driver.findElementById("ru.dc:id/edittext_slid");
		el19.sendKeys("Тест");
		MobileElement el111 = (MobileElement) driver.findElementById("ru.dc:id/textview_region");
		el111.sendKeys("Томская обл");
		scroll(null);
		MobileElement el211 = (MobileElement) driver.findElementById("ru.dc:id/textview_area");
		el211.sendKeys("Верхнекетский р-н");
		MobileElement el311 = (MobileElement) driver.findElementById("ru.dc:id/textview_city");
		el311.sendKeys("поселок Центральный");
		MobileElement el411 = (MobileElement) driver.findElementById("ru.dc:id/textview_street");
		el411.sendKeys("ул Советская");
		MobileElement el511 = (MobileElement) driver.findElementById("ru.dc:id/textview_house");
		el511.sendKeys("д 3");
		MobileElement el611 = (MobileElement) driver.findElementById("ru.dc:id/edittext_house_body");
		el611.sendKeys("1");
		MobileElement el711 = (MobileElement) driver.findElementById("ru.dc:id/edittext_flat");
		el711.sendKeys("1");
		MobileElement el39 = (MobileElement) driver.findElementById("ru.dc:id/edittext_date_begin");
		el39.sendKeys("15062000");
		scroll(el711);
		MobileElement el38 = (MobileElement) driver.findElementById("ru.dc:id/checkbox_live_by_registration");
		el38.click();
		scroll(null);
		MobileElement ebl1 = (MobileElement) driver.findElementById("ru.dc:id/button_step3");
		ebl1.click();
	}

	public void step1() {
		MobileElement el4 = (MobileElement) driver.findElementById("ru.dc:id/etStepOneLastName");
		el4.sendKeys("Автотест");
		MobileElement el5 = (MobileElement) driver.findElementById("ru.dc:id/etStepOneFirstName");
		el5.sendKeys("Автотест");
		MobileElement el6 = (MobileElement) driver.findElementById("ru.dc:id/etStepOneMiddleName");
		el6.sendKeys("Автотест");
		MobileElement el7 = (MobileElement) driver.findElementById("ru.dc:id/etStepOneBirthday");
		el7.sendKeys("15061994");
		MobileElement el8 = (MobileElement) driver.findElementById("ru.dc:id/metStepOnePhone");
		scroll(el8);
		el8.sendKeys("9518266543");
		MobileElement el9 = (MobileElement) driver.findElementById("ru.dc:id/etStepOneEmail");
		el9.sendKeys("example@test.ru");
		el9.click();
		MobileElement el10 = (MobileElement) driver.findElementById("ru.dc:id/cbStepOneConsent");
		scroll(el10);
		el10.click();
		MobileElement el11 = (MobileElement) driver.findElementById("ru.dc:id/cbStepOneSubscribe");
		el11.click();
		MobileElement el123 = (MobileElement) driver.findElementById("ru.dc:id/btnToStep2");
		el123.click();
	}

	public void takeApp(MobileElement el2) {
		MobileElement el12 = (MobileElement) driver.findElementById("ru.dc:id/sbCalcSum");
		el12.sendKeys("4");
		scroll(el2);
		MobileElement el3 = (MobileElement) driver.findElementById("ru.dc:id/btnCalcAccept");
		el3.click();
	}

	public void scroll(MobileElement el) {
		PointOption pointOptionStart = new PointOption().withCoordinates(1014, 1757); 
		PointOption pointOptionEnd = new PointOption().withCoordinates(1014, 266);
		new TouchAction(driver)
		  .press(pointOptionStart)
		  .moveTo(pointOptionEnd)
		  .release()
		  .perform();
	}

}
