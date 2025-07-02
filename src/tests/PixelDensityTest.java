package tests;

import HandWritten.PixelDensity;

/**
 * Test class for PixelDensity feature extraction.
 * Contains methods to compare test image features with database features and print results.
 */
public class PixelDensityTest {
    /**
     * Runs the pixel density test and prints the results.
     * @param densityx The PixelDensity instance for the test image.
     * @param densities The database features for all digits and variants.
     */
    public static void runTest(PixelDensity densityx, double[][][] densities) {
        double[] test2Total = new double[10];
        double[][][] test2 = new double[10][7][17];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 16; k++) {
                    test2[i][j][k] = Math.abs(densityx.getDensityData()[k] - densities[i][j][k]);
                    test2[i][j][16] += test2[i][j][k];
                }
                test2Total[i] += test2[i][j][16];
            }
        }
        // Print results
        System.out.println("********************TEST 2 - DENSITY********************");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print("[" + i + "][" + j + "] = ");
                for (int k = 0; k < 17; k++) {
                    System.out.printf("%.2f    ", test2[i][j][k]);
                }
                System.out.println();
            }
            System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Total = %.2f\n\n", test2Total[i]);
        }
        System.out.println();
    }
}