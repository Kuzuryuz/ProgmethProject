package usage;

public enum Status {
    NONE,
    BURN,
    FREEZE,
    PARALYSIS,
    POISON,
    SLEEP;

    public String toString() {
        return switch (this) {
            case NONE -> "NORM";
            case BURN -> "BRN";
            case FREEZE -> "FRZ";
            case PARALYSIS -> "PAR";
            case POISON -> "PSN";
            case SLEEP -> "SLP";
        };
    }
}
