package parsers;



import java.io.IOException;

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
		String replacePat = "[\\W | \\s]";
		String test = cleanStr("Мыоценив аемвашу\n" + 
				"платежеспосо бность");
		String test2 = cleanStr("Мыоцен иваемвашу\r\n\\r\n" + 
				"платежеспо"
				+ "соб ность");
		
		System.out.println(test.equals(test2));
		System.out.println(test);
		System.out.println(test2);
	}
	
	public static String cleanStr(String str) {
		str = str.replaceAll("[\\W]", "");
		str = str.replaceAll("[\\S]", "");
		return str;
	}

}
