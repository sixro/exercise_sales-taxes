package salestaxes;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ReceiptLineTest {

	@Test public void amount_returns_the_sum_of_price_and_sales_taxes() {
		ShoppingBasket.Item item = new ShoppingBasket.Item("book", BigDecimal.TEN, true /* taxFree */, false /* imported */);
		Receipt.Line line = new Receipt.Line(item, BigDecimal.TEN);
		assertEquals(BigDecimal.valueOf(20), line.amount());
	}

}
