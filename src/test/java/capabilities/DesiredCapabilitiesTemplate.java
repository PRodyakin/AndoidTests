package capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesTemplate extends DesiredCapabilities {
 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DesiredCapabilitiesTemplate(){
		this.setCapability("deviceName", "My Phone");
		this.setCapability("udid", "emulator-5554"); //Give Device ID of your mobile phone
		this.setCapability("platformName", "Android");
		this.setCapability("platformVersion", "6.0");
		this.setCapability("appPackage", "com.oneclickmoney.ocm");
		this.setCapability("appActivity", ".PreloadActivity");
		this.setCapability("noReset", "true");
		this.setCapability("unicodeKeyboard", "true");
		//caps.setCapability("automationName", "selendroid");
	}
	

}
