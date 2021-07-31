package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public abstract class BasePage {

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver getDriver() {
        return driver;
    }


    public abstract void open();

    public abstract void waitUntilLoaded();

    public abstract void verifyLoaded();

    protected WebElement findElementByDataTestId(String dataTestId) {
        return driver.findElement(By.cssSelector(String.format("[data-testid='%s']", dataTestId)));
    }

    protected WebElement findElementByXPath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    protected WebElement findElementByCssSelector(String selector) {
        return driver.findElement(By.cssSelector(selector));
    }

    protected void clickElement(WebElement element) {
        element.click();
    }

    protected void sendKeys(WebElement element, String keys) {
        element.sendKeys(keys);
    }

    protected void assertElementDisplayed(WebElement element, String message) {
        Assert.assertTrue(element.isDisplayed(), message);
    }

    public abstract void validateMandatoryElements();
}
