package X;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

/* renamed from: X.0hj  reason: invalid class name and case insensitive filesystem */
public class C01970hj extends AnonymousClass0qG implements Serializable {
    public static final C01970hj A00 = new C01970hj();
    public static final C01980hk A01;
    public static final C01980hk A02;
    public static final C01980hk A03;
    public static final C01980hk A04 = new C01980hk(null, AnonymousClass0C7.A00(String.class), C02000hn.A01(String.class, null, null), Collections.emptyList());
    public static final long serialVersionUID = 1;

    static {
        Class cls = Boolean.TYPE;
        A01 = new C01980hk(null, AnonymousClass0C7.A00(cls), C02000hn.A01(cls, null, null), Collections.emptyList());
        Class cls2 = Integer.TYPE;
        A02 = new C01980hk(null, AnonymousClass0C7.A00(cls2), C02000hn.A01(cls2, null, null), Collections.emptyList());
        Class cls3 = Long.TYPE;
        A03 = new C01980hk(null, AnonymousClass0C7.A00(cls3), C02000hn.A01(cls3, null, null), Collections.emptyList());
    }

    public static final C01980hk A00(AbstractC02190iF r1) {
        Class<?> cls = r1._class;
        if (cls == String.class) {
            return A04;
        }
        if (cls == Boolean.TYPE) {
            return A01;
        }
        if (cls == Integer.TYPE) {
            return A02;
        }
        if (cls == Long.TYPE) {
            return A03;
        }
        return null;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0i2<*>;LX/0iF;LX/0qF;ZLjava/lang/String;)LX/0qK; */
    private final AnonymousClass0qK A01(AbstractC02110i2 r8, AbstractC02190iF r9, AnonymousClass0qF r10, boolean z) {
        AbstractC02230iJ r0;
        boolean A05 = r8.A05(EnumC02160i9.USE_ANNOTATIONS);
        Class<?> cls = r9._class;
        if (A05) {
            r0 = r8.A01();
        } else {
            r0 = null;
        }
        AnonymousClass0qK r1 = new AnonymousClass0qK(r8, z, r9, C02000hn.A00(cls, r0, r10), "set");
        r1.A07();
        return r1;
    }

    @Override // X.AnonymousClass0qG
    public final AbstractC04010oz A04(AnonymousClass0HU r8, AbstractC02190iF r9, AnonymousClass0qF r10) {
        String str;
        C04100pH A08;
        AbstractC02230iJ r1 = null;
        if (r8.A05(EnumC02160i9.USE_ANNOTATIONS)) {
            r1 = r8.A01();
        }
        C02000hn A002 = C02000hn.A00(r9._class, r1, r10);
        if (r1 == null || (A08 = r1.A08(A002)) == null) {
            str = "with";
        } else {
            str = A08.A01;
        }
        AnonymousClass0qK r12 = new AnonymousClass0qK(r8, false, r9, A002, str);
        r12.A07();
        return C01980hk.A00(r12);
    }

    @Override // X.AnonymousClass0qG
    public final AbstractC04010oz A06(AbstractC02110i2 r4, AbstractC02190iF r5, AnonymousClass0qF r6) {
        AbstractC02230iJ r0;
        boolean A05 = r4.A05(EnumC02160i9.USE_ANNOTATIONS);
        Class<?> cls = r5._class;
        if (A05) {
            r0 = r4.A01();
        } else {
            r0 = null;
        }
        return new C01980hk(r4, r5, C02000hn.A00(cls, r0, r6), Collections.emptyList());
    }

    @Override // X.AnonymousClass0qG
    public final AbstractC04010oz A02(AnonymousClass0HU r2, AbstractC02190iF r3, AnonymousClass0qF r4) {
        C01980hk A002 = A00(r3);
        if (A002 == null) {
            return C01980hk.A00(A01(r2, r3, r4, false));
        }
        return A002;
    }

    /* renamed from: A07 */
    public C01980hk A03(AnonymousClass0HU r2, AbstractC02190iF r3, AnonymousClass0qF r4) {
        C01980hk A002 = A00(r3);
        if (A002 == null) {
            return C01980hk.A00(A01(r2, r3, r4, false));
        }
        return A002;
    }

    /* renamed from: A08 */
    public C01980hk A05(AnonymousClass0HM r6, AbstractC02190iF r7, AnonymousClass0qF r8) {
        AbstractC01990hm r0;
        C01980hk A002 = A00(r7);
        if (A002 == null) {
            AnonymousClass0qK A012 = A01(r6, r7, r8, true);
            A002 = new C01980hk(A012);
            A002.A01 = A012.A06();
            LinkedList<AbstractC01990hm> linkedList = A012.A02;
            if (linkedList == null) {
                r0 = null;
            } else if (linkedList.size() > 1) {
                StringBuilder sb = new StringBuilder("Multiple 'any-getters' defined (");
                sb.append(linkedList.get(0));
                sb.append(" vs ");
                sb.append(A012.A02.get(1));
                sb.append(")");
                AnonymousClass0qK.A04(A012, sb.toString());
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else {
                r0 = linkedList.getFirst();
            }
            A002.A00 = r0;
        }
        return A002;
    }
}
