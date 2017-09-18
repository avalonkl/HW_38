package core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Facebook {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver;
		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);
		String browser = null;
		String driverPath = "";
		browser = "Firefox";  // Chrome  Firefox
		
		if ((browser == "Firefox") && (System.getProperty("os.name").toUpperCase().contains("MAC")))
			driverPath = "./resources/webdrivers/mac/geckodriver.sh";
		else
			if ((browser == "Firefox") && (System.getProperty("os.name").toUpperCase().contains("WINDOWS")))
				driverPath = "./resources/webdrivers/pc/geckodriver.exe";
		    else
		    	if ((browser == "Chrome") && (System.getProperty("os.name").toUpperCase().contains("MAC")))
		    		driverPath = "./resources/webdrivers/mac/chromedriver";
				else
					if ((browser == "Chrome") && (System.getProperty("os.name").toUpperCase().contains("WINDOWS")))
						driverPath = "./resources/webdrivers/pc/geckodriver.exe";
					else throw new IllegalArgumentException("Unknown OS");
			
		switch (browser) {

		case "Firefox":
			System.setProperty("webdriver.gecko.driver", driverPath);
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			break;

		case "Chrome":
			System.setProperty("webdriver.chrome.driver", driverPath);
			System.setProperty("webdriver.chrome.silentOutput", "true");
			ChromeOptions option = new ChromeOptions();
			option.addArguments("disable-infobars"); 
			option.addArguments("--disable-notifications");
			if (System.getProperty("os.name").toUpperCase().contains("MAC"))
				option.addArguments("-start-fullscreen");
			else
				if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
					option.addArguments("--start-maximized");
			    else throw new IllegalArgumentException("Unknown OS");
			driver = new ChromeDriver(option);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			break;
			
		default: throw new IllegalArgumentException("Unknown Broweser");
		}
	
		String url = "http://facebook.com/";
		String email_address = "************";
		String password = "************";
		
		driver.get(url);

		Thread.sleep(1000); // Pause in milleseconds (1000 – 1 sec)
		
		String title = driver.getTitle();
		String copyright = driver.findElement(By.xpath("//div[2]/div/div[3]/div/span")).getText();
		
		driver.findElement(By.id("email")).sendKeys(email_address);
		driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.id("u_0_2")).click();
        
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[1]/div/div/div/div[2]/div[1]/div[1]/div/a/span")).click();

        Thread.sleep(1000);
        String friends = driver.findElement(By.xpath("//div[2]/ul/li[3]/a")).getText();
        
        Thread.sleep(1000);
        driver.findElement(By.id("userNavigationLabel")).click();
        driver.findElement(By.xpath("//div/div/div/div/div[1]/div/div/ul/li[14]/a/span/span")).click();
        // //div/div/div/div/div[1]/div/div/ul/li[14]/a/span/span
		// //*[@id="js_12"]/div/div/ul/li[14]/a/span/span
      
        Thread.sleep(1000);
		driver.quit();

		char[] chars = friends.toCharArray();
		friends = "";
		for (int i = 0; i < chars.length; i++){
			if ((int) chars[i] > 47 && (int) chars[i] < 58){
				friends += chars[i];
			}
		}
        
		System.out.println("Browser is: " + browser);
        System.out.println("Title of the page: " + title);
        System.out.println("Copyright: " + copyright);
        System.out.println("You have " + friends + " friends");
	}
}