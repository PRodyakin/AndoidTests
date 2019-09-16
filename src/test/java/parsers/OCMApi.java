package parsers;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class OCMApi {

	public String STATE_DECLINE = "-3";


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

		

		for (UserData user : api.getUsers()) {
			System.out.println(user.toString());
		}

	}

	public void deleteUsers()
			throws FailingHttpStatusCodeException, MalformedURLException, IOException, Exception {
		for (UserData user : getUsers()) {

			//System.out.println(user.toString());
			if (! user.isDeleted) {
				deleteUser(user.id);				
			}
			//https://backend	-rc.dev.oneclickmoney.ru/applications/change-status/?id=4029682&status=-3

		}
	}

	public void declineAllUsersApps() 
			throws FailingHttpStatusCodeException, MalformedURLException, IOException, Exception {

		for (UserData user : getUsers()) {

			//System.out.println(user.toString());
			for (AppData app : user.applications) {

				if (! "DECLINED".equals(app.getState())) {
					changeAppStatus(app.getId(), STATE_DECLINE);				
				}

			}
			//https://backend-rc.dev.oneclickmoney.ru/applications/change-status/?id=4029682&status=-3

		}

	}

	public ArrayList<AppData> getApplications(String userId, String page) 
			throws FailingHttpStatusCodeException, MalformedURLException, IOException {

		String url = OCMData.getUrlFunction("api/apps/", "userId=" + userId, "page=" + page);
		String response = webClient.getPage(url).getWebResponse().getContentAsString();

		Gson gson = new Gson();
		Type datasetListType = new TypeToken<Collection<AppData>>(){}.getType();
		ArrayList<AppData> datasets = gson.fromJson(response, datasetListType); 
		return datasets;

	}

	public void changeAppStatus(int appId, String status) 
			throws FailingHttpStatusCodeException, MalformedURLException, IOException {

		String url = OCMData.getUrlFunction("applications/change-status/", "id=" + appId, "status=" + status);
		HtmlPage changeAppStatusPage = webClient.getPage(url);

	}

	public void login() throws Exception {

		webClientInit();
		postLoginForm();
		waitForJs(20000);
		currentPage = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();

	}

	public void deleteUser(String id) 
			throws FailingHttpStatusCodeException, MalformedURLException, IOException {

		String url = OCMData.getUrlFunction("user/delete/", "id=" +id);
		HtmlPage deletePage = webClient.getPage(url);
	}

	public ArrayList<UserData> getUsers() throws Exception {

		HtmlPage userPage = webClient.getPage(OCMData.URL_USERS);
		waitForJs(10000);
		usersDataArray = new ArrayList<UserData>(); 
		final HtmlTableBody body = getTableBody();

		for (final HtmlTableRow row : body.getRows()) {

			String userName = getUserNameFromRow(row);

			if (this.userNameMask.equals(userName)) {

				String userId = row.getCell(0).asText();
				String appStatusRu = row.getCell(5).asText();
				ArrayList<AppData> applications = getApplications(userId,"1");

				//UserData userData = ;
				usersDataArray.add(new UserData(userId, appStatusRu, !hasDeleteAnchor(row), applications));

			}
		}
		return usersDataArray;
	}

	private boolean hasDeleteAnchor(final HtmlTableRow row) {
		String elemAsString = row.getChildNodes().get(1).getChildNodes().get(1).getChildNodes().get(1).toString();		
		return elemAsString.contains("success")||elemAsString.contains("default");
	}

	private String getUserNameFromRow(final HtmlTableRow row) {
		String userName = row.getCell(2).asText();
		//System.out.println(String.format("%s %s",row.getCell(0).asText(),row.getCell(2).asText()));
		return userName;
	}

	private HtmlTableBody getTableBody() throws Exception {
		DomNodeList<DomElement> tables = currentPage.getElementsByTagName("table");
		if (tables.isEmpty()) {
			throw new Exception("There are no tables on page");			
		}

		final HtmlTable table = (HtmlTable) tables.get(0);
		final HtmlTableBody body = table.getBodies().get(0);
		return body;
	}

	private void postLoginForm() throws IOException, MalformedURLException {
		final HtmlPage loginPage = webClient.getPage(OCMData.URL_LOGIN_FORM);
		final HtmlForm form = loginPage.getHtmlElementById("login-form");
		final HtmlButton button =  loginPage.getHtmlElementById("sbmt");
		final HtmlInput username = loginPage.getHtmlElementById("loginform-username");//("LoginForm[username]");
		final HtmlInput password = loginPage.getHtmlElementById("loginform-password");//("LoginForm[password]");
		username.type(OCMData.USERNAME_LOGIN_FORM);
		password.type(OCMData.PASSWORD_LOGIN_FORM);
		button.click();
	}

	private void waitForJs(int mlsec) {
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
