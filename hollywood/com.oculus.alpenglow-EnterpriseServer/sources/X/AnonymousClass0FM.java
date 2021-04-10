package X;

import java.io.Serializable;
import java.util.Map;

/* renamed from: X.0FM  reason: invalid class name */
public final class AnonymousClass0FM extends AnonymousClass0L1<AnonymousClass0a9, AnonymousClass0FM> implements Serializable {
    public static final long serialVersionUID = 8849092838541724233L;
    public final AbstractC06850oF _filterProvider = null;
    public final int _serFeatures = AnonymousClass0a7.A00(AnonymousClass0a9.class);
    public EnumC05760l7 _serializationInclusion = null;

    @Override // X.AnonymousClass0a7
    public final AbstractC02590aM A01() {
        if (A05(EnumC02540aC.USE_ANNOTATIONS)) {
            return super.A01();
        }
        return AnonymousClass0K8.A00;
    }

    @Override // X.AnonymousClass0a7
    public final AbstractC06260mR A02(AnonymousClass0aI r2) {
        return this._base._classIntrospector.A06(this, r2, this);
    }

    public final boolean A06(AnonymousClass0a9 r3) {
        if ((r3.getMask() & this._serFeatures) != 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return AnonymousClass006.A07("[SerializationConfig: flags=0x", Integer.toHexString(this._serFeatures), "]");
    }

    public AnonymousClass0FM(C06420mr r3, AnonymousClass0o1 r4, Map<C07010oa, Class<?>> map) {
        super(r3, r4, map);
    }

    @Override // X.AnonymousClass0a7
    public final AbstractC06760no<?> A04() {
        AbstractC06760no<?> A04 = super.A04();
        if (!A05(EnumC02540aC.AUTO_DETECT_GETTERS)) {
            A04 = A04.A8r(EnumC05730kt.NONE);
        }
        if (!A05(EnumC02540aC.AUTO_DETECT_IS_GETTERS)) {
            A04 = A04.A8s(EnumC05730kt.NONE);
        }
        if (!A05(EnumC02540aC.AUTO_DETECT_FIELDS)) {
            return A04.A8q(EnumC05730kt.NONE);
        }
        return A04;
    }
}
