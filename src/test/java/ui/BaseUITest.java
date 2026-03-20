package ui;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import utils.ConfigReader;
import utils.DriverFactory;
import utils.DriverManager;

public class BaseUITest {

	@BeforeClass
	public void setup()  {
		try {
			DriverManager.setDriver(DriverFactory.createDriver());
			String appUrl = ConfigReader.getProperty("appUrl");

			DriverManager.getDriver().get(appUrl);
			DriverManager.getDriver().manage().window().maximize();


		}catch(Exception e) {
			throw new RuntimeException("Driver setup failed", e);
		}
	}

	@AfterClass
	public void teardown() {
		DriverManager.driverUnload();
	}

}
