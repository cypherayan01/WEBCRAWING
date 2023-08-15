import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupRun {

	public static void main(String[] args) throws IOException {
		try {
			System.out.println("Inside the program.");
			Document doc = Jsoup.connect("https://www.justdial.com/Kolkata/Marriage-Function-Halls/nct-10035861").timeout(3600).get();
			Elements ele=doc.select("div.jsx-4d407376001b01ad.resultbox_info");
			for(Element e:ele.select("div.jsx-4d407376001b01ad.resultbox_textbox")) {
				//String result =e.attr("h2").text();
				//String result =e.text();
				Element h2Element = e.selectFirst("h2.resultbox_title");
                if (h2Element != null) {
                    String title = h2Element.attr("title");
                    System.out.println("Title: " + title);
                }
                System.out.println(e);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Outside the program.");
		}
		

	}

}
