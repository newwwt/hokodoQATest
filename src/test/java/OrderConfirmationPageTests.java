import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import pages.OrderConfirmationPage;
import util.DriverProvider;
import util.Log;

import java.io.IOException;

public class OrderConfirmationPageTests {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() throws IOException {
        driver = DriverProvider.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverProvider.tearDown();
    }

    @Test(dataProvider = "orders")
    public void validateOrderPageHasAllExpectedElements(String order, String plan, String key, String template) throws IOException {
        OrderConfirmationPage orderConfirmationPage =
                new OrderConfirmationPage(driver, order, plan, key, template);
        orderConfirmationPage.open();
        orderConfirmationPage.waitUntilLoaded();
    }



    @DataProvider(name = "orders")
    public static Object[][] orderParameters() {
        return new Object[][]{
                {
                        "order-4y6cccfgcYNUem5mUxYcFb",
                        "ppln-Hd8qGzxy6oQqARmUFGtRZE",
                        "nYI2aBVGWrdo-Cq4kWu0pYL16ZKrk-76jRVsdioxaGE",
                        "pptemp-H5SMNDpBpVtT7ZYqAPDntf"
                },
                {
                        "order-KBeZ9WcMQrb3RnZreyY26W",
                        "ppln-9xg2ok5V5pCByZmcSeUit7",
                        "T7vf0d6Xmkqqw6bJ7j8TXe5SuqdMnt4YdiBXsaKtdcI",
                        "pptemp-H5SMNDpBpVtT7ZYqAPDntf"
                }};
    }
}
