package X;

/* renamed from: X.pu  reason: case insensitive filesystem */
public enum EnumC0467pu {
    INTERN_FIELD_NAMES(true),
    CANONICALIZE_FIELD_NAMES(true);
    
    public final boolean _defaultState;

    /* access modifiers changed from: public */
    EnumC0467pu(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        EnumC0467pu[] values = values();
        int i = 0;
        for (EnumC0467pu puVar : values) {
            if (puVar.enabledByDefault()) {
                i |= puVar.getMask();
            }
        }
        return i;
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

    public boolean enabledByDefault() {
        return this._defaultState;
    }
}
