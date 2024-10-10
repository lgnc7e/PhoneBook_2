package com.phonebook.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class BaseHelper {
    Logger logger = LoggerFactory.getLogger(BaseHelper.class);
    protected WebDriver driver;
    protected WebDriverWait wait;


    public BaseHelper(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }


    public boolean isElementPresent(By locator) {
        logger.info("Check [" + locator + "] on a page");
        return driver.findElements(locator).size() > 0;
    }

    //!!!!
    public void click(By locator) {
        driver.findElement(locator).click();
        //logger.error("[{}] and {} is pressed", locator, locator); //другой синтаксис
        //logger.info("[" + locator + "] is pressed");
        //logger.warn("[" + locator + "] is pressed");
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public boolean isAlertPresent() {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            if(alert == null){
                return false;
            } else {
                logger.warn("Alert has text: [" + driver.switchTo().alert().getText() + "]");
                //driver.switchTo().alert().accept();
                alert.accept();
                return true;
            }
    }

    public String alertTextPresent() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    public void moveTo(int x, int y) { //двигать экран по осям
        new Actions(driver).moveByOffset(x, y).perform();
    }

    public String takeScreenshot() {
        File screenshot = new File("src/test_screenshots/screen-"+ System.currentTimeMillis() + ".png");
        try {
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(tmp.toPath(), screenshot.toPath());
        } catch (IOException e) {
            logger.error("Failed to save screenshot", e);
            throw new RuntimeException(e);
        }
        //logger.info("Screenshot saved to path: ["+ screenshotPath + "]");

        return screenshot.getAbsolutePath();
    }
}
