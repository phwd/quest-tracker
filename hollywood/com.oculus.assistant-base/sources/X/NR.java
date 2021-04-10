package X;

public enum NR {
    INTERN_FIELD_NAMES(true),
    CANONICALIZE_FIELD_NAMES(true);
    
    public final boolean _defaultState;

    /* access modifiers changed from: public */
    NR(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        NR[] values = values();
        int i = 0;
        for (NR nr : values) {
            if (nr.enabledByDefault()) {
                i |= nr.getMask();
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
