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
        images[0][0] = loadImage("data/mnist_data_7/0.1.png");
        images[0][1] = loadImage("data/mnist_data_7/0.2.png");
        images[0][2] = loadImage("data/mnist_data_7/0.3.png");
        images[0][3] = loadImage("data/mnist_data_7/0.4.png");
        images[0][4] = loadImage("data/mnist_data_7/0.5.png");
        images[0][5] = loadImage("data/mnist_data_7/0.6.png");
        images[0][6] = loadImage("data/mnist_data_7/0.7.png");

        // Repeat the pattern for digits 1 through 9
        for (int digit = 1; digit <= 9; digit++) {
            for (int variant = 0; variant < 7; variant++) {
                String filePath = String.format("data/mnist_data_7/%d.%d.png", digit, variant + 1);
                images[digit][variant] = loadImage(filePath);
            }
        }

        // Convert the images to pixel data sets
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
	        	trim[i][j] = trimmedEdgeInstance[i][j].getTrimmedEdge();		// Process each image using the TimeEdge class
        	}
        }
        
        // Get metrics for each picture. 8 values for each quadrant for each picture
        DoubleQuadrantPattern[][] quadrantPatternInstance = new DoubleQuadrantPattern[10][7];
        int[][][] quad = new int[10][7][];
        for (int i = 0; i < pixelData.length; i++) {
        	for (int j = 0; j < pixelData[0].length; j++) {
        		quadrantPatternInstance[i][j] = new DoubleQuadrantPattern(trim[i][j], trim[i][j].length, trim[i][j][0].length);
        		quad[i][j] = quadrantPatternInstance[i][j].getDataSetTransitions();		// Process each image using the TimeEdge class
        	}
        }
        
        // Get 16 pixel density for each picture
        PixelDensity[][] pixelDensityInstance = new PixelDensity[10][7];
        double[][][] densities = new double[10][7][];
        for (int i = 0; i < pixelData.length; i++) {
        	for (int j = 0; j < pixelData[0].length; j++) {
        		pixelDensityInstance[i][j] = new PixelDensity(trim[i][j], trim[i][j].length, trim[i][j][0].length);
        		densities[i][j] = pixelDensityInstance[i][j].getDensityData();		// Process each image using the TimeEdge class
        	}
        }
        
        // Get 16 boundary edge detections for each picture
        BoundaryEdge[][] boundaryInstances = new BoundaryEdge[10][7];
        double[][][] bounds = new double[10][7][];
        for (int i = 0; i < pixelData.length; i++) {
        	for (int j = 0; j < pixelData[0].length; j++) {
        		boundaryInstances[i][j] = new BoundaryEdge(trim[i][j], trim[i][j].length, trim[i][j][0].length);
        		bounds[i][j] = boundaryInstances[i][j].getBoundaryArray();
        	}
        }
        
        // Get 6 ThirdQuadrantPattern detections for each picture
        ThirdQuadrantPattern[][] thirdQuadrantInstances = new ThirdQuadrantPattern[10][7];
        int[][][] triples = new int[10][7][];
        for (int i = 0; i < pixelData.length; i++) {
        	for (int j = 0; j < pixelData[0].length; j++) {
        		thirdQuadrantInstances[i][j] = new ThirdQuadrantPattern(trim[i][j], trim[i][j].length, trim[i][j][0].length);
        		triples[i][j] = thirdQuadrantInstances[i][j].getDataSetTransitions();
        	}
        }
        
        // Get 14 NearestNeighbors values for each picture
        NearestNeighbors[][] nearestNeighborInstances = new NearestNeighbors[10][7];
        double[][][] neighbors = new double[10][7][];
        for (int i = 0; i < pixelData.length; i++) {
        	for (int j = 0; j < pixelData[0].length; j++) {
        		nearestNeighborInstances[i][j] = new NearestNeighbors(trim[i][j], trim[i][j].length, trim[i][j][0].length);
        		neighbors[i][j] = nearestNeighborInstances[i][j].getDataSetNeighbors();
        	}
        }
        
        //printing out all test values for `digit` and its iteration `selection` this is for reference only
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
        
        //loading up test subject image
        BufferedImage imagex = loadTestSubject();
        //BufferedImage imagex = loadImage("exp8.2.png");
        int[][] data = convertToPixelData(imagex);
        TrimEdge trimtest = new TrimEdge(data);
        int [][] trimx = trimtest.getTrimmedEdge();
        
        //All test processes for test subject
        DoubleQuadrantPattern quadx = new DoubleQuadrantPattern(trimx, trimx.length, trimx[0].length);  	//get quad values for test subject
        PixelDensity densityx = new PixelDensity(trimx, trimx.length, trimx[0].length);						//get density values for test subject
        BoundaryEdge boundx = new BoundaryEdge(trimx, trimx.length, trimx[0].length);						//get boundary values for test subject
        ThirdQuadrantPattern triplex = new ThirdQuadrantPattern(trimx, trimx.length, trimx[0].length);		//get thirdQuad values for test subject
        NearestNeighbors neighborx = new NearestNeighbors(trimx, trimx.length, trimx[0].length);			//get neighbor values for test subject
        
        // Run all feature tests using the new TestRunner class
        // This replaces the previous in-line test and print logic
        tests.TestRunner.runAllTests(
            quadx, quad,
            densityx, densities,
            boundx, bounds,
            triplex, triples,
            neighborx, neighbors
        );
        
        
        
        
        
     // --- Prediction Aggregation Logic ---

     // 1. Calculate testXtotals (totals for each test and digit)
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

     // 2. Call VotingAggregator to get the prediction
     PredictionResult result = VotingAggregator.predictDigit(testXtotals);

     // 3. Print the voting breakdown and final prediction
     result.printSummary();
        
    }
    
    
    //**************using test subject as the user different method made on june 2025*****************///
    public static BufferedImage loadTestSubject() {
    	//return loadImage("exp8.2.png");   // remember this line for the future because this is where you manually choose a file without userinput. 
    	return loadImage("user_input.png");
    }
    //************************************************************************************************///
    

    // Method to load an image from file
    private static BufferedImage loadImage(String filePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    


    // Method to convert an image to a 2D array of pixel data sets
    private static int[][] convertToPixelData(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] pixelData = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get the RGB value of the pixel at (x, y)
                int rgb = image.getRGB(x, y);
                // Convert RGB to grayscale
                int gray = (rgb >> 16) & 0xFF; // Red channel
                pixelData[y][x] = gray;
            }
        }

        return pixelData;
    }
} 