package X;

/* renamed from: X.9s  reason: invalid class name */
public enum AnonymousClass9s {
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
    AnonymousClass9s(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        AnonymousClass9s[] values = values();
        int i = 0;
        for (AnonymousClass9s r1 : values) {
            if (r1.enabledByDefault()) {
                i |= r1.getMask();
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
