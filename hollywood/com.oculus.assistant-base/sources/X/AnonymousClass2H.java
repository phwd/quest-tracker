package X;

import java.io.Serializable;
import java.util.Map;

/* renamed from: X.2H  reason: invalid class name */
public final class AnonymousClass2H extends V4 implements Serializable {
    public static final long serialVersionUID = 8849092838541724233L;
    public final AbstractC0279Pb _filterProvider = null;
    public final int _serFeatures = AbstractC1032r3.A00(EnumC1030r1.class);
    public N3 _serializationInclusion = null;

    @Override // X.AbstractC1032r3
    public final AbstractC1020qp A01() {
        if (A05(EnumC1027qy.USE_ANNOTATIONS)) {
            return super.A01();
        }
        return AnonymousClass0n.A00;
    }

    public final boolean A06(EnumC1030r1 r1Var) {
        if ((r1Var.getMask() & this._serFeatures) != 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return AnonymousClass08.A05("[SerializationConfig: flags=0x", Integer.toHexString(this._serFeatures), "]");
    }

    public AnonymousClass2H(OS os, PP pp, Map map) {
        super(os, pp, map);
    }

    @Override // X.AbstractC1032r3
    public final PN A04() {
        PN A04 = super.A04();
        if (!A05(EnumC1027qy.AUTO_DETECT_GETTERS)) {
            A04 = A04.A5d(EnumC0242Mp.NONE);
        }
        if (!A05(EnumC1027qy.AUTO_DETECT_IS_GETTERS)) {
            A04 = A04.A5e(EnumC0242Mp.NONE);
        }
        if (!A05(EnumC1027qy.AUTO_DETECT_FIELDS)) {
            return A04.A5c(EnumC0242Mp.NONE);
        }
        return A04;
    }
}
