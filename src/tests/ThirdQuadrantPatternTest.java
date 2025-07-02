package tests;

import HandWritten.ThirdQuadrantPattern;

/**
 * Test class for ThirdQuadrantPattern feature extraction.
 * Contains methods to compare test image features with database features and print results.
 */
public class ThirdQuadrantPatternTest {
    /**
     * Runs the third quadrant pattern test and prints the results.
     * @param triplex The ThirdQuadrantPattern instance for the test image.
     * @param triples The database features for all digits and variants.
     */
    public static void runTest(ThirdQuadrantPattern triplex, int[][][] triples) {
        double[] test4Total = new double[10];
        double[][][] test4 = new double[10][7][7];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 6; k++) {
                    test4[i][j][k] = Math.abs(triplex.getDataSetTransitions()[k] - triples[i][j][k]);
                    test4[i][j][6] += test4[i][j][k];
                }
                test4Total[i] += test4[i][j][6];
            }
        }
        // Print results
        System.out.println("********************TEST 4 - TRIPLES********************");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print("[" + i + "][" + j + "] = ");
                for (int k = 0; k < 7; k++) {
                    System.out.printf("%.2f    ", test4[i][j][k]);
                }
                System.out.println();
            }
            System.out.printf("\t\t\t\t\t\t Total = %.2f\n\n", test4Total[i]);
        }
        System.out.println();
    }
}