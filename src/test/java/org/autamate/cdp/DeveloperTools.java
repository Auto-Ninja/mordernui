package org.autamate.cdp;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v128.network.Network;
import org.openqa.selenium.devtools.v128.network.model.Headers;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

public class DeveloperTools {
    protected WebDriver driver;
    protected WebDriverWait wait;
    DevTools devTools;
    @BeforeEach
    public void createSession() {
        System.out.println("-- BeforeEach --");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void BasicHttp_WebPage_AuthenticationTest() {
        System.out.println("-- Testing The Basic Auth Functionality of a Url By Passing User ans Password Via Network to browsers header attribute");
        devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.of(100000), Optional.of(100000), Optional.of(100000)));
        String UserName ="admin";
        String Password ="admin";
        String encodedAuth = Base64.getEncoder().encodeToString((UserName+":"+Password).getBytes());
        System.out.println("Basic Http Auth > " +encodedAuth);
        Map<String, Object> headers = ImmutableMap.of("Authorization", "Basic " + encodedAuth);

        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("https://the-internet.herokuapp.com/basic_auth");

        Assertions.assertEquals(
                "Congratulations! You must have the proper credentials.",
                driver.findElement(By.tagName("p")).getText());
    }

        @AfterEach
    public void quit() {
        System.out.println("-- AfterEach --");
        if (wait != null) {
            wait=null;
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
