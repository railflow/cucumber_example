package io.railflow.demo.cucumber;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PizzaTests {

	private final WebDriver webDriver = WebDriverProvider.getWebDriver();
	private List<WebElement> results;

	
	@Given("^user navigates to http://google\\.com$")
	public void user_navigates_to_google() {
		this.webDriver.navigate().to(SearchTests.GOOGLE_URL);
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
		final WebElement element = this.webDriver.findElement(By.name("q"));
		element.sendKeys(query);
		element.submit();
		new WebDriverWait(this.webDriver, 1)
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("search")));
		return this.webDriver.findElements(By.xpath("//*[@id='rso']//*[@class='g']//a"));
	}
}
