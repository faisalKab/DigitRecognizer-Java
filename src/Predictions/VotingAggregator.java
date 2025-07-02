package Predictions;

/**
 * Aggregates test results and predicts the digit using a voting/weighting system.
 */
public class VotingAggregator {

    /**
     * Predicts the digit based on test totals using a voting/weighting system.
     * @param testXtotals [test][digit] array of total differences for each test and digit
     * @return PredictionResult containing the predicted digit and voting breakdown
     */
    public static PredictionResult predictDigit(double[][] testXtotals) {
        int numTests = testXtotals.length;
        int numDigits = testXtotals[0].length;

        // Find the minimum index for each test (best match)
        int[] minIndices = new int[numTests];
        for (int i = 0; i < numTests; i++) {
            minIndices[i] = 0;
            for (int j = 1; j < numDigits; j++) {
                if (testXtotals[i][j] < testXtotals[i][minIndices[i]]) {
                    minIndices[i] = j;
                }
            }
        }

        // Voting/weighting system
        int[][] weightedArray = new int[numTests][numDigits];
        int[] votesPerDigit = new int[numDigits];

        for (int test = 0; test < numTests; test++) {
            // Sort indices for this test based on their values (lowest is best)
            int[] sortedIndices = sortIndicesBasedOnValues(testXtotals[test]);
            for (int rank = 0; rank < sortedIndices.length; rank++) {
                int points = numDigits - rank;
                weightedArray[test][sortedIndices[rank]] = points;
            }
        }

        // Sum up the votes for each digit
        for (int test = 0; test < numTests; test++) {
            for (int digit = 0; digit < numDigits; digit++) {
                votesPerDigit[digit] += weightedArray[test][digit];
            }
        }

        // Find the digit with the highest votes
        int predictedDigit = 0;
        for (int i = 1; i < numDigits; i++) {
            if (votesPerDigit[i] > votesPerDigit[predictedDigit]) {
                predictedDigit = i;
            }
        }

        return new PredictionResult(predictedDigit, votesPerDigit, minIndices, testXtotals);
    }

    // Utility: Sorts indices of an array based on their values (ascending)
    private static int[] sortIndicesBasedOnValues(double[] values) {
        int length = values.length;
        int[] indices = new int[length];
        for (int i = 0; i < length; i++) {
            indices[i] = i;
        }
        // Simple bubble sort
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (values[indices[j]] > values[indices[j + 1]]) {
                    int temp = indices[j];
                    indices[j] = indices[j + 1];
                    indices[j + 1] = temp;
                }
            }
        }
        return indices;
    }
}