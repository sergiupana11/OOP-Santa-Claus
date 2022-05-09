package visitor;

import child.Child;
import database.Database;
import enums.Category;
import gift.Gift;
import input.GiftInputData;

public class YellowElfVisitor implements Visitor {
    @Override
    public final void visit(final Child child) {
        if (child.getReceivedGifts().isEmpty()) {
            for (Category preference : child.getGiftsPreferences()) {
                for (GiftInputData gift : Database.getDatabase().getSantaGiftsList()) {
                    if (gift.getCategory().equals(preference)) {
                        if (gift.getQuantity() > 0) {
                            child.getReceivedGifts().add(new Gift(gift.getProductName(),
                                                        gift.getPrice(), gift.getCategory()));
                            gift.setQuantity(gift.getQuantity() - 1);
                        }
                        return;
                    }
                }
            }
        }
    }
}
