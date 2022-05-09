package input;

import annualchange.AnnualChange;

import java.util.List;

/**
 * Class that keeps all input data parsed from input file
 */
public class Input {
    private Integer numberOfYears;
    private Double santaBudget;
    private InitialData initialData;
    private List<AnnualChange> annualChanges;

    public Input(final Integer numberOfYears, final Double santaBudget,
                 final InitialData initialData, final List<AnnualChange> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.initialData = initialData;
        this.annualChanges = annualChanges;
    }

    public Input() {
        ///default constructor for parsing input file
        ///removing it will cause the app to not work properly
    }

    public final Integer getNumberOfYears() {
        return numberOfYears;
    }

    public final void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public final InitialData getInitialData() {
        return initialData;
    }

    public final void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public final Double getSantaBudget() {
        return santaBudget;
    }

    public final void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public final List<AnnualChange> getAnnualChanges() {
        return annualChanges;
    }

    public final void setAnnualChanges(final List<AnnualChange> annualChanges) {
        this.annualChanges = annualChanges;
    }
}
