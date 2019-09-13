package parsers;

import org.apache.commons.codec.binary.Base64;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;

public final class OCMData {
	
	public static final String URL = "https://backend-rc.dev.oneclickmoney.ru";
	public static final String URL_USERS = URL +"/user/list/";
	public static final String URL_LOGIN_FORM = URL +"/login/";

	public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0";  
	public static final String LOGIN_ACTION_URL = URL;  
	public static final String USERNAME_LOGIN_FORM = "prodiakin@dengisrazy.ru";  
	public static final String PASSWORD_LOGIN_FORM = "1617209"; 
	
	
	public static final String USERNAME_CRED = "1";
	public static final String PASSWORD_CRED = "1";
	public static final String LOGIN_CRED = USERNAME_CRED + ":" + PASSWORD_CRED;
	public static final String BASE_64_LOGIN = new String(Base64.encodeBase64(LOGIN_CRED.getBytes()));

	
	public static DefaultCredentialsProvider getUserCred() {

		DefaultCredentialsProvider userCredentials= new DefaultCredentialsProvider();
		userCredentials.addCredentials(USERNAME_CRED, PASSWORD_CRED);
		return userCredentials;

	}
	
	
	public static String getUrlFunction(String function, String ... params) {
		
		String urlParams = "";
		if (!"".equals(params)) {
			 String.join("&", params);
		}
		
		return String.format("%s/%s?%s", URL, function, urlParams);
	}
}
