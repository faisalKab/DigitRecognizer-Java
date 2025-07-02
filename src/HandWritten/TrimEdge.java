package HandWritten;

public class TrimEdge {

    int newRows;
    int newColumns;
    int[][] edgeArray;
    int[] extremes;

    public TrimEdge(int[][] arr) {
        input(arr, arr.length, arr[0].length);
    }

    public void input(int[][] arr, int rows, int columns) {
        // Convert Array to array that holds only 1s and 0s
        int[][] Array = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (arr[i][j] > 130) Array[i][j] = 1;
                else Array[i][j] = 0;
            }
        }

        // Initialize with opposite extremes for comparison
        int topMostInitial = rows;
        int leftMostInitial = columns;
        int rightMostInitial = -1;
        int bottomMostInitial = -1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (Array[i][j] == 0) { // Assuming non-blank is any value > 0
                    if (i < topMostInitial) topMostInitial = i;
                    if (i > bottomMostInitial) bottomMostInitial = i;
                    if (j < leftMostInitial) leftMostInitial = j;
                    if (j > rightMostInitial) rightMostInitial = j;
                }
            }
        }

        topMostInitial -= 1;
        bottomMostInitial += 1;
        leftMostInitial -= 1;
        rightMostInitial += 1;

        // Now calculate the size of the trimmed edges
        this.newRows = bottomMostInitial - topMostInitial + 1;
        this.newColumns = rightMostInitial - leftMostInitial + 1;

        // Make the new trimmed Array
        this.edgeArray = new int[newRows][newColumns];
        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < newColumns; j++) {
                edgeArray[i][j] = Array[i + topMostInitial][j + leftMostInitial];
            }
        }

        // Store the extremes
        extremes = new int[]{topMostInitial, leftMostInitial, bottomMostInitial, rightMostInitial};
    }

    // Return the trimmed edge array
    public int[][] getTrimmedEdge() {
        return edgeArray;
    }

    // Return the extreme values for edge detection
    public int[] getExtremes() {
        return extremes;
    }
    
    // Print the new trimmed digits in binary
    public void printEdgeArray() {
        // Printing the trimmed edge array
        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < newColumns; j++) {
                System.out.print(edgeArray[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    //print out the extreme edge values
    public void printExtremes() {
        System.out.println("topMost: " + extremes[0] + ", leftMost: " + extremes[1] + ", bottomMost: " + extremes[2] + ", rightMost: " + extremes[3]);
    }
}