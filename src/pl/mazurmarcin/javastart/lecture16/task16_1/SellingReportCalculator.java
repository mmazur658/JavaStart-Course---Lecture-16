package pl.mazurmarcin.javastart.lecture16.task16_1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SellingReportCalculator {

	public BigDecimal getBruttoSum(List<Product> products) {

		BigDecimal sum = new BigDecimal("0.0");

		for (Product product : products) 
			sum = sum.add(BigDecimal.valueOf(product.getPrice()));

		return sum;
	}

	public BigDecimal getNettoSum(List<Product> products) {

		BigDecimal sum = new BigDecimal("0");

		for (Product product : products)
			sum = sum.add(BigDecimal.valueOf(product.getPrice()).subtract(getVatValue(product)));

		return sum;
	}

	public BigDecimal getVatSum(List<Product> products) {

		BigDecimal sum = new BigDecimal("0");

		for (Product product : products)
			sum = sum.add(getVatValue(product));

		return sum;

	}

	// 123 - ( 123 / 1,23 )
	public BigDecimal getVatValue(Product product) {

		if (product.getVat().equals(Vat.VAT_0)) {

			return new BigDecimal("0");

		} else {

			BigDecimal value = BigDecimal.valueOf(product.getPrice());
			BigDecimal vatValue = value.divide(product.getVat().getValue(),2,RoundingMode.HALF_EVEN);
			return value.subtract(vatValue);

		}

	}
}
