import java.io.*;

class dataGenerator {
	// Method to generate a circle 
	static int[] generateCircleArray(int size) {
		// Generate a random radius, minimum 3, maximum 7
		int radius = ((int)(Math.random() * (7 - 3)) + 3);
		// Generate X and Y position, minimum at position 'radius', maximum at position 'size - radius'
		int posX = ((int)(Math.random() * (size - radius * 2)) + radius);
		int posY = ((int)(Math.random() * (size - radius * 2)) + radius);

		// Array to store the circle and the label
		int[] circleGenerated = new int[size * size + 1];
		for (int i = 0; i < (size * size); i++) {
			// current x and y positions
			int x = i % size;
			int y = (int)(i / size);
			// square of distance from posX and posY
			int distanceSq = ((posX - x) * (posX - x)) + ((posY - y) * (posY - y));

			// if distance <= radius, value is 1, else it is 0
			if (distanceSq <= radius * radius) {
				circleGenerated[i] = 1;
			} else {
				circleGenerated[i] = 0;
			}
		}

		// labeled as '0'
		circleGenerated[size * size] = 0;

		return circleGenerated;
	}

	// Method to generate a rectangle
	static int[] generateRectArray(int size) {
		// Generate a random value for width, height, and X, Y position
		int width = ((int)(Math.random() * (10 - 3)) + 3);
		int height = ((int)(Math.random() * (10 - 3)) + 3);
		int posX = (int)(Math.random() * (size - width));
		int posY = (int)(Math.random() * (size - height));

		// Array to store the rectangle and the label
		int[] rectGenerated = new int[size * size + 1];
		for (int i = 0; i < (size * size); i++) {
			// current x and y positions
			int x = i % size;
			int y = (int)(i / size);

			// if inside the bounds, value is 1, else it is 0
			if (x >= posX && x <= posX + width) {
				if (y >= posY && y <= posY + height) {
					rectGenerated[i] = 1;
				}
			} else {
				rectGenerated[i] = 0;
			}
		}

		// labelled as '1'
		rectGenerated[size * size] = 1;

		return rectGenerated;
	}

	// Method to Write the Data in a .txt file
	static void writeTXT(String filePath, int[][] dataset) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (int[] row : dataset) {
				for (int data : row) {
					writer.write(String.valueOf(data));
				}
				writer.newLine();
			}
			System.out.println("Data written to " + filePath);
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}
	}

	// Main Function
	public static void main(String[] args) {
		int size = 16;

		// preparing to import the data in a .txt file
		int[][] data = new int[1000][size * size + 1];
		for (int i = 0; i < 1000; i+=2) {
			data[i] = generateCircleArray(size);
			data[i+1] = generateRectArray(size);
		}

		writeTXT("dataset.txt", data);
	}
}
