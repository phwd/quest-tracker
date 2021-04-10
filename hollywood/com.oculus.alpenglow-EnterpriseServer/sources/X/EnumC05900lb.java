package X;

/* renamed from: X.0lb  reason: invalid class name and case insensitive filesystem */
public enum EnumC05900lb {
    AUTO_CLOSE_SOURCE(true),
    ALLOW_COMMENTS(false),
    ALLOW_UNQUOTED_FIELD_NAMES(false),
    ALLOW_SINGLE_QUOTES(false),
    ALLOW_UNQUOTED_CONTROL_CHARS(false),
    ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false),
    ALLOW_NUMERIC_LEADING_ZEROS(false),
    ALLOW_NON_NUMERIC_NUMBERS(false);
    
    public final boolean _defaultState;

    public boolean enabledByDefault() {
        return this._defaultState;
    }

    /* access modifiers changed from: public */
    EnumC05900lb(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        EnumC05900lb[] values = values();
        int i = 0;
        for (EnumC05900lb r1 : values) {
            if (r1.enabledByDefault()) {
                i |= r1.getMask();
            }
        }
        return i;
    }

    public int getMask() {
        return 1 << ordinal();
    }
}
