package X;

/* renamed from: X.0rg  reason: invalid class name and case insensitive filesystem */
public enum EnumC05000rg {
    WRITE_HEADER(true),
    WRITE_END_MARKER(false),
    ENCODE_BINARY_AS_7BIT(true),
    CHECK_SHARED_NAMES(true),
    CHECK_SHARED_STRING_VALUES(false);
    
    public final boolean _defaultState;
    public final int _mask = (1 << ordinal());

    /* access modifiers changed from: public */
    EnumC05000rg(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        EnumC05000rg[] values = values();
        int i = 0;
        for (EnumC05000rg r1 : values) {
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
