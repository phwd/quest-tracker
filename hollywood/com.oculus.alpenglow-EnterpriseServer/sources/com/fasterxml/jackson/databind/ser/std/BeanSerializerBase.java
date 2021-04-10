package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02450Zr;
import X.AbstractC02590aM;
import X.AbstractC02640aV;
import X.AbstractC06790ns;
import X.AbstractC06810nz;
import X.AbstractC06840oE;
import X.AbstractC06870oI;
import X.AbstractC07200ov;
import X.AnonymousClass006;
import X.AnonymousClass0Jw;
import X.AnonymousClass0a8;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.AnonymousClass0o6;
import X.AnonymousClass0oC;
import X.C02620aS;
import X.C02650aW;
import X.C05750kz;
import X.C06290mV;
import X.C06820oB;
import X.C06930oQ;
import X.C06980oV;
import X.C07110ok;
import X.EnumC05740ky;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public abstract class BeanSerializerBase extends StdSerializer<Object> implements AbstractC06840oE, AbstractC06870oI, AbstractC06790ns, AbstractC06810nz {
    public static final AnonymousClass0Jw[] A07 = new AnonymousClass0Jw[0];
    public final EnumC05740ky A00;
    public final AbstractC02450Zr A01;
    public final C06820oB A02;
    public final C06930oQ A03;
    public final Object A04;
    public final AnonymousClass0Jw[] A05;
    public final AnonymousClass0Jw[] A06;

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public abstract void A0D(Object obj, AbstractC02640aV v, AnonymousClass0a8 v2) throws IOException, C02650aW;

    public abstract BeanSerializerBase A0E();

    public abstract BeanSerializerBase A0F(C06930oQ v);

    public abstract BeanSerializerBase A0G(String[] strArr);

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A08() {
        if (this.A03 != null) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0037  */
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void A0A(java.lang.Object r6, X.AbstractC02640aV r7, X.AnonymousClass0a8 r8, X.AnonymousClass0o6 r9) throws java.io.IOException, X.C02650aW {
        /*
        // Method dump skipped, instructions count: 154
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.A0A(java.lang.Object, X.0aV, X.0a8, X.0o6):void");
    }

    public final void A0H() throws IOException, C02650aW {
        Object obj = this.A04;
        throw new AnonymousClass0aG("Can not resolve BeanPropertyFilter with id '" + obj + "'; no FilterProvider configured");
    }

    public final void A0I(Object obj, AbstractC02640aV r10, AnonymousClass0a8 r11) throws IOException, C02650aW {
        AbstractC02450Zr r1;
        Object A0Q;
        String str = "[anySetter]";
        AnonymousClass0Jw[] r5 = this.A05;
        if (r5 == null || r11._serializationView == null) {
            r5 = this.A06;
        }
        try {
            for (AnonymousClass0Jw r0 : r5) {
                if (r0 != null) {
                    r0.A06(obj, r10, r11);
                }
            }
            C06820oB r3 = this.A02;
            if (!(r3 == null || (A0Q = (r1 = r3.A02).A0Q(obj)) == null)) {
                if (A0Q instanceof Map) {
                    r3.A00.A0G((Map) A0Q, r10, r11);
                    return;
                }
                throw new AnonymousClass0aG(AnonymousClass006.A08("Value returned by 'any-getter' (", r1.A0L(), "()) not java.util.Map but ", A0Q.getClass().getName()));
            }
        } catch (Exception e) {
            if (0 != r5.length) {
                str = r5[0].A06.getValue();
            }
            StdSerializer.A05(r11, e, obj, str);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } catch (StackOverflowError e2) {
            AnonymousClass0aG r12 = new AnonymousClass0aG("Infinite recursion (StackOverflowError)", e2);
            if (0 != r5.length) {
                str = r5[0].A06.getValue();
            }
            r12.A04(new C06290mV(obj, str));
            throw r12;
        }
    }

    public final void A0J(Object obj, AbstractC02640aV r6, AnonymousClass0a8 r7, boolean z) throws IOException, C02650aW {
        C06930oQ r3 = this.A03;
        C06980oV A0C = r7.A0C(obj, r3.A00);
        Object obj2 = A0C.A00;
        if (obj2 == null || (!A0C.A01 && !r3.A04)) {
            obj2 = A0C.A02.A03(obj);
            A0C.A00 = obj2;
            if (!r3.A04) {
                if (z) {
                    r6.A0F();
                }
                C02620aS r1 = r3.A01;
                A0C.A01 = true;
                if (r1 != null) {
                    r6.A0N(r1);
                    r3.A03.A0D(A0C.A00, r6, r7);
                }
                if (this.A04 != null) {
                    A0H();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                A0I(obj, r6, r7);
                if (z) {
                    r6.A0C();
                    return;
                }
                return;
            }
        }
        r3.A03.A0D(obj2, r6, r7);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d6, code lost:
        if (r9 != null) goto L_0x0043;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    @Override // X.AbstractC06840oE
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonSerializer<?> A1x(X.AnonymousClass0a8 r16, X.AbstractC02580aL r17) throws X.AnonymousClass0aG {
        /*
        // Method dump skipped, instructions count: 259
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.A1x(X.0a8, X.0aL):com.fasterxml.jackson.databind.JsonSerializer");
    }

    @Override // X.AbstractC06870oI
    public final void A7U(AnonymousClass0a8 r10) throws AnonymousClass0aG {
        int length;
        AnonymousClass0Jw r0;
        AnonymousClass0o6 r1;
        Type genericType;
        Object A0Z;
        JsonSerializer<Object> jsonSerializer;
        AnonymousClass0Jw r02;
        AnonymousClass0Jw[] r7 = this.A05;
        if (r7 == null) {
            length = 0;
        } else {
            length = r7.length;
        }
        AnonymousClass0Jw[] r4 = this.A06;
        int length2 = r4.length;
        for (int i = 0; i < length2; i++) {
            AnonymousClass0Jw r8 = r4[i];
            if (!r8.A0A && r8.A01 == null && (jsonSerializer = r10._nullValueSerializer) != null) {
                r8.A03(jsonSerializer);
                if (i < length && (r02 = r7[i]) != null) {
                    r02.A03(jsonSerializer);
                }
            }
            if (r8.A02 == null) {
                AbstractC02590aM A012 = r10._config.A01();
                if (A012 == null || (A0Z = A012.A0Z(r8.A41())) == null) {
                    AnonymousClass0aI r12 = r8.A07;
                    if (r12 == null) {
                        Method method = r8.A09;
                        if (method != null) {
                            genericType = method.getGenericReturnType();
                        } else {
                            genericType = r8.A08.getGenericType();
                        }
                        r12 = r10.A07().A09(genericType, null);
                        if (!Modifier.isFinal(r12._class.getModifiers())) {
                            if (r12.A0N() || r12.A03() > 0) {
                                r8.A00 = r12;
                            }
                        }
                    }
                    JsonSerializer<Object> A08 = r10.A08(r12, r8);
                    if (r12.A0N() && (r1 = (AnonymousClass0o6) r12.A04()._typeHandler) != null && (A08 instanceof ContainerSerializer)) {
                        A08 = ((ContainerSerializer) A08).A0E(r1);
                    }
                    r8.A04(A08);
                    if (i < length && (r0 = r7[i]) != null) {
                        r0.A04(A08);
                    }
                } else {
                    r10.A05(A0Z);
                    throw null;
                }
            }
        }
        C06820oB r2 = this.A02;
        if (r2 != null) {
            r2.A00 = (MapSerializer) r2.A00.A1x(r10, r2.A01);
        }
    }

    public BeanSerializerBase(AnonymousClass0aI r3, AnonymousClass0oC r4, AnonymousClass0Jw[] r5, AnonymousClass0Jw[] r6) {
        super(r3);
        this.A06 = r5;
        this.A05 = r6;
        EnumC05740ky r0 = null;
        if (r4 == null) {
            this.A01 = null;
            this.A02 = null;
            this.A04 = null;
            this.A03 = null;
        } else {
            this.A01 = r4.A01;
            this.A02 = r4.A02;
            this.A04 = r4.A04;
            this.A03 = r4.A03;
            C05750kz A032 = r4.A07.A03(null);
            if (A032 != null) {
                r0 = A032.A00;
            }
        }
        this.A00 = r0;
    }

    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, C06930oQ r3) {
        super(((StdSerializer) beanSerializerBase).A00);
        this.A06 = beanSerializerBase.A06;
        this.A05 = beanSerializerBase.A05;
        this.A01 = beanSerializerBase.A01;
        this.A02 = beanSerializerBase.A02;
        this.A03 = r3;
        this.A04 = beanSerializerBase.A04;
        this.A00 = beanSerializerBase.A00;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, AbstractC07200ov r8) {
        super(((StdSerializer) beanSerializerBase).A00);
        int length;
        int length2;
        AnonymousClass0Jw[] r5 = beanSerializerBase.A06;
        if (!(r5 == null || (length2 = r5.length) == 0 || r8 == null || r8 == AbstractC07200ov.A00)) {
            AnonymousClass0Jw[] r2 = new AnonymousClass0Jw[length2];
            for (int i = 0; i < length2; i++) {
                AnonymousClass0Jw r0 = r5[i];
                if (r0 != null) {
                    r2[i] = r0.A01(r8);
                }
            }
            r5 = r2;
        }
        AnonymousClass0Jw[] r4 = beanSerializerBase.A05;
        if (!(r4 == null || (length = r4.length) == 0 || r8 == null || r8 == AbstractC07200ov.A00)) {
            AnonymousClass0Jw[] r22 = new AnonymousClass0Jw[length];
            for (int i2 = 0; i2 < length; i2++) {
                AnonymousClass0Jw r02 = r4[i2];
                if (r02 != null) {
                    r22[i2] = r02.A01(r8);
                }
            }
            r4 = r22;
        }
        this.A06 = r5;
        this.A05 = r4;
        this.A01 = beanSerializerBase.A01;
        this.A02 = beanSerializerBase.A02;
        this.A03 = beanSerializerBase.A03;
        this.A04 = beanSerializerBase.A04;
        this.A00 = beanSerializerBase.A00;
    }

    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, String[] strArr) {
        super(((StdSerializer) beanSerializerBase).A00);
        HashSet A002 = C07110ok.A00(strArr);
        AnonymousClass0Jw[] r8 = beanSerializerBase.A06;
        AnonymousClass0Jw[] r7 = beanSerializerBase.A05;
        int length = r8.length;
        ArrayList arrayList = new ArrayList(length);
        AnonymousClass0Jw[] r0 = null;
        ArrayList arrayList2 = r7 == null ? null : new ArrayList(length);
        for (int i = 0; i < length; i++) {
            AnonymousClass0Jw r2 = r8[i];
            if (!A002.contains(r2.A06.getValue())) {
                arrayList.add(r2);
                if (r7 != null) {
                    arrayList2.add(r7[i]);
                }
            }
        }
        this.A06 = (AnonymousClass0Jw[]) arrayList.toArray(new AnonymousClass0Jw[arrayList.size()]);
        this.A05 = arrayList2 != null ? (AnonymousClass0Jw[]) arrayList2.toArray(new AnonymousClass0Jw[arrayList2.size()]) : r0;
        this.A01 = beanSerializerBase.A01;
        this.A02 = beanSerializerBase.A02;
        this.A03 = beanSerializerBase.A03;
        this.A04 = beanSerializerBase.A04;
        this.A00 = beanSerializerBase.A00;
    }
}
