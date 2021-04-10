package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC01990hm;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02230iJ;
import X.AbstractC02300iS;
import X.AbstractC04490qS;
import X.AbstractC04500qW;
import X.AbstractC04550qd;
import X.AbstractC04600qk;
import X.AbstractC04620qo;
import X.AbstractC04870rR;
import X.AnonymousClass006;
import X.AnonymousClass0Og;
import X.AnonymousClass0nW;
import X.AnonymousClass0qh;
import X.AnonymousClass0r1;
import X.C02180iD;
import X.C02270iP;
import X.C02310iT;
import X.C03550nX;
import X.C04030p3;
import X.C04580qi;
import X.C04660qw;
import X.C04790rG;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public abstract class BeanSerializerBase extends StdSerializer<Object> implements AbstractC04600qk, AbstractC04620qo, AbstractC04490qS, AbstractC04500qW {
    public static final AnonymousClass0Og[] A07 = new AnonymousClass0Og[0];
    public final AnonymousClass0nW A00;
    public final AbstractC01990hm A01;
    public final AnonymousClass0qh A02;
    public final C04660qw A03;
    public final Object A04;
    public final AnonymousClass0Og[] A05;
    public final AnonymousClass0Og[] A06;

    public abstract BeanSerializerBase A04();

    public abstract BeanSerializerBase A05(C04660qw v);

    public abstract BeanSerializerBase A06(String[] strArr);

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public abstract void serialize(Object obj, AbstractC02300iS v, AbstractC02120i3 v2) throws IOException, C02310iT;

    public final void A01() throws IOException, C02310iT {
        Object obj = this.A04;
        StringBuilder sb = new StringBuilder("Can not resolve BeanPropertyFilter with id '");
        sb.append(obj);
        sb.append("'; no FilterProvider configured");
        throw new C02180iD(sb.toString());
    }

    public final void A02(Object obj, AbstractC02300iS r10, AbstractC02120i3 r11) throws IOException, C02310iT {
        AbstractC01990hm r1;
        Object A0Q;
        String str = "[anySetter]";
        AnonymousClass0Og[] r5 = this.A05;
        if (r5 == null || r11._serializationView == null) {
            r5 = this.A06;
        }
        try {
            for (AnonymousClass0Og r0 : r5) {
                if (r0 != null) {
                    r0.A06(obj, r10, r11);
                }
            }
            AnonymousClass0qh r3 = this.A02;
            if (!(r3 == null || (A0Q = (r1 = r3.A02).A0Q(obj)) == null)) {
                if (A0Q instanceof Map) {
                    r3.A00.A05((Map) A0Q, r10, r11);
                    return;
                }
                throw new C02180iD(AnonymousClass006.A0B("Value returned by 'any-getter' (", r1.A0L(), "()) not java.util.Map but ", A0Q.getClass().getName()));
            }
        } catch (Exception e) {
            if (0 != r5.length) {
                str = r5[0].A06.getValue();
            }
            StdSerializer.A05(r11, e, obj, str);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } catch (StackOverflowError e2) {
            C02180iD r12 = new C02180iD("Infinite recursion (StackOverflowError)", e2);
            if (0 != r5.length) {
                str = r5[0].A06.getValue();
            }
            r12.A04(new C04030p3(obj, str));
            throw r12;
        }
    }

    public final void A03(Object obj, AbstractC02300iS r6, AbstractC02120i3 r7, boolean z) throws IOException, C02310iT {
        C04660qw r3 = this.A03;
        AnonymousClass0r1 A0D = r7.A0D(obj, r3.A00);
        Object obj2 = A0D.A00;
        if (obj2 == null || (!A0D.A01 && !r3.A04)) {
            obj2 = A0D.A02.A03(obj);
            A0D.A00 = obj2;
            if (!r3.A04) {
                if (z) {
                    r6.A0I();
                }
                C02270iP r1 = r3.A01;
                A0D.A01 = true;
                if (r1 != null) {
                    r6.A0P(r1);
                    r3.A03.serialize(A0D.A00, r6, r7);
                }
                if (this.A04 != null) {
                    A01();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                A02(obj, r6, r7);
                if (z) {
                    r6.A0F();
                    return;
                }
                return;
            }
        }
        r3.A03.serialize(obj2, r6, r7);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d6, code lost:
        if (r9 != null) goto L_0x0043;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    @Override // X.AbstractC04600qk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonSerializer<?> A2P(X.AbstractC02120i3 r16, X.AbstractC02220iI r17) throws X.C02180iD {
        /*
        // Method dump skipped, instructions count: 259
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.A2P(X.0i3, X.0iI):com.fasterxml.jackson.databind.JsonSerializer");
    }

    @Override // X.AbstractC04620qo
    public final void A9O(AbstractC02120i3 r10) throws C02180iD {
        int length;
        AnonymousClass0Og r0;
        AbstractC04550qd r1;
        Type genericType;
        Object A0Z;
        JsonSerializer<Object> jsonSerializer;
        AnonymousClass0Og r02;
        AnonymousClass0Og[] r7 = this.A05;
        if (r7 == null) {
            length = 0;
        } else {
            length = r7.length;
        }
        AnonymousClass0Og[] r4 = this.A06;
        int length2 = r4.length;
        for (int i = 0; i < length2; i++) {
            AnonymousClass0Og r8 = r4[i];
            if (!r8.A0B && r8.A01 == null && (jsonSerializer = r10._nullValueSerializer) != null) {
                r8.A03(jsonSerializer);
                if (i < length && (r02 = r7[i]) != null) {
                    r02.A03(jsonSerializer);
                }
            }
            if (r8.A02 == null) {
                AbstractC02230iJ A012 = r10._config.A01();
                if (A012 == null || (A0Z = A012.A0Z(r8.A4N())) == null) {
                    AbstractC02190iF r12 = r8.A07;
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
                    JsonSerializer<Object> A09 = r10.A09(r12, r8);
                    if (r12.A0N() && (r1 = (AbstractC04550qd) r12.A04()._typeHandler) != null && (A09 instanceof ContainerSerializer)) {
                        A09 = ((ContainerSerializer) A09).A03(r1);
                    }
                    r8.A04(A09);
                    if (i < length && (r0 = r7[i]) != null) {
                        r0.A04(A09);
                    }
                } else {
                    r10.A05(A0Z);
                    throw new NullPointerException("getOutputType");
                }
            }
        }
        AnonymousClass0qh r2 = this.A02;
        if (r2 != null) {
            r2.A00 = (MapSerializer) r2.A00.A2P(r10, r2.A01);
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        if (r3 != null) {
            throw new NullPointerException("expectObjectFormat");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0076  */
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void serializeWithType(java.lang.Object r6, X.AbstractC02300iS r7, X.AbstractC02120i3 r8, X.AbstractC04550qd r9) throws java.io.IOException, X.C02310iT {
        /*
        // Method dump skipped, instructions count: 168
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeWithType(java.lang.Object, X.0iS, X.0i3, X.0qd):void");
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean usesObjectId() {
        if (this.A03 != null) {
            return true;
        }
        return false;
    }

    public BeanSerializerBase(AbstractC02190iF r3, C04580qi r4, AnonymousClass0Og[] r5, AnonymousClass0Og[] r6) {
        super(r3);
        this.A06 = r5;
        this.A05 = r6;
        AnonymousClass0nW r0 = null;
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
            C03550nX A032 = r4.A07.A03(null);
            if (A032 != null) {
                r0 = A032.A00;
            }
        }
        this.A00 = r0;
    }

    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, C04660qw r3) {
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
    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, AbstractC04870rR r8) {
        super(((StdSerializer) beanSerializerBase).A00);
        int length;
        int length2;
        AnonymousClass0Og[] r5 = beanSerializerBase.A06;
        if (!(r5 == null || (length2 = r5.length) == 0 || r8 == null || r8 == AbstractC04870rR.A00)) {
            AnonymousClass0Og[] r2 = new AnonymousClass0Og[length2];
            for (int i = 0; i < length2; i++) {
                AnonymousClass0Og r0 = r5[i];
                if (r0 != null) {
                    r2[i] = r0.A01(r8);
                }
            }
            r5 = r2;
        }
        AnonymousClass0Og[] r4 = beanSerializerBase.A05;
        if (!(r4 == null || (length = r4.length) == 0 || r8 == null || r8 == AbstractC04870rR.A00)) {
            AnonymousClass0Og[] r22 = new AnonymousClass0Og[length];
            for (int i2 = 0; i2 < length; i2++) {
                AnonymousClass0Og r02 = r4[i2];
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
        HashSet A002 = C04790rG.A00(strArr);
        AnonymousClass0Og[] r8 = beanSerializerBase.A06;
        AnonymousClass0Og[] r7 = beanSerializerBase.A05;
        int length = r8.length;
        ArrayList arrayList = new ArrayList(length);
        AnonymousClass0Og[] r0 = null;
        ArrayList arrayList2 = r7 == null ? null : new ArrayList(length);
        for (int i = 0; i < length; i++) {
            AnonymousClass0Og r2 = r8[i];
            if (!A002.contains(r2.A06.getValue())) {
                arrayList.add(r2);
                if (r7 != null) {
                    arrayList2.add(r7[i]);
                }
            }
        }
        this.A06 = (AnonymousClass0Og[]) arrayList.toArray(new AnonymousClass0Og[arrayList.size()]);
        this.A05 = arrayList2 != null ? (AnonymousClass0Og[]) arrayList2.toArray(new AnonymousClass0Og[arrayList2.size()]) : r0;
        this.A01 = beanSerializerBase.A01;
        this.A02 = beanSerializerBase.A02;
        this.A03 = beanSerializerBase.A03;
        this.A04 = beanSerializerBase.A04;
        this.A00 = beanSerializerBase.A00;
    }
}
