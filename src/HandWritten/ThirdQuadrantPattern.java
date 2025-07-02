package HandWritten;
public class ThirdQuadrantPattern {
	
	int[] dataSetTransitions = new int[6];
	
	public ThirdQuadrantPattern(int[][] arr, int rows, int columns) {
		input(arr, rows, columns);
	}
	
	public void input(int[][] arr, int rows, int columns) {
		
		int counter = 0;
		int horizontalFlag1 = 0;
		int horizontalFlag2 = 0;
		int horizontalFlag3 = 0;
		int verticalFlag1 = 0;
		int verticalFlag2 = 0;
		int verticalFlag3 = 0;
		
		//horizontalFlag1
		for(int i = 0; i < rows/3; i++) {
			for(int j = 0; j < columns-1; j++) {	
				if(arr[i][j] != arr[i][j+1]) counter++;
			}
			if(counter > horizontalFlag1) horizontalFlag1 = counter;
			counter = 0;
		}
		
		//horizontalFlag2
		for(int i = rows/3; i < (2*rows)/3; i++) {
			for(int j = 0; j < columns-1; j++) {	
				if(arr[i][j] != arr[i][j+1]) counter++;
			}
			if(counter > horizontalFlag2) horizontalFlag2 = counter;
			counter = 0;
		}
		
		//horizontalFlag3
		for(int i = (2*rows)/3; i < rows; i++) {
			for(int j = 0; j < (columns/2)-1; j++) {	
				if(arr[i][j] != arr[i][j+1]) counter++;
			}
			if(counter > horizontalFlag3) horizontalFlag3 = counter;
			counter = 0;
		}
		
		
		//verticalFlagTopLeft
		for(int j = 0; j < columns/3; j++) {
			for(int i = 0; i < rows-1; i++) {	
				if(arr[i][j] != arr[i+1][j]) counter++;
			}
			if(counter > verticalFlag1) verticalFlag1 = counter;
			counter = 0;
		}
		
		//verticalFlagTopRight
		for(int j = columns/3; j < (2*columns)/3; j++) {
			for(int i = 0; i < rows-1; i++) {	
				if(arr[i][j] != arr[i+1][j]) counter++;
			}
			if(counter > verticalFlag2) verticalFlag2 = counter;
			counter = 0;
		}
		
		//verticalFlagBottomLeft
		for(int j = (2*columns)/3; j < columns; j++) {
			for(int i = 0; i < rows-1; i++) {	
				if(arr[i][j] != arr[i+1][j]) counter++;
			}
			if(counter > verticalFlag3) verticalFlag3 = counter;
			counter = 0;
		}

        // Correctly populating dataSetTransitions array
        dataSetTransitions[0] = horizontalFlag1;
        dataSetTransitions[1] = horizontalFlag2;
        dataSetTransitions[2] = horizontalFlag3;
        dataSetTransitions[3] = verticalFlag1;
        dataSetTransitions[4] = verticalFlag2;
        dataSetTransitions[5] = verticalFlag3;
		
	}	
	
	// Returns Array of the 6 data sets of the digit
    public int[] getDataSetTransitions(){
    	return dataSetTransitions;
    }
    
    // Print Array if the 6 data sets of the digit instance
    public void printThirdQuadrantArray() {
		System.out.println("horizontalFlag1: " + dataSetTransitions[0] +
						 "\nhorizontalFlag2: " + dataSetTransitions[1] +
						 "\nhorizontalFlag3: " + dataSetTransitions[2] +
						 "\nverticalFlag1: " + dataSetTransitions[3] +
						 "\nverticalFlag2: " + dataSetTransitions[4] +
						 "\nverticalFlag3: " + dataSetTransitions[5]);
		System.out.println();
    }
   

}
