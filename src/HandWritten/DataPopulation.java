package HandWritten;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DataPopulation {

    public static void main(String[] args) {
        final String directoryPath = "path/to/your/images"; // Set your images directory path here
        File dir = new File(directoryPath);

        // Filter to only include .png files if necessary
        File[] files = dir.listFiles((d, name) -> name.endsWith(".png"));

        // Check if files are found
        if (files == null || files.length == 0) {
            System.out.println("No files found");
            return;
        }

        // Initialize the BufferedImage array with the number of image files found
        BufferedImage[] images = new BufferedImage[files.length];

        // Load the images
        for (int i = 0; i < files.length; i++) {
            images[i] = loadImage(files[i].getPath());
        }

        // Rest of your code to process the images...
    }

    // Method to load an image from file
    public static BufferedImage loadImage(String filePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    // Method to convert an image to a 2D array of pixel data sets
    public static int[][] convertToPixelData(BufferedImage image) {
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


