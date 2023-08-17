package com.project;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CrawlerWithEdgeDriver {
	static int count =0;
    public static void main(String[] args) {
        // Set the path to your EdgeDriver executable
        System.setProperty("webdriver.edge.driver", "C:\\data\\db\\msedgedriver.exe");

        EdgeOptions options = new EdgeOptions();
        WebDriver driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String url = "https://www.justdial.com/Kolkata/Marriage-Function-Halls/nct-10035861/";
        crawl(driver, url, new ArrayList<String>());
        System.out.println("Count : " + count);

        driver.quit();
    }

    private static void crawl(WebDriver driver, String url, List<String> visited) {
        driver.get(url);

        for (int i = 1; i <= 5; i++) {
            extractAndPrintTitles(driver, visited);

            // Scroll down using JavaScript
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

            try {
                Thread.sleep(2000); // Wait for some time after scrolling
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void extractAndPrintTitles(WebDriver driver, List<String> visited) { // div.jsx-4d407376001b01ad.resultbox_textbox
        List<WebElement> titleElements = driver.findElements(By.cssSelector("h2.jsx-4d407376001b01ad.resultbox_title"));// h2.jsx-4d407376001b01ad.resultbox_title

        //List<WebElement> titleElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.jsx-4d407376001b01ad.resultbox_textbox")));
        
        System.out.println("Inside main..");
        System.out.println(titleElements);
        
        for (WebElement element : titleElements) {
        	count++;
            String title = element.getText();
            System.out.println(title);
//        	String className=element.getAttribute("class");
//        	if(className.equals("resultbox_title")) {	
//        		String title = element.getText();
//        		System.out.println("Title: " + title);
//        	}
        }
    }
}
