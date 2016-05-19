package com.piratepowwow;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Jame on 5/19/2016.
 */
public class TestAutomationChallenge {

    @Test
    public void startWebDriver() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
        WebDriver driver = new InternetExplorerDriver();
        driver.navigate().to("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("James Powell");
        element.submit();
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#rso div.srg div:nth-of-type(3) > div.rc > h3.r > a")));
        element = driver.findElement(By.cssSelector("div#rso div.srg div:nth-of-type(3) > div.rc > h3.r > a"));
        element.click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("james powell");
            }
        });
        Assert.assertTrue(driver.getTitle().equals(driver.getTitle()));
        driver.navigate().back();
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#rso div.srg div:first-child > div.rc > h3 > a")));
        element = driver.findElement(By.cssSelector("div#rso div.srg div:first-child > div.rc > h3 > a"));
        element.click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("home | james lawrence");
            }
        });
        Assert.assertTrue("This test should fail".equals(driver.getTitle()));
    }
}
