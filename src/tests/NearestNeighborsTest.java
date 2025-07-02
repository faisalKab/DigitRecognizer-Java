package tests;

import HandWritten.NearestNeighbors;

/**
 * Test class for NearestNeighbors feature extraction.
 * Contains methods to compare test image features with database features and print results.
 */
public class NearestNeighborsTest {
    /**
     * Runs the nearest neighbors test and prints the results.
     * @param neighborx The NearestNeighbors instance for the test image.
     * @param neighbors The database features for all digits and variants.
     */
    public static void runTest(NearestNeighbors neighborx, double[][][] neighbors) {
        double[] test5Total = new double[10];
        double[][][] test5 = new double[10][7][15];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 14; k++) {
                    test5[i][j][k] = Math.abs(neighborx.getDataSetNeighbors()[k] - neighbors[i][j][k]);
                    test5[i][j][14] += test5[i][j][k];
                }
                test5Total[i] += test5[i][j][14];
            }
        }
        // Print results
        System.out.println("********************TEST 5 - NEAREST NEIGHBORS********************");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print("[" + i + "][" + j + "] = ");
                for (int k = 0; k < 15; k++) {
                    System.out.printf("%.2f    ", test5[i][j][k]);
                }
                System.out.println();
            }
            System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Total = %.2f\n\n", test5Total[i]);
        }
        System.out.println();
    }
}