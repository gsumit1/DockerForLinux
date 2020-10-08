package dockerComptEST;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SampleTest {
	final static String userDir = System.getProperty("user.dir");

	@Test
	public void sample() {
		 System.setProperty("webdriver.chrome.driver", userDir + "/chromedriver");
		 WebDriver driver=new ChromeDriver();
		 driver.get("https://www.google.com/");
		 System.out.println(driver.getTitle());
			
		
	}

}
