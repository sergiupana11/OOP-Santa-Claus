package main;

import checker.Checker;
import common.Constants;
import input.Input;
import input.InputLoader;
import output.OutputData;
import output.OutputLoader;
import solver.Solver;

import java.io.IOException;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }

    /**
     * This method is used to call the checker which calculates the score
     *
     * @param args the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {

        for (int i = 1; i <= Constants.TESTS_NUMBER; i++) {
            // creating input path and reading from input file
            String inputPath = Constants.INPUT_PATH + i + Constants.FILE_EXTENSION;
            Input input = InputLoader.readInput(inputPath);

            // Solver class processes the data and returns it to an OutputData object
            Solver solver = new Solver();
            OutputData outputData = solver.solve(input);


            // creating output path and writing to output file
            String outputPath = Constants.OUTPUT_PATH + i + Constants.FILE_EXTENSION;
            OutputLoader.writeOutput(outputData, outputPath);
        }

        Checker.calculateScore();
    }
}
