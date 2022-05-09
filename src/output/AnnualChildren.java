package output;

import java.util.ArrayList;
import java.util.List;

public class AnnualChildren {
    private List<ChildOutputData> children;

    public AnnualChildren() {
        children = new ArrayList<>();
    }

    public AnnualChildren(final List<ChildOutputData> children) {
        this.children = children;
    }

    public final List<ChildOutputData> getChildren() {
        return children;
    }

    public final void setChildren(final List<ChildOutputData> children) {
        this.children = children;
    }
}
