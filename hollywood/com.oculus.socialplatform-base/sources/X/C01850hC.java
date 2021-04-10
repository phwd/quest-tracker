package X;

import java.io.Serializable;

/* renamed from: X.0hC  reason: invalid class name and case insensitive filesystem */
public final class C01850hC implements AbstractC04560qf, Serializable {
    public static final C01850hC A00;
    public static final C01850hC A01 = new C01850hC(true);
    public static final C01850hC A02;
    public static final long serialVersionUID = -3271940633258788634L;
    public final boolean _cfgBigDecimalExact;

    static {
        C01850hC r2 = new C01850hC(false);
        A02 = r2;
        A00 = r2;
    }

    public C01850hC() {
        this(false);
    }

    public C01850hC(boolean z) {
        this._cfgBigDecimalExact = z;
    }
}
