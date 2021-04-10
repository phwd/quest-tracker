package X;

import java.io.Serializable;

/* renamed from: X.0fv  reason: invalid class name and case insensitive filesystem */
public final class C03700fv implements AbstractC05960mE, Serializable {
    public static final C03700fv A00;
    public static final C03700fv A01 = new C03700fv(true);
    public static final C03700fv A02;
    public static final long serialVersionUID = -3271940633258788634L;
    public final boolean _cfgBigDecimalExact;

    static {
        C03700fv r2 = new C03700fv(false);
        A02 = r2;
        A00 = r2;
    }

    public C03700fv() {
        this(false);
    }

    public C03700fv(boolean z) {
        this._cfgBigDecimalExact = z;
    }
}
