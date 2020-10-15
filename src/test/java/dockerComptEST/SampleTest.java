package dockerComptEST;

import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class SampleTest {
	
	@Test
	public void sample() {
		
		try {
			System.out.println("Hello I am sumit");
			new File("/chromedriver/chromedriver.exe").setExecutable(true);
			System.out.println("1st block");
		} catch (Exception e) {

			System.out.println("1st error");

			try {
				new File("/chromedriver/chromedriver").setExecutable(true);
				System.out.println("2st block");
			} catch (Exception e1) {

				System.out.println("2nd error");

			}

		} 
		System.setProperty("webdriver.chrome.driver", "/chromedriver/chromedriver");
		ChromeOptions options = new ChromeOptions();
	    options.addArguments("--incognito");
	    options.addArguments("--headless");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--start-maximixed");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
	    
		ChromeDriver driver = new ChromeDriver(options);

		driver.get("https://www.lumen.com/en-us/home.html");

		/*
		 * 
		 * ChromeOptions options = new ChromeOptions();
		 * //System.setProperty("webdriver.chrome.driver",
		 * "/chromedriver/chromedriver"); options.addArguments("start-maximized"); //
		 * open Browser in maximized mode // options.addArguments("disable-infobars");
		 * // disabling infobars options.addArguments("--disable-extensions"); //
		 * disabling extensions options.addArguments("--disable-dev-shm-usage"); //
		 * overcome limited resource problems options.addArguments("--no-sandbox"); //
		 * options.addArguments("--headless"); // ChromeDriverService service =
		 * ChromeDriverService.createDefaultService(); ChromeDriver driver = new
		 * ChromeDriver(service, options);
		 */

		
		System.out.println(driver.getTitle());

	}

}
