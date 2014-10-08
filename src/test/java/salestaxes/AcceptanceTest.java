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
	@Test public void return_expected_output_of_1st_acceptance_test() {
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

	/**
	 * Input 2:
	 * 	1 imported box of chocolates at 10.00
	 * 	1 imported bottle of perfume at 47.50
	 * Output 2:
	 * 	1 imported box of chocolates: 10.50
	 * 	1 imported bottle of perfume: 54.65
	 * 	Sales Taxes: 7.65
	 * 	Total: 65.15
	 */
	@Test public void return_expected_output_of_2nd_acceptance_test() {
		ShoppingBasket basket = new ShoppingBasket();
		basket.add(new Item("imported box of chocolates", new BigDecimal("10.00"), true /* taxFree */, true /* imported */));
		basket.add(new Item("imported bottle of perfume", new BigDecimal("47.50"), false /* taxFree */, true /* imported */));
		Receipt receipt = basket.toReceipt();
		assertEquals(2, receipt.linesCount());
		assertBigDecimalEquals(new BigDecimal("10.50"), receipt.line(0).amount());
		assertBigDecimalEquals(new BigDecimal("54.65"), receipt.line(1).amount());
		assertBigDecimalEquals(new BigDecimal( "7.65"), receipt.salesTaxes());
		assertBigDecimalEquals(new BigDecimal("65.15"), receipt.total());
	}

	/**
	 * 
	 * Input 3:
	 * 	1 imported bottle of perfume at 27.99
	 * 	1 bottle of perfume at 18.99
	 * 	1 packet of headache pills at 9.75
	 * 	1 box of imported chocolates at 11.25
	 * Output 3:
	 * 	1 imported bottle of perfume: 32.19
	 * 	1 bottle of perfume: 20.89
	 * 	1 packet of headache pills: 9.75
	 * 	1 imported box of chocolates: 11.85
	 * 	Sales Taxes: 6.70
	 * 	Total: 74.68
	 */
	@Test public void return_expected_output_of_3rd_acceptance_test() {
		ShoppingBasket basket = new ShoppingBasket();
		basket.add(new Item("imported bottle of perfume", new BigDecimal("27.99"), false /* taxFree */, true  /* imported */));
		basket.add(new Item("bottle of perfume", new BigDecimal("18.99"), false /* taxFree */, false /* imported */));
		basket.add(new Item("packet of headache pills", new BigDecimal("9.75"), true /* taxFree */, false /* imported */));
		basket.add(new Item("box of imported chocolates", new BigDecimal("11.25"), true /* taxFree */, true  /* imported */));
		Receipt receipt = basket.toReceipt();
		assertEquals(4, receipt.linesCount());
		assertBigDecimalEquals(new BigDecimal("32.19"), receipt.line(0).amount());
		assertBigDecimalEquals(new BigDecimal("20.89"), receipt.line(1).amount());
		assertBigDecimalEquals(new BigDecimal( "9.75"), receipt.line(2).amount());
		assertBigDecimalEquals(new BigDecimal("11.85"), receipt.line(3).amount());
		assertBigDecimalEquals(new BigDecimal( "6.70"), receipt.salesTaxes());
		assertBigDecimalEquals(new BigDecimal("74.68"), receipt.total());
	}

}
