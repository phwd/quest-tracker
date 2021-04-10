package java.time.format;

public enum SignStyle {
    NORMAL,
    ALWAYS,
    NEVER,
    NOT_NEGATIVE,
    EXCEEDS_PAD;

    /* access modifiers changed from: package-private */
    public boolean parse(boolean positive, boolean strict, boolean fixedWidth) {
        int ordinal = ordinal();
        if (ordinal == 0) {
            return !positive || !strict;
        }
        if (ordinal == 1 || ordinal == 4) {
            return true;
        }
        return !strict && !fixedWidth;
    }
}
