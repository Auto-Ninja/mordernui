package org.automate.actions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Draggable {


    @Test
    public void dragsToElement() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");
        Thread.sleep(3000);

        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        new Actions(driver)
                .dragAndDrop(draggable, droppable)
                .perform();
        System.out.println("Performing Drag and Drop");
        Thread.sleep(3000);
        Assertions.assertEquals("dropped", driver.findElement(By.id("drop-status")).getText());
        driver.quit();
    }

    @Test
    public void Slidder() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://emicalculator.net/");
        Thread.sleep(5000);

        //Add code to check for consent button

        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Slider update the text box without reloading the page , so webelement is hard to find the change . Use javascript to get data
        String loanAmount = js.executeScript("return document.getElementById('loanamount').value").toString();

        System.out.println( "Before Slide > " +loanAmount);
        // Locate the slider element
        WebElement slider = driver.findElement(By.xpath("//*[@id=\"loanamountslider\"]/span"));

        // Initialize Actions class
        Actions actions = new Actions(driver);

        // Move the slider by offset
        actions.clickAndHold(slider)
                .moveByOffset(100, 0) // Move the slider 50 pixels to the right
                .release()
                .perform();
        loanAmount = js.executeScript("return document.getElementById('loanamount').value").toString();

        System.out.println( "After Slide > " +loanAmount);
        Thread.sleep(5000);
        driver.quit();
    }
}
