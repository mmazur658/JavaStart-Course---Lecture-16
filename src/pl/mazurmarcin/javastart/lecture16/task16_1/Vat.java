package pl.mazurmarcin.javastart.lecture16.task16_1;

import java.math.BigDecimal;

public enum Vat {
	VAT_0(new BigDecimal("0")), VAT_5(new BigDecimal("1.05")), 
	VAT_8(new BigDecimal("1.08")), VAT_23(new BigDecimal("1.23"));

	private final BigDecimal value;

	private Vat(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

}
