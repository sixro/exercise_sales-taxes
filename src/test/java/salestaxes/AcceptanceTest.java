package salestaxes;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import salestaxes.ShoppingBasket.Item;

import static salestaxes.BigDecimalAssert.*;

public class AcceptanceTest {

	/**
	 * Input 1:
	 *   1 book at 12.49 
	 *   1 music CD at 14.99
	 *   1 chocolate bar at 0.85
	 * Output 1:
	 *   1 book : 12.49
	 *   1 music CD: 16.49
	 *   1 chocolate bar: 0.85
	 *   Sales Taxes: 1.50
	 *   Total: 29.83
	 */
	@Test public void return_expect_output_of_1st_acceptance_test() {
		ShoppingBasket basket = new ShoppingBasket();
		basket.add(new Item("book", new BigDecimal("12.49"), true /* taxFree */));
		basket.add(new Item("music CD", new BigDecimal("14.99"), false /* taxFree */));
		basket.add(new Item("chocolate bar", new BigDecimal("0.85"), true /* taxFree */));
		Receipt receipt = basket.toReceipt();
		assertEquals(3, receipt.linesCount());
		assertBigDecimalEquals(new BigDecimal("12.49"), receipt.line(0).amount());
		assertBigDecimalEquals(new BigDecimal("16.49"), receipt.line(1).amount());
		assertBigDecimalEquals(new BigDecimal( "0.85"), receipt.line(2).amount());
		assertBigDecimalEquals(new BigDecimal( "1.50"), receipt.salesTaxes());
		assertBigDecimalEquals(new BigDecimal("29.83"), receipt.total());
	}

	// FIXME add others 2 acceptance tests
	// FIXME tests toString of Receipt and Receipt.Line to print out receipt
}
