package HandWritten;

import Predictions.VotingAggregator;
import Predictions.PredictionResult;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessor {

    public static void main(String[] args) {

        // Load the image files
        BufferedImage[][] images = new BufferedImage[10][7];
        String basePath = "mnist_data_7";

        // Load digit 0 manually
        for (int variant = 0; variant < 7; variant++) {
            String path = String.format("%s/0.%d.png", basePath, variant + 1);
            images[0][variant] = loadImage(path);
        }

        // Load digits 1 through 9
        for (int digit = 1; digit <= 9; digit++) {
            for (int variant = 0; variant < 7; variant++) {
                String path = String.format("%s/%d.%d.png", basePath, digit, variant + 1);
                images[digit][variant] = loadImage(path);
            }
        }

        // Convert images to pixel data
        int[][][][] pixelData = new int[10][7][][];
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[0].length; j++) {
                pixelData[i][j] = convertToPixelData(images[i][j]);
            }
        }

        // Edge trimming
        TrimEdge[][] trimmedEdgeInstance = new TrimEdge[10][7];
        int[][][][] trim = new int[10][7][][];
        for (int i = 0; i < pixelData.length; i++) {
            for (int j = 0; j < pixelData[0].length; j++) {
                trimmedEdgeInstance[i][j] = new TrimEdge(pixelData[i][j]);
                trim[i][j] = trimmedEdgeInstance[i][j].getTrimmedEdge();
            }
        }

        // Get quadrant patterns
        DoubleQuadrantPattern[][] quadrantPatternInstance = new DoubleQuadrantPattern[10][7];
        int[][][] quad = new int[10][7][];
        for (int i = 0; i < pixelData.length; i++) {
            for (int j = 0; j < pixelData[0].length; j++) {
                quadrantPatternInstance[i][j] = new DoubleQuadrantPattern(trim[i][j], trim[i][j].length, trim[i][j][0].length);
                quad[i][j] = quadrantPatternInstance[i][j].getDataSetTransitions();
            }
        }

        // Get pixel densities
        PixelDensity[][] pixelDensityInstance = new PixelDensity[10][7];
        double[][][] densities = new double[10][7][];
        for (int i = 0; i < pixelData.length; i++) {
            for (int j = 0; j < pixelData[0].length; j++) {
                pixelDensityInstance[i][j] = new PixelDensity(trim[i][j], trim[i][j].length, trim[i][j][0].length);
                densities[i][j] = pixelDensityInstance[i][j].getDensityData();
            }
        }

        // Get boundary edges
        BoundaryEdge[][] boundaryInstances = new BoundaryEdge[10][7];
        double[][][] bounds = new double[10][7][];
        for (int i = 0; i < pixelData.length; i++) {
            for (int j = 0; j < pixelData[0].length; j++) {
                boundaryInstances[i][j] = new BoundaryEdge(trim[i][j], trim[i][j].length, trim[i][j][0].length);
                bounds[i][j] = boundaryInstances[i][j].getBoundaryArray();
            }
        }

        // Get third quadrant patterns
        ThirdQuadrantPattern[][] thirdQuadrantInstances = new ThirdQuadrantPattern[10][7];
        int[][][] triples = new int[10][7][];
        for (int i = 0; i < pixelData.length; i++) {
            for (int j = 0; j < pixelData[0].length; j++) {
                thirdQuadrantInstances[i][j] = new ThirdQuadrantPattern(trim[i][j], trim[i][j].length, trim[i][j][0].length);
                triples[i][j] = thirdQuadrantInstances[i][j].getDataSetTransitions();
            }
        }

        // Get nearest neighbors
        NearestNeighbors[][] nearestNeighborInstances = new NearestNeighbors[10][7];
        double[][][] neighbors = new double[10][7][];
        for (int i = 0; i < pixelData.length; i++) {
            for (int j = 0; j < pixelData[0].length; j++) {
                nearestNeighborInstances[i][j] = new NearestNeighbors(trim[i][j], trim[i][j].length, trim[i][j][0].length);
                neighbors[i][j] = nearestNeighborInstances[i][j].getDataSetNeighbors();
            }
        }

        // Print all feature outputs for digit 0, variant 0
        int digit = 0;
        int selection = 0;
        quadrantPatternInstance[digit][selection].printQuadrantArray();
        System.out.println();
        pixelDensityInstance[digit][selection].printDensity();
        System.out.println();
        boundaryInstances[digit][selection].printBoundaryArray();
        System.out.println();
        thirdQuadrantInstances[digit][selection].printThirdQuadrantArray();
        System.out.println();
        nearestNeighborInstances[digit][selection].printNeighborsArray();

        System.out.println("\n===========================================\n===========================================\n");

        // Load test subject image
        BufferedImage imagex = loadTestSubject();
        int[][] data = convertToPixelData(imagex);
        TrimEdge trimtest = new TrimEdge(data);
        int[][] trimx = trimtest.getTrimmedEdge();

        // Extract features from test subject
        DoubleQuadrantPattern quadx = new DoubleQuadrantPattern(trimx, trimx.length, trimx[0].length);
        PixelDensity densityx = new PixelDensity(trimx, trimx.length, trimx[0].length);
        BoundaryEdge boundx = new BoundaryEdge(trimx, trimx.length, trimx[0].length);
        ThirdQuadrantPattern triplex = new ThirdQuadrantPattern(trimx, trimx.length, trimx[0].length);
        NearestNeighbors neighborx = new NearestNeighbors(trimx, trimx.length, trimx[0].length);

        // Run tests
        tests.TestRunner.runAllTests(
                quadx, quad,
                densityx, densities,
                boundx, bounds,
                triplex, triples,
                neighborx, neighbors
        );

        // --- Prediction Aggregation ---
        double[][] testXtotals = new double[5][10];

        // Quadrant
        for (int i = 0; i < 10; i++) {
            double sum = 0;
            for (int j = 0; j < 7; j++) {
                int total = 0;
                for (int k = 0; k < 8; k++) {
                    total += Math.abs(quadx.getDataSetTransitions()[k] - quad[i][j][k]);
                }
                sum += total;
            }
            testXtotals[0][i] = sum;
        }

        // Density
        for (int i = 0; i < 10; i++) {
            double sum = 0;
            for (int j = 0; j < 7; j++) {
                double total = 0;
                for (int k = 0; k < 16; k++) {
                    total += Math.abs(densityx.getDensityData()[k] - densities[i][j][k]);
                }
                sum += total;
            }
            testXtotals[1][i] = sum;
        }

        // Boundary
        for (int i = 0; i < 10; i++) {
            double sum = 0;
            for (int j = 0; j < 7; j++) {
                double total = 0;
                for (int k = 0; k < 16; k++) {
                    total += Math.abs(boundx.getBoundaryArray()[k] - bounds[i][j][k]);
                }
                sum += total;
            }
            testXtotals[2][i] = sum;
        }

        // Third Quadrant
        for (int i = 0; i < 10; i++) {
            double sum = 0;
            for (int j = 0; j < 7; j++) {
                double total = 0;
                for (int k = 0; k < 6; k++) {
                    total += Math.abs(triplex.getDataSetTransitions()[k] - triples[i][j][k]);
                }
                sum += total;
            }
            testXtotals[3][i] = sum;
        }

        // Nearest Neighbors
        for (int i = 0; i < 10; i++) {
            double sum = 0;
            for (int j = 0; j < 7; j++) {
                double total = 0;
                for (int k = 0; k < 14; k++) {
                    total += Math.abs(neighborx.getDataSetNeighbors()[k] - neighbors[i][j][k]);
                }
                sum += total;
            }
            testXtotals[4][i] = sum;
        }

        // Final Prediction
        PredictionResult result = VotingAggregator.predictDigit(testXtotals);
        result.printSummary();
    }

    // Load test subject image
    public static BufferedImage loadTestSubject() {
        return loadImage(new File("user_input.png"));
    }

    // New preferred method (File-based)
    private static BufferedImage loadImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            System.err.println("Failed to load: " + file.getPath());
            e.printStackTrace();
            return null;
        }
    }

    // Compatibility method (String path)
    private static BufferedImage loadImage(String filePath) {
        return loadImage(new File(filePath));
    }

    // Convert BufferedImage to grayscale 2D array
    private static int[][] convertToPixelData(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] pixelData = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int gray = (rgb >> 16) & 0xFF;
                pixelData[y][x] = gray;
            }
        }

        return pixelData;
    }
}
