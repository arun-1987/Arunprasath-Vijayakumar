package com.selenium.ui.browser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.selenium.ui.helper.ResourceHelper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public enum DriverType implements DriverSetup {

	CHROME {
		public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourceHelper("/src/main/resources/drivers/chromedriver.exe"));
			System.setProperty("webdriver.chrome.silentOutput","true");
			HashMap<String, Object> chromePreferences = new HashMap<>();
			chromePreferences.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.merge(capabilities);
			options.setHeadless(HEADLESS);
			options.addArguments("--no-default-browser-check");
			options.addArguments("--silent");
			options.setExperimentalOption("prefs", chromePreferences);
			return new ChromeDriver(options);
		}
	},

	FIREFOX {

		public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			FirefoxOptions options = new FirefoxOptions();
			options.merge(capabilities);
			options.setHeadless(HEADLESS);
			return new FirefoxDriver(options);
		}

	},

	SAFARI {

		public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			// TODO Auto-generated method stub
			return null;
		}

	},

	API {

		public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			// TODO Auto-generated method stub
			return null;
		}

	},

	APPIUM {

		public RemoteWebDriver getWebDriverObject(DesiredCapabilities capabilities) {
			// TODO Auto-generated method stub
			capabilities.setCapability(CapabilityType.PLATFORM_NAME, "ANDROID");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Honor 6X");
			capabilities.setCapability(MobileCapabilityType.UDID, "GPH3Y17703009780");
			capabilities.setCapability("appPackage", "com.monefy.app.lite");
			capabilities.setCapability("appActivity", "com.monefy.activities.main.MainActivity_");
			AppiumDriver<MobileElement> appiumdriver = null;
			try {
				URL url = new URL("http://127.0.0.1:4723/wd/hub");
				appiumdriver = new AppiumDriver<MobileElement>(url,capabilities);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return appiumdriver;
		}

	};

	public final static boolean HEADLESS = Boolean.getBoolean("headless");

}
