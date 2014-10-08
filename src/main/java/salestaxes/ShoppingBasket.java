package salestaxes;

import java.math.BigDecimal;
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

		private static final double GOOD_TAX_RATE   = 10.;
		private static final double IMPORT_TAX_RATE =  5.;
		
		@SuppressWarnings("unused")
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
		
		private static BigDecimal percentage(double percentage, BigDecimal amount) {
			double value = amount.doubleValue() * percentage / 100.;
			double rounded = Math.ceil(value * 20.) / 20.;
			return BigDecimal.valueOf(rounded);
		}

		public Receipt.Line toReceiptLine() {
			return new Receipt.Line(this, salesTaxes());
		}

	}
	
}
