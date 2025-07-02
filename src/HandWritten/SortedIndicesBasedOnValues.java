package HandWritten;
public class SortedIndicesBasedOnValues {
	
	// Sorts the indices of the array based on their values
    public static int[] sortIndicesBasedOnValues(double[] values) {
        int length = values.length;
        int[] indices = new int[length];
        for (int i = 0; i < length; i++) {
            indices[i] = i;
        }

        // Simple bubble sort on the indices array based on the values array
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (values[indices[j]] > values[indices[j + 1]]) {
                    // Swap the indices
                    int temp = indices[j];
                    indices[j] = indices[j + 1];
                    indices[j + 1] = temp;
                }
            }
        }
        return indices;
     }

}
