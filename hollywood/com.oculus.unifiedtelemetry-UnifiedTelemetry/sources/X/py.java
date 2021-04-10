package X;

public enum py {
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
    py(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        py[] values = values();
        int i = 0;
        for (py pyVar : values) {
            if (pyVar.enabledByDefault()) {
                i |= pyVar.getMask();
            }
        }
        return i;
    }

    public int getMask() {
        return 1 << ordinal();
    }

    public boolean enabledByDefault() {
        return this._defaultState;
    }
}
