import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.DriverProvider;

import java.io.IOException;

public class OrderPageTests {

    private WebDriver driver;

    @BeforeClass
    public void setUp() throws IOException {
        driver = DriverProvider.getDriver();
    }

    @Test
    public void firstTest() {
        driver.get("");
    }
}
