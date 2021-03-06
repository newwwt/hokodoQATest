package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() throws IOException {
        if (driver == null) {
            String targetBrowser = ConfigHelper.retrieveProperty("targetBrowser");
            if (targetBrowser.equals("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();

            } else if (targetBrowser.equals("safari")) {
                if (!System.getProperty("os.name").equals("Mac OS X")) {
                    throw new IllegalArgumentException("Safari tests can only be executed on MacOS. Please choose another browser");
                }
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setUseTechnologyPreview(true);
                driver = new SafariDriver();
            } else throw new IllegalArgumentException("Unsupported target browser. Please use chrome or safari.");
        }
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void tearDown() {
        driver.quit();
        driver = null;
    }

}
