import java.io.*;

public class dataReader {

    public static void main(String[] args) {
        // Define the path of the CSV file
        String filePath = "data.csv"; 
        
        // Read and print data from the CSV file
        readCSV(filePath);
    }

    // Method to read data from a CSV file
    public static void readCSV(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Print each line read from the CSV
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}
