package X;

public enum NU {
    AUTO_CLOSE_SOURCE(true),
    ALLOW_COMMENTS(false),
    ALLOW_UNQUOTED_FIELD_NAMES(false),
    ALLOW_SINGLE_QUOTES(false),
    ALLOW_UNQUOTED_CONTROL_CHARS(false),
    ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false),
    ALLOW_NUMERIC_LEADING_ZEROS(false),
    ALLOW_NON_NUMERIC_NUMBERS(false);
    
    public final boolean _defaultState;

    /* access modifiers changed from: public */
    NU(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        NU[] values = values();
        int i = 0;
        for (NU nu : values) {
            if (nu.enabledByDefault()) {
                i |= nu.getMask();
            }
        }
        return i;
    }

    public boolean enabledByDefault() {
        return this._defaultState;
    }

    public int getMask() {
        return 1 << ordinal();
    }
}
