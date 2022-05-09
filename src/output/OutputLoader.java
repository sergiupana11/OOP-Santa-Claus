package output;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class OutputLoader {

    private OutputLoader() {
        // constructor for checkstyle
    }

    /**
     * Writes the output in the specified output file
     * @param outputData the data that needs to be written to output file
     * @param outputPath the path of the output file
     */
    public static void writeOutput(final OutputData outputData, final String outputPath)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputPath), outputData);
    }
}
