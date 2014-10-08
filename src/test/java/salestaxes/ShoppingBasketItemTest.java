package salestaxes;

import java.math.BigDecimal;

import org.junit.Test;

public class ShoppingBasketItemTest {

	@Test public void returns_no_sales_taxes_when_product_is_a_book_because_it_is_exempt() {
		ShoppingBasket.Item item = new ShoppingBasket.Item("book", BigDecimal.ONE, true /* taxFree */);
		BigDecimalAssert.assertBigDecimalEquals(BigDecimal.ZERO, item.salesTaxes());
	}

	@Test public void returns_sales_taxes_valued_to_10_percent_when_product_is_a_CD() {
		ShoppingBasket.Item item = new ShoppingBasket.Item("music CD", BigDecimal.TEN, false /* taxFree */);
		BigDecimalAssert.assertBigDecimalEquals(BigDecimal.ONE, item.salesTaxes());
	}

	@Test public void returns_sales_taxes_valued_to_5_percent_when_product_is_an_imported_book() {
		ShoppingBasket.Item item = new ShoppingBasket.Item("book", new BigDecimal("20"), true /* taxFree */, true /* imported */);
		BigDecimalAssert.assertBigDecimalEquals(BigDecimal.ONE, item.salesTaxes());
	}

	@Test public void returns_sales_taxes_rounded_up() {
		ShoppingBasket.Item item = new ShoppingBasket.Item("music CD", new BigDecimal("14.99"), false /* taxFree */);
		BigDecimalAssert.assertBigDecimalEquals(new BigDecimal("1.50"), item.salesTaxes());
	}

	/*
	 * NOTE FOR THE RECRUITER this is an intermediate test as you can see for example in Kent Beck
	 *                        videos. I'll remove them, but I leave to allow you to have a feel on
	 *                        how I work
	 */
//	@Test public void percentage_returns_a_rounded_up_value() {
//		ShoppingBasket.Item item = new ShoppingBasket.Item("music CD", new BigDecimal("14.99"), false /* taxFree */);
//		BigDecimalAssert.assertBigDecimalEquals(new BigDecimal("1.50"), item.salesTaxes());
//	}

}
