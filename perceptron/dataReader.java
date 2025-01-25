import java.io.*;

public class dataReader {
    // Method to read data from a TXT file
    public static int[][] readTXT(String filePath, int size1, int size2) {
		int[][] data = new int[size1][size2];
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
			int j = 0;
			// reading each line of the file
            while ((line = reader.readLine()) != null) {
				for (int i = 0; i < size2; i++) {
					// reading each character of the line,
					// it is subtracted by 48 because Integer.valueOf(char) returns the ASCII value of the char,
					// and ASCII value of '0' is 48, and '1' is 49,
					data[j][i] = Integer.valueOf(line.charAt(i) - 48);
				}
				j++;
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
		return data;
    }
}
