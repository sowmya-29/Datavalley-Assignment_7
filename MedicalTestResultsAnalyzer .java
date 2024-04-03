import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MedicalTestResultsAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Accept user input for medical test results
        List<Double> testResults = new ArrayList<>();
        System.out.print("Enter the number of patients: ");
        int numPatients = scanner.nextInt();

        System.out.println("Enter the test results for each patient (one per line):");
        for (int i = 0; i < numPatients; i++) {
            double result = scanner.nextDouble();
            testResults.add(result);
        }

        // Calculate medical test statistics
        Map<String, Integer> patientsByRange = new HashMap<>();
        Map<String, Double> averageValueByRange = new HashMap<>();

        for (Double result : testResults) {
            String range = getResultRange(result);
            patientsByRange.put(range, patientsByRange.getOrDefault(range, 0) + 1);
            averageValueByRange.put(range, averageValueByRange.getOrDefault(range, 0.0) + result);
        }

        // Calculate average value for each range
        for (Map.Entry<String, Double> entry : averageValueByRange.entrySet()) {
            String range = entry.getKey();
            double totalValue = entry.getValue();
            int patientsInRange = patientsByRange.get(range);
            double averageValue = totalValue / patientsInRange;
            averageValueByRange.put(range, averageValue);
        }

        // Print the results
        System.out.println("Medical test statistics by result range:");
        for (Map.Entry<String, Integer> entry : patientsByRange.entrySet()) {
            String range = entry.getKey();
            int patients = entry.getValue();
            double averageValue = averageValueByRange.get(range);
            System.out.println("Range: " + range + ", Patients: " + patients + ", Average Value: " + averageValue);
        }

        scanner.close();
    }

    private static String getResultRange(double result) {
        if (result < 50) {
            return "Low";
        } else if (result >= 50 && result < 100) {
            return "Normal";
        } else if (result >= 100 && result < 150) {
            return "Borderline";
        } else {
            return "High";
        }
    }
}
