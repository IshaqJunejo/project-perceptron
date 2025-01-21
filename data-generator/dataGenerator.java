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

	public static void main(String[] args) {
		int size = 16;
		int[] circle = generateCircleArray(size);
		int[] rect = generateRectArray(size);

		for (int i = 0; i < circle.length - 1; i++) {
			// New line after every 16 characters
			if (i % size == 0) {
				System.out.println();
			}

			if (circle[i] == 1) {
				System.out.print("O ");
			} else {
				System.out.print("  ");
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
				System.out.print("  ");
			}
		}
		System.out.println();
		System.out.println("Labelled as: " + rect[rect.length - 1]);
	}
}
