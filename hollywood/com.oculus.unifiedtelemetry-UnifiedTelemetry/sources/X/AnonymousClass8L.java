package X;

import java.io.Serializable;
import java.util.Map;

/* renamed from: X.8L  reason: invalid class name */
public final class AnonymousClass8L extends D2<EnumC0217Wc, AnonymousClass8L> implements Serializable {
    public static final long serialVersionUID = 8849092838541724233L;
    public final UG _filterProvider = null;
    public final int _serFeatures = WZ.A00(EnumC0217Wc.class);
    public EnumC0462pV _serializationInclusion = null;

    @Override // X.WZ
    public final Wp A01() {
        if (A05(EnumC0220Wf.USE_ANNOTATIONS)) {
            return super.A01();
        }
        return C8.A00;
    }

    @Override // X.WZ
    public final jm A02(AbstractC0224Wl wl) {
        return this._base._classIntrospector.A04(this, wl, this);
    }

    public final String toString() {
        return AnonymousClass06.A05("[SerializationConfig: flags=0x", Integer.toHexString(this._serFeatures), "]");
    }

    public AnonymousClass8L(dV dVVar, V6 v6, Map<ON, Class<?>> map) {
        super(dVVar, v6, map);
    }

    @Override // X.WZ
    public final VI<?> A04() {
        VI<?> A04 = super.A04();
        if (!A05(EnumC0220Wf.AUTO_DETECT_GETTERS)) {
            A04 = A04.A5i(pH.NONE);
        }
        if (!A05(EnumC0220Wf.AUTO_DETECT_IS_GETTERS)) {
            A04 = A04.A5j(pH.NONE);
        }
        if (!A05(EnumC0220Wf.AUTO_DETECT_FIELDS)) {
            return A04.A5h(pH.NONE);
        }
        return A04;
    }
}
