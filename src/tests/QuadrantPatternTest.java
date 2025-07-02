package tests;

import HandWritten.DoubleQuadrantPattern;

/**
 * Test class for DoubleQuadrantPattern feature extraction.
 * Contains methods to compare test image features with database features and print results.
 */
public class QuadrantPatternTest {
    /**
     * Runs the quadrant pattern test and prints the results.
     * @param quadx The DoubleQuadrantPattern instance for the test image.
     * @param quad The database features for all digits and variants.
     */
    public static void runTest(DoubleQuadrantPattern quadx, int[][][] quad) {
        double[] test1Total = new double[10];
        int[][][] test1 = new int[10][7][9];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 8; k++) {
                    test1[i][j][k] = Math.abs(quadx.getDataSetTransitions()[k] - quad[i][j][k]);
                    test1[i][j][8] += test1[i][j][k]; // Sum for display
                }
                test1Total[i] += test1[i][j][8];
            }
        }
        // Print results
        System.out.println("********************TEST 1 - QUADRANT********************");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print("[" + i + "][" + j + "] = ");
                for (int k = 0; k < 9; k++) {
                    System.out.print(test1[i][j][k] + "    ");
                }
                System.out.println();
            }
            System.out.println("\t\t\t\t Total = " + test1Total[i] + "\n");
        }
        System.out.println();
    }
}