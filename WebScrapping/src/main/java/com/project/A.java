package com.project;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class A {

	public static void main(String[] args) {
		//A.disableCertificateValidation();
        System.setProperty("webdriver.edge.driver", "C:\\data\\db\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        try {
            driver.get("http://yellowpages.in/hyderabad/marriage-halls/901640908");
            Thread.sleep(1000);

            // Click the "Load More" button with ID "26"
            WebElement loadMoreButton26 = driver.findElement(By.id("26"));
            loadMoreButton26.click();
            
            // Wait for the overlay to disappear
            WebDriverWait overlayWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            overlayWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal")));

            Thread.sleep(1000); // Wait for new titles to load
            
            // Click the "Load More" button with ID "51"
            WebElement loadMoreButton51 = driver.findElement(By.id("51"));
            loadMoreButton51.click();
            
            // Wait for the overlay to disappear
            overlayWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal")));

            Thread.sleep(1000); // Wait for new titles to load

            Document doc = Jsoup.parse(driver.getPageSource());

            Elements titleElements = doc.select("a.eachPopularTitle.hasOtherInfo");
            Elements phoneElements = doc.select("a.businessContact[href^=tel:]");
            Elements addressElements=doc.select("address.businessArea");
            Elements isOpenElements=doc.select("div.openNow");
           // Elements emailElements=doc.select("div.eachPopularLink")
            System.out.println(titleElements.size());
            System.out.println(phoneElements.size());
            System.out.println(addressElements.size());
            System.out.println(isOpenElements.size());
           
            
            List<String> allDatas = new ArrayList<>();
            for (int i = 0; i < titleElements.size(); i++) {
                String title = titleElements.get(i).text();
                String phoneNumber = phoneElements.get(i).attr("href").replace("tel:", "");
               String address=addressElements.get(i).text();
               String openTill=isOpenElements.get(i).text();
               
                allDatas.add("Title: " + title + " | Phone Number: " + phoneNumber+ " | Address: "+address+ " | Timings: "+ openTill);
                //allDatas.add("Title: " + title + " | Phone Number: " + phoneNumber);
            }

            for (String entry : allDatas) {
                System.out.println(entry);
            }

            System.out.println("Total Entries: " + allDatas.size());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
