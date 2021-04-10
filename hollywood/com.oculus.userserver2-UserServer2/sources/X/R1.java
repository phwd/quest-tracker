package X;

import java.io.Serializable;

public final class R1 implements AnonymousClass7o, Serializable {
    public static final R1 A00;
    public static final R1 A01 = new R1(true);
    public static final R1 A02;
    public static final long serialVersionUID = -3271940633258788634L;
    public final boolean _cfgBigDecimalExact;

    static {
        R1 r1 = new R1(false);
        A02 = r1;
        A00 = r1;
    }

    public R1() {
        this(false);
    }

    public R1(boolean z) {
        this._cfgBigDecimalExact = z;
    }
}
