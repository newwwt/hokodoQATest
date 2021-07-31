package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
        Log.info("Waiting for the Order Confirmation Page to load - looking for direct debit section");
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("direct_debit")));
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

    public void clickDirectDebitPaymentMethod() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("for (let e of document.querySelectorAll(\"#direct_debit\")) { e.click(); }");
        Thread.sleep(500);


    }

    public void clickAddADirectDebitLink() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("for (let e of document.querySelectorAll(\"button[data-testid='paymentSelection.addPaymentMethod']\")) { e.click(); }");
    }

    public void validateDirectDebitDetails(String accountNumber, String name) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        WebElement element =
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@data-testid='paymentInfo.accountName']")));

        String expectedName;
        if(name.length() > 14) {
            expectedName = name.substring(0,14).toLowerCase();
        } else expectedName = name.toLowerCase();
        Assert.assertEquals(findElementByDataTestId("paymentInfo.accountName").getText().toLowerCase(), expectedName);
        Assert.assertEquals(findElementByDataTestId("paymentInfo.accountDetails").getText(),
                "******" + accountNumber.substring(6));
    }

}
