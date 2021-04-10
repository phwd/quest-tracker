package X;

public enum NS {
    AUTO_CLOSE_TARGET(true),
    AUTO_CLOSE_JSON_CONTENT(true),
    QUOTE_FIELD_NAMES(true),
    QUOTE_NON_NUMERIC_NUMBERS(true),
    WRITE_NUMBERS_AS_STRINGS(false),
    FLUSH_PASSED_TO_STREAM(true),
    ESCAPE_NON_ASCII(false);
    
    public final boolean _defaultState;
    public final int _mask = (1 << ordinal());

    /* access modifiers changed from: public */
    NS(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        NS[] values = values();
        int i = 0;
        for (NS ns : values) {
            if (ns.enabledByDefault()) {
                i |= ns.getMask();
            }
        }
        return i;
    }

    public boolean enabledByDefault() {
        return this._defaultState;
    }

    public int getMask() {
        return this._mask;
    }
}
