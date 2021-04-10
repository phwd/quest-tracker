package X;

/* renamed from: X.pv  reason: case insensitive filesystem */
public enum EnumC0468pv {
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
    EnumC0468pv(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        EnumC0468pv[] values = values();
        int i = 0;
        for (EnumC0468pv pvVar : values) {
            if (pvVar.enabledByDefault()) {
                i |= pvVar.getMask();
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
