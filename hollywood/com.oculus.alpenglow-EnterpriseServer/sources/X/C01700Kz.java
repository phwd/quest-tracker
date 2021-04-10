package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: X.0Kz  reason: invalid class name and case insensitive filesystem */
public final class C01700Kz extends AnonymousClass0a4 implements Serializable {
    public static final C01700Kz A00 = new C01700Kz(new C06440mt());
    public static final Class<?>[] A01 = {Throwable.class};
    public static final Class<?>[] A02 = new Class[0];
    public static final long serialVersionUID = 1;

    private final AbstractC01680Ku A00(AbstractC02570aK r10, AbstractC06260mR r11, AbstractC02410Zn r12, Type type) throws AnonymousClass0aG {
        AbstractC01680Ku r3;
        AbstractC02450Zr A0A = r12.A0A();
        if (r10.A06().A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            C07130om.A06(A0A.A0R());
        }
        AnonymousClass0aI A05 = r11.A05(type);
        r12.A07();
        r11.A0F();
        r12.A0F();
        AnonymousClass0aI A0M = A0M(r10, A05, A0A);
        JsonDeserializer<?> A06 = AnonymousClass0a4.A06(r10, A0A);
        AnonymousClass0aI A052 = AnonymousClass0a4.A05(r10, A0A, A0M);
        AnonymousClass0o3 r6 = (AnonymousClass0o3) A052._typeHandler;
        boolean z = A0A instanceof AnonymousClass0EC;
        AbstractC07080oh A0F = r11.A0F();
        if (z) {
            r3 = new AnonymousClass0Ew(r12, A052, r6, A0F, (AnonymousClass0EC) A0A);
        } else {
            r3 = new AnonymousClass0Ez(r12, A052, r6, A0F, (AnonymousClass0KC) A0A);
        }
        if (A06 != null) {
            r3 = r3.A02(A06);
        }
        C06250mQ A062 = r12.A06();
        if (A062 != null && A062.A00 == AnonymousClass007.A00) {
            r3._managedReferenceName = A062.A01;
        }
        return r3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d3, code lost:
        if (r2.A05(X.EnumC02540aC.AUTO_DETECT_GETTERS) == false) goto L_0x00d5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x017d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A01(X.AbstractC02570aK r23, X.AbstractC06260mR r24, X.C06490mz r25) throws X.AnonymousClass0aG {
        /*
        // Method dump skipped, instructions count: 641
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C01700Kz.A01(X.0aK, X.0mR, X.0mz):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:179:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x027d  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x02de  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x03cf  */
    @Override // X.AbstractC06540n6
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> A0A(X.AbstractC02570aK r7, X.AnonymousClass0aI r8, X.AbstractC06260mR r9) throws X.AnonymousClass0aG {
        /*
        // Method dump skipped, instructions count: 1007
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C01700Kz.A0A(X.0aK, X.0aI, X.0mR):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    @Override // X.AbstractC06540n6
    public final JsonDeserializer<Object> A0B(AbstractC02570aK r13, AnonymousClass0aI r14, AbstractC06260mR r15, Class<?> cls) throws AnonymousClass0aG {
        String str;
        String str2;
        AnonymousClass0aI A03 = r13._config.A03(cls);
        C01260Fu r1 = r13._config;
        AbstractC06260mR A04 = r1._base._classIntrospector.A04(r1, A03, r1);
        AnonymousClass0nB A0N = A0N(r13, A04);
        C01260Fu r3 = r13._config;
        C06490mz r5 = new C06490mz(A04, r3);
        r5.A02 = A0N;
        A01(r13, A04, r5);
        A04(r13, A04, r5);
        A02(r13, A04, r5);
        A03(r13, A04, r5);
        C06380mi A06 = A04.A06();
        if (A06 == null) {
            str = "build";
        } else {
            str = A06.A00;
        }
        AnonymousClass0EC A0C = A04.A0C(str, null);
        if (A0C != null && r3.A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            C07130om.A06(A0C.A00);
        }
        r5.A04 = A0C;
        r5.A00 = A06;
        C06440mt r12 = this._factoryConfig;
        if (r12.A00()) {
            Iterator it = new C07100oj(r12._modifiers).iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        AnonymousClass0EC r0 = r5.A04;
        if (r0 != null) {
            Class<?> returnType = r0.A00.getReturnType();
            if (r14._class.isAssignableFrom(returnType)) {
                Collection<AbstractC01680Ku> values = r5.A0A.values();
                C06570nF r7 = new C06570nF(values);
                r7.A02();
                boolean z = !r5.A0B;
                if (!z) {
                    Iterator<AbstractC01680Ku> it2 = values.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (it2.next()._viewMatcher != null) {
                                z = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                AnonymousClass0nL r16 = r5.A03;
                if (r16 != null) {
                    r7 = r7.A01(new AnonymousClass0Eu(r16));
                }
                BuilderBasedDeserializer builderBasedDeserializer = new BuilderBasedDeserializer(r5, r5.A09, r7, r5.A05, r5.A06, r5.A08, z);
                C06440mt r17 = this._factoryConfig;
                if (r17.A00()) {
                    Iterator it3 = new C07100oj(r17._modifiers).iterator();
                    while (it3.hasNext()) {
                        it3.next();
                    }
                }
                return builderBasedDeserializer;
            }
            str2 = "Build method '" + r5.A04.A0b() + " has bad return type (" + returnType.getName() + "), not compatible with POJO type (" + r14._class.getName() + ")";
        } else {
            str2 = AnonymousClass006.A09("Builder class ", r5.A09.A00._class.getName(), " does not have build method '", str, "()'");
        }
        throw new IllegalArgumentException(str2);
    }

    private final void A02(AbstractC02570aK r8, AbstractC06260mR r9, C06490mz r10) throws AnonymousClass0aG {
        Type A0K;
        AbstractC02590aM A012;
        Map<String, AbstractC02450Zr> A0P = r9.A0P();
        if (A0P != null) {
            for (Map.Entry<String, AbstractC02450Zr> entry : A0P.entrySet()) {
                String key = entry.getKey();
                AbstractC02450Zr value = entry.getValue();
                if (value instanceof AnonymousClass0EC) {
                    A0K = ((AnonymousClass0EC) value).A0X(0);
                } else {
                    A0K = value.A0K();
                }
                C01260Fu r0 = r8._config;
                String A0L = value.A0L();
                if (r0 == null) {
                    A012 = null;
                } else {
                    A012 = r0.A01();
                }
                AbstractC01680Ku A002 = A00(r8, r9, new C01580Jm(value, A0L, A012), A0K);
                HashMap<String, AbstractC01680Ku> hashMap = r10.A05;
                if (hashMap == null) {
                    hashMap = new HashMap<>(4);
                    r10.A05 = hashMap;
                }
                hashMap.put(key, A002);
                Map<String, AbstractC01680Ku> map = r10.A0A;
                if (map != null) {
                    map.remove(A002._propName);
                }
            }
        }
    }

    public static final void A03(AbstractC02570aK r9, AbstractC06260mR r10, C06490mz r11) throws AnonymousClass0aG {
        Map<Object, AbstractC02450Zr> A0Q = r10.A0Q();
        if (A0Q != null) {
            boolean A05 = r9.A06().A05(EnumC02540aC.CAN_OVERRIDE_ACCESS_MODIFIERS);
            for (Map.Entry<Object, AbstractC02450Zr> entry : A0Q.entrySet()) {
                AbstractC02450Zr value = entry.getValue();
                if (A05) {
                    C07130om.A06(value.A0R());
                }
                String A0L = value.A0L();
                AnonymousClass0aI A052 = r10.A05(value.A0O());
                AbstractC07080oh A0F = r10.A0F();
                Object key = entry.getKey();
                List list = r11.A07;
                if (list == null) {
                    list = new ArrayList();
                    r11.A07 = list;
                }
                list.add(new AnonymousClass0Er(A0L, A052, A0F, value, key));
            }
        }
    }

    public static final void A04(AbstractC02570aK r7, AbstractC06260mR r8, C06490mz r9) throws AnonymousClass0aG {
        AnonymousClass0aI r2;
        AbstractC01680Ku r6;
        AnonymousClass0lR A03;
        C06720nk A0D = r8.A0D();
        if (A0D != null) {
            Class<? extends AnonymousClass0lR<?>> cls = A0D.A00;
            if (cls == AnonymousClass0LQ.class) {
                String str = A0D.A02;
                r6 = r9.A0A.get(str);
                if (r6 != null) {
                    r2 = r6.A4h();
                    A03 = new AnonymousClass0Et(A0D.A01);
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A09("Invalid Object Id definition for ", r8.A00._class.getName(), ": can not find property with name '", str, "'"));
                }
            } else {
                r2 = r7.A07().A0A(r7._config.A03(cls), AnonymousClass0lR.class)[0];
                r6 = null;
                A03 = r7.A03(A0D);
            }
            r9.A03 = new AnonymousClass0nL(r2, A0D.A02, A03, r7.A08(r2), r6);
        }
    }

    public C01700Kz(C06440mt r1) {
        super(r1);
    }
}
