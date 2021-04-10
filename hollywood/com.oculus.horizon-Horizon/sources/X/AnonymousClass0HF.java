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

/* renamed from: X.0HF  reason: invalid class name */
public final class AnonymousClass0HF extends AbstractC03900gN implements Serializable {
    public static final AnonymousClass0HF A00 = new AnonymousClass0HF(new C05370kx());
    public static final Class<?>[] A01 = {Throwable.class};
    public static final Class<?>[] A02 = new Class[0];
    public static final long serialVersionUID = 1;

    private final AnonymousClass0HD A00(AbstractC04020gg r10, AbstractC05180kU r11, AnonymousClass0g5 r12, Type type) throws C03990gZ {
        AnonymousClass0HD r3;
        AnonymousClass0g9 A09 = r12.A09();
        if (r10.A03().A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            C06330mu.A05(A09.A0P());
        }
        AbstractC04000gb A03 = r11.A03(type);
        r12.A07();
        r11.A0C();
        r12.A0E();
        AbstractC04000gb A0M = A0M(r10, A03, A09);
        JsonDeserializer<?> A06 = AbstractC03900gN.A06(r10, A09);
        AbstractC04000gb A05 = AbstractC03900gN.A05(r10, A09, A0M);
        AnonymousClass0m9 r6 = (AnonymousClass0m9) A05._typeHandler;
        boolean z = A09 instanceof AnonymousClass07O;
        AbstractC06280mp A0C = r11.A0C();
        if (z) {
            r3 = new AnonymousClass08M(r12, A05, r6, A0C, (AnonymousClass07O) A09);
        } else {
            r3 = new AnonymousClass08P(r12, A05, r6, A0C, (AnonymousClass0GX) A09);
        }
        if (A06 != null) {
            r3 = r3.A02(A06);
        }
        C05170kT A062 = r12.A06();
        if (A062 != null && A062.A00 == AnonymousClass007.A00) {
            r3._managedReferenceName = A062.A01;
        }
        return r3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00e3, code lost:
        if (r2.A05(X.EnumC03960gV.AUTO_DETECT_GETTERS) == false) goto L_0x00e5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x02bb  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x018e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01cf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A01(X.AbstractC04020gg r24, X.AbstractC05180kU r25, X.AnonymousClass0l3 r26) throws X.C03990gZ {
        /*
        // Method dump skipped, instructions count: 730
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0HF.A01(X.0gg, X.0kU, X.0l3):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:179:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x027f  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x02cb A[LOOP_START, PHI: r4 
      PHI: (r4v2 java.lang.Class<?>) = (r4v0 java.lang.Class<?>), (r4v3 java.lang.Class<?>) binds: [B:308:0x02cb, B:211:0x02df] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x0317  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x0321  */
    /* JADX WARNING: Removed duplicated region for block: B:235:0x0336  */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0430  */
    @Override // X.AbstractC05460lB
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> A0A(X.AbstractC04020gg r8, X.AbstractC04000gb r9, X.AbstractC05180kU r10) throws X.C03990gZ {
        /*
        // Method dump skipped, instructions count: 1104
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0HF.A0A(X.0gg, X.0gb, X.0kU):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    @Override // X.AbstractC05460lB
    public final JsonDeserializer<Object> A0B(AbstractC04020gg r13, AbstractC04000gb r14, AbstractC05180kU r15, Class<?> cls) throws C03990gZ {
        String str;
        String str2;
        AbstractC04000gb A03 = r13._config.A03(cls);
        AnonymousClass08X r1 = r13._config;
        AbstractC05180kU A032 = r1._base._classIntrospector.A03(r1, A03, r1);
        AnonymousClass0lG A0N = A0N(r13, A032);
        AnonymousClass08X r3 = r13._config;
        AnonymousClass0l3 r5 = new AnonymousClass0l3(A032, r3);
        r5.A02 = A0N;
        A01(r13, A032, r5);
        A04(r13, A032, r5);
        A02(r13, A032, r5);
        A03(r13, A032, r5);
        C05310km A04 = A032.A04();
        if (A04 == null) {
            str = "build";
        } else {
            str = A04.A00;
        }
        AnonymousClass07O A09 = A032.A09(str, null);
        if (A09 != null && r3.A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            C06330mu.A05(A09.A00);
        }
        r5.A04 = A09;
        r5.A00 = A04;
        C05370kx r12 = this._factoryConfig;
        if (r12.A00()) {
            Iterator it = new C06300mr(r12._modifiers).iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        AnonymousClass07O r0 = r5.A04;
        if (r0 != null) {
            Class<?> returnType = r0.A00.getReturnType();
            if (r14._class.isAssignableFrom(returnType)) {
                Collection<AnonymousClass0HD> values = r5.A0A.values();
                AnonymousClass0lK r7 = new AnonymousClass0lK(values);
                r7.A02();
                boolean z = !r5.A0B;
                if (!z) {
                    Iterator<AnonymousClass0HD> it2 = values.iterator();
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
                C05530lQ r16 = r5.A03;
                if (r16 != null) {
                    r7 = r7.A01(new AnonymousClass08L(r16));
                }
                BuilderBasedDeserializer builderBasedDeserializer = new BuilderBasedDeserializer(r5, r5.A09, r7, r5.A05, r5.A06, r5.A08, z);
                C05370kx r17 = this._factoryConfig;
                if (r17.A00()) {
                    Iterator it3 = new C06300mr(r17._modifiers).iterator();
                    while (it3.hasNext()) {
                        it3.next();
                    }
                }
                return builderBasedDeserializer;
            }
            str2 = AnonymousClass006.A0B("Build method '", r5.A04.A0Y(), " has bad return type (", returnType.getName(), "), not compatible with POJO type (", r14._class.getName(), ")");
        } else {
            str2 = AnonymousClass006.A09("Builder class ", r5.A09.A00._class.getName(), " does not have build method '", str, "()'");
        }
        throw new IllegalArgumentException(str2);
    }

    private final void A02(AbstractC04020gg r8, AbstractC05180kU r9, AnonymousClass0l3 r10) throws C03990gZ {
        Type A0J;
        AbstractC04040gi A012;
        Map<String, AnonymousClass0g9> A0K = r9.A0K();
        if (A0K != null) {
            for (Map.Entry<String, AnonymousClass0g9> entry : A0K.entrySet()) {
                String key = entry.getKey();
                AnonymousClass0g9 value = entry.getValue();
                if (value instanceof AnonymousClass07O) {
                    A0J = ((AnonymousClass0GV) value).A0U(0);
                } else {
                    A0J = value.A0J();
                }
                AnonymousClass08X r0 = r8._config;
                String A0K2 = value.A0K();
                if (r0 == null) {
                    A012 = null;
                } else {
                    A012 = r0.A01();
                }
                AnonymousClass0HD A002 = A00(r8, r9, new AnonymousClass0GC(value, A0K2, A012), A0J);
                HashMap<String, AnonymousClass0HD> hashMap = r10.A05;
                if (hashMap == null) {
                    hashMap = new HashMap<>(4);
                    r10.A05 = hashMap;
                }
                hashMap.put(key, A002);
                Map<String, AnonymousClass0HD> map = r10.A0A;
                if (map != null) {
                    map.remove(A002._propName);
                }
            }
        }
    }

    public static final void A03(AbstractC04020gg r9, AbstractC05180kU r10, AnonymousClass0l3 r11) throws C03990gZ {
        Map<Object, AnonymousClass0g9> A0L = r10.A0L();
        if (A0L != null) {
            boolean A05 = r9.A03().A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS);
            for (Map.Entry<Object, AnonymousClass0g9> entry : A0L.entrySet()) {
                AnonymousClass0g9 value = entry.getValue();
                if (A05) {
                    C06330mu.A05(value.A0P());
                }
                String A0K = value.A0K();
                AbstractC04000gb A03 = r10.A03(value.A0N());
                AbstractC06280mp A0C = r10.A0C();
                Object key = entry.getKey();
                List list = r11.A07;
                if (list == null) {
                    list = new ArrayList();
                    r11.A07 = list;
                }
                list.add(new AnonymousClass08I(A0K, A03, A0C, value, key));
            }
        }
    }

    public static final void A04(AbstractC04020gg r9, AbstractC05180kU r10, AnonymousClass0l3 r11) throws C03990gZ {
        AbstractC04000gb r4;
        AnonymousClass0HD r8;
        AbstractC04750jT A012;
        C05770lp A0A = r10.A0A();
        if (A0A != null) {
            Class<? extends AbstractC04750jT<?>> cls = A0A.A00;
            if (cls == AnonymousClass08K.class) {
                String str = A0A.A02;
                r8 = r11.A0A.get(str);
                if (r8 != null) {
                    r4 = r8._type;
                    A012 = new AnonymousClass08K(A0A.A01);
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A09("Invalid Object Id definition for ", r10.A00._class.getName(), ": can not find property with name '", str, "'"));
                }
            } else {
                r4 = r9._config._base._typeFactory.A0A(r9._config.A03(cls), AbstractC04750jT.class)[0];
                r8 = null;
                A012 = ((AbstractC04750jT) C06330mu.A02(cls, r9.A03().A05(EnumC03960gV.CAN_OVERRIDE_ACCESS_MODIFIERS))).A01(A0A.A01);
            }
            r11.A03 = new C05530lQ(r4, A0A.A02, A012, r9.A04(r4), r8);
        }
    }

    public AnonymousClass0HF(C05370kx r1) {
        super(r1);
    }
}
