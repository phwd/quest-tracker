package X;

import java.io.Serializable;

public final class PW implements Serializable {
    public static final PW A00;
    public static final PW A01 = new PW(true);
    public static final PW A02;
    public static final long serialVersionUID = -3271940633258788634L;
    public final boolean _cfgBigDecimalExact;

    static {
        PW pw = new PW(false);
        A02 = pw;
        A00 = pw;
    }

    public PW() {
        this(false);
    }

    public PW(boolean z) {
        this._cfgBigDecimalExact = z;
    }
}
