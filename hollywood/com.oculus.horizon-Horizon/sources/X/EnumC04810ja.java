package X;

/* renamed from: X.0ja  reason: invalid class name and case insensitive filesystem */
public enum EnumC04810ja {
    INTERN_FIELD_NAMES(true),
    CANONICALIZE_FIELD_NAMES(true);
    
    public final boolean _defaultState;

    /* access modifiers changed from: public */
    EnumC04810ja(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        EnumC04810ja[] values = values();
        int i = 0;
        for (EnumC04810ja r1 : values) {
            if (r1.enabledByDefault()) {
                i |= r1.getMask();
            }
        }
        return i;
    }

    public boolean enabledByDefault() {
        return this._defaultState;
    }

    public boolean enabledIn(int i) {
        if ((i & getMask()) != 0) {
            return true;
        }
        return false;
    }

    public int getMask() {
        return 1 << ordinal();
    }
}
