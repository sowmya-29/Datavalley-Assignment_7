import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductSalesAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Accept user input for product sales
        List<Double> productSales = new ArrayList<>();
        System.out.print("Enter the number of product sales: ");
        int numSales = scanner.nextInt();

        System.out.println("Enter the sales prices (one per line):");
        for (int i = 0; i < numSales; i++) {
            double price = scanner.nextDouble();
            productSales.add(price);
        }

        // Calculate sales statistics
        Map<String, Integer> productCountByRange = new HashMap<>();
        Map<String, Double> revenueByRange = new HashMap<>();

        for (Double salePrice : productSales) {
            String priceRange = getPriceRange(salePrice);
            productCountByRange.put(priceRange, productCountByRange.getOrDefault(priceRange, 0) + 1);
            revenueByRange.put(priceRange, revenueByRange.getOrDefault(priceRange, 0.0) + salePrice);
        }

        // Print the results
        System.out.println("Sales statistics by price range:");
        for (Map.Entry<String, Integer> entry : productCountByRange.entrySet()) {
            String range = entry.getKey();
            int count = entry.getValue();
            double revenue = revenueByRange.get(range);
            System.out.println("Range: " + range + ", Products Sold: " + count + ", Total Revenue: $" + revenue);
        }

        scanner.close();
    }

    private static String getPriceRange(double price) {
        if (price >= 0 && price < 50) {
            return "$0-50";
        } else if (price >= 50 && price < 100) {
            return "$50-100";
        } else if (price >= 100 && price < 200) {
            return "$100-200";
        } else {
            return "Over $200";
        }
    }
}
