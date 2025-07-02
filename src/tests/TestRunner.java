package tests;

import HandWritten.DoubleQuadrantPattern;
import HandWritten.PixelDensity;
import HandWritten.BoundaryEdge;
import HandWritten.ThirdQuadrantPattern;
import HandWritten.NearestNeighbors;

/**
 * Central runner for all feature tests.
 * Calls each test class in sequence to compare the test image features with the database.
 */
public class TestRunner {
    /**
     * Runs all feature tests for a given test image and database features.
     * @param quadx DoubleQuadrantPattern instance for the test image
     * @param quad Database quadrant features
     * @param densityx PixelDensity instance for the test image
     * @param densities Database pixel density features
     * @param boundx BoundaryEdge instance for the test image
     * @param bounds Database boundary edge features
     * @param triplex ThirdQuadrantPattern instance for the test image
     * @param triples Database third quadrant features
     * @param neighborx NearestNeighbors instance for the test image
     * @param neighbors Database nearest neighbor features
     */
    public static void runAllTests(
        DoubleQuadrantPattern quadx, int[][][] quad,
        PixelDensity densityx, double[][][] densities,
        BoundaryEdge boundx, double[][][] bounds,
        ThirdQuadrantPattern triplex, int[][][] triples,
        NearestNeighbors neighborx, double[][][] neighbors
    ) {
        QuadrantPatternTest.runTest(quadx, quad);
        PixelDensityTest.runTest(densityx, densities);
        BoundaryEdgeTest.runTest(boundx, bounds);
        ThirdQuadrantPatternTest.runTest(triplex, triples);
        NearestNeighborsTest.runTest(neighborx, neighbors);
    }

    /**
     * Example main method (for demonstration; actual data should be passed from ImageProcessor).
     */
    public static void main(String[] args) {
        // This main is a placeholder. Actual invocation should be from ImageProcessor after setup.
        System.out.println("TestRunner: Please run tests via ImageProcessor after setting up data.");
    }
}