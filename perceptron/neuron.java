class neuron {
	public double weights[] = {};
	public double bias;
	public double output;
	private double inputs[] = {};
	private double z;

	// Contructor
	public neuron (int numOfInputs) {
		// Randomizing Weights
		for (int i = 0; i < numOfInputs; i++) {
			this.weights[i] = Math.random();
		}

		// Randomizing Bias
		this.bias = Math.random();
	}

	// Method for Forward Propagation
	public double forwardPass (double inputValues[]) {
		this.inputs = inputValues;
		this.z = dotProduct(weights, inputs) + bias;
		this.output = sigmoid(z);
		return this.output;
	}

	// Method to get the value of Output
	public double getOutput () {
		return this.output;
	}

	// Method to get the values of Weights
	public double[] getWeights () {
		return this.weights;
	}

	// Method to get the value of Bias
	public double getBias () {
		return this.bias;
	}

	// Sigmoid for Activation
	private double sigmoid (double x) {
		return 1.0 / (1 + Math.exp(-x));
	}

	// Method for Calculating Dot Product
	private double dotProduct (double[] weights, double[] inputs) {
		double total = 0.0;

		for (int i = 0; i < weights.length; i++) {
			total += this.weights[i] * this.inputs[i];
		}

		return total;
	}
}
