package X;

import java.io.Serializable;

public final class W6 implements V0, Serializable {
    public static final W6 A00;
    public static final W6 A01 = new W6(true);
    public static final W6 A02;
    public static final long serialVersionUID = -3271940633258788634L;
    public final boolean _cfgBigDecimalExact;

    static {
        W6 w6 = new W6(false);
        A02 = w6;
        A00 = w6;
    }

    public W6() {
        this(false);
    }

    public W6(boolean z) {
        this._cfgBigDecimalExact = z;
    }
}
