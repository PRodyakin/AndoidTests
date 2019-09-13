package parsers;

import java.awt.List;
import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import parsers.OCMData;

public class ParseHTMLUnit {

	private String userName;
	private HtmlPage homePage;	
	private WebClient webClient; 
	public void setUserName(String userName) {
		this.userName = userName;		
	}


	public ParseHTMLUnit(){
		try {
			this.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	
	public static void main(String[] args) {

		ParseHTMLUnit parseHTMLUnit = new ParseHTMLUnit();
		parseHTMLUnit.setUserName("Автотест Автотест Автотест");
		try {
			parseHTMLUnit.openEditUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void parse() throws Exception {

		webClient = new WebClient(BrowserVersion.CHROME);
		webClient.addRequestHeader("ACCEPT", "application/json");
		webClient.setCredentialsProvider(getUserCred());
	    webClient.waitForBackgroundJavaScriptStartingBefore(200);
	    webClient.waitForBackgroundJavaScript(20000);
	    webClient.getOptions().setThrowExceptionOnScriptError(false);
	    webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		final HtmlPage loginPage = webClient.getPage(OCMData.URL_LOGIN_FORM);
		WebResponse loginPageResponse = getResponse(loginPage);
		//System.out.println(response.getContentAsString());

		final HtmlForm form = loginPage.getHtmlElementById("login-form");
		final HtmlButton button =  loginPage.getHtmlElementById("sbmt");
		final HtmlInput username = loginPage.getHtmlElementById("loginform-username");//("LoginForm[username]");
		final HtmlInput password = loginPage.getHtmlElementById("loginform-password");//("LoginForm[password]");

		username.type(OCMData.USERNAME_LOGIN_FORM);
		password.type(OCMData.PASSWORD_LOGIN_FORM);
		// Change the value of the text field
		//        username.type("root");
		//        username.type("root");
		// Now submit the form by clicking the button and get back the second page.
		//System.out.println(loginPage.asText());


		button.click();
		webClient.waitForBackgroundJavaScriptStartingBefore(200);
	    webClient.waitForBackgroundJavaScript(20000);
		homePage = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();

	}


	public void deleteUser() throws Exception, IOException {
				
		final HtmlTableBody body = getTableBody();

		for (final HtmlTableRow row : body.getRows()) {
			
			String userName = getUserNameFromRow(row);

			if (hasDeleteAnchor(row) && this.userName.equals(userName)) {

				HtmlAnchor a = (HtmlAnchor) row.getChildNodes().get(1).getChildNodes().get(2).getChildNodes().get(1);	
				webClient.waitForBackgroundJavaScriptStartingBefore(200);
			    webClient.waitForBackgroundJavaScript(20000);
			    System.out.println(a.getHrefAttribute()); 
				HtmlPage page = a.click();
				System.out.println(page.asText());
				System.out.println(page.getWebResponse().getContentAsString());
				
			}


		}
		
		
	}

	public void openEditUser() throws Exception {
		final HtmlTableBody body = getTableBody();

		for (final HtmlTableRow row : body.getRows()) {
			
			String userName = getUserNameFromRow(row);
			
			if (hasOpenAnchor(row) && this.userName.equals(userName)) {

				HtmlAnchor a = (HtmlAnchor) row.getChildNodes().get(1).getChildNodes().get(1).getChildNodes().get(1);	
				webClient.waitForBackgroundJavaScriptStartingBefore(200);
			    webClient.waitForBackgroundJavaScript(20000);
			    System.out.println(a.getHrefAttribute()); 
				HtmlPage page = a.click();
				System.out.println(page.asText());
				System.out.println(page.getWebResponse().getContentAsString());
				
			}


		}
	}
	
	public void openEditUser(String url) throws Exception {
		final HtmlTableBody body = getTableBody();

		for (final HtmlTableRow row : body.getRows()) {
			
			String userName = getUserNameFromRow(row);
			
			if (hasOpenAnchor(row) && this.userName.equals(userName)) {

				HtmlAnchor a = (HtmlAnchor) row.getChildNodes().get(1).getChildNodes().get(1).getChildNodes().get(1);	
				webClient.waitForBackgroundJavaScriptStartingBefore(200);
			    webClient.waitForBackgroundJavaScript(20000);
			    System.out.println(a.getHrefAttribute()); 
				HtmlPage page = a.click();
				System.out.println(page.asText());
				System.out.println(page.getWebResponse().getContentAsString());
				
			}


		}
	}

	public boolean hasDeleteAnchor(final HtmlTableRow row) {
		String elemAsString = row.getChildNodes().get(1).getChildNodes().get(1).getChildNodes().get(1).toString();		
		return elemAsString.contains("success")||elemAsString.contains("default");
	}



	public boolean hasOpenAnchor(final HtmlTableRow row) {
		String elemAsString = row.getChildNodes().get(1).getChildNodes().get(1).getChildNodes().get(1).toString();		
		return elemAsString.contains("success")||elemAsString.contains("default");
	}

	public WebResponse getResponse(final HtmlPage page) {
		WebResponse response = page.getWebResponse();
		System.out.println(response.getStatusCode());
		return response;
	}

	public DefaultCredentialsProvider getUserCred() {

		DefaultCredentialsProvider userCredentials= new DefaultCredentialsProvider();
		userCredentials.addCredentials(OCMData.USERNAME_CRED, OCMData.PASSWORD_CRED);
		return userCredentials;

	}

	public void checkUser() throws Exception {
		if (this.userName.isEmpty()) {
			throw new Exception("USER IS EMPTY");
		}else if(userName == null) {
			throw new Exception("USER IS NULL");
		}else {
			System.out.println("USER IS OK " + userName);
		}
	}

	public HtmlTableBody getTableBody() throws Exception {
		checkUser();
		DomNodeList<DomElement> tables = homePage.getElementsByTagName("table");
		if (tables.isEmpty()) {
			throw new Exception("There are no tables on page");			
		}

		final HtmlTable table = (HtmlTable) tables.get(0);

		final HtmlTableBody body = table.getBodies().get(0);
		return body;
	}

	public String getUserNameFromRow(final HtmlTableRow row) {
		String userName = row.getCell(2).asText();
		System.out.println(String.format("%s %s",row.getCell(0).asText(),row.getCell(2).asText()));
		return userName;
	}



}
