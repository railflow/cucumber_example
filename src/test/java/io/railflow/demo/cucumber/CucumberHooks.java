package io.railflow.demo.cucumber;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class CucumberHooks {

	@After
	public void afterScenario(final Scenario scenario) {
		WebDriverProvider.closeWebDriver();
	}

	@AfterStep
	public void afterStep(final Scenario scenario) {
		if (scenario.isFailed()) {
			scenario.attach(((TakesScreenshot) WebDriverProvider.getWebDriver()).getScreenshotAs(OutputType.BYTES), "image/png", "failure.png");
		}
	}
}
