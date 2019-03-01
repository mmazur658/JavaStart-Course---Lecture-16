package pl.mazurmarcin.javastart.lecture16.task16_1;

import java.util.List;

public class SellingReportApp {

	public static void main(String[] args) {

		SellingReportCalculator sellingReportCalculator = new SellingReportCalculator();
		SellingReportFileUtils sellingReportFileUtils = new SellingReportFileUtils();

		List<Product> products = sellingReportFileUtils.importDataFromFile();

		System.out.println("Raport sprzedaży: ");
		System.out.println("Suma sprzedaży brutto: " + sellingReportCalculator.getBruttoSum(products) + " ZŁ");
		System.out.println("Suma sprzedaży netto: " + sellingReportCalculator.getNettoSum(products) + " ZŁ");
		System.out.println("Suma vat: " + sellingReportCalculator.getVatSum(products) + " ZŁ");

	}
}
