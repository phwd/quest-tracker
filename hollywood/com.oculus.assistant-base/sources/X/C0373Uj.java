package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.Uj  reason: case insensitive filesystem */
public final class C0373Uj extends AbstractC1033r6 implements Serializable {
    public static final C0373Uj A00 = new C0373Uj(new OU());
    public static final Class[] A01 = {Throwable.class};
    public static final Class[] A02 = new Class[0];
    public static final long serialVersionUID = 1;

    public static final AbstractC1034r7 A00(C0373Uj uj, AbstractC1022qr qrVar, O4 o4, PE pe, Type type) {
        AbstractC1024qt A09;
        AbstractC1034r7 uc;
        AbstractC1044rJ A092 = pe.A09();
        if (qrVar.A04().A05(EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            Q5.A06(A092.A0R());
        }
        if (type == null) {
            A09 = null;
        } else {
            C0299Pv A05 = o4.A05();
            A09 = A05.A03.A09(type, A05);
        }
        pe.A06();
        o4.A06();
        pe.A0J();
        AbstractC1024qt A0B = uj.A0B(qrVar, A09, A092);
        JsonDeserializer A06 = AbstractC1033r6.A06(qrVar, A092);
        AbstractC1024qt A052 = AbstractC1033r6.A05(qrVar, A092, A0B);
        PR pr = (PR) A052._typeHandler;
        boolean z = A092 instanceof AnonymousClass0q;
        Q0 A062 = o4.A06();
        if (z) {
            uc = new C0352Tc(pe, A052, pr, A062, (AnonymousClass0q) A092);
        } else {
            uc = new C0367Uc(pe, A052, pr, A062, (St) A092);
        }
        if (A06 != null) {
            uc = uc.A02(A06);
        }
        if (pe instanceof C1052rR) {
            C1052rR rRVar = (C1052rR) pe;
            O3 o3 = (O3) C1052rR.A04(rRVar, new C1049rO(rRVar));
            if (o3 != null && o3.A00 == AnonymousClass09.A00) {
                uc._managedReferenceName = o3.A01;
            }
        }
        return uc;
    }

    public static final void A01(AbstractC1022qr qrVar, O4 o4, C0262Oa oa) {
        AbstractC1024qt A09;
        Map map = ((C1046rL) o4).A05;
        if (map != null) {
            boolean A05 = qrVar.A04().A05(EnumC1027qy.CAN_OVERRIDE_ACCESS_MODIFIERS);
            for (Map.Entry entry : map.entrySet()) {
                AbstractC1044rJ rJVar = (AbstractC1044rJ) entry.getValue();
                if (A05) {
                    Q5.A06(rJVar.A0R());
                }
                String A0K = rJVar.A0K();
                Type A0N = rJVar.A0N();
                if (A0N == null) {
                    A09 = null;
                } else {
                    C0299Pv A052 = o4.A05();
                    A09 = A052.A03.A09(A0N, A052);
                }
                Q0 A06 = o4.A06();
                Object key = entry.getKey();
                List list = oa.A07;
                if (list == null) {
                    list = new ArrayList();
                    oa.A07 = list;
                }
                list.add(new TZ(A0K, A09, A06, rJVar, key));
            }
        }
    }

    public static final void A02(AbstractC1022qr qrVar, O4 o4, C0262Oa oa) {
        AbstractC1024qt qtVar;
        AbstractC1034r7 r7Var;
        NN A022;
        PJ pj = ((C1046rL) o4).A03;
        if (pj != null) {
            Class cls = pj.A00;
            if (cls == VP.class) {
                String str = pj.A02;
                r7Var = (AbstractC1034r7) oa.A0A.get(str);
                if (r7Var != null) {
                    qtVar = r7Var.A34();
                    A022 = new AnonymousClass2B(pj.A01);
                } else {
                    throw new IllegalArgumentException(AnonymousClass08.A07("Invalid Object Id definition for ", o4.A00._class.getName(), ": can not find property with name '", str, "'"));
                }
            } else {
                qtVar = qrVar.A05().A0A(qrVar._config.A03(cls), NN.class)[0];
                r7Var = null;
                A022 = qrVar.A02(pj);
            }
            oa.A03 = new C0273Ou(qtVar, pj.A02, A022, qrVar.A07(qtVar), r7Var);
        }
    }

    public static final void A04(C0373Uj uj, AbstractC1022qr qrVar, O4 o4, C0262Oa oa) {
        Type A0J;
        AbstractC1020qp A012;
        O3 A04;
        C1046rL rLVar = (C1046rL) o4;
        HashMap hashMap = null;
        for (PE pe : rLVar.A0A) {
            AbstractC1044rJ A09 = pe.A09();
            if (!(A09 == null || (A04 = rLVar.A07.A04(A09)) == null || A04.A00 != AnonymousClass09.A01)) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                String str = A04.A01;
                if (hashMap.put(str, A09) != null) {
                    throw new IllegalArgumentException(AnonymousClass08.A05("Multiple back-reference properties with name '", str, "'"));
                }
            }
        }
        if (hashMap != null) {
            for (Map.Entry entry : hashMap.entrySet()) {
                Object key = entry.getKey();
                AbstractC1044rJ rJVar = (AbstractC1044rJ) entry.getValue();
                if (rJVar instanceof AnonymousClass0q) {
                    A0J = ((SV) rJVar).A0V(0);
                } else {
                    A0J = rJVar.A0J();
                }
                AnonymousClass2I r0 = qrVar._config;
                String A0K = rJVar.A0K();
                if (r0 == null) {
                    A012 = null;
                } else {
                    A012 = r0.A01();
                }
                AbstractC1034r7 A002 = A00(uj, qrVar, o4, new C1078ru(rJVar, A0K, A012), A0J);
                HashMap hashMap2 = oa.A05;
                if (hashMap2 == null) {
                    hashMap2 = new HashMap(4);
                    oa.A05 = hashMap2;
                }
                hashMap2.put(key, A002);
                Map map = oa.A0A;
                if (map != null) {
                    map.remove(A002._propName);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x021e, code lost:
        if (r3 == null) goto L_0x0220;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0112, code lost:
        if (r3.A05(X.EnumC1027qy.AUTO_DETECT_GETTERS) == false) goto L_0x0114;
     */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x01cc A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x020d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A03(X.C0373Uj r18, X.AbstractC1022qr r19, X.O4 r20, X.C0262Oa r21) {
        /*
        // Method dump skipped, instructions count: 732
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0373Uj.A03(X.Uj, X.qr, X.O4, X.Oa):void");
    }

    public C0373Uj(OU ou) {
        super(ou);
    }
}
