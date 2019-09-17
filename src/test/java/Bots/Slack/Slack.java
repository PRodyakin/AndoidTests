package Bots.Slack;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;

public class Slack {

	private static final Map<String, Method> commands = new HashMap<>();
	private static final CommandListener listener = new CommandListener();

	static
	{

		for (Method m : listener.getClass().getDeclaredMethods()) //Берем список всех методов в классе CommandListener
		{
			if (m.isAnnotationPresent(Command.class)) //Смотрим, есть ли у метода нужная нам Аннотация @Command
			{
				Command cmd = m.getAnnotation(Command.class); //Берем объект нашей Аннотации
				commands.put(cmd.value(), m); //Обращаемся к аргументу name (чтобы использовать его как ключ), m - переменная хранящая наш метод
				//				for (String s : cmd.aliases())  //Также заносим каждый элемент aliases как ключ указывающий на тот же самый метод.
				//				{
				//					commands.put(s, m);
				//				}
			}
		}
	}

	public static SlackSession session;
	public static enum buildStages {

		NOT_ACTIVE(1);



		private final int stage;

		private buildStages(int stage){
			this.stage = stage;
		}

	}

	public static void main(String[] args) throws IOException {

		session = SlackSessionFactory.createWebSocketSlackSession("xoxb-750210181331-764555283527-iwbQ8bMdSYkJHm5gdSUgQyhR");
		session.connect();
		SlackChannel channel = session.findChannelByName("bot"); //make sure bot is a member of the channel.
		session.sendMessage(channel, "hi im a bot" );
		registeringAListener(session);

	}


	public static void registeringAListener(SlackSession session)
	{
		// first define the listener
		SlackMessagePostedListener messagePostedListener = new SlackMessagePostedListener()
		{
			@Override
			public void onEvent(SlackMessagePosted event, SlackSession session)
			{
				if (session.sessionPersona().getId().equals(event.getSender().getId())) {
					return;
				}

				SlackChannel channelOnWhichMessageWasPosted = event.getChannel();
				String messageContent = event.getMessageContent();
				SlackUser messageSender = event.getSender();

				try {
					handleMessage(messageContent, event);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}


		};
		//add it to the session
		session.addMessagePostedListener(messagePostedListener);

		//that's it, the listener will get every message post events the bot can get notified on
		//(IE: the messages sent on channels it joined or sent directly to it)
	}


	private static void handleMessage(String messageContent, SlackMessagePosted event) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		if (messageContent.contains("exit")) {
//			session.disconnect();			
//		}
		Method m = commands.get(messageContent);
		m.invoke(listener, session, event);

	}

	/**
	 * This method demonstrate what is available in a SlackMessagePosted event
	 */
	public void slackMessagePostedEventContent(SlackSession session)
	{
		session.addMessagePostedListener(new SlackMessagePostedListener()
		{
			@Override
			public void onEvent(SlackMessagePosted event, SlackSession session1)
			{
				// if I'm only interested on a certain channel :
				// I can filter out messages coming from other channels
				SlackChannel theChannel = session1.findChannelByName("thechannel");

				if (!theChannel.getId().equals(event.getChannel().getId())) {
					return;
				}

				// if I'm only interested on messages posted by a certain user :
				// I can filter out messages coming from other users
				SlackUser myInterestingUser = session1.findUserByUserName("gueststar");

				if (!myInterestingUser.getId().equals(event.getSender().getId())) {
					return;
				}

				// How to avoid message the bot send (yes it is receiving notification for its own messages)
				// session.sessionPersona() returns the user this session represents
				if (session1.sessionPersona().getId().equals(event.getSender().getId())) {
					return;
				}

				// Then you can also filter out on the message content itself
				String messageContent = event.getMessageContent();
				if (!messageContent.contains("keyword")) {
					return;
				}

				// once you've defined that the bot needs to react you can use the session to do that :
				session1.sendMessage(event.getChannel(),"Message with keyword was sent by the expected user on this channel !");
			}
		});
	}

}
