package io.railflow.demo.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchTests {
	public static final String GOOGLE_URL = "https://www.google.com";
	private final WebDriver webdriver = WebDriverProvider.getWebDriver();

	@Given("^I have gone to the Google page$")
	public void goToTheGooglePage() {
		this.webdriver.get(GOOGLE_URL);
	}

	@When("^I add railflow to the search box$")
	public void addKeywordToSearchBox() {
		final WebElement searchInput = this.webdriver.findElement(By.name("q"));
		assertTrue(searchInput.isDisplayed());
		searchInput.sendKeys("railflow");
	}

	@And("^click the Search Button$")
	public void clickSearch() {
		new WebDriverWait(this.webdriver, 10).until(ExpectedConditions.elementToBeClickable(By.name("btnK")));
		this.webdriver.findElement(By.name("btnK")).click();
	}

	@Then("^railflow should be mentioned in the results$")
	public void displayResults() {
		final String text = this.webdriver.findElement(By.className("LC20lb")).getText();
		assertEquals("some wrong title", text);
	}

}
