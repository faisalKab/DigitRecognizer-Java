package HandWritten;
public class PixelDensity {
	
	double[] densityArray = new double[16];
	
	public PixelDensity(int[][] arr, int rows, int columns) {
		input(arr, rows, columns);
	}
    
    public void input(int[][] arr, int rows, int columns) {
        
        double pictureDensity = rows * columns;
        
        double inkDensity1 = 0;
        double inkDensity2 = 0;
        double inkDensity3 = 0;
        double inkDensity4 = 0;
        double inkDensity5 = 0;
        double inkDensity6 = 0;
        double inkDensity7 = 0;
        double inkDensity8 = 0;
        double inkDensity9 = 0;
        double inkDensity10 = 0;
        double inkDensity11 = 0;
        double inkDensity12 = 0;
        double inkDensity13 = 0;
        double inkDensity14 = 0;
        double inkDensity15 = 0;
        double inkDensity16 = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (arr[i][j] == 0) {
                    if (i >= 0 && j >= 0 && i < rows / 4 && j < columns / 4) {
                        inkDensity1++;
                    }
                    if (i >= 0 && j >= columns / 4 && i < rows / 4 && j < columns / 2) {
                        inkDensity2++;
                    }
                    if (i >= 0 && j >= columns / 2 && i < rows / 4 && j < columns * (3.0 / 4)) {
                        inkDensity3++;
                    }
                    if (i >= 0 && j >= columns * (3.0 / 4) && i < rows / 4 && j < columns) {
                        inkDensity4++;
                    }
                    if (i >= rows / 4 && j >= 0 && i < rows / 2 && j < columns / 4) {
                        inkDensity5++;
                    }
                    if (i >= rows / 4 && j >= columns / 4 && i < rows / 2 && j < columns / 2) {
                        inkDensity6++;
                    }
                    if (i >= rows / 4 && j >= columns / 2 && i < rows / 2 && j < columns * (3.0 / 4)) {
                        inkDensity7++;
                    }
                    if (i >= rows / 4 && j >= columns * (3.0 / 4) && i < rows / 2 && j < columns) {
                        inkDensity8++;
                    }
                    if (i >= rows / 2 && j >= 0 && i < rows * (3.0 / 4) && j < columns / 4) {
                        inkDensity9++;
                    }
                    if (i >= rows / 2 && j >= columns / 4 && i < rows * (3.0 / 4) && j < columns / 2) {
                        inkDensity10++;
                    }
                    if (i >= rows / 2 && j >= columns / 2 && i < rows * (3.0 / 4) && j < columns * (3.0 / 4)) {
                        inkDensity11++;
                    }
                    if (i >= rows / 2 && j >= columns * (3.0 / 4) && i < rows && j < columns) {
                        inkDensity12++;
                    }
                    if (i >= rows * (3.0 / 4) && j >= 0 && i < rows && j < columns / 4) {
                        inkDensity13++;
                    }
                    if (i >= rows * (3.0 / 4) && j >= columns / 4 && i < rows && j < columns / 2) {
                        inkDensity14++;
                    }
                    if (i >= rows * (3.0 / 4) && j >= columns / 2 && i < rows && j < columns * (3.0 / 4)) {
                        inkDensity15++;
                    }
                    if (i >= rows * (3.0 / 4) && j >= columns * (3.0 / 4) && i < rows && j < columns) {
                        inkDensity16++;
                    }
                }
            }
        }
        
        densityArray[0] = (inkDensity1 / pictureDensity) * 100;
        densityArray[1] = (inkDensity2 / pictureDensity) * 100;
        densityArray[2] = (inkDensity3 / pictureDensity) * 100;
        densityArray[3] = (inkDensity4 / pictureDensity) * 100;
        densityArray[4] = (inkDensity5 / pictureDensity) * 100;
        densityArray[5] = (inkDensity6 / pictureDensity) * 100;
        densityArray[6] = (inkDensity7 / pictureDensity) * 100;
        densityArray[7] = (inkDensity8 / pictureDensity) * 100;
        densityArray[8] = (inkDensity9 / pictureDensity) * 100;
        densityArray[9] = (inkDensity10 / pictureDensity) * 100;
        densityArray[10] = (inkDensity11 / pictureDensity) * 100;
        densityArray[11] = (inkDensity12 / pictureDensity) * 100;
        densityArray[12] = (inkDensity13 / pictureDensity) * 100;
        densityArray[13] = (inkDensity14 / pictureDensity) * 100;
        densityArray[14] = (inkDensity15 / pictureDensity) * 100;
        densityArray[15] = (inkDensity16 / pictureDensity) * 100;

    }
    
    public void printDensity() {
    	System.out.printf("%.3f %.3f %.3f %.3f\n" +
                "%.3f %.3f %.3f %.3f\n" +
                "%.3f %.3f %.3f %.3f\n" +
                "%.3f %.3f %.3f %.3f\n\n",
                densityArray[0], densityArray[1], densityArray[2], densityArray[3],
                densityArray[4], densityArray[5], densityArray[6], densityArray[7],
                densityArray[8], densityArray[9], densityArray[10], densityArray[11],
                densityArray[12], densityArray[13], densityArray[14], densityArray[15]);
    }
    
    // Returns array of the 16 data set of digit
    public double[] getDensityData() {
    	return densityArray;
    }
    
}
