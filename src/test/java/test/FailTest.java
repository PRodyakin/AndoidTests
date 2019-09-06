package test;

import org.junit.Test;

import io.qameta.allure.Feature;

public class FailTest {
	
	@Test
	@Feature("Fail test")
	public void FailTest() {
		assert "e".equals("s");
	}

}
