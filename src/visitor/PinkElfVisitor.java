package visitor;

import child.Child;
import common.Constants;

public class PinkElfVisitor implements Visitor {

    @Override
    public final void visit(final Child child) {
        Double budget = child.getAssignedBudget();
        budget += budget * Constants.BUDGET_MODIFIER / Constants.PERCENT;
        child.setAssignedBudget(budget);
    }
}
