package salestaxes;

import java.math.BigDecimal;
import java.util.*;

import org.apache.commons.lang3.builder.*;

import salestaxes.ShoppingBasket.Item;

public class Receipt {

	private final List<Line> lines;

	public Receipt(List<Line> lines) {
		super();
		this.lines = new LinkedList<>();
		this.lines.addAll(lines);
	}

	public int linesCount() {
		return lines.size();
	}
	
	public Line line(int index) {
		return lines.get(index);
	}

	public BigDecimal salesTaxes() {
		BigDecimal sum = BigDecimal.ZERO;
		for (Line line : lines) sum = sum.add(line.salesTaxes);
		return sum;
	}

	public BigDecimal total() {
		BigDecimal sum = BigDecimal.ZERO;
		for (Line line : lines) sum = sum.add(line.amount());
		return sum;
	}

	public static class Line {

		private final ShoppingBasket.Item item;
		private final BigDecimal salesTaxes;

		public Line(Item item, BigDecimal salesTaxes) {
			super();
			this.item = item;
			this.salesTaxes = salesTaxes;
		}

		public BigDecimal salesTaxes() {
			return salesTaxes;
		}
		
		public BigDecimal amount() {
			return item.price().add(salesTaxes);
		}
		
		@Override
		public int hashCode() {
			return HashCodeBuilder.reflectionHashCode(this);
		}

		@Override
		public boolean equals(Object obj) {
			return EqualsBuilder.reflectionEquals(this, obj);
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}

	}

}
