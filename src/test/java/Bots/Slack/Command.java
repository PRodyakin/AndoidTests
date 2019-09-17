package Bots.Slack;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //���������, ��� ���� ��������� ����� ������������ �� ����� ���������� ����� Reflection (��� ��� ��� ��� �����).
@Target(ElementType.METHOD) //���������, ��� ����� ����� ��������� �������� ����� (�� �����, �� ����������, �� ����, � ������ �����).
public @interface Command {
	
	String value();

}
