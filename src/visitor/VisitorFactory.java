package visitor;

import enums.ElvesType;

public final class VisitorFactory {

    private VisitorFactory() {
        //constructor for checkstyle
    }

    /**
     * Creates the type of elf visitor, depending on the type of elf
     * @return elf visitor of specified type
     */
    public static Visitor createVisitor(final ElvesType elf) {
        return switch (elf) {
            case PINK -> new PinkElfVisitor();
            case BLACK -> new BlackElfVisitor();
            case YELLOW -> new YellowElfVisitor();
            case WHITE -> new WhiteElfVisitor();
        };
    }
}
