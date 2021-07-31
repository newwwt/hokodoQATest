package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.util.Properties;

public class DriverProvider {

    private static WebDriver driver;

    public static WebDriver getDriver() throws IOException {
        if(driver == null) {
            String targetBrowser = retrieveProperty("target.browser");
            if(targetBrowser.equals("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();

            } else if (targetBrowser.equals("safari")) {
                if(System.getProperty("os.name") != "MacOS") {
                    throw new IllegalArgumentException("Safari tests can only be executed on MacOS. Please choose another browser");
                }
                driver = new SafariDriver();
            } else throw new IllegalArgumentException("Unsupported target browser. Please use chrome or safari.");
        }
        return driver;
    }

    public static String retrieveProperty(String propertyName) throws IOException {
        final Properties properties = new Properties();
        properties.load(DriverProvider.class.getClassLoader().getResourceAsStream("project.properties"));
        return properties.getProperty(propertyName);
    }

}
