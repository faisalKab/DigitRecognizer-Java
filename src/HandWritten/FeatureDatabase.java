package HandWritten;

/**
 * Provides access to the precomputed database features for digit recognition.
 * In a real project, you might load these from files or a database.
 * For now, you can initialize them statically or add loading logic later.
 */
public class FeatureDatabase {
    // Example: Replace these with your actual data loading logic
    private static int[][][] quad = new int[10][7][8];
    private static double[][][] densities = new double[10][7][16];
    private static double[][][] bounds = new double[10][7][16];
    private static int[][][] triples = new int[10][7][6];
    private static double[][][] neighbors = new double[10][7][14];

    static {
        // TODO: Load your actual feature data here.
        // For now, these are just empty arrays.
        // You can load from files, or initialize with real data.
    }

    public static int[][][] getQuad() { return quad; }
    public static double[][][] getDensities() { return densities; }
    public static double[][][] getBounds() { return bounds; }
    public static int[][][] getTriples() { return triples; }
    public static double[][][] getNeighbors() { return neighbors; }
}