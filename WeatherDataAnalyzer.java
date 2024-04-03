import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WeatherDataAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Accept user input for weather data
        List<Double> temperatures = new ArrayList<>();
        List<Double> humidities = new ArrayList<>();
        System.out.print("Enter the number of days: ");
        int numDays = scanner.nextInt();

        System.out.println("Enter the temperature and humidity for each day (one pair per line):");
        for (int i = 0; i < numDays; i++) {
            double temperature = scanner.nextDouble();
            double humidity = scanner.nextDouble();
            temperatures.add(temperature);
            humidities.add(humidity);
        }

        // Calculate weather statistics
        Map<String, Integer> daysByTemperatureRange = new HashMap<>();
        Map<String, Double> averageHumidityByRange = new HashMap<>();

        for (int i = 0; i < numDays; i++) {
            double temperature = temperatures.get(i);
            double humidity = humidities.get(i);
            String temperatureRange = getTemperatureRange(temperature);

            daysByTemperatureRange.put(temperatureRange, daysByTemperatureRange.getOrDefault(temperatureRange, 0) + 1);
            averageHumidityByRange.put(temperatureRange, averageHumidityByRange.getOrDefault(temperatureRange, 0.0) + humidity);
        }

        // Calculate average humidity for each temperature range
        for (Map.Entry<String, Double> entry : averageHumidityByRange.entrySet()) {
            String range = entry.getKey();
            double totalHumidity = entry.getValue();
            int daysInTemperatureRange = daysByTemperatureRange.get(range);
            double averageHumidity = totalHumidity / daysInTemperatureRange;
            averageHumidityByRange.put(range, averageHumidity);
        }

        // Print the results
        System.out.println("Weather statistics by temperature range:");
        for (Map.Entry<String, Integer> entry : daysByTemperatureRange.entrySet()) {
            String range = entry.getKey();
            int days = entry.getValue();
            double averageHumidity = averageHumidityByRange.get(range);
            System.out.println("Range: " + range + ", Days: " + days + ", Average Humidity: " + averageHumidity);
        }

        scanner.close();
    }

    private static String getTemperatureRange(double temperature) {
        if (temperature < 0) {
            return "<0°C";
        } else if (temperature >= 0 && temperature < 10) {
            return "0-10°C";
        } else if (temperature >= 10 && temperature < 20) {
            return "10-20°C";
        } else if (temperature >= 20 && temperature < 30) {
            return "20-30°C";
        } else {
            return ">=30°C";
        }
    }
}
