package utils;

import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class DriverFactory {


	public static WebDriver createDriver() {
		WebDriver driver;
		String filePath = "";
		
		try {
			
			ConfigReader.loadConfig(filePath);
			
			filePath = ConfigReader.getProperty("filePath");

			FrameworkParams.ExecutionEnv executionEnv = FrameworkParams.ExecutionEnv.valueOf(ConfigReader.getProperty("executionEnv"));
			
			FrameworkParams.Browser browser = FrameworkParams.Browser.valueOf(ConfigReader.getProperty("browser"));
			
			FrameworkParams.OS os = FrameworkParams.OS.valueOf(ConfigReader.getProperty("os"));
			
			switch(executionEnv) {
			case REMOTE:
				String gridUrl = ConfigReader.getProperty("gridUrl");
				
				MutableCapabilities capabilities = new MutableCapabilities();

				switch(browser) {

				case CHROME: 
					ChromeOptions chromeOptions = new ChromeOptions();

					//browser options
					chromeOptions.setCapability("platformName", os);
					chromeOptions.merge(capabilities);
					driver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
					break;

				case EDGE:
					EdgeOptions edgeOptions = new EdgeOptions();

					//browser options
					edgeOptions.setCapability("platformName", os);
					edgeOptions.merge(capabilities);
					driver = new RemoteWebDriver(new URL(gridUrl), edgeOptions);
					break;

				case FIREFOX:
					FirefoxOptions firefoxOptions = new FirefoxOptions();

					//browser options
					firefoxOptions.setCapability("platformName", os);
					firefoxOptions.merge(capabilities);
					driver = new RemoteWebDriver(new URL(gridUrl), firefoxOptions);
					break;

				case SAFARI:
					SafariOptions safariOptions = new SafariOptions();

					//browser options
					safariOptions.setCapability("platformName", os);
					safariOptions.merge(capabilities);
					driver = new RemoteWebDriver(new URL(gridUrl), safariOptions);
					break;

				default:
					throw new RuntimeException("Unsupported browser: " + browser);
				}

				return driver;

			case LOCAL : 

				switch(browser) {
				case CHROME:
					driver = new ChromeDriver();
					break;
				case EDGE:
					driver = new EdgeDriver();
					break;
				case FIREFOX:
					driver = new FirefoxDriver();
					break;
				case SAFARI:
					driver = new SafariDriver();
					break;
				default:
					throw new RuntimeException("Unsupported browser: " + browser);
				}		

				return driver;

			default :
				throw new RuntimeException("Wrong Environment");
			}

		}catch(Exception e) {
			throw new RuntimeException("Driver creation failed", e);
		}



	}
}
