class perceptron {
	public static void main(String[] args) {
		int length = 1000;
		int size = 16;
		// Dataset, Inputs, and Outputs
		int[][] dataset = new int[length][size * size + 1];
		double[][] inputs = new double[length][size * size];
		double[] outputs = new double[length];		

		// Our only output neuron
		neuron outputneuron = new neuron(size * size);
		
		// Reading the Dataset from a .txt file
		dataset = dataReader.readCSV("dataset.txt", 1000, size * size + 1);
		
		// Splitting the dataset into inputs and outputs (ie, Labels)
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < size * size; j++) {
				inputs[i][j] = (double)dataset[i][j];
			}
			outputs[i] = (double)dataset[i][size * size];
		}
		
		// selecting a random shape from the dataset
		int randomVal = (int)(Math.random() * 1000);

		// Printing out our shape
		for (int i = 0; i < 256; i++) {
			if (i % 16 == 0) { System.out.println(); }
			System.out.print((int)inputs[randomVal][i] + " ");
		}
		
		System.out.println();
		System.out.println();

		// Printing out our weights
		for (int i = 0; i < 256; i++) {
			if (i % 16 == 0) { System.out.println(); }
			System.out.print(outputneuron.getWeights()[i] + " ");
		}
		
		System.out.println();
		System.out.println();

		// Printing out our Bias
		System.out.println(outputneuron.getBias());

		System.out.println();
		System.out.println();

		// Printing out the result of Forward Propagation (Untrained)
		System.out.println(outputneuron.forwardPass(inputs[randomVal]));
	}
}
