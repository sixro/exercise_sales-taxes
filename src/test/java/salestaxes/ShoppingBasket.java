package salestaxes;

import java.math.*;
import java.util.*;

public class ShoppingBasket {

	private final List<Item> items;

	public ShoppingBasket() {
		super();
		this.items = new LinkedList<>();
	}

	public void add(Item item) {
		this.items.add(item);
	}

	public Receipt toReceipt() {
		List<Receipt.Line> lines = new LinkedList<>();
		for (Item item : items) lines.add(item.toReceiptLine());
		return new Receipt(lines);
	}

	public static class Item {

		private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
		
		private static final BigDecimal GOOD_TAX_RATE = BigDecimal.TEN;
		private static final BigDecimal IMPORT_TAX_RATE = BigDecimal.valueOf(5);
		
		private final String name;
		private final BigDecimal price;
		private final boolean taxFree;
		private final boolean imported;

		public Item(String name, BigDecimal price, boolean taxFree) {
			this(name, price, taxFree, false);
		}

		public Item(String name, BigDecimal price, boolean taxFree, boolean imported) {
			super();
			this.name = name;
			this.price = price;
			this.taxFree = taxFree;
			this.imported = imported;
		}

		public BigDecimal price() {
			return price;
		}

		public BigDecimal salesTaxes() {
			BigDecimal importTaxes = (imported)
					? percentage(IMPORT_TAX_RATE, price)
					: BigDecimal.ZERO;
			BigDecimal goodtypeTaxes = (taxFree)
					? BigDecimal.ZERO
					: percentage(GOOD_TAX_RATE, price);
			return goodtypeTaxes.add(importTaxes);
		}
		
		private static BigDecimal percentage(BigDecimal percentage, BigDecimal amount) {
			return amount.multiply(percentage).divide(ONE_HUNDRED, RoundingMode.HALF_UP);
		}

		public Receipt.Line toReceiptLine() {
			return new Receipt.Line(this, salesTaxes());
		}

	}
	
}
