package X;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: X.rL  reason: case insensitive filesystem */
public final class C1046rL extends O4 {
    public AbstractC1044rJ A00;
    public AnonymousClass0q A01;
    public AnonymousClass0q A02;
    public PJ A03;
    public C0299Pv A04;
    public Map A05;
    public Set A06;
    public final AbstractC1020qp A07;
    public final AbstractC1032r3 A08;
    public final C1043rI A09;
    public final List A0A;

    public static final Q6 A01(C1046rL rLVar, Object obj) {
        if (obj != null) {
            if (obj instanceof Class) {
                Class cls = (Class) obj;
                if (!(cls == AbstractC1072ro.class || cls == OR.class)) {
                    if (Q6.class.isAssignableFrom(cls)) {
                        Q5.A02(cls, rLVar.A08.A05(EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS));
                    } else {
                        throw new IllegalStateException(AnonymousClass08.A05("AnnotationIntrospector returned Class ", cls.getName(), "; expected Class<Converter>"));
                    }
                }
            } else {
                throw new IllegalStateException(AnonymousClass08.A05("AnnotationIntrospector returned Converter definition of type ", obj.getClass().getName(), "; expected type Converter or Class<Converter> instead"));
            }
        }
        return null;
    }

    public static C1046rL A00(PK pk) {
        AnonymousClass0q r0;
        C1046rL rLVar = new C1046rL(pk);
        LinkedList linkedList = pk.A03;
        if (linkedList == null) {
            r0 = null;
        } else if (linkedList.size() > 1) {
            StringBuilder sb = new StringBuilder("Multiple 'any-setters' defined (");
            sb.append(linkedList.get(0));
            sb.append(" vs ");
            sb.append(pk.A03.get(1));
            sb.append(")");
            PK.A04(pk, sb.toString());
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else {
            r0 = (AnonymousClass0q) linkedList.getFirst();
        }
        rLVar.A01 = r0;
        rLVar.A06 = pk.A00;
        rLVar.A05 = pk.A01;
        rLVar.A02 = pk.A06();
        return rLVar;
    }

    public static final boolean A02(C1046rL rLVar, AnonymousClass0q r5) {
        if (!((O4) rLVar).A00._class.isAssignableFrom(r5.A00.getReturnType()) || (!rLVar.A07.A0N(r5) && !"valueOf".equals(r5.A0K()))) {
            return false;
        }
        return true;
    }

    public C1046rL(AbstractC1032r3 r3Var, AbstractC1024qt qtVar, C1043rI rIVar, List list) {
        super(qtVar);
        AbstractC1020qp A012;
        this.A08 = r3Var;
        if (r3Var == null) {
            A012 = null;
        } else {
            A012 = r3Var.A01();
        }
        this.A07 = A012;
        this.A09 = rIVar;
        this.A0A = list;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C1046rL(X.PK r6) {
        /*
            r5 = this;
            X.r3 r4 = r6.A08
            X.qt r3 = r6.A07
            X.rI r2 = r6.A09
            java.util.LinkedHashMap r0 = r6.A0C
            java.util.Collection r1 = r0.values()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r1)
            r5.<init>(r4, r3, r2, r0)
            X.qp r1 = r6.A06
            if (r1 != 0) goto L_0x001c
            r0 = 0
        L_0x0019:
            r5.A03 = r0
            return
        L_0x001c:
            X.PJ r0 = r1.A07(r2)
            if (r0 == 0) goto L_0x0019
            X.PJ r0 = r1.A08(r2, r0)
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1046rL.<init>(X.PK):void");
    }
}
