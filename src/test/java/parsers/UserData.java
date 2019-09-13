package parsers;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;

public class UserData {
	
	public String id;
	public String appStatusRu;
	public boolean isDeleted;
	
	public UserData() {	}
	
	public UserData(String id, String appStatusRu, boolean isDeleted){
		this.id = id;
		this.appStatusRu = appStatusRu;
		this.isDeleted = isDeleted;
	}
}
