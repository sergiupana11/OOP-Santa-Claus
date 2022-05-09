package input;

import annualchange.AnnualChange;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Class used to parse input ".json" file
 */
public final class InputLoader {

    private InputLoader() {
        ///constructor for checkstyle
    }

    /**
     *
     * @param inputPath path to input file
     * @return Input object, which contains all data parsed from input file
     */
    public static Input readInput(final String inputPath) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        Input input = objectMapper.readValue(new File(inputPath), Input.class);
        Integer numberOfYears = input.getNumberOfYears();
        Double santaBudget = input.getSantaBudget();
        InitialData initialData = input.getInitialData();
        List<AnnualChange> annualChanges = input.getAnnualChanges();

        return new Input(numberOfYears, santaBudget, initialData, annualChanges);
    }
}
