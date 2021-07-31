import modals.DirectDebitModal;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.OrderConfirmationPage;
import util.DriverManager;
import util.UserProvider;

import java.io.IOException;
import java.util.HashMap;
public class OrderConfirmationPageTests {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() throws IOException {
        driver = DriverManager.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.tearDown();
    }

    @Test(dataProvider = "orders")
    public void validateOrderPageHasAllExpectedElements(String order, String plan, String key, String template) throws IOException {
        OrderConfirmationPage orderConfirmationPage =
                new OrderConfirmationPage(driver, order, plan, key, template);
        orderConfirmationPage.open();
        orderConfirmationPage.waitUntilLoaded();
        orderConfirmationPage.validateMandatoryElements();
    }

    @Test(dataProvider = "orders")
    public void validateUserCanAccessDirectDebitSetupPopup(String order, String plan, String key, String template) throws IOException, InterruptedException {
        OrderConfirmationPage orderConfirmationPage =
                new OrderConfirmationPage(driver, order, plan, key, template);
        orderConfirmationPage.open();
        orderConfirmationPage.waitUntilLoaded();
        orderConfirmationPage.clickDirectDebitPaymentMethod();
        orderConfirmationPage.clickAddADirectDebitLink();
        DirectDebitModal directDebitModal = new DirectDebitModal(driver);
        directDebitModal.switchToModalIframe();
        directDebitModal.waitUntilLoaded();
    }

    @Test(dataProvider = "orders")
    public void validateIndividualUserCanAddValidDirectDebit(String order, String plan, String key, String template) throws IOException, InterruptedException {
        HashMap<String, String> user = UserProvider.getIndividualUserWithValidDirectDebit();
        OrderConfirmationPage orderConfirmationPage =
                new OrderConfirmationPage(driver, order, plan, key, template);
        orderConfirmationPage.open();
        orderConfirmationPage.waitUntilLoaded();
        orderConfirmationPage.clickDirectDebitPaymentMethod();
        orderConfirmationPage.clickAddADirectDebitLink();
        DirectDebitModal directDebitModal = new DirectDebitModal(driver);
        directDebitModal.switchToModalIframe();
        directDebitModal.waitUntilLoaded();
        directDebitModal.fillFormAsIndividual(user);
        directDebitModal.submitForm();
        directDebitModal.clickSetUpThisDirectDebit();
        directDebitModal.clickCloseThisWindow();
        directDebitModal.switchOutFromIframe();
        orderConfirmationPage.validateDirectDebitDetails(user.get("accountNumber"), user.get("firstName") + " " + user.get("lastName"));
    }
    @Test(dataProvider = "orders")
    public void validateCompanyUserCanAddValidDirectDebit(String order, String plan, String key, String template) throws IOException, InterruptedException {
        HashMap<String, String> user = UserProvider.getCompanyWithValidDirectDebit();
        OrderConfirmationPage orderConfirmationPage =
                new OrderConfirmationPage(driver, order, plan, key, template);
        orderConfirmationPage.open();
        orderConfirmationPage.waitUntilLoaded();
        orderConfirmationPage.clickDirectDebitPaymentMethod();
        orderConfirmationPage.clickAddADirectDebitLink();
        DirectDebitModal directDebitModal = new DirectDebitModal(driver);
        directDebitModal.switchToModalIframe();
        directDebitModal.waitUntilLoaded();
        directDebitModal.fillFormAsCompany(user);
        directDebitModal.submitForm();
        directDebitModal.clickSetUpThisDirectDebit();
        directDebitModal.clickCloseThisWindow();
        directDebitModal.switchOutFromIframe();
        orderConfirmationPage.validateDirectDebitDetails(user.get("accountNumber"), user.get("companyName"));
    }


    @DataProvider(name = "orders")
    public static Object[][] orderParameters() {
        return new Object[][]{
                {
                        "order-4y6cccfgcYNUem5mUxYcFb",
                        "ppln-Hd8qGzxy6oQqARmUFGtRZE",
                        "nYI2aBVGWrdo-Cq4kWu0pYL16ZKrk-76jRVsdioxaGE",
                        "pptemp-H5SMNDpBpVtT7ZYqAPDntf",
                },
                {
                        "order-KBeZ9WcMQrb3RnZreyY26W",
                        "ppln-9xg2ok5V5pCByZmcSeUit7",
                        "T7vf0d6Xmkqqw6bJ7j8TXe5SuqdMnt4YdiBXsaKtdcI",
                        "pptemp-H5SMNDpBpVtT7ZYqAPDntf"
                }
        };
    }


}
