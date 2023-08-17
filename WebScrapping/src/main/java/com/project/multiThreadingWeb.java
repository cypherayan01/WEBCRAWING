package com.project;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class multiThreadingWeb {
	public static Set<String> titleHeaders = new HashSet<>();
    public static int count = 1;
    
	public static void crawler(String url) {
        try {
            Connection connection = Jsoup.connect(url).timeout(1000);
            Document doc = connection.get();
            Elements ele = doc.select("div.jsx-4d407376001b01ad.resultbox_info");
            for (Element e : ele.select("div.jsx-4d407376001b01ad.resultbox_textbox")) {
                Element h2Element = e.selectFirst("h2.resultbox_title");
                if (h2Element != null) {
                    String title = h2Element.attr("title");
                    if (!titleHeaders.contains(title)) {
                        System.out.println("Title " + count++ + " : " + title);
                        titleHeaders.add(title);
                    }
                }
            }
        } catch (IOException e) {
            // Handle exceptions appropriately, e.g., log them
        }
    }
	public static void main(String[] args) {
        String baseUrl = "https://www.justdial.com/Kolkata/Marriage-Function-Halls/nct-10035861/";
        int maxTitles = 120;

        ExecutorService executor = Executors.newFixedThreadPool(10); // Adjust thread pool size as needed

        while (titleHeaders.size() <= maxTitles) {
            executor.submit(() -> crawler(baseUrl));
        }

        executor.shutdown();

        // Wait for all threads to finish
        while (!executor.isTerminated()) {
            Thread.yield();
        }

        System.out.println("===========================================================================================");
        System.out.println("Printing the complete data .............." + titleHeaders.size() + " ..................... ");
        System.out.println("===========================================================================================");
    }
}
