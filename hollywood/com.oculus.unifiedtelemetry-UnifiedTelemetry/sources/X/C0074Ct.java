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

/* renamed from: X.Ct  reason: case insensitive filesystem */
public final class C0074Ct extends WW implements Serializable {
    public static final C0074Ct A00 = new C0074Ct(new dT());
    public static final Class<?>[] A01 = {Throwable.class};
    public static final Class<?>[] A02 = new Class[0];
    public static final long serialVersionUID = 1;

    private final AbstractC0073Cr A00(AbstractC0226Wn wn, jm jmVar, WF wf, Type type) throws C0223Wj {
        AbstractC0073Cr r3;
        WJ A09 = wf.A09();
        if (wn.A04().A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            Mv.A05(A09.A0P());
        }
        AbstractC0224Wl A03 = jmVar.A03(type);
        wf.A07();
        jmVar.A0C();
        wf.A0E();
        AbstractC0224Wl A0M = A0M(wn, A03, A09);
        JsonDeserializer<?> A06 = WW.A06(wn, A09);
        AbstractC0224Wl A05 = WW.A05(wn, A09, A0M);
        V4 v4 = (V4) A05._typeHandler;
        boolean z = A09 instanceof AnonymousClass7P;
        N6 A0C = jmVar.A0C();
        if (z) {
            r3 = new AnonymousClass8B(wf, A05, v4, A0C, (AnonymousClass7P) A09);
        } else {
            r3 = new AnonymousClass8E(wf, A05, v4, A0C, (CD) A09);
        }
        if (A06 != null) {
            r3 = r3.A02(A06);
        }
        jn A062 = wf.A06();
        if (A062 != null && A062.A00 == AnonymousClass07.A00) {
            r3._managedReferenceName = A062.A01;
        }
        return r3;
    }

