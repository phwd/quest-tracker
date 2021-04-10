package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/* renamed from: X.0Og  reason: invalid class name */
public class AnonymousClass0Og implements AbstractC02220iI {
    public static final Object A0I = new Object();
    public AbstractC02190iF A00;
    public JsonSerializer<Object> A01;
    public JsonSerializer<Object> A02;
    public AbstractC04550qd A03;
    public AbstractC04690qz A04;
    public HashMap<Object, Object> A05;
    public final C02270iP A06;
    public final AbstractC02190iF A07;
    public final Field A08;
    public final Method A09;
    public final boolean A0A;
    public final boolean A0B;
    public final Class<?>[] A0C;
    public final AbstractC02190iF A0D;
    public final C04070pB A0E;
    public final AbstractC01990hm A0F;
    public final AbstractC04760rD A0G;
    public final Object A0H;

    public JsonSerializer<Object> A00(AbstractC04690qz r4, Class<?> cls, AbstractC02120i3 r6) throws C02180iD {
        JsonSerializer<Object> A0B2;
        AbstractC04690qz A012;
        AbstractC02190iF r0 = this.A00;
        if (r0 != null) {
            AbstractC02190iF A042 = r6.A04(r0, cls);
            A0B2 = r6.A09(A042, this);
            A012 = r4.A01(A042._class, A0B2);
        } else {
            A0B2 = r6.A0B(cls, this);
            A012 = r4.A01(cls, A0B2);
        }
        C04670qx r1 = new C04670qx(A0B2, A012);
        AbstractC04690qz r02 = r1.A01;
        if (r4 != r02) {
            this.A04 = r02;
        }
        return r1.A00;
    }

    public AnonymousClass0Og A01(AbstractC04870rR r4) {
        C02270iP r1 = this.A06;
        String A002 = r4.A00(r1.getValue());
        if (A002.equals(r1.toString())) {
            return this;
        }
        return new AnonymousClass0Og(this, new C02270iP(A002));
    }

    public final Object A02(Object obj) throws Exception {
        Method method = this.A09;
        if (method != null) {
            return method.invoke(obj, new Object[0]);
        }
        return this.A08.get(obj);
    }

    public void A03(JsonSerializer<Object> jsonSerializer) {
        JsonSerializer<Object> jsonSerializer2 = this.A01;
        if (jsonSerializer2 == null || jsonSerializer2 == jsonSerializer) {
            this.A01 = jsonSerializer;
            return;
        }
        throw new IllegalStateException("Can not override null serializer");
    }

    public void A04(JsonSerializer<Object> jsonSerializer) {
        JsonSerializer<Object> jsonSerializer2 = this.A02;
        if (jsonSerializer2 == null || jsonSerializer2 == jsonSerializer) {
            this.A02 = jsonSerializer;
            return;
        }
        throw new IllegalStateException("Can not override serializer");
    }

