package org.autamate.cdp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.logging.HasLogEvents;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.openqa.selenium.devtools.events.CdpEventTypes.consoleEvent;

public class ConsoleUsage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    DevTools devTools;
    @BeforeEach
    public void createSession() {
        System.out.println("-- BeforeEach --");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test
    public void consoleLogs() {
        System.out.println("Console Log types");
        driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
        CopyOnWriteArrayList<String> messages = new CopyOnWriteArrayList<>();

        ((HasLogEvents) driver).onLogEvent(consoleEvent(e ->

                {
                    String consoleMessage= e.getMessages().get(0);
                    System.out.println(e.getType() + " Message > " + consoleMessage);
                    messages.add(consoleMessage);

                }

        ));
        System.out.println("Click to Generate Log Message");
        driver.findElement(By.id("consoleLog")).click();
        System.out.println("Click to Generate Error Message");
        driver.findElement(By.id("consoleError")).click();

        wait.until(_d -> messages.size() > 1);
        Assertions.assertTrue(messages.contains("Hello, world!"));
        Assertions.assertTrue(messages.contains("I am console error"));
    }
    @AfterEach
    public void quit() {
        System.out.println("-- AfterEach --");
        if (wait != null) {
            wait = null;
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
