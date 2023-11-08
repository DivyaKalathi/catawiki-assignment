package com.catawiki.helpers;


import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * This class has all the methods required to interact with the browser using selenium
 */
public class GuiHelper {
    private final WebDriver driver;
    WebDriverWait webDriverWait;

    public GuiHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage(String url) {
        driver.get(url);
    }

    public void waitForPageLoad(int secs) {
        driver.manage().timeouts().pageLoadTimeout(secs, TimeUnit.SECONDS);
    }

    public void setWebDriverWait() {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public void enterText(WebElement element, String text) {
        waitUntilVisibleToClick(element);
        element.sendKeys(text);
    }

    public void click(WebElement element) {
        try {
            waitUntil(element);
            waitUntilVisibleToClick(element);
            element.click();
        } catch (StaleElementReferenceException exception) {
            element.click();  }
    }

    public void click(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        click(element);
    }

    public void waitUntil(WebElement element) {
        setWebDriverWait();
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilVisibleToClick(WebElement element) {
        setWebDriverWait();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }


}