package Predictions;

import java.io.PrintWriter;

/**
 * Encapsulates the result of a digit prediction.
 */
public class PredictionResult {
    private final int predictedDigit;
    private final int[] votesPerDigit;
    private final int[] minIndices; // Best match per test
    private final double[][] testTotals; // The raw totals for each test and digit

    public PredictionResult(int predictedDigit, int[] votesPerDigit, int[] minIndices, double[][] testTotals) {
        this.predictedDigit = predictedDigit;
        this.votesPerDigit = votesPerDigit;
        this.minIndices = minIndices;
        this.testTotals = testTotals;
    }

    public int getPredictedDigit() {
        return predictedDigit;
    }

    public int[] getVotesPerDigit() {
        return votesPerDigit;
    }

    public int[] getMinIndices() {
        return minIndices;
    }

    public double[][] getTestTotals() {
        return testTotals;
    }

    public void printSummary() {
    	
    	// chat gpt help method for gui response*********************
    	    try (PrintWriter out = new PrintWriter("result.txt")) {
    	        out.println("-------VOTES for highest scores-------");
    	        for (int i = 0; i < votesPerDigit.length; i++) {
    	            out.println(i + " = " + votesPerDigit[i] + " votes");
    	        }
    	        out.println();
    	        out.println("ULTIMATE RESULT = " + predictedDigit);
    	        out.print("Best match per test: ");
    	        for (int i = 0; i < minIndices.length; i++) {
    	            out.print("Test " + (i + 1) + ": " + minIndices[i] + "  ");
    	        }
    	        out.println();
    	    } catch (Exception e) {
    	        System.out.println("Failed to write result to file.");
    	    }
    	 //***********************************************************
    	
        System.out.println("\n-------VOTES for highest scores-------");
        for (int i = 0; i < votesPerDigit.length; i++) {
            System.out.println(i + " = " + votesPerDigit[i] + " votes");
        }
        System.out.println("\nULTIMATE RESULT = " + predictedDigit + "\n");
        System.out.print("Best match per test: ");
        for (int i = 0; i < minIndices.length; i++) {
            System.out.print("Test " + (i+1) + ": " + minIndices[i] + "  ");
        }
        System.out.println();
    }
    
    public String getSummaryString() {
    	
    	
    	
        StringBuilder sb = new StringBuilder();
        sb.append("-------VOTES for highest scores-------\n");
        for (int i = 0; i < votesPerDigit.length; i++) {
            sb.append(i).append(" = ").append(votesPerDigit[i]).append(" votes\n");
        }
        sb.append("\nULTIMATE RESULT = ").append(predictedDigit).append("\n\n");
        sb.append("Best match per test: ");
        for (int i = 0; i < minIndices.length; i++) {
            sb.append("Test ").append(i + 1).append(": ").append(minIndices[i]).append("  ");
        }
        sb.append("\n");
        return sb.toString();
    }
}