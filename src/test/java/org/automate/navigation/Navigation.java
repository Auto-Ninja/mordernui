package org.automate.navigation;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Navigation {

    @Test
    public void Broswer_Navigation_Test() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        //Convenient
        driver.get("https://www.amazon.in/");
        Thread.sleep(3000);
        System.out.println("Page Loaded");
        //Longer way
        driver.navigate().to("https://www.amazon.in/deals?ref_=nav_cs_gb&discounts-widget=%2522%257B%255C%2522state%255C%2522%253A%257B%255C%2522refinementFilters%255C%2522%253A%257B%257D%257D%252C%255C%2522version%255C%2522%253A1%257D%2522");
        String title = driver.getTitle();
        assertEquals(title, "Amazon.in - Deals");

        //Back
        driver.navigate().back();
        title = driver.getTitle();
        assertEquals(title, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
        Thread.sleep(3000);
        System.out.println("navigate().back()");
        //Forward
        driver.navigate().forward();
        title = driver.getTitle();
        assertEquals(title, "Amazon.in - Deals");
        Thread.sleep(3000);
        System.out.println("navigate().forward()");
        //Refresh
        driver.navigate().refresh();
        title = driver.getTitle();
        assertEquals(title, "Amazon.in - Deals");
        Thread.sleep(3000);
        System.out.println("Refresh");
        Thread.sleep(3000);
        driver.quit();
    }
}