    public final String toString() {
        String name;
        String A072;
        StringBuilder sb = new StringBuilder(40);
        sb.append("property '");
        sb.append(this.A06.getValue());
        sb.append("' (");
        Method method = this.A09;
        if (method != null) {
            sb.append("via method ");
            sb.append(method.getDeclaringClass().getName());
            sb.append("#");
            name = method.getName();
        } else {
            sb.append("field \"");
            Field field = this.A08;
            sb.append(field.getDeclaringClass().getName());
            sb.append("#");
            name = field.getName();
        }
        sb.append(name);
        JsonSerializer<Object> jsonSerializer = this.A02;
        if (jsonSerializer == null) {
            A072 = ", no static serializer";
        } else {
            A072 = AnonymousClass006.A07(", static serializer of type ", jsonSerializer.getClass().getName());
        }
        sb.append(A072);
        sb.append(')');
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        if (r0 != false) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void A05(java.lang.Object r5, X.AbstractC02300iS r6, X.AbstractC02120i3 r7) throws java.lang.Exception {
        /*
            r4 = this;
            java.lang.Object r3 = r4.A02(r5)
            if (r3 == 0) goto L_0x0028
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r2 = r4.A02
            if (r2 != 0) goto L_0x001a
            java.lang.Class r1 = r3.getClass()
            X.0qz r0 = r4.A04
            com.fasterxml.jackson.databind.JsonSerializer r2 = r0.A00(r1)
            if (r2 != 0) goto L_0x001a
            com.fasterxml.jackson.databind.JsonSerializer r2 = r4.A00(r0, r1, r7)
        L_0x001a:
            java.lang.Object r1 = r4.A0H
            if (r1 == 0) goto L_0x003a
            java.lang.Object r0 = X.AnonymousClass0Og.A0I
            if (r0 != r1) goto L_0x0031
            boolean r0 = r2.isEmpty(r3)
        L_0x0026:
            if (r0 == 0) goto L_0x003a
        L_0x0028:
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r1 = r4.A01
            if (r1 == 0) goto L_0x0036
            r0 = 0
            r1.serialize(r0, r6, r7)
            return
        L_0x0031:
            boolean r0 = r1.equals(r3)
            goto L_0x0026
        L_0x0036:
            r6.A0G()
            return
        L_0x003a:
            if (r3 != r5) goto L_0x004a
            boolean r0 = r2.usesObjectId()
            if (r0 != 0) goto L_0x004a
            java.lang.String r1 = "Direct self-reference leading to cycle"
            X.0iD r0 = new X.0iD
            r0.<init>(r1)
            throw r0
        L_0x004a:
            X.0qd r0 = r4.A03
            if (r0 != 0) goto L_0x0052
            r2.serialize(r3, r6, r7)
            return
        L_0x0052:
            r2.serializeWithType(r3, r6, r7, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Og.A05(java.lang.Object, X.0iS, X.0i3):void");
    }

    public void A06(Object obj, AbstractC02300iS r6, AbstractC02120i3 r7) throws Exception {
        boolean equals;
        Class<?> cls;
        AbstractC04690qz r0;
        Object A022 = A02(obj);
        if (A022 != null) {
            JsonSerializer<Object> jsonSerializer = this.A02;
            if (jsonSerializer == null && (jsonSerializer = (r0 = this.A04).A00((cls = A022.getClass()))) == null) {
                jsonSerializer = A00(r0, cls, r7);
            }
            Object obj2 = this.A0H;
            if (obj2 != null) {
                if (A0I == obj2) {
                    equals = jsonSerializer.isEmpty(A022);
                } else {
                    equals = obj2.equals(A022);
                }
                if (equals) {
                    return;
                }
            }
            if (A022 != obj || jsonSerializer.usesObjectId()) {
                r6.A0P(this.A06);
                AbstractC04550qd r02 = this.A03;
                if (r02 == null) {
                    jsonSerializer.serialize(A022, r6, r7);
                } else {
                    jsonSerializer.serializeWithType(A022, r6, r7, r02);
                }
            } else {
                throw new C02180iD("Direct self-reference leading to cycle");
            }
        } else if (this.A01 != null) {
            r6.A0P(this.A06);
            this.A01.serialize(null, r6, r7);
        }
    }

    @Override // X.AbstractC02220iI
    public final AbstractC01990hm A4N() {
        return this.A0F;
    }

    @Override // X.AbstractC02220iI
    public final AbstractC02190iF A59() {
        return this.A0D;
    }

    public AnonymousClass0Og(AbstractC01960hi r3, AbstractC01990hm r4, AbstractC04760rD r5, AbstractC02190iF r6, JsonSerializer<?> jsonSerializer, AbstractC04550qd r8, AbstractC02190iF r9, boolean z, Object obj) {
        C01810h8 r0;
        this.A0F = r4;
        this.A0G = r5;
        this.A06 = new C02270iP(r3.A0E());
        this.A0E = r3.A07();
        this.A0D = r6;
        this.A02 = jsonSerializer;
        if (jsonSerializer == null) {
            r0 = C01810h8.A00;
        } else {
            r0 = null;
        }
        this.A04 = r0;
        this.A03 = r8;
        this.A07 = r9;
        this.A0A = r3.A0F();
        if (r4 instanceof AnonymousClass0Oy) {
            this.A09 = null;
            this.A08 = (Field) r4.A0R();
        } else if (r4 instanceof AnonymousClass0Cr) {
            this.A09 = (Method) r4.A0R();
            this.A08 = null;
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A07("Can not pass member of type ", r4.getClass().getName()));
        }
        this.A0B = z;
        this.A0H = obj;
        this.A0C = r3.A0M();
        this.A01 = null;
    }

    public AnonymousClass0Og(AnonymousClass0Og r3, C02270iP r4) {
        this.A06 = r4;
        this.A0E = r3.A0E;
        this.A0F = r3.A0F;
        this.A0G = r3.A0G;
        this.A0D = r3.A0D;
        this.A09 = r3.A09;
        this.A08 = r3.A08;
        this.A02 = r3.A02;
        this.A01 = r3.A01;
        HashMap<Object, Object> hashMap = r3.A05;
        if (hashMap != null) {
            this.A05 = new HashMap<>(hashMap);
        }
        this.A07 = r3.A07;
        this.A04 = r3.A04;
        this.A0B = r3.A0B;
        this.A0H = r3.A0H;
        this.A0C = r3.A0C;
        this.A03 = r3.A03;
        this.A00 = r3.A00;
        this.A0A = r3.A0A;
    }
}
