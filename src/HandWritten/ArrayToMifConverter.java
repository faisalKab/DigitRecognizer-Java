package HandWritten;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArrayToMifConverter {
	
    public static void convertToMif(int[][] data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write only the data values
        	for (int[] row : data) {
        	    for (int bit : row) {
        	        int binaryValue = (bit > 130) ? 1 : 0; // Convert to binary based on some condition
        	        writer.write(binaryValue + "\n");
        	    }
        	}
            System.out.println("Data file was created successfully at " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while writing the data file: " + e.getMessage());
        }
    }
    
    public static void convertToMifDataSet(double[] data, String filePath) {
        int depth = data.length;
        int width = 20;  // Adjust this based on the maximum value after scaling

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the header
            writer.write("DEPTH = " + depth + ";\n");
            writer.write("WIDTH = " + width + ";\n");
            writer.write("ADDRESS_RADIX = DEC;\n");  // Using unsigned integers for addresses
            writer.write("DATA_RADIX = DEC;\n");  // Storing data in decimal format
            writer.write("CONTENT\n");
            writer.write("BEGIN\n");

            // Write the data
            for (int i = 0; i < data.length; i++) {
                // Scale the double value and convert to integer
                int scaledValue = (int)(data[i] * 10000);
                if( scaledValue > 1037748) {	//this check is to ensure the output data width doesn't surpass 20 bits
                	scaledValue = 1037747;
                }
                writer.write(i + " : " + scaledValue + ";\n");
            }

            // End the file
            writer.write("END;\n");

            System.out.println("MIF file was created successfully at " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while writing the MIF file: " + e.getMessage());
        }
    }
    
}
