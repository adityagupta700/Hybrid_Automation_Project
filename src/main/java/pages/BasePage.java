package pages;

import org.openqa.selenium.support.PageFactory;

import utils.DriverManager;

public class BasePage {

	BasePage() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
}
