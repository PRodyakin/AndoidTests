package Bots.Slack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

public class CommandListener {
	Process process;
	SlackSession session;
	SlackUser user;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		final List<String> commands = new ArrayList<String>();                

		commands.add("C:\\\\BAT\\\\Main.bat");

		ProcessBuilder pb = new ProcessBuilder(commands);
		pb.redirectErrorStream(true);
		Process pr = pb.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
		    System.out.println(line);
		}
		pr.waitFor();
		System.out.println("ok!");

		in.close();
		System.exit(0);
		
	}
	
	
	@Command("exit")
	public void exit(SlackSession session, SlackMessagePosted event) {
		session.sendMessageToUser(event.getSender(), "exiting...", null);
		System.out.println("exe exit");
		
	}
	
	@Command("start")
	public void start(SlackSession session, SlackMessagePosted event) {
		this.session = session;
		this.user = event.getSender();
		sendMessage("start testing");
		
		startTesting();
		
		
	}
	
	@Command("stop")
	public void stop(SlackSession session, SlackMessagePosted event) {
		this.session = session;
		this.user = event.getSender();
		sendMessage("stop testing");
		
		System.out.println(process.isAlive());
		//process.destroy();
		
		
		
	}
	
	private void startTesting() {
		ProcessBuilder processBuilder = new ProcessBuilder("C:\\BAT\\Main.bat");
	       
		//Process process = Runtime.getRuntime().exec(
        //            "cmd /c hello.bat", null, new File("C:\\Users\\mkyong\\"));
					
        try {

            process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
                sendMessage(line);
            }
            
            

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println(output);
                System.exit(0);
            } else {
            	System.out.println("abnormal");
                //abnormal...
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
	}

	public void sendMessage(String message) {
		
		session.sendMessageToUser(user, message,null);
		
	}

}
