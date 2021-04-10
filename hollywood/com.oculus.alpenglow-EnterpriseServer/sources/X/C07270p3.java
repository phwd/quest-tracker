package X;

import java.io.Serializable;

/* renamed from: X.0p3  reason: invalid class name and case insensitive filesystem */
public final class C07270p3 implements Serializable {
    public static final long serialVersionUID = 1;
    public C07180ot<C07010oa, C02620aS> _rootNames;

    public final synchronized C02620aS A00(Class<?> cls, AnonymousClass0a7<?> r6) {
        String str;
        C07010oa r3 = new C07010oa(cls);
        C07180ot<C07010oa, C02620aS> r0 = this._rootNames;
        if (r0 == null) {
            this._rootNames = new C07180ot<>(20, 200);
        } else {
            C02620aS r02 = r0.get(r3);
            if (r02 != null) {
                return r02;
            }
        }
        C06350mc A07 = r6.A01().A07(r6.A02(r6.A03(cls)).A07());
        if (A07 != null) {
            str = A07._simpleName;
            if (str.length() > 0) {
                C02620aS r1 = new C02620aS(str);
                this._rootNames.put(r3, r1);
                return r1;
            }
        }
        str = cls.getSimpleName();
        C02620aS r12 = new C02620aS(str);
        this._rootNames.put(r3, r12);
        return r12;
    }
}
