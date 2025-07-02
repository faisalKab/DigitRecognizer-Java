package HandWritten;

public class ImageReducer {
    
    int[][] newGrid = new int[10][10];
    
    public ImageReducer(int[][] arr, int rows, int columns) {
        input(arr, rows, columns);
    }
    
    // Method to reduce the image into a 10x10 grid
    public void input(int[][] image, int rows, int columns) {
        // Calculate the size of each cell
        int cellHeight = rows / 10;
        int cellWidth = columns / 10;
        
        // Iterate through each cell in the grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int zeroCount = 0;
                
                // Determine the boundaries of the current cell
                int startX = i * cellHeight;
                int endX = (i + 1) * cellHeight;
                int startY = j * cellWidth;
                int endY = (j + 1) * cellWidth;
                
                // Adjust for the rightmost and bottommost edges
                if (i == 9) {
                    endX = rows;
                }
                if (j == 9) {
                    endY = columns;
                }
                
                // Count zeros in the current cell
                for (int x = startX; x < endX; x++) {
                    for (int y = startY; y < endY; y++) {
                        if (image[x][y] == 0) {
                            zeroCount++;
                        }
                    }
                }
                
                // Determine the value for the new grid cell
                newGrid[i][j] = zeroCount > ((endX - startX) * (endY - startY)) / 2 ? 0 : 1;
            }
        }
    }
    
    public int[][] getImageReducer() {
        return newGrid;
    }
    
    public void printImageReducer() {
        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid[i].length; j++) {
                System.out.print(newGrid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
