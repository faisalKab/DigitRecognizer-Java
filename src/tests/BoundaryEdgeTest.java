package tests;

import HandWritten.BoundaryEdge;

/**
 * Test class for BoundaryEdge feature extraction.
 * Contains methods to compare test image features with database features and print results.
 */
public class BoundaryEdgeTest {
    /**
     * Runs the boundary edge test and prints the results.
     * @param boundx The BoundaryEdge instance for the test image.
     * @param bounds The database features for all digits and variants.
     */
    public static void runTest(BoundaryEdge boundx, double[][][] bounds) {
        double[] test3Total = new double[10];
        double[][][] test3 = new double[10][7][17];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 16; k++) {
                    test3[i][j][k] = Math.abs(boundx.getBoundaryArray()[k] - bounds[i][j][k]);
                    test3[i][j][16] += test3[i][j][k];
                }
                test3Total[i] += test3[i][j][16];
            }
        }
        // Print results
        System.out.println("********************TEST 3 - BOUNDARY********************");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print("[" + i + "][" + j + "] = ");
                for (int k = 0; k < 17; k++) {
                    System.out.printf("%.2f    ", test3[i][j][k]);
                }
                System.out.println();
            }
            System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Total = %.2f\n\n", test3Total[i]);
        }
        System.out.println();
    }
}