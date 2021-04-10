package X;

import java.io.Serializable;

/* renamed from: X.0pS  reason: invalid class name and case insensitive filesystem */
public final class C04160pS implements Serializable {
    public static final AbstractC03990ow[] A00 = new AbstractC03990ow[0];
    public static final AbstractC04220pZ[] A01 = new AbstractC04220pZ[0];
    public static final AbstractC04260pg[] A02 = new AbstractC04260pg[0];
    public static final AbstractC04270ph[] A03 = {new C02020hp()};
    public static final AbstractC04310pl[] A04 = new AbstractC04310pl[0];
    public static final long serialVersionUID = 3683541151102256824L;
    public final AbstractC03990ow[] _abstractTypeResolvers;
    public final AbstractC04260pg[] _additionalDeserializers;
    public final AbstractC04270ph[] _additionalKeyDeserializers;
    public final AbstractC04220pZ[] _modifiers;
    public final AbstractC04310pl[] _valueInstantiators;

    public final boolean A00() {
        if (this._modifiers.length > 0) {
            return true;
        }
        return false;
    }

    public C04160pS() {
        this(null, null, null, null, null);
    }

    public C04160pS(AbstractC04260pg[] r1, AbstractC04270ph[] r2, AbstractC04220pZ[] r3, AbstractC03990ow[] r4, AbstractC04310pl[] r5) {
        this._additionalDeserializers = r1 == null ? A02 : r1;
        this._additionalKeyDeserializers = r2 == null ? A03 : r2;
        this._modifiers = r3 == null ? A01 : r3;
        this._abstractTypeResolvers = r4 == null ? A00 : r4;
        this._valueInstantiators = r5 == null ? A04 : r5;
    }
}
