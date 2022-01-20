package io.railflow.demo.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverProvider {
	private static final ThreadLocal<WebDriver> CURRENT_DRIVER = new ThreadLocal<>();

	public static synchronized WebDriver getWebDriver() {
		WebDriver webDriver = CURRENT_DRIVER.get();
		if (webDriver == null) {
			WebDriverManager.chromedriver().setup();
			final ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("--start-fullscreen");
			webDriver = new ChromeDriver(options);
			CURRENT_DRIVER.set(webDriver);
		}
		return webDriver;
	}

	public static synchronized void closeWebDriver() {
		final WebDriver webDriver = CURRENT_DRIVER.get();
		if (webDriver != null) {
			CURRENT_DRIVER.remove();
			webDriver.close();
			webDriver.quit();
		}
	}
}
