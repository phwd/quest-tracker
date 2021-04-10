package X;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

/* renamed from: X.0Zo  reason: invalid class name and case insensitive filesystem */
public final class C02420Zo extends AbstractC06700nh implements Serializable {
    public static final C02420Zo A00 = new C02420Zo();
    public static final C02430Zp A01;
    public static final C02430Zp A02;
    public static final C02430Zp A03;
    public static final C02430Zp A04 = new C02430Zp(null, AnonymousClass0C9.A00(String.class), C02460Zs.A01(String.class, null, null), Collections.emptyList());
    public static final long serialVersionUID = 1;

    static {
        Class cls = Boolean.TYPE;
        A01 = new C02430Zp(null, AnonymousClass0C9.A00(cls), C02460Zs.A01(cls, null, null), Collections.emptyList());
        Class cls2 = Integer.TYPE;
        A02 = new C02430Zp(null, AnonymousClass0C9.A00(cls2), C02460Zs.A01(cls2, null, null), Collections.emptyList());
        Class cls3 = Long.TYPE;
        A03 = new C02430Zp(null, AnonymousClass0C9.A00(cls3), C02460Zs.A01(cls3, null, null), Collections.emptyList());
    }

    public static final C02430Zp A00(AnonymousClass0aI r1) {
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

    /* JADX WARN: Incorrect args count in method signature: (LX/0a7<*>;LX/0aI;LX/0ng;ZLjava/lang/String;)LX/0nl; */
    private final C06730nl A01(AnonymousClass0a7 r8, AnonymousClass0aI r9, AbstractC06690ng r10, boolean z) {
        AbstractC02590aM r0;
        boolean A05 = r8.A05(EnumC02540aC.USE_ANNOTATIONS);
        Class<?> cls = r9._class;
        if (A05) {
            r0 = r8.A01();
        } else {
            r0 = null;
        }
        C06730nl r1 = new C06730nl(r8, z, r9, C02460Zs.A00(cls, r0, r10), "set");
        r1.A07();
        return r1;
    }

    @Override // X.AbstractC06700nh
    public final AbstractC06260mR A04(C01260Fu r8, AnonymousClass0aI r9, AbstractC06690ng r10) {
        String str;
        C06380mi A08;
        AbstractC02590aM r1 = null;
        if (r8.A05(EnumC02540aC.USE_ANNOTATIONS)) {
            r1 = r8.A01();
        }
        C02460Zs A002 = C02460Zs.A00(r9._class, r1, r10);
        if (r1 == null || (A08 = r1.A08(A002)) == null) {
            str = "with";
        } else {
            str = A08.A01;
        }
        C06730nl r12 = new C06730nl(r8, false, r9, A002, str);
        r12.A07();
        return C02430Zp.A00(r12);
    }

    @Override // X.AbstractC06700nh
    public final AbstractC06260mR A06(AnonymousClass0a7 r4, AnonymousClass0aI r5, AbstractC06690ng r6) {
        AbstractC02590aM r0;
        boolean A05 = r4.A05(EnumC02540aC.USE_ANNOTATIONS);
        Class<?> cls = r5._class;
        if (A05) {
            r0 = r4.A01();
        } else {
            r0 = null;
        }
        return new C02430Zp(r4, r5, C02460Zs.A00(cls, r0, r6), Collections.emptyList());
    }

    @Override // X.AbstractC06700nh
    public final AbstractC06260mR A02(C01260Fu r2, AnonymousClass0aI r3, AbstractC06690ng r4) {
        C02430Zp A002 = A00(r3);
        if (A002 == null) {
            return C02430Zp.A00(A01(r2, r3, r4, false));
        }
        return A002;
    }

    @Override // X.AbstractC06700nh
    public final AbstractC06260mR A03(C01260Fu r2, AnonymousClass0aI r3, AbstractC06690ng r4) {
        C02430Zp A002 = A00(r3);
        if (A002 == null) {
            return C02430Zp.A00(A01(r2, r3, r4, false));
        }
        return A002;
    }

    @Override // X.AbstractC06700nh
    public final AbstractC06260mR A05(AnonymousClass0FM r6, AnonymousClass0aI r7, AbstractC06690ng r8) {
        AbstractC02450Zr r0;
        C02430Zp A002 = A00(r7);
        if (A002 == null) {
            C06730nl A012 = A01(r6, r7, r8, true);
            A002 = new C02430Zp(A012);
            A002.A01 = A012.A06();
            LinkedList<AbstractC02450Zr> linkedList = A012.A02;
            if (linkedList == null) {
                r0 = null;
            } else if (linkedList.size() > 1) {
                C06730nl.A04(A012, "Multiple 'any-getters' defined (" + linkedList.get(0) + " vs " + A012.A02.get(1) + ")");
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else {
                r0 = linkedList.getFirst();
            }
            A002.A00 = r0;
        }
        return A002;
    }
}
