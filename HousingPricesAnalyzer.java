import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HousingPricesAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Accept user input for housing prices and square footage
        List<Double> prices = new ArrayList<>();
        List<Double> squareFootage = new ArrayList<>();
        System.out.print("Enter the number of houses sold: ");
        int numHouses = scanner.nextInt();

        System.out.println("Enter the price and square footage for each house (one pair per line):");
        for (int i = 0; i < numHouses; i++) {
            double price = scanner.nextDouble();
            double footage = scanner.nextDouble();
            prices.add(price);
            squareFootage.add(footage);
        }

        // Calculate housing statistics
        Map<String, Integer> housesByRange = new HashMap<>();
        Map<String, Double> averageSquareFootageByRange = new HashMap<>();

        for (int i = 0; i < numHouses; i++) {
            double price = prices.get(i);
            double footage = squareFootage.get(i);
            String priceRange = getPriceRange(price);
            
            housesByRange.put(priceRange, housesByRange.getOrDefault(priceRange, 0) + 1);
            averageSquareFootageByRange.put(priceRange, averageSquareFootageByRange.getOrDefault(priceRange, 0.0) + footage);
        }

        // Calculate average square footage for each price range
        for (Map.Entry<String, Double> entry : averageSquareFootageByRange.entrySet()) {
            String range = entry.getKey();
            double totalSquareFootage = entry.getValue();
            int housesInPriceRange = housesByRange.get(range);
            double averageSquareFootage = totalSquareFootage / housesInPriceRange;
            averageSquareFootageByRange.put(range, averageSquareFootage);
        }

        // Print the results
        System.out.println("Housing statistics by price range:");
        for (Map.Entry<String, Integer> entry : housesByRange.entrySet()) {
            String range = entry.getKey();
            int housesSold = entry.getValue();
            double averageFootage = averageSquareFootageByRange.get(range);
            System.out.println("Range: " + range + ", Houses Sold: " + housesSold + ", Average Square Footage: " + averageFootage);
        }

        scanner.close();
    }

    private static String getPriceRange(double price) {
        if (price >= 100000 && price < 200000) {
            return "$100,000-200,000";
        } else if (price >= 200000 && price < 300000) {
            return "$200,000-300,000";
        } else if (price >= 300000 && price < 400000) {
            return "$300,000-400,000";
        } else {
            return "Over $400,000";
        }
    }
}
