package pl.mazurmarcin.javastart.lecture16.task16_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SellingReportFileUtils {

	private final String FILE_NAME = "sells.csv";

	public List<Product> importDataFromFile() {

		List<Product> products = new ArrayList<>();

		try (Scanner scanner = new Scanner(new File(FILE_NAME))) {

			String line = null;
			Product product = null;

			while (scanner.hasNextLine()) {

				line = scanner.nextLine();
				String[] tempArr = line.split(";");				
				product = new Product(tempArr[0], Double.parseDouble(tempArr[1]), Vat.valueOf(tempArr[2]));
				products.add(product);
			}

		} catch (FileNotFoundException exception) {
			System.err.println("File does not exist");
		}

		return products;
	}

}
