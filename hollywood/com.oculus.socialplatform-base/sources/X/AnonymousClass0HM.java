package X;

import java.io.Serializable;
import java.util.Map;

/* renamed from: X.0HM  reason: invalid class name */
public final class AnonymousClass0HM extends AnonymousClass0SV<AnonymousClass0i4, AnonymousClass0HM> implements Serializable {
    public static final long serialVersionUID = 8849092838541724233L;
    public final AbstractC04610ql _filterProvider;
    public final int _serFeatures;
    public EnumC03560nf _serializationInclusion;

    @Override // X.AbstractC02110i2
    public final AbstractC02230iJ A01() {
        if (A05(EnumC02160i9.USE_ANNOTATIONS)) {
            return super.A01();
        }
        return AnonymousClass0Ou.A00;
    }

    @Override // X.AbstractC02110i2
    public final AbstractC04010oz A02(AbstractC02190iF r2) {
        return this._base._classIntrospector.A06(this, r2, this);
    }

    public final boolean A06(AnonymousClass0i4 r3) {
        if ((r3.getMask() & this._serFeatures) != 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return AnonymousClass006.A09("[SerializationConfig: flags=0x", Integer.toHexString(this._serFeatures), "]");
    }

    @Override // X.AbstractC02110i2
    public final AnonymousClass0qO<?> A04() {
        AnonymousClass0qO<?> A04 = super.A04();
        if (!A05(EnumC02160i9.AUTO_DETECT_GETTERS)) {
            A04 = A04.AB5(AnonymousClass0nR.NONE);
        }
        if (!A05(EnumC02160i9.AUTO_DETECT_IS_GETTERS)) {
            A04 = A04.AB6(AnonymousClass0nR.NONE);
        }
        if (!A05(EnumC02160i9.AUTO_DETECT_FIELDS)) {
            return A04.AB4(AnonymousClass0nR.NONE);
        }
        return A04;
    }

    public AnonymousClass0HM(AnonymousClass0HM r2, EnumC03560nf r3) {
        super(r2);
        this._serializationInclusion = null;
        this._serFeatures = r2._serFeatures;
        this._serializationInclusion = r3;
        this._filterProvider = null;
    }

    public AnonymousClass0HM(AnonymousClass0HM r2, C04140pQ r3) {
        super(r2, r3);
        this._serializationInclusion = null;
        this._serFeatures = r2._serFeatures;
        this._serializationInclusion = r2._serializationInclusion;
        this._filterProvider = null;
    }

    public AnonymousClass0HM(C04140pQ r3, AbstractC04510qY r4, Map<C04720r6, Class<?>> map) {
        super(r3, r4, map);
        this._serializationInclusion = null;
        this._serFeatures = AbstractC02110i2.A00(AnonymousClass0i4.class);
        this._filterProvider = null;
    }
}
