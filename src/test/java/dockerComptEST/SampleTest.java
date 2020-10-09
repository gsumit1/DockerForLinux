package dockerComptEST;

import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class SampleTest {
	final static String userDir = System.getProperty("user.dir");

	@Test
	public void sample() {
		new File ("/chromedriver/chromedriver").setExecutable(true);
		System.setProperty("webdriver.chrome.driver", "/chromedriver/chromedriver");
		

		ChromeOptions options = new ChromeOptions();

		System.setProperty("webdriver.chrome.driver", "/chromedriver/chromedriver");
		options.addArguments("start-maximized"); // open Browser in maximized mode
		// options.addArguments("disable-infobars"); // disabling infobars
		options.addArguments("--disable-extensions"); // disabling extensions
		options.addArguments("--disable-gpu"); // applicable to windows os only
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.addArguments("--no-sandbox"); //
		options.addArguments("--headless"); //
		ChromeDriverService service = ChromeDriverService.createDefaultService();
		ChromeDriver driver = new ChromeDriver(service, options);

		driver.get("https://www.google.com/");
		System.out.println(driver.getTitle());

	}

}
