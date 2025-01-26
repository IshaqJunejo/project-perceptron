class perceptron {
	// Method to shuffle the dataset
	private int[][] shuffleData(int[][] dataset) {
		int[][] shuffledDataset = new int[dataset.length][dataset[0].length];
		int randomizer;

		for (int i = 0; i < dataset.length; i++) {
			randomizer = (int)(Math.random() * dataset.length);
			shuffledDataset[i] = dataset[randomizer];
		}

		return shuffledDataset;
	}

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
		dataset = dataReader.readTXT("dataset.txt", 1000, size * size + 1);
		
		// Splitting the dataset into inputs and outputs (ie, Labels)
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < size * size; j++) {
				inputs[i][j] = (double)dataset[i][j];
			}
			outputs[i] = (double)dataset[i][size * size];
		}

		// Training for 200 Epochs
		for (int i = 0; i < 200; i++) {
			for (int j = 0; j < dataset.length; j++) {
				// Forward Propagation
				outputneuron.forwardPass(inputs[j]);
				// Back Propagation (and updating parameters)
				outputneuron.backPropagate(outputs[j], 0.1);
			}

			// Update after every 10 Epoch
			if (i % 10 == 0) {
				System.out.println("Epoch No: " + i);
			}
		}
	}
}
