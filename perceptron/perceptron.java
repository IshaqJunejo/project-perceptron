import java.io.*;

class perceptron {
	// Method to shuffle the dataset (risks losing some data due to duplication)
	private static int[][] shuffleData(int[][] dataset) {
		int[][] shuffledDataset = new int[dataset.length][dataset[0].length];
		int randomizer;

		for (int i = 0; i < dataset.length; i++) {
			randomizer = (int)(Math.random() * dataset.length);
			shuffledDataset[i] = dataset[randomizer];
		}

		return shuffledDataset;
	}

	// Function to write parameters (weights and bias) in txt files
	private static void writeDataToFile(double[] weight, double bias) {
		// Writing Weights
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("weights.txt"))) {
			for (double value : weight) {
				writer.write(String.valueOf(value));
				writer.newLine();
			}
			System.out.println("Weights stored in weights.txt");
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}
		// Writing Bias
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("bias.txt"))) {
			writer.write(String.valueOf(bias));
			writer.newLine();
			System.out.println("Bias stored in bias.txt");
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}
	}

	// Main Function
	public static void main(String[] args) {
		int length = 1000;
		int size = 16;
		// Dataset, Inputs, and Outputs
		int[][] dataset = new int[length][size * size + 1];
		int[][] trainingData = new int[(int)(length * 0.75)][size * size + 1];
		int[][] testData = new int[(int)(length * 0.25)][size * size + 1];
		double[][] trainingInputs = new double[trainingData.length][size * size];
		double[] trainingOutputs = new double[trainingData.length];
		double[][] testInputs = new double[testData.length][size * size];
		double[] testOutputs = new double[testData.length];

		// Our only neuron
		neuron outputneuron = new neuron(size * size);
		
		// Reading the Dataset from a .txt file
		dataset = dataReader.readTXT("dataset.txt", 1000, size * size + 1);
		// Shuffling the Dataset
		dataset = shuffleData(dataset);
		
		// Splitting the dataset for training and testing
		for (int i = 0; i < length; i++) {
			if (i < trainingData.length) {
				trainingData[i] = dataset[i];
			} else {
				testData[i - trainingData.length] = dataset[i];
			}
		}

		// Splitting the training dataset into inputs and outputs (ie, Labels)
		for (int i = 0; i < trainingData.length; i++) {
			for (int j = 0; j < size * size; j++) {
				trainingInputs[i][j] = (double)trainingData[i][j];
			}
			trainingOutputs[i] = (double)trainingData[i][size * size];
		}

		// Splitting the testing dataset into inputs and outputs
		for (int i = 0; i < testData.length; i++) {
			for (int j = 0; j < size * size; j++) {
				testInputs[i][j] = (double)testData[i][j];
			}
			testOutputs[i] = (double)testData[i][size * size];
		}

		// Training for 2000 Epochs
		System.out.println("Delata 25 approves training for 2000 Epochs");
		for (int i = 1; i <= 2000; i++) {
			int correctPrediction = 0;
			double accuracy = 0.0;
			for (int j = 0; j < trainingData.length; j++) {
				// Forward Propagation
				outputneuron.forwardPass(trainingInputs[j]);
				// Back Propagation (and updating parameters)
				outputneuron.backPropagate(trainingOutputs[j], 0.1);

				// Computing Accuracy
				if (trainingOutputs[j] == 0 && outputneuron.getOutput() < 0.3) {
					correctPrediction++;
				} else if (trainingOutputs[j] == 1 && outputneuron.getOutput() > 0.7) {
					correctPrediction++;
				}
			}

			accuracy = (double)correctPrediction / (double)dataset.length;

			// Update after every 10 Epoch
			if (i % 10 == 0) {
				System.out.println("Epoch No: " + i);
				System.out.println("Accuracy: " + (accuracy * 100.0) + "%");
				System.out.println();
			}
		}

		// Testing
		System.out.println("Delta 25 approves to test the Perceptron");
		int correctPrediction = 0;
		double accuracy = 0.0;
		for (int i = 0; i < testData.length; i++) {
			// Forward Propagation
			outputneuron.forwardPass(testInputs[i]);

			// Counting Correct Predictions
			if (testOutputs[i] == 0 && outputneuron.getOutput() < 0.3) {
				correctPrediction++;
			} else if (testOutputs[i] == 1 && outputneuron.getOutput() > 0.7) {
				correctPrediction++;
			}
		}
		// Calculating Accuracy
		accuracy = (double)correctPrediction / (double)testData.length;

		//
		System.out.println("Accuracy in the test: " + (accuracy * 100) + "%");

		// Storing Parameters 
		writeDataToFile(outputneuron.getWeights(), outputneuron.getBias());
	}
}
