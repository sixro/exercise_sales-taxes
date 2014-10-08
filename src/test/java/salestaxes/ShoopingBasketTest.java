package salestaxes;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import salestaxes.ShoppingBasket.Item;

public class ShoopingBasketTest {

	@Test public void returns_a_receipt_with_one_line_with_0_sales_taxes_and_a_total_equals_to_item_price() {
		ShoppingBasket basket = new ShoppingBasket();
		basket.add(new Item("book", BigDecimal.TEN, true /* taxFree */));
		Receipt receipt = basket.toReceipt();
		assertEquals(1, receipt.linesCount());
		assertEquals(BigDecimal.ZERO, receipt.salesTaxes());
		assertEquals(BigDecimal.TEN, receipt.total());
	}

	@Test public void returns_a_receipt_with_two_lines() {
		ShoppingBasket basket = new ShoppingBasket();
		basket.add(new Item("book", BigDecimal.TEN, true /* taxFree */));
		basket.add(new Item("music CD", BigDecimal.ONE, false /* taxFree */));
		Receipt receipt = basket.toReceipt();
		assertEquals(2, receipt.linesCount());
	}

}
