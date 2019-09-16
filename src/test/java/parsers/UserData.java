package parsers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;

public class UserData {

	public String id;
	public String appStatusRu;
	public boolean isDeleted;
	public ArrayList<AppData> applications;

	public UserData() {	}

	public UserData(
			String id,
			String appStatusRu, 
			boolean isDeleted, 
			ArrayList<AppData> applications){

		this.id = id;
		this.appStatusRu = appStatusRu;
		this.isDeleted = isDeleted;
		this.applications = applications;


	}

	@Override
	public String toString() {
		
		
		String apps = "";
		for (AppData appData : applications) {
			apps += appData.toString() + "\n";			
		}
		

		return String.format(
				"id: %s; appStatusRu: %s; isDeleted: %s; applications: \n%s"
				,id, appStatusRu, isDeleted, apps);
	}



}
