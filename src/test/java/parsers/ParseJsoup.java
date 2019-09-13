package parsers;

import java.io.IOException;
import java.net.MalformedURLException;
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


public class ParseJsoup {

	public String URL = "https://backend-rc.dev.oneclickmoney.ru/";
	
	final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0";  
	 String loginFormUrl = "https://backend-rc.dev.oneclickmoney.ru/login/";  
	 String loginActionUrl = URL;  
	 String urlUsers = "https://backend-rc.dev.oneclickmoney.ru/user/list/";
	 String usernameForm = "prodiakin@dengisrazy.ru";  
	 String passwordForm = "1617209"; 
	
	
	String username = "1";
	String password = "1";
	String login = username + ":" + password;
	String base64login = new String(Base64.encodeBase64(login.getBytes()));

	
	
	public static void main(String[] args) throws MalformedURLException, IOException {

		ParseJsoup p = new ParseJsoup();
		p.URL = "https://backend-rc.dev.oneclickmoney.ru/user/list/";
		System.out.println(p.getUserId());

	}


	public String getUserId() throws  MalformedURLException, IOException {

		
//		_csrf: EEV_JaYYGd_i6iv_5emvGAy0O6Cxy6pOxXM_2vwr5MgiDxBM4C5I5rLTb8_dsdUhXe5a1dCn8GOHOFiulmaXpw==
//				LoginForm[username]: prodiakin@dengisrazy.ru
//				LoginForm[password]: 1617209
//				LoginForm[rememberMe]: 0
//				LoginForm[rememberMe]: 1
//				login-button: 
		
		
		 Connection.Response loginForm = Jsoup.connect(loginFormUrl).header("Authorization", "Basic " + base64login).method(Connection.Method.GET).userAgent(USER_AGENT).execute();  
		 Document loginDoc = loginForm.parse(); // this is the document that contains response html
		  
		 Map<String, String> LoginCookies = loginForm.cookies();
         System.out.println("cookies: "+LoginCookies.size());
         for (String key : LoginCookies.keySet())
             System.out.println(key);


         Elements metaTags = loginDoc.getElementsByTag("meta");

         String csrf_token = "";
         for (Element metaTag : metaTags) {

             String content = metaTag.attr("content");
             String name = metaTag.attr("name");

             if("csrf-token".equals(name)) {
                 csrf_token = content;
                 System.out.println(content);
             }

         }
         
         HashMap<String, String> formData = new HashMap<>();  
		 formData.put("LoginForm[username]", usernameForm);  
		 formData.put("LoginForm[password]", passwordForm);
		 formData.put("_csrf", csrf_token);
		 

		 Response res = Jsoup.connect(loginFormUrl).header("Authorization", "Basic " + base64login) 
		         .cookies(LoginCookies)  
		         .data("_csrf", csrf_token)
		         .data(formData)
		        
		         .userAgent(USER_AGENT)
		         .method(Method.POST)
		         .execute();  
		
		 
		
		
//		if (! doc.select("table").isEmpty()) {
//			Element table = doc.select("table").get(0);
//
//			Elements rows = table.select("tr");
//			
//			for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
//			    Element row = rows.get(i);
//			    Elements cols = row.select("td");
//			    System.out.println(cols.get(2).text());		 
//			    
////			    if (cols.get(2).text().equals("down")) {
////			        downServers.add(cols.get(5).text());
////			    }
//			}
//		}else {
//			throw new MalformedURLException();
//		}
		
		
		Map<String, String> cookies = res.cookies();
		Document doc = Jsoup.connect(urlUsers).header("Authorization", "Basic " + base64login) 
		         .cookies(cookies)  
		         .userAgent(USER_AGENT)
		         .method(Method.GET)
		         .execute()
		         .parse();  
		System.out.println(doc);
		return doc.baseUri();


	}





}
