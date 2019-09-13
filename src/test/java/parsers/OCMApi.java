package parsers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.UnexpectedPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.gson.Gson;

public class OCMApi {

	
	//https://backend-rc.dev.oneclickmoney.ru/user/delete/?id=2821336
	//https://backend-rc.dev.oneclickmoney.ru/applications/change-status/?id=4029695&status=-3
	//https://backend-rc.dev.oneclickmoney.ru/api/apps/?userId=2821336&page=1
	
	private HtmlPage currentPage;
	private WebClient webClient;
	public String userNameMask;
	public ArrayList<UserData> usersDataArray;
	
	public OCMApi() throws Exception{
		webClientInit();
		login();
	}
	
	public static void main(String[] args) throws Exception {
		
		OCMApi api = new OCMApi();
		api.userNameMask = "Автотест Автотест Автотест";
		api.getUsers();
		
	}
	
	
	public void changeAppStatus(String appId, String status) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
	
		HtmlPage changeAppStatusPage = webClient.getPage(OCMData.getUrlFunction("applications/change-status/", "id=" + appId, "status=" + status));
		
	}
	
	
	public void deleteUser(String id) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		
		HtmlPage deletePage = webClient.getPage(OCMData.getUrlFunction("user/delete/", "id=" +id));
	}
	
	public void getUsers() throws Exception {

		HtmlPage userPage = webClient.getPage(OCMData.URL_USERS);
		waitForJs(10000);
		usersDataArray = new ArrayList<UserData>(); 
		final HtmlTableBody body = getTableBody();

		for (final HtmlTableRow row : body.getRows()) {

			String userName = getUserNameFromRow(row);
			
			if (this.userNameMask.equals(userName)) {
				usersDataArray.add(new UserData(
						row.getCell(0).asText(),
						row.getCell(5).asText(),
						! hasDeleteAnchor(row)));
				//TODO add app id
			}
		}
	}
	
	public boolean hasDeleteAnchor(final HtmlTableRow row) {
		String elemAsString = row.getChildNodes().get(1).getChildNodes().get(1).getChildNodes().get(1).toString();		
		return elemAsString.contains("success")||elemAsString.contains("default");
	}

	public String getUserNameFromRow(final HtmlTableRow row) {
		String userName = row.getCell(2).asText();
		System.out.println(String.format("%s %s",row.getCell(0).asText(),row.getCell(2).asText()));
		return userName;
	}

	
	public HtmlTableBody getTableBody() throws Exception {
		DomNodeList<DomElement> tables = currentPage.getElementsByTagName("table");
		if (tables.isEmpty()) {
			throw new Exception("There are no tables on page");			
		}

		final HtmlTable table = (HtmlTable) tables.get(0);
		final HtmlTableBody body = table.getBodies().get(0);
		return body;
	}


	public void login() throws Exception {

		webClientInit();
		postLoginForm();
		waitForJs(20000);
		currentPage = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();

	}


	public void postLoginForm() throws IOException, MalformedURLException {
		final HtmlPage loginPage = webClient.getPage(OCMData.URL_LOGIN_FORM);
		final HtmlForm form = loginPage.getHtmlElementById("login-form");
		final HtmlButton button =  loginPage.getHtmlElementById("sbmt");
		final HtmlInput username = loginPage.getHtmlElementById("loginform-username");//("LoginForm[username]");
		final HtmlInput password = loginPage.getHtmlElementById("loginform-password");//("LoginForm[password]");
		username.type(OCMData.USERNAME_LOGIN_FORM);
		password.type(OCMData.PASSWORD_LOGIN_FORM);
		button.click();
	}


	public void waitForJs(int mlsec) {
		webClient.waitForBackgroundJavaScriptStartingBefore(mlsec);
		webClient.waitForBackgroundJavaScript(mlsec);
	}


	private void webClientInit() {
		webClient = new WebClient(BrowserVersion.CHROME);
		webClient.addRequestHeader("ACCEPT", "application/json");
		webClient.setCredentialsProvider(OCMData.getUserCred());
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setJavaScriptEnabled(true);
	}




}
