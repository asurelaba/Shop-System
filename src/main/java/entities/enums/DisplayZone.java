package entities.enums;

public enum DisplayZone {
    FRONT("Area near entrance"),
    CENTER("Area in the center of market asile"),
    BACK("Back of the market asile"),
    COUNTER("Area where customers are waiting in line at the counter");

    private final String description;

    DisplayZone(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
