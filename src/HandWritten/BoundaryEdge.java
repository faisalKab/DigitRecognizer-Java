package HandWritten;

public class BoundaryEdge {
    
    double[] boundaryArray = new double[16];
    
    double top1 = 0.0;
    double top2 = 0.0;
    double top3 = 0.0;
    double top4 = 0.0;

    double right1 = 0.0;
    double right2 = 0.0;
    double right3 = 0.0;
    double right4 = 0.0;

    double bottom1 = 0.0;
    double bottom2 = 0.0;
    double bottom3 = 0.0;
    double bottom4 = 0.0;

    double left1 = 0.0;
    double left2 = 0.0;
    double left3 = 0.0;
    double left4 = 0.0;

    public BoundaryEdge(int[][] arr, int rows, int columns) {
        input(arr, rows, columns);
    }

    public void input(int[][] array, int rows, int columns) {
        //top stuff
        for(int i = 0; i < rows-1; i++) {
            if(array[i][columns/5] == array[i+1][columns/5]) {
                top1++;
            }
            else break;
        }
        for(int i = 0; i < rows-1; i++) {
            if(array[i][2*columns/5] == array[i+1][2*columns/5]) {
                top2++;
            }
            else break;
        }
        for(int i = 0; i < rows-1; i++) {
            if(array[i][3*columns/5] == array[i+1][3*columns/5]) {
                top3++;
            }
            else break;
        }
        for(int i = 0; i < rows-1; i++) {
            if(array[i][4*columns/5] == array[i+1][4*columns/5]) {
                top4++;
            }
            else break;
        }
        //bottom stuff        
        for(int i = rows-1; i > 0; i--) {
            if(array[i][columns/5] == array[i-1][columns/5]) {
                bottom1++;
            }
            else break;
        }
        for(int i = rows-1; i > 0; i--) {
            if(array[i][2*columns/5] == array[i-1][2*columns/5]) {
                bottom2++;
            }
            else break;
        }
        for(int i = rows-1; i > 0; i--) {
            if(array[i][3*columns/5] == array[i-1][3*columns/5]) {
                bottom3++;
            }
            else break;
        }
        for(int i = rows-1; i > 0; i--) {
            if(array[i][4*columns/5] == array[i-1][4*columns/5]) {
                bottom4++;
            }
            else break;
        }
        //left stuff
        for(int i = 0; i < columns-1; i++) {
            if(array[rows/5][i] == array[rows/5][i+1]) {
                left1++;
            }
            else break;
        }
        for(int i = 0; i < columns-1; i++) {
            if(array[2*rows/5][i] == array[2*rows/5][i+1]) {
                left2++;
            }
            else break;
        }
        for(int i = 0; i < columns-1; i++) {
            if(array[3*rows/5][i] == array[3*rows/5][i+1]) {
                left3++;
            }
            else break;
        }
        for(int i = 0; i < columns-1; i++) {
            if(array[4*rows/5][i] == array[4*rows/5][i+1]) {
                left4++;
            }
            else break;
        }
        //right stuff
        for(int i = columns-1; i > 0; i--) {
            if(array[rows/5][i] == array[rows/5][i-1]) {
                right1++;
            }
            else break;
        }
        for(int i = columns-1; i > 0; i--) {
            if(array[2*rows/5][i] == array[2*rows/5][i-1]) {
                right2++;
            }
            else break;
        }
        for(int i = columns-1; i > 0; i--) {
            if(array[3*rows/5][i] == array[3*rows/5][i-1]) {
                right3++;
            }
            else break;
        }
        for(int i = columns-1; i > 0; i--) {
            if(array[4*rows/5][i] == array[4*rows/5][i-1]) {
                right4++;
            }
            else break;
        }
        boundaryArray[0] = top1 / rows;
        boundaryArray[1] = top2 / rows;
        boundaryArray[2] = top3 / rows;
        boundaryArray[3] = top4 / rows;
        boundaryArray[4] = right1 / columns;
        boundaryArray[5] = right2 / columns;
        boundaryArray[6] = right3 / columns;
        boundaryArray[7] = right4 / columns;
        boundaryArray[8] = bottom1 / rows;
        boundaryArray[9] = bottom2 / rows;
        boundaryArray[10] = bottom3 / rows;
        boundaryArray[11] = bottom4 / rows;
        boundaryArray[12] = left1 / columns;
        boundaryArray[13] = left2 / columns;
        boundaryArray[14] = left3 / columns;
        boundaryArray[15] = left4 / columns;
    }
    public double[] getBoundaryArray() {
        return boundaryArray;
    }
    public void printBoundaryArray() {
        for(int i = 0; i < boundaryArray.length; i++) {
            System.out.println(i + " = " + boundaryArray[i]);
        }System.out.println();
    }
}