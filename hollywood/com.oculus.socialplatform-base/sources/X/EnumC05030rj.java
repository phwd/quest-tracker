package X;

/* renamed from: X.0rj  reason: invalid class name and case insensitive filesystem */
public enum EnumC05030rj {
    REQUIRE_HEADER(true);
    
    public final boolean _defaultState;
    public final int _mask = (1 << ordinal());

    /* access modifiers changed from: public */
    EnumC05030rj(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        EnumC05030rj[] values = values();
        int i = 0;
        for (EnumC05030rj r1 : values) {
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
