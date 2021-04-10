package X;

import java.io.Serializable;

/* renamed from: X.0pU  reason: invalid class name and case insensitive filesystem */
public final class C04180pU implements Serializable {
    public static final AbstractC04590qj[] A00 = new AbstractC04590qj[0];
    public static final AbstractC04640qs[] A01 = new AbstractC04640qs[0];
    public static final long serialVersionUID = 1;
    public final AbstractC04640qs[] _additionalKeySerializers;
    public final AbstractC04640qs[] _additionalSerializers;
    public final AbstractC04590qj[] _modifiers;

    public C04180pU() {
        this(null, null, null);
    }

    public C04180pU(AbstractC04640qs[] r1, AbstractC04640qs[] r2, AbstractC04590qj[] r3) {
        this._additionalSerializers = r1 == null ? A01 : r1;
        this._additionalKeySerializers = r2 == null ? A01 : r2;
        this._modifiers = r3 == null ? A00 : r3;
    }
}
