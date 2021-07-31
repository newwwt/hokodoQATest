package pages;

import org.openqa.selenium.WebDriver;
import util.ConfigHelper;
import util.Log;

import java.io.IOException;

public class OrderConfirmationPage extends BasePage {

    private final String url;

    public OrderConfirmationPage(WebDriver driver,
                                 String order,
                                 String plan,
                                 String key,
                                 String template) throws IOException {
        super(driver);
        this.url = String.format("%s?design=default&order=%s&plan=%s&key=%s&template=%s",
                ConfigHelper.retrieveProperty("baseUrl"), order, plan, key, template);
    }

    @Override
    public void open() {
        getDriver().navigate().to(this.url);
    }

    @Override
    public void waitUntilLoaded() {
        Log.info("Waiting for the Order Confirmation Page to load - looking for order summary");
        findElementByDataTestId("orderSummary.summary");
    }

    @Override
    public void verifyLoaded() {

    }

    @Override
    public void validateMandatoryElements() {
        Log.info("Validating mandatory elements.");
        findElementByDataTestId("contactDetails");
        findElementByDataTestId("contactInformation.details");
        findElementByDataTestId("paymentTimeline");
        findElementByDataTestId("paymentAuthorisation");
        findElementByDataTestId("paymentMethod.direct_debit.label");
        findElementByDataTestId("paymentMethod.invoice.label");
        findElementByDataTestId("paymentMethod.card.label");
        findElementByDataTestId("orderSummary");
        findElementByDataTestId("orderSummary.products");
        findElementByDataTestId("orderSummary.summary");
    }
}
