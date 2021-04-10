package X;

public enum Za {
    UNKNOWN,
    LIVESTREAMING,
    VRCASTING,
    SCREENRECORDING;

    public static Za fromString(String str) {
        Za[] values = values();
        for (Za za : values) {
            if (za.name().equals(str)) {
                return za;
            }
        }
        return UNKNOWN;
    }
}
