package com.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebScrappingFinal {
	public static boolean visited =false;
	public static boolean pageAccess =true;
	public static Set<String> titleHeaders = new HashSet<>();
	public static List<String> details =new ArrayList<>();
	public static int count=1;
	public static void crawler(String url) throws IOException {
		try {
			Document doc = Jsoup.connect("https://www.justdial.com/Kolkata/Marriage-Function-Halls/nct-10035861").timeout(6000).get();
			Elements ele=doc.select("div.jsx-4d407376001b01ad.resultbox_info");
			for(Element e:ele.select("div.jsx-4d407376001b01ad.resultbox_textbox")) {
				//String result =e.attr("h2").text();
				//String result =e.text();
				Element h2Element = e.selectFirst("h2.resultbox_title");
	            if (h2Element != null) {
	                String title = h2Element.attr("title");
	                if(!titleHeaders.contains(title)) {
	                	System.out.println("Title " + count++ +" : " + title);
	                }
	                titleHeaders.add(title);
	                details.add(title);
	                //System.out.println("Title: " + title);
	            }
	            //System.out.println(e);
			}
		}catch(HttpStatusException se) {
			System.out.println("Access denied in the web page");
			pageAccess=false;
			return;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		String url="https://www.justdial.com/Kolkata/Marriage-Function-Halls/nct-10035861/";
		
		while(titleHeaders.size()<=20 ) {
			if(pageAccess) {
				crawler(url);
			}else {
				break;
			}			
		}
		
//		for(String s:titleHeaders) {
//			System.out.println(s);
//		}
		System.out.println("===========================================================================================");
		
		
		System.out.println("Printing the complete data .............." +titleHeaders.size()+" .............."+details.size() +" ..................... ");
		System.out.println("===========================================================================================");

	}

}
