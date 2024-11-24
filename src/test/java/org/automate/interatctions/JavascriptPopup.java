package org.automate.interatctions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavascriptPopup {
    @Test
    public void Popup_Alert_Test() throws Exception {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-search-engine-choice-screen");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.manage().window().maximize();
        //Navigate to Url
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        //This is added for seeing the page > remove it
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Click for JS Alert']")));
        var button = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        button.click();

        org.openqa.selenium.Alert alert = driver.switchTo().alert();
        //This is added for seeing the page > remove it
        //Store the alert text in a variable and verify it
        String text = alert.getText();
        System.out.println( text);
        Thread.sleep(2000);
        assertEquals(text, "I am a JS Alert");
        //Press the OK button
        alert.accept();
        System.out.println("Alert Clicked");

        driver.quit();
    }

    @Test
    public void Popup_Confirm_Test() throws Exception {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-search-engine-choice-screen");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.manage().window().maximize();
        //Navigate to Url
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        //This is added for seeing the page > remove it
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Click for JS Confirm']")));
        var button = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
        button.click();

        org.openqa.selenium.Alert alert = driver.switchTo().alert();
        //This is added for seeing the page > remove it
        //Store the alert text in a variable and verify it
        String text = alert.getText();
        System.out.println( text);

        assertEquals(text, "I am a JS Confirm");
        //Press the cancel button
        alert.dismiss();
        System.out.println("Alert Cancelled");
        Thread.sleep(3000);
        driver.quit();
    }
    @Test
    public void Popup_Prompt_Test() throws Exception {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-search-engine-choice-screen");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.manage().window().maximize();
        //Navigate to Url
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        //This is added for seeing the page > remove it
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Click for JS Prompt']")));
        var button = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
        button.click();

        org.openqa.selenium.Alert alert = driver.switchTo().alert();
        //This is added for seeing the page > remove it
        //Store the alert text in a variable and verify it
        String text = alert.getText();
        System.out.println( text);

        assertEquals(text, "I am a JS prompt");
        //Type your message
        alert.sendKeys("Hello JS");
        //Press the OK button
        alert.accept();
        //quit the browser
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void testForAlerts() throws Exception {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-search-engine-choice-screen");
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.manage().window().maximize();
        //Navigate to Url
        driver.get("https://www.selenium.dev/documentation/webdriver/interactions/alerts/");
        //This is added for seeing the page > remove it
        Thread.sleep(2000);
        //Simple Alert
        //Click the link to activate the alert
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //execute js for alert
        js.executeScript("alert('Sample Alert');");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //Wait for the alert to be displayed and store it in a variable
        wait.until(ExpectedConditions.alertIsPresent());

        org.openqa.selenium.Alert alert = driver.switchTo().alert();
        //This is added for seeing the page > remove it
        Thread.sleep(2000);
        //Store the alert text in a variable and verify it
        String text = alert.getText();
        assertEquals(text, "Sample Alert");
        //Press the OK button
        alert.accept();

        //Confirm
        //execute js for confirm
        js.executeScript("confirm('Are you sure?');");
        //Wait for the alert to be displayed
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());


        alert = driver.switchTo().alert();
        //Store the alert text in a variable and verify it
        text = alert.getText();
        assertEquals(text, "Are you sure?");
        //Press the Cancel button
        alert.dismiss();

        //Prompt
        //execute js for prompt
        js.executeScript("prompt('What is your name?');");
        //Wait for the alert to be displayed and store it in a variable
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());

        alert = driver.switchTo().alert();
        //Store the alert text in a variable and verify it
        text = alert.getText();
        assertEquals(text, "What is your name?");
        //Type your message
        alert.sendKeys("Selenium");
        //Press the OK button
        alert.accept();
        //quit the browser
        driver.quit();
    }
}
