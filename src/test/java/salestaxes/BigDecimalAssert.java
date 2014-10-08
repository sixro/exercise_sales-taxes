package salestaxes;

import static org.junit.Assert.*;

import java.math.BigDecimal;

public class BigDecimalAssert {

	private BigDecimalAssert() { }
	
	public static void assertBigDecimalEquals(BigDecimal expected, BigDecimal actual) {
		assertTrue("expected " + expected + " got " + actual, expected.compareTo(actual) == 0);
	}
	
}
