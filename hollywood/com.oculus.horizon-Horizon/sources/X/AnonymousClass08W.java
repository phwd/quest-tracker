package X;

import java.io.Serializable;
import java.util.Map;

/* renamed from: X.08W  reason: invalid class name */
public final class AnonymousClass08W extends AnonymousClass0HH<EnumC03930gS, AnonymousClass08W> implements Serializable {
    public static final long serialVersionUID = 8849092838541724233L;
    public final AbstractC06010mM _filterProvider = null;
    public final int _serFeatures = AbstractC03910gQ.A00(EnumC03930gS.class);
    public EnumC04700j9 _serializationInclusion = null;

    @Override // X.AbstractC03910gQ
    public final AbstractC04040gi A01() {
        if (A05(EnumC03960gV.USE_ANNOTATIONS)) {
            return super.A01();
        }
        return AnonymousClass0GT.A00;
    }

    @Override // X.AbstractC03910gQ
    public final AbstractC05180kU A02(AbstractC04000gb r2) {
        return this._base._classIntrospector.A04(this, r2, this);
    }

    public final String toString() {
        return AnonymousClass006.A07("[SerializationConfig: flags=0x", Integer.toHexString(this._serFeatures), "]");
    }

    public AnonymousClass08W(C05350kv r3, AbstractC05920m7 r4, Map<C06210mi, Class<?>> map) {
        super(r3, r4, map);
    }

    @Override // X.AbstractC03910gQ
    public final AbstractC05820lu<?> A04() {
        AbstractC05820lu<?> A04 = super.A04();
        if (!A05(EnumC03960gV.AUTO_DETECT_GETTERS)) {
            A04 = A04.AA2(EnumC04680iv.NONE);
        }
        if (!A05(EnumC03960gV.AUTO_DETECT_IS_GETTERS)) {
            A04 = A04.AA3(EnumC04680iv.NONE);
        }
        if (!A05(EnumC03960gV.AUTO_DETECT_FIELDS)) {
            return A04.AA1(EnumC04680iv.NONE);
        }
        return A04;
    }
}
