package X;

/* renamed from: X.0lY  reason: invalid class name and case insensitive filesystem */
public enum EnumC05870lY {
    AUTO_CLOSE_TARGET(true),
    AUTO_CLOSE_JSON_CONTENT(true),
    QUOTE_FIELD_NAMES(true),
    QUOTE_NON_NUMERIC_NUMBERS(true),
    WRITE_NUMBERS_AS_STRINGS(false),
    FLUSH_PASSED_TO_STREAM(true),
    ESCAPE_NON_ASCII(false);
    
    public final boolean _defaultState;
    public final int _mask = (1 << ordinal());

    public boolean enabledByDefault() {
        return this._defaultState;
    }

    public int getMask() {
        return this._mask;
    }

    /* access modifiers changed from: public */
    EnumC05870lY(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        EnumC05870lY[] values = values();
        int i = 0;
        for (EnumC05870lY r1 : values) {
            if (r1.enabledByDefault()) {
                i |= r1.getMask();
            }
        }
        return i;
    }
}
