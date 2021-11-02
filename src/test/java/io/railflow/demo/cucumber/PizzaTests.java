package io.railflow.demo.cucumber;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PizzaTests {
	private static final String GOOGLE_URL = "http://google.com";
	private WebDriver driver;
	private List<WebElement> results;

	@Before
	public void beforeScenario() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		final ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-fullscreen");
		this.driver = new ChromeDriver(options);
	}
	
	@Given("^user navigates to http://google\\.com$")
	public void user_navigates_to_google() {
		this.driver.navigate().to(GOOGLE_URL);
	}

	@When("^the user enters \"([^\"]*)\" and hits Enter$")
	public void user_enters_query(final String query) {
		this.results = getGoogleResults(query);
	}

	@Then("^number of results more than zero$")
	public void number_of_results_more_than_zero() {
		Assert.assertTrue("There are no results for pizza", this.results.size() > 0);
	}

	@Then("^no results are shown$")
	public void no_results_are_shown() {
		if (this.results.size() > 0) {
			throw new RuntimeException("Liar, there are no bad pizzas!");
		}
	}

	private List<WebElement> getGoogleResults(final String query) {
		final WebElement element = this.driver.findElement(By.name("q"));
		element.sendKeys(query);
		element.submit();
		new WebDriverWait(this.driver, 1)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("search")));
		return this.driver.findElements(By.xpath("//*[@id='rso']//*[@class='g']//a"));
	}

	@After
	public void afterScenario() {
		if (this.driver != null) {
			this.driver.close();
			this.driver.quit();
		}
	}
	
	@AfterStep
	public void afterStep(final Scenario scenario)
	{
		if (scenario.isFailed()) {
			scenario.attach(((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES), "image/png","failure.png");
		}
	}
}
