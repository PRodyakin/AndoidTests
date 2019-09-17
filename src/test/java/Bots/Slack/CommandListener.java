package Bots.Slack;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

public class CommandListener {
	
	@Command("exit")
	public void exit(SlackSession session, SlackMessagePosted event) {
		session.sendMessageToUser(event.getSender(), "exiting...", null);
		System.out.println("exe exit");
		
	}

}
