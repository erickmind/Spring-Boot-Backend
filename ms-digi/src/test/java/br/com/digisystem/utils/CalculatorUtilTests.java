package br.com.digisystem.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CalculatorUtilTests {
	
	private CalculatorUtil calculatorUtil = new CalculatorUtil();
	
	@Test
	void sumTest() {
		int a = 9;
		int b = 5;
		int expected = 14;
		
		int actualResult = calculatorUtil.sum(a, b);
		
		assertEquals(expected, actualResult);
	}
}