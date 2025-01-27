import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class testPerceptron {
	// array to store custom Inputs
	private static int[] inputs = new int[256];

	// Method to read Weights from a txt file
	static double[] readWeights(String filepath) {
		double[] weights = new double[256];
		try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
			String line;
			int counter = 0;
			while ((line = reader.readLine()) != null && counter < 256) {
				weights[counter] = Double.parseDouble(line);
				counter++;
			}
		} catch (IOException e) {
			System.out.println("Error reading from file: " + e.getMessage());
		}

		return weights;
	}

	// Method to read Bias from a txt file
	static double readBias(String filepath) {
		double bias = 0.0;
		try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
			String line = reader.readLine();
			bias = Double.parseDouble(line);
		} catch (IOException e) {
			System.out.println("Error reading from file: " + e.getMessage());
		}

		return bias;
	}

	static void updateLabels(JLabel label1, JLabel label2, double[] w, int[] in, double b) {
		double out = forwardPass(w, in, b);
		label1.setText("Output is: " + out);

		if (out > 0.7) {
			label2.setText("It is a Rectangle");
		} else if (out < 0.3) {
			label2.setText("It is a Circle");
		} else {
			label2.setText("I dont know either");
		}
	}

	private static double forwardPass(double[] weights, int[] inputs, double bias) {
		// weighted sum
		double z = 0;
		for (int i = 0; i < inputs.length; i++) {
			z += weights[i] * (double)inputs[i];
		}
		z += bias;
		// sigmoid
		z = 1.0 / (1 + Math.exp(-z));

		return z;
	}

	// Main function
	public static void main(String[] args) {
		// Loading Weights and Bias from the File
		double[] weights = readWeights("weights.txt");
		double bias = readBias("bias.txt");
		double output = 0.0;

		// GUI Frame
		JFrame frame = new JFrame("Try the Perceptron");
		frame.setSize(451, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		// 16 by 16 Grid with 3px gap
		JPanel panel = new JPanel(new GridLayout(16, 16, 3, 3));
		panel.setLayout(null);
		panel.setBounds(3, 3, 448, 448);

		//
		for (int i = 0; i < 256; i++) {
			JButton button = new JButton();
			button.setBackground((inputs[i] == 1) ? Color.WHITE : Color.BLACK);
			button.setOpaque(true);
			button.setBorderPainted(false);
			button.setBounds(((i % 16) * (25 + 3)), ((i / 16) * (25 + 3)), 25, 25);

			// Add and ActionListener to the Button
			button.addActionListener(new ButtonClickListener(i));

			panel.add(button);
		}

		// Add panel to the JFrame
		frame.add(panel);

		//
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(3, 455, 448, 100);

		//
		JLabel outputLabel = new JLabel("Output is: " + output);
		outputLabel.setBounds(3, 500, 300, 30);
		panel2.add(outputLabel);
		JLabel outputLabel2 = new JLabel("Translation: ");
		outputLabel2.setBounds(3, 520, 300, 30);
		panel2.add(outputLabel2);

		//
		JButton outputButton = new JButton("Check Output");
		outputButton.setBounds(3, 460, 120, 30);
		outputButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// update the label
				updateLabels(outputLabel, outputLabel2, weights, inputs, bias);
			}
		});
		panel2.add(outputButton);

		//
		frame.add(panel2);

		//
		frame.setVisible(true);
		
	}

	// Inner class for handling button clicks
	private static class ButtonClickListener implements ActionListener {
		private final int index;

		public ButtonClickListener (int i) {
			this.index = i;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// change the value of Input at index
			if (inputs[this.index] == 0) { inputs[this.index] = 1; }
			else { inputs[this.index] = 0;}

			// change the background color
			JButton source = (JButton) e.getSource();
			source.setBackground((inputs[this.index] == 1) ? Color.WHITE : Color.BLACK);
		}
	}
}

// If you are wondering about the cleanliness of this code,
// Yes, I am ashamed of it
