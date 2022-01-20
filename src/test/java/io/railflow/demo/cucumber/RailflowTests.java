package io.railflow.demo.cucumber;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * @author Sergey Oplavin
 */
public class RailflowTests {

	private final WebDriver webdriver = WebDriverProvider.getWebDriver();

	@Given("^user navigates to https://railflow\\.io$")
	public void userNavigatesToRailflow() {
		this.webdriver.get("https://railflow.io");
	}

	@Then("Register link exists on the first page")
	public void registerLinkExistsOnTheFirstPage() {

	}

	@When("user clicks on Register link")
	public void userClicksOnLink() {

	}

	@Then("the register form is shown")
	public void theRegisterFormIsShown() {
	}

}
