package annualchange;

import child.ChildUpdate;
import enums.CityStrategyEnum;
import input.ChildInputData;
import input.GiftInputData;

import java.util.List;

/**
 * Class that contains all the data that is changed during a year
 */
public class AnnualChange {
    private Double newSantaBudget;
    private List<GiftInputData> newGifts;
    private List<ChildInputData> newChildren;
    private List<ChildUpdate> childrenUpdates;
    private CityStrategyEnum strategy;

    public final CityStrategyEnum getStrategy() {
        return strategy;
    }

    public final void setStrategy(final CityStrategyEnum strategy) {
        this.strategy = strategy;
    }

    public final Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public final  void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public final List<GiftInputData> getNewGifts() {
        return newGifts;
    }

    public final void setNewGifts(final List<GiftInputData> newGifts) {
        this.newGifts = newGifts;
    }

    public final List<ChildInputData> getNewChildren() {
        return newChildren;
    }

    public final void setNewChildren(final List<ChildInputData> newChildren) {
        this.newChildren = newChildren;
    }

    public final List<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    public final void setChildrenUpdates(final List<ChildUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }
}
