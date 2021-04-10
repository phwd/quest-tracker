package X;

/* renamed from: X.0jb  reason: invalid class name */
public enum AnonymousClass0jb {
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
    AnonymousClass0jb(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        AnonymousClass0jb[] values = values();
        int i = 0;
        for (AnonymousClass0jb r1 : values) {
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
        return this._mask;
    }
}
