package output;

import java.util.List;

public final class OutputData {

    private OutputData() {
        //constructor for checkstyle
    }

    private List<AnnualChildren> annualChildren;

    public OutputData(final List<AnnualChildren> annualChildren) {
        this.annualChildren = annualChildren;
    }

    public List<AnnualChildren> getAnnualChildren() {
        return annualChildren;
    }

    public void setAnnualChildren(final List<AnnualChildren> annualChildren) {
        this.annualChildren = annualChildren;
    }
}
