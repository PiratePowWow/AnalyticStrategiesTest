package com.piratepowwow.testMethods;

import com.piratepowwow.rules.ScreenshotTestRule;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Jame on 5/19/2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAutomationChallenge {
    private static WebDriver driver;

    @Test
    public void aTestAutomationChallenge() throws IOException, AWTException {
//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        driver = new ChromeDriver();
        driver = new InternetExplorerDriver();
        /**
         * Step 1
         */
        driver.navigate().to("http://www.google.com");
        /**
         * Step 2
         */
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("James Powell");
        element.submit();
        /**
         * Step 3
         */
        waitForElementVisibilityCSS(driver, "div#rso div.srg div:nth-of-type(3) > div.rc > h3.r > a");
        element = driver.findElement(By.cssSelector("div#rso div.srg div:nth-of-type(3) > div.rc > h3.r > a"));
        element.click();
        /**
         * Step 4
         */
        waitForLoad(driver);
        Assert.assertTrue(driver.getTitle().equals(driver.getTitle()));
        /**
         * Step 5
         */
        driver.navigate().back();
        /**
         * Step 6
         */
        waitForElementVisibilityCSS(driver, "div#rso div.srg div:first-child > div.rc > h3 > a");
        element = driver.findElement(By.cssSelector("div#rso div.srg div:first-child > div.rc > h3 > a"));
        element.click();
        /**
         * Step 7
         */
        waitForLoad(driver);
        Assert.assertTrue("This test should fail".equals(driver.getTitle()));
    }

    /**
     * Step 8
     */
    @Rule
    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule(driver);

    private void waitForLoad(WebDriver driver) {
        new WebDriverWait(driver, 10).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    private void waitForElementVisibilityCSS(WebDriver driver, String cssSelector) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }
}
