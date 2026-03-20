package utils;

import org.openqa.selenium.WebDriver;

public class DriverManager {
	private DriverManager() {}

	private static final ThreadLocal<WebDriver> driverContainer = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return driverContainer.get();
	}

	public static void setDriver(WebDriver driverInstance) {
		driverContainer.set(driverInstance);
	}

	public static void driverUnload() {
		if(driverContainer.get() != null) {
			driverContainer.get().quit();
			driverContainer.remove();
		}
	}
}
