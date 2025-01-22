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

	static void writeCSV(String filePath, int[][] dataset) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			for (int[] row : dataset) {
				for (int data : row) {
					writer.write(String.valueOf(data));
					writer.write(",");
				}
//				writer.write(String.join(",", row));
				writer.newLine();
			}
			System.out.println("Data written to " + filePath);
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		int size = 16;
		int[] circle = generateCircleArray(size);
		int[] rect = generateRectArray(size);

/*
		for (int i = 0; i < circle.length - 1; i++) {
			// New line after every 16 characters
			if (i % size == 0) {
				System.out.println();
			}

			if (circle[i] == 1) {
				System.out.print("O ");
			} else {
				System.out.print(". ");
			}
		}
		System.out.println();
		System.out.println("Labelled as: " + circle[circle.length - 1]);
		System.out.println();

		for (int i = 0; i < rect.length - 1; i++) {
			// New line after every 16 characters
			if (i % size == 0) {
				System.out.println();
			}

			if (rect[i] == 1) {
				System.out.print("O ");
			} else {
				System.out.print(". ");
			}
		}
		System.out.println();
		System.out.println("Labelled as: " + rect[rect.length - 1]);
*/

		// preparing to import the data in a csv file
		int[][] data = new int[1000][rect.length];
		for (int i = 0; i < 1000; i+=2) {
			data[i] = generateCircleArray(size);
			data[i+1] = generateRectArray(size);
		}

		writeCSV("dataset.csv", data);
//		for (int i = 0; i < 1000; i++) {
//			System.out.println(data[i][rect.length - 1]);
//		}
/*
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < rect.length - 1; j++) {
				if (j % size == 0) { System.out.println(); }
				System.out.print(data[i][j]);
			}
			System.out.println();
		}
*/
	}
}
