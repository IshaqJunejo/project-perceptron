class perceptron {
	public static void main(String[] args) {
		int length = 1000;
		int size = 16;
		int[][] dataset = new int[length][size * size + 1];

		dataset = dataReader.readCSV("dataset.txt", 1000, size * size + 1);
		System.out.println("Compiler Check, 1, 2, 3, Do you Copy?");
	}
}
