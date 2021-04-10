package X;

/* renamed from: X.0lX  reason: invalid class name and case insensitive filesystem */
public enum EnumC05860lX {
    INTERN_FIELD_NAMES(true),
    CANONICALIZE_FIELD_NAMES(true);
    
    public final boolean _defaultState;

    public boolean enabledByDefault() {
        return this._defaultState;
    }

    /* access modifiers changed from: public */
    EnumC05860lX(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        EnumC05860lX[] values = values();
        int i = 0;
        for (EnumC05860lX r1 : values) {
            if (r1.enabledByDefault()) {
                i |= r1.getMask();
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
}
