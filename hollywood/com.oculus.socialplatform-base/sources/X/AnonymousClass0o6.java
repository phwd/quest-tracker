package X;

/* renamed from: X.0o6  reason: invalid class name */
public enum AnonymousClass0o6 {
    INTERN_FIELD_NAMES(true),
    CANONICALIZE_FIELD_NAMES(true);
    
    public final boolean _defaultState;

    /* access modifiers changed from: public */
    AnonymousClass0o6(boolean z) {
        this._defaultState = z;
    }

    public static int collectDefaults() {
        AnonymousClass0o6[] values = values();
        int i = 0;
        for (AnonymousClass0o6 r1 : values) {
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
