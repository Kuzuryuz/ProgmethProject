package usage;

public enum Status {
    NONE,
    BURN,
    FREEZE,
    PARALYSIS,
    POISON,
    SLEEP;

    public String toString() {
        switch (this) {
            case NONE:
                return "NORM";
            case BURN:
                return "BURN";
            case FREEZE:
                return "FRE";
            case PARALYSIS:
                return "PAR";
            case POISON:
                return "POI";
            case SLEEP:
                return "SlP";
            default:
                return super.toString();
        }
    }
}
