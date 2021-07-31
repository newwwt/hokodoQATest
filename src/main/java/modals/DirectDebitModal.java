package modals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import util.Log;

import java.util.HashMap;

public class DirectDebitModal extends BasePage {
    public DirectDebitModal(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {

    }

    @Override
    public void waitUntilLoaded() {
        Log.info("Waiting for the Direct Debit modal to be loaded - looking for Sort Code field");
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        WebElement element =
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='branchCode']")));
    }

    @Override
    public void verifyLoaded() {

    }

    @Override
    public void validateMandatoryElements() {

    }

    public void switchToModalIframe() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("iframeId")));
        getDriver().switchTo().frame("iframeId");
    }

    public void switchToCompany() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("for (let e of document.querySelectorAll(\"input[value='company']\")) { e.click(); }");
    }

    public void enterFirstName(String name) {
        findElementByCustomAttribute("name", "givenName").sendKeys(name);
    }

    public void enterLastName(String lastName) {
        findElementByCustomAttribute("name", "familyName").sendKeys(lastName);
    }

    public void enterCompanyName(String companyName) {
        findElementByCustomAttribute("name", "companyName").sendKeys(companyName);
    }

    public void enterSortCode(String sortCode) {
        findElementByCustomAttribute("name", "branchCode").sendKeys(sortCode);
    }

    public void enterAccountNumber(String accountNumber) {
        findElementByCustomAttribute("name", "accountNumber").sendKeys(accountNumber);
    }

    public void enterAddressLine1(String addressLine1) {
        findElementByCustomAttribute("name", "addressLine1").sendKeys(addressLine1);
    }

    public void enterCity(String city) {
        findElementByCustomAttribute("name", "city").sendKeys(city);
    }

    public void enterPostcode(String postcode) {
        findElementByCustomAttribute("name", "postalCode").sendKeys(postcode);
    }

    public void enterEmailAddress(String email) {
        findElementByCustomAttribute("name", "email").sendKeys(email);
    }

    public void fillFormAsIndividual(HashMap<String, String> user) {
        enterFirstName(user.get("firstName"));
        enterLastName(user.get("lastName"));
        enterSortCode(user.get("sortCode"));
        enterAccountNumber(user.get("accountNumber"));
        enterAddressLine1(user.get("billingAddressLine1"));
        enterCity(user.get("city"));
        enterPostcode(user.get("postcode"));
        enterEmailAddress(user.get("email"));
    }

    public void fillFormAsCompany(HashMap<String, String> user) {
        switchToCompany();
        enterCompanyName(user.get("companyName"));
        enterSortCode(user.get("sortCode"));
        enterAccountNumber(user.get("accountNumber"));
        enterAddressLine1(user.get("billingAddressLine1"));
        enterCity(user.get("city"));
        enterPostcode(user.get("postcode"));
        enterEmailAddress(user.get("email"));
    }

    public void submitForm() {
        findElementByCustomAttribute("type", "submit").click();
    }

    public void clickSetUpThisDirectDebit() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        WebElement element =
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button']/span[.='Set up this Direct Debit']")));
//        findElementByXPath("//button[@type='button']/span[.='Set up this Direct Debit']").click();
        element.click();

    }

    public void clickCloseThisWindow() {
//        findElementByXPath("//button[@type='button']/span[.='Close this window']").click();
        WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        WebElement element =
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button']/span[.='Close this window']")));
        element.click();
    }

    public void switchOutFromIframe() {
        getDriver().switchTo().defaultContent();
    }



}
