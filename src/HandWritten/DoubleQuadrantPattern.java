package HandWritten;
public class DoubleQuadrantPattern {
	
	int[] dataSetTransitions = new int[8];
	
	public DoubleQuadrantPattern(int[][] arr, int rows, int columns) {
		input(arr, rows, columns);
	}
	
	public void input(int[][] arr, int rows, int columns) {
		
		int counter = 0;
		int horizontalFlagTopLeft = 0;
		int horizontalFlagTopRight = 0;
		int horizontalFlagBottomLeft = 0;
		int horizontalFlagBottomRight = 0;
		int verticalFlagTopLeft = 0;
		int verticalFlagTopRight = 0;
		int verticalFlagBottomLeft = 0;
		int verticalFlagBottomRight = 0;
		
		//horizontalFlagTopLeft
		for(int i = 0; i < rows/2; i++) {
			for(int j = 0; j < (columns/2)-1; j++) {	
				if(arr[i][j] != arr[i][j+1]) counter++;
			}
			if(counter > horizontalFlagTopLeft) horizontalFlagTopLeft = counter;
			counter = 0;
		}
		
		//horizontalFlagTopRight
		for(int i = 0; i < rows/2; i++) {
			for(int j = columns/2; j < columns-1; j++) {	
				if(arr[i][j] != arr[i][j+1]) counter++;
			}
			if(counter > horizontalFlagTopRight) horizontalFlagTopRight = counter;
			counter = 0;
		}
		
		//horizontalFlagBottomLeft
		for(int i = rows/2; i < rows; i++) {
			for(int j = 0; j < (columns/2)-1; j++) {	
				if(arr[i][j] != arr[i][j+1]) counter++;
			}
			if(counter > horizontalFlagBottomLeft) horizontalFlagBottomLeft = counter;
			counter = 0;
		}
		
		
		//horizontalFlagBottomRight
		for(int i = rows/2; i < rows; i++) {
			for(int j = columns/2; j < columns-1; j++) {	
				if(arr[i][j] != arr[i][j+1]) counter++;
			}
			if(counter > horizontalFlagBottomRight) horizontalFlagBottomRight = counter;
			counter = 0;
		}
		
		
		//verticalFlagTopLeft
		for(int j = 0; j < columns/2; j++) {
			for(int i = 0; i < (rows/2)-1; i++) {	
				if(arr[i][j] != arr[i+1][j]) counter++;
			}
			if(counter > verticalFlagTopLeft) verticalFlagTopLeft = counter;
			counter = 0;
		}
		
		//verticalFlagTopRight
		for(int j = columns/2; j < columns; j++) {
			for(int i = 0; i < (rows/2)-1; i++) {	
				if(arr[i][j] != arr[i+1][j]) counter++;
			}
			if(counter > verticalFlagTopRight) verticalFlagTopRight = counter;
			counter = 0;
		}
		
		//verticalFlagBottomLeft
		for(int j = 0; j < columns/2; j++) {
			for(int i = rows/2; i < rows-1; i++) {	
				if(arr[i][j] != arr[i+1][j]) counter++;
			}
			if(counter > verticalFlagBottomLeft) verticalFlagBottomLeft = counter;
			counter = 0;
		}
		
		//verticalFlagBottomRight
		for(int j = columns/2; j < columns; j++) {
			for(int i = rows/2; i < rows-1; i++) {	
				if(arr[i][j] != arr[i+1][j]) counter++;
			}
			if(counter > verticalFlagBottomRight) verticalFlagBottomRight = counter;
			counter = 0;
		}
		
	/*	
		System.out.println("horizontalFlagTopLeft: " + horizontalFlagTopLeft +
		 "\nhorizontalFlagTopRight: " + horizontalFlagTopRight +
		 "\nhorizontalFlagBottomLeft: " + horizontalFlagBottomLeft +
		 "\nhorizontalFlagBottomRight: " + horizontalFlagBottomRight +
		 "\nverticalFlagTopLeft: " + verticalFlagTopLeft +
		 "\nverticalFlagTopRight: " + verticalFlagTopRight +
		 "\nverticalFlagBottomLeft: " + verticalFlagBottomLeft +
		 "\nverticalFlagBottomRight: " + verticalFlagBottomRight);
		System.out.println();
	*/	
		
        // Correctly populating dataSetTransitions array
        dataSetTransitions[0] = horizontalFlagTopLeft;
        dataSetTransitions[1] = horizontalFlagTopRight;
        dataSetTransitions[2] = horizontalFlagBottomLeft;
        dataSetTransitions[3] = horizontalFlagBottomRight;
        dataSetTransitions[4] = verticalFlagTopLeft;
        dataSetTransitions[5] = verticalFlagTopRight;
        dataSetTransitions[6] = verticalFlagBottomLeft;
        dataSetTransitions[7] = verticalFlagBottomRight;
		
	}	
	
	// Returns Array of the 8 data sets of the digit
    public int[] getDataSetTransitions(){
    	return dataSetTransitions;
    }
    
    // Print Array if the 8 data sets of the digit instance
    public void printQuadrantArray() {
		System.out.println("horizontalFlagTopLeft: " + dataSetTransitions[0] +
				 "\nhorizontalFlagTopRight: " + dataSetTransitions[1] +
				 "\nhorizontalFlagBottomLeft: " + dataSetTransitions[2] +
				 "\nhorizontalFlagBottomRight: " + dataSetTransitions[3] +
				 "\nverticalFlagTopLeft: " + dataSetTransitions[4] +
				 "\nverticalFlagTopRight: " + dataSetTransitions[5] +
				 "\nverticalFlagBottomLeft: " + dataSetTransitions[6] +
				 "\nverticalFlagBottomRight: " + dataSetTransitions[7]);
				System.out.println();
    }
   

}
