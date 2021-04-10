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

/* renamed from: X.0SH  reason: invalid class name */
public final class AnonymousClass0SH extends AbstractC02100hz implements Serializable {
    public static final AnonymousClass0SH A00 = new AnonymousClass0SH(new C04160pS());
    public static final Class<?>[] A01 = {Throwable.class};
    public static final Class<?>[] A02 = new Class[0];
    public static final long serialVersionUID = 1;

    private final AbstractC01170Rz A00(AbstractC02210iH r10, AbstractC04010oz r11, AbstractC01960hi r12, Type type) throws C02180iD {
        AbstractC01170Rz r3;
        AbstractC01990hm A0A = r12.A0A();
        if (r10.A06().A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            C04810rI.A06(A0A.A0R());
        }
        AbstractC02190iF A05 = r11.A05(type);
        r12.A07();
        r11.A0F();
        r12.A0F();
        AbstractC02190iF A0N = A0N(r10, A05, A0A);
        JsonDeserializer<?> A06 = AbstractC02100hz.A06(r10, A0A);
        AbstractC02190iF A052 = AbstractC02100hz.A05(r10, A0A, A0N);
        AbstractC04520qa r6 = (AbstractC04520qa) A052._typeHandler;
        boolean z = A0A instanceof AnonymousClass0Cr;
        AbstractC04760rD A0F = r11.A0F();
        if (z) {
            r3 = new AnonymousClass0Gv(r12, A052, r6, A0F, (AnonymousClass0Cr) A0A);
        } else {
            r3 = new AnonymousClass0Gy(r12, A052, r6, A0F, (AnonymousClass0Oy) A0A);
        }
        if (A06 != null) {
            r3 = r3.A02(A06);
        }
        C04000oy A062 = r12.A06();
        if (A062 != null && A062.A00 == AnonymousClass007.A00) {
            r3._managedReferenceName = A062.A01;
        }
        return r3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d3, code lost:
        if (r2.A05(X.EnumC02160i9.AUTO_DETECT_GETTERS) == false) goto L_0x00d5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x017d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A01(X.AbstractC02210iH r23, X.AbstractC04010oz r24, X.C04210pY r25) throws X.C02180iD {
        /*
        // Method dump skipped, instructions count: 641
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0SH.A01(X.0iH, X.0oz, X.0pY):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:120:0x01bf  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x01df  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0240  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x0347  */
    @Override // X.AbstractC04250pf
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> A0A(X.AbstractC02210iH r7, X.AbstractC02190iF r8, X.AbstractC04010oz r9) throws X.C02180iD {
        /*
        // Method dump skipped, instructions count: 865
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0SH.A0A(X.0iH, X.0iF, X.0oz):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    @Override // X.AbstractC04250pf
    public final JsonDeserializer<Object> A0B(AbstractC02210iH r13, AbstractC02190iF r14, AbstractC04010oz r15, Class<?> cls) throws C02180iD {
        String str;
        AbstractC02190iF A03 = r13._config.A03(cls);
        AnonymousClass0HU r1 = r13._config;
        AbstractC04010oz A04 = r1._base._classIntrospector.A04(r1, A03, r1);
        AbstractC04300pk A0P = A0P(r13, A04);
        AnonymousClass0HU r3 = r13._config;
        C04210pY r5 = new C04210pY(A04, r3);
        r5.A02 = A0P;
        A01(r13, A04, r5);
        A04(r13, A04, r5);
        A02(r13, A04, r5);
        A03(r13, A04, r5);
        C04100pH A06 = A04.A06();
        if (A06 == null) {
            str = "build";
        } else {
            str = A06.A00;
        }
        AnonymousClass0Cr A0C = A04.A0C(str, null);
        if (A0C != null && r3.A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            C04810rI.A06(A0C.A00);
        }
        r5.A04 = A0C;
        r5.A00 = A06;
        C04160pS r12 = this._factoryConfig;
        if (r12.A00()) {
            Iterator it = new C04780rF(r12._modifiers).iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        AnonymousClass0Cr r0 = r5.A04;
        if (r0 != null) {
            Class<?> returnType = r0.A00.getReturnType();
            if (r14._class.isAssignableFrom(returnType)) {
                Collection<AbstractC01170Rz> values = r5.A0A.values();
                C04330po r7 = new C04330po(values);
                r7.A02();
                boolean z = !r5.A0B;
                if (!z) {
                    Iterator<AbstractC01170Rz> it2 = values.iterator();
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
                C04390pu r16 = r5.A03;
                if (r16 != null) {
                    r7 = r7.A01(new AnonymousClass0Gu(r16));
                }
                BuilderBasedDeserializer builderBasedDeserializer = new BuilderBasedDeserializer(r5, r5.A09, r7, r5.A05, r5.A06, r5.A08, z);
                C04160pS r17 = this._factoryConfig;
                if (r17.A00()) {
                    Iterator it3 = new C04780rF(r17._modifiers).iterator();
                    while (it3.hasNext()) {
                        it3.next();
                    }
                }
                return builderBasedDeserializer;
            }
            StringBuilder sb = new StringBuilder("Build method '");
            sb.append(r5.A04.A0b());
            sb.append(" has bad return type (");
            sb.append(returnType.getName());
            sb.append("), not compatible with POJO type (");
            sb.append(r14._class.getName());
            sb.append(")");
            throw new IllegalArgumentException(sb.toString());
        }
        throw new IllegalArgumentException(AnonymousClass006.A0C("Builder class ", r5.A09.A00._class.getName(), " does not have build method '", str, "()'"));
    }

    @Override // X.AbstractC02100hz
    public final AbstractC04250pf A0O(C04160pS r4) {
        if (this._factoryConfig == r4) {
            return this;
        }
        Class<?> cls = getClass();
        if (cls == AnonymousClass0SH.class) {
            return new AnonymousClass0SH(r4);
        }
        throw new IllegalStateException(AnonymousClass006.A09("Subtype of BeanDeserializerFactory (", cls.getName(), ") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with additional deserializer definitions"));
    }

    private final void A02(AbstractC02210iH r8, AbstractC04010oz r9, C04210pY r10) throws C02180iD {
        Type A0K;
        AbstractC02230iJ A012;
        Map<String, AbstractC01990hm> A0P = r9.A0P();
        if (A0P != null) {
            for (Map.Entry<String, AbstractC01990hm> entry : A0P.entrySet()) {
                String key = entry.getKey();
                AbstractC01990hm value = entry.getValue();
                if (value instanceof AnonymousClass0Cr) {
                    A0K = ((AnonymousClass0Ow) value).A0X(0);
                } else {
                    A0K = value.A0K();
                }
                AnonymousClass0HU r0 = r8._config;
                String A0L = value.A0L();
                if (r0 == null) {
                    A012 = null;
                } else {
                    A012 = r0.A01();
                }
                AbstractC01170Rz A002 = A00(r8, r9, new AnonymousClass0OF(value, A0L, A012), A0K);
                HashMap<String, AbstractC01170Rz> hashMap = r10.A05;
                if (hashMap == null) {
                    hashMap = new HashMap<>(4);
                    r10.A05 = hashMap;
                }
                hashMap.put(key, A002);
                Map<String, AbstractC01170Rz> map = r10.A0A;
                if (map != null) {
                    map.remove(A002._propName);
                }
            }
        }
    }

    public static final void A03(AbstractC02210iH r9, AbstractC04010oz r10, C04210pY r11) throws C02180iD {
        Map<Object, AbstractC01990hm> A0Q = r10.A0Q();
        if (A0Q != null) {
            boolean A05 = r9.A06().A05(EnumC02160i9.CAN_OVERRIDE_ACCESS_MODIFIERS);
            for (Map.Entry<Object, AbstractC01990hm> entry : A0Q.entrySet()) {
                AbstractC01990hm value = entry.getValue();
                if (A05) {
                    C04810rI.A06(value.A0R());
                }
                String A0L = value.A0L();
                AbstractC02190iF A052 = r10.A05(value.A0O());
                AbstractC04760rD A0F = r10.A0F();
                Object key = entry.getKey();
                List list = r11.A07;
                if (list == null) {
                    list = new ArrayList();
                    r11.A07 = list;
                }
                list.add(new C00530Gr(A0L, A052, A0F, value, key));
            }
        }
    }

    public static final void A04(AbstractC02210iH r7, AbstractC04010oz r8, C04210pY r9) throws C02180iD {
        AbstractC02190iF r2;
        AbstractC01170Rz r6;
        AbstractC03600nz A03;
        C04480qJ A0D = r8.A0D();
        if (A0D != null) {
            Class<? extends AbstractC03600nz<?>> cls = A0D.A00;
            if (cls == AnonymousClass0Sy.class) {
                String str = A0D.A02;
                r6 = r9.A0A.get(str);
                if (r6 != null) {
                    r2 = r6.A59();
                    A03 = new C00550Gt(A0D.A01);
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A0C("Invalid Object Id definition for ", r8.A00._class.getName(), ": can not find property with name '", str, "'"));
                }
            } else {
                r2 = r7.A07().A0A(r7._config.A03(cls), AbstractC03600nz.class)[0];
                r6 = null;
                A03 = r7.A03(A0D);
            }
            r9.A03 = new C04390pu(r2, A0D.A02, A03, r7.A08(r2), r6);
        }
    }

    public AnonymousClass0SH(C04160pS r1) {
        super(r1);
    }
}
