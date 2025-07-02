package HandWritten;

public class NearestNeighbors {
    
    double[] dataSetNeighbors = new double[14];
    
    public NearestNeighbors(int[][] arr, int rows, int columns) {
        input(arr, rows, columns);
    }
    
    public void input(int[][] arr, int rows, int columns) {
        // Original datasets (0 to 7) as in the initial version
        // Additional datasets (8 to 15) for enhanced granularity

		//horizontalFlagLeftTop
		for(int j = 0; j < columns-1; j++) {	
			if(arr[rows/3][j] != arr[rows/3][j+1]) {
				dataSetNeighbors[0] = Math.sqrt(Math.pow(rows/3, 2) + Math.pow(j, 2));
				break;
			}
			else dataSetNeighbors[0] = 0;
		}
		
		//horizontalFlagLeftBottom
		for(int j = 0; j < columns-1; j++) {	
			if(arr[(2*rows)/3][j] != arr[(2*rows)/3][j+1]) {
				dataSetNeighbors[1] = Math.sqrt(Math.pow((2*rows)/3, 2) + Math.pow(j, 2));
				break;
			}
			else dataSetNeighbors[1] = 0;
		}
		
		//verticalFlagBottomLeft
		for(int i = rows-1; i > 0; i--) {	
			if(arr[i][columns/3] != arr[i-1][columns/3]) {
				dataSetNeighbors[2] = Math.sqrt(Math.pow(i, 2) + Math.pow(columns/3, 2));
				break;
			}
			else dataSetNeighbors[2] = 0;
		}
		
		//verticalFlagBottomRight
		for(int i = rows-1; i > 0; i--) {	
			if(arr[i][(2*columns)/3] != arr[i-1][(2*columns)/3]) {
				dataSetNeighbors[3] = Math.sqrt(Math.pow(i, 2) + Math.pow((2*columns)/3, 2));
				break;
			}
			else dataSetNeighbors[3] = 0;
		}
		
		//horizontalFlagRightBottom
		for(int j = columns-1; j > 0; j--) {	
			if(arr[(2*rows)/3][j] != arr[(2*rows)/3][j-1]) {
				dataSetNeighbors[4] = Math.sqrt(Math.pow((2*rows)/3, 2) + Math.pow(j, 2));
				break;
			}
			else dataSetNeighbors[4] = 0;
		}
		
		//horizontalFlagRightTop
		for(int j = columns-1; j > 0; j--) {	
			if(arr[rows/3][j] != arr[rows/3][j-1]) {
				dataSetNeighbors[5] = Math.sqrt(Math.pow(rows/3, 2) + Math.pow(j, 2));
				break;
			}
			else dataSetNeighbors[5] = 0;
		}
		
		//verticalFlagTopRight
		for(int i = 0; i < rows-1; i++) {	
			if(arr[i][(2*columns)/3] != arr[i+1][(2*columns)/3]) {
				dataSetNeighbors[6] = Math.sqrt(Math.pow(i, 2) + Math.pow((2*columns)/3, 2));
				break;
			}
			else dataSetNeighbors[6] = 0;
		}
		
		//verticalFlagTopLeft
		for(int i = 0; i < rows-1; i++) {	
			if(arr[i][columns/3] != arr[i+1][columns/3]) {
				dataSetNeighbors[7] = Math.sqrt(Math.pow(i, 2) + Math.pow(columns/3, 2));
				break;
			}
			else dataSetNeighbors[7] = 0;
		}

        // Additional horizontal checks at 1/4 and 3/4 of the height for both top and bottom halves
        for (int j = 0; j < columns - 1; j++) {
            if (arr[rows / 4][j] != arr[rows / 4][j + 1]) {
                dataSetNeighbors[8] = Math.sqrt(Math.pow(rows / 4, 2) + Math.pow(j, 2));
                break;
            } else dataSetNeighbors[8] = 0;
        }

        for (int j = 0; j < columns - 1; j++) {
            if (arr[3 * rows / 4][j] != arr[3 * rows / 4][j + 1]) {
                dataSetNeighbors[9] = Math.sqrt(Math.pow(3 * rows / 4, 2) + Math.pow(j, 2));
                break;
            } else dataSetNeighbors[9] = 0;
        }

        // Additional vertical checks at 1/4 and 3/4 of the width for both left and right sides
        for (int i = 0; i < rows - 1; i++) {
            if (arr[i][columns / 4] != arr[i + 1][columns / 4]) {
                dataSetNeighbors[10] = Math.sqrt(Math.pow(i, 2) + Math.pow(columns / 4, 2));
                break;
            } else dataSetNeighbors[10] = 0;
        }

        for (int i = 0; i < rows - 1; i++) {
            if (arr[i][3 * columns / 4] != arr[i + 1][3 * columns / 4]) {
                dataSetNeighbors[11] = Math.sqrt(Math.pow(i, 2) + Math.pow(3 * columns / 4, 2));
                break;
            } else dataSetNeighbors[11] = 0;
        }

        // Additional diagonal checks
        for (int i = 0, j = 0; i < rows - 1 && j < columns - 1; i++, j++) {
            if (arr[i][j] != arr[i + 1][j + 1]) {
                dataSetNeighbors[12] = Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2));
                break;
            } else dataSetNeighbors[12] = 0;
        }

        for (int i = rows - 1, j = 0; i > 0 && j < columns - 1; i--, j++) {
            if (arr[i][j] != arr[i - 1][j + 1]) {
                dataSetNeighbors[13] = Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2));
                break;
            } else dataSetNeighbors[13] = 0;
        }

        //add more checks for additional datasets, such as other diagonal lines or specific pattern points.

    }
    
    public double[] getDataSetNeighbors() {
        return dataSetNeighbors;
    }
    
    public void printNeighborsArray() {
        for (int i = 0; i < dataSetNeighbors.length; i++) {
            System.out.println("DataSet " + i + ": " + dataSetNeighbors[i]);
        }
    }
}
