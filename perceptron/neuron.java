class neuron {
	private double[] weights = new double[256];
	private double bias;
	private double output;
	private double[] inputs = new double[256];
	private double z;

	// Contructor
	public neuron (int numOfInputs) {
		// Randomizing Weights (range: -1 to 1)
		for (int i = 0; i < numOfInputs; i++) {
			this.weights[i] = Math.random() * 2 - 1;
		}

		// Randomizing Bias (range: -1 to 1)
		this.bias = Math.random() * 2 - 1;
	}

	// Method for Forward Propagation
	public double forwardPass (double[] inputValues) {
		// Inputs
		this.inputs = inputValues;
		// Weighted Sum symbolised as 'z'
		this.z = dotProduct(weights, inputs) + bias;
		// Output = activation of weight sum
		this.output = sigmoid(z);
		// return output
		return this.output;
	}

	// Method for Back Propagation
	public void backPropagate (double label, double learningRate) {
		// Computing Loss
		double loss = this.lossFunction(this.getOutput(), label);
		// Derivative of Loss with respect to 'z' (weighted sum)
		double dz = loss * sigmoidDerivative(this.getOutput());
		// Derivative of Loss with respect to weights
		double[] dweights = new double[this.inputs.length];
		for (int i = 0; i < dweights.length; i++) {
			dweights[i] = dz * this.inputs[i];
		}
		// Derivative of Loss with respect to Bias
		double dbias = dz * 1;

		// Updating weights and bias
		for (int i = 0; i < this.weights.length; i++) {
			this.weights[i] -= dweights[i] * learningRate;
		}
		this.bias -= dbias * learningRate;
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

	// Loss Function
	private double lossFunction (double output, double label) {
		return output - label;
	}

	// Sigmoid for Activation
	private double sigmoid (double x) {
		return 1.0 / (1 + Math.exp(-x));
	}

	// Method for derivative of Sigmoid
	private double sigmoidDerivative(double x) {
		return x * (1 - x);
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
