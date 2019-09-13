package parsers;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AppId {

	public static void main(String[] args) throws IOException {

		//Document doc = Jsoup.connect("https://backend-rc.dev.oneclickmoney.ru/user/edit/?id=2776318").get();
		
		setNumberWebView("", "9518266543");
		setNumberWebView("", "6126125");
		  
	}
	
	public static void setNumberWebView(String id, String number) {
		
		 String[] array= number.split("(?<=\\G.{2})");
		 
		 for (int i = 0; i < array.length; i++) {
			
			 array[i] += " "; 
		}
		 
		 

		 for (String string : array) {

			
			 System.out.println(string);
		 }




	}

}
