package pl.mazurmarcin.javastart.lecture16.task16_1;

public class Product {

	private String name;
	private double price;
	private Vat vat;

	public Product(String name, double price, Vat vat) {
		this.name = name;
		this.price = price;
		this.vat = vat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Vat getVat() {
		return vat;
	}

	public void setVat(Vat vat) {
		this.vat = vat;
	}

	public String toCsv() {
		return name + ";" + price + ";" + vat;
	}

	@Override
	public String toString() {
		return "Nazwa: " + name + ", cena: " + price + ", VAT: " + vat;
	}
}