    @Override // X.ZD
    public final JsonDeserializer<Object> A0B(AbstractC0226Wn wn, AbstractC0224Wl wl, jm jmVar, Class<?> cls) throws C0223Wj {
        String str;
        String str2;
        AbstractC0224Wl A03 = wn._config.A03(cls);
        AnonymousClass8M r1 = wn._config;
        jm A032 = r1._base._classIntrospector.A03(r1, A03, r1);
        AbstractC0262Ym A0N = A0N(wn, A032);
        AnonymousClass8M r3 = wn._config;
        C0295aT aTVar = new C0295aT(A032, r3);
        aTVar.A02 = A0N;
        A01(wn, A032, aTVar);
        A04(wn, A032, aTVar);
        A02(wn, A032, aTVar);
        A03(wn, A032, aTVar);
        C0410hE A04 = A032.A04();
        if (A04 == null) {
            str = "build";
        } else {
            str = A04.A00;
        }
        AnonymousClass7P A09 = A032.A09(str, null);
        if (A09 != null && r3.A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            Mv.A05(A09.A00);
        }
        aTVar.A04 = A09;
        aTVar.A00 = A04;
        dT dTVar = this._factoryConfig;
        if (dTVar.A00()) {
            Iterator it = new N4(dTVar._modifiers).iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        AnonymousClass7P r0 = aTVar.A04;
        if (r0 != null) {
            Class<?> returnType = r0.A00.getReturnType();
            if (wl._class.isAssignableFrom(returnType)) {
                Collection<AbstractC0073Cr> values = aTVar.A0A.values();
                C0245Xs xs = new C0245Xs(values);
                xs.A02();
                boolean z = !aTVar.A0B;
                if (!z) {
                    Iterator<AbstractC0073Cr> it2 = values.iterator();
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
                C0204Vm vm = aTVar.A03;
                if (vm != null) {
                    xs = xs.A01(new AnonymousClass8A(vm));
                }
                BuilderBasedDeserializer builderBasedDeserializer = new BuilderBasedDeserializer(aTVar, aTVar.A09, xs, aTVar.A05, aTVar.A06, aTVar.A08, z);
                dT dTVar2 = this._factoryConfig;
                if (dTVar2.A00()) {
                    Iterator it3 = new N4(dTVar2._modifiers).iterator();
                    while (it3.hasNext()) {
                        it3.next();
                    }
                }
                return builderBasedDeserializer;
            }
            StringBuilder sb = new StringBuilder("Build method '");
            sb.append(aTVar.A04.A0Y());
            sb.append(" has bad return type (");
            sb.append(returnType.getName());
            sb.append("), not compatible with POJO type (");
            sb.append(wl._class.getName());
            sb.append(")");
            str2 = sb.toString();
        } else {
            str2 = AnonymousClass06.A07("Builder class ", aTVar.A09.A00._class.getName(), " does not have build method '", str, "()'");
        }
        throw new IllegalArgumentException(str2);
    }

    private final void A02(AbstractC0226Wn wn, jm jmVar, C0295aT aTVar) throws C0223Wj {
        Type A0J;
        Wp A012;
        Map<String, WJ> A0K = jmVar.A0K();
        if (A0K != null) {
            for (Map.Entry<String, WJ> entry : A0K.entrySet()) {
                String key = entry.getKey();
                WJ value = entry.getValue();
                if (value instanceof AnonymousClass7P) {
                    A0J = ((CB) value).A0U(0);
                } else {
                    A0J = value.A0J();
                }
                AnonymousClass8M r0 = wn._config;
                String A0K2 = value.A0K();
                if (r0 == null) {
                    A012 = null;
                } else {
                    A012 = r0.A01();
                }
                AbstractC0073Cr A002 = A00(wn, jmVar, new Bs(value, A0K2, A012), A0J);
                HashMap<String, AbstractC0073Cr> hashMap = aTVar.A05;
                if (hashMap == null) {
                    hashMap = new HashMap<>(4);
                    aTVar.A05 = hashMap;
                }
                hashMap.put(key, A002);
                Map<String, AbstractC0073Cr> map = aTVar.A0A;
                if (map != null) {
                    map.remove(A002._propName);
                }
            }
        }
    }

    public static final void A03(AbstractC0226Wn wn, jm jmVar, C0295aT aTVar) throws C0223Wj {
        Map<Object, WJ> A0L = jmVar.A0L();
        if (A0L != null) {
            boolean A05 = wn.A04().A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS);
            for (Map.Entry<Object, WJ> entry : A0L.entrySet()) {
                WJ value = entry.getValue();
                if (A05) {
                    Mv.A05(value.A0P());
                }
                String A0K = value.A0K();
                AbstractC0224Wl A03 = jmVar.A03(value.A0N());
                N6 A0C = jmVar.A0C();
                Object key = entry.getKey();
                List list = aTVar.A07;
                if (list == null) {
                    list = new ArrayList();
                    aTVar.A07 = list;
                }
                list.add(new AnonymousClass87(A0K, A03, A0C, value, key));
            }
        }
    }

    public static final void A04(AbstractC0226Wn wn, jm jmVar, C0295aT aTVar) throws C0223Wj {
        AbstractC0224Wl wl;
        AbstractC0073Cr cr;
        pp A012;
        VN A0A = jmVar.A0A();
        if (A0A != null) {
            Class<? extends pp<?>> cls = A0A.A00;
            if (cls == AnonymousClass89.class) {
                String str = A0A.A02;
                cr = aTVar.A0A.get(str);
                if (cr != null) {
                    wl = cr._type;
                    A012 = new AnonymousClass89(A0A.A01);
                } else {
                    throw new IllegalArgumentException(AnonymousClass06.A07("Invalid Object Id definition for ", jmVar.A00._class.getName(), ": can not find property with name '", str, "'"));
                }
            } else {
                wl = wn._config._base._typeFactory.A0A(wn._config.A03(cls), pp.class)[0];
                cr = null;
                A012 = ((pp) Mv.A02(cls, wn.A04().A05(EnumC0220Wf.CAN_OVERRIDE_ACCESS_MODIFIERS))).A01(A0A.A01);
            }
            aTVar.A03 = new C0204Vm(wl, A0A.A02, A012, wn.A05(wl), cr);
        }
    }

    public C0074Ct(dT dTVar) {
        super(dTVar);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00e3, code lost:
        if (r2.A05(X.EnumC0220Wf.AUTO_DETECT_GETTERS) == false) goto L_0x00e5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x02bb  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x018e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01cf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A01(X.AbstractC0226Wn r24, X.jm r25, X.C0295aT r26) throws X.C0223Wj {
        /*
        // Method dump skipped, instructions count: 731
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0074Ct.A01(X.Wn, X.jm, X.aT):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:179:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x027f  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x02cb A[LOOP_START, PHI: r4 
      PHI: (r4v2 java.lang.Class<?>) = (r4v0 java.lang.Class<?>), (r4v3 java.lang.Class<?>) binds: [B:308:0x02cb, B:211:0x02df] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x0317  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x0321  */
    /* JADX WARNING: Removed duplicated region for block: B:235:0x0336  */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0430  */
    @Override // X.ZD
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> A0A(X.AbstractC0226Wn r8, X.AbstractC0224Wl r9, X.jm r10) throws X.C0223Wj {
        /*
        // Method dump skipped, instructions count: 1104
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0074Ct.A0A(X.Wn, X.Wl, X.jm):com.fasterxml.jackson.databind.JsonDeserializer");
    }
}
