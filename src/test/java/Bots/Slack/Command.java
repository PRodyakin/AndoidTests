package Bots.Slack;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //”казывает, что наша јннотаци€ может использована во врем€ выполнени€ через Reflection (нам как раз это нужно).
@Target(ElementType.METHOD) //”казывает, что целью нашей јннотации €вл€етс€ метод (не класс, не переменна€, не поле, а именно метод).
public @interface Command {
	
	String value();

}
