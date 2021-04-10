package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/* renamed from: X.0Jw  reason: invalid class name */
public class AnonymousClass0Jw implements AbstractC02580aL {
    public static final Object A0I = new Object();
    public AnonymousClass0aI A00;
    public JsonSerializer<Object> A01;
    public JsonSerializer<Object> A02;
    public AnonymousClass0o6 A03;
    public AbstractC06960oT A04;
    public HashMap<Object, Object> A05;
    public final C02620aS A06;
    public final AnonymousClass0aI A07;
    public final Field A08;
    public final Method A09;
    public final boolean A0A;
    public final Class<?>[] A0B;
    public final AnonymousClass0aI A0C;
    public final C06350mc A0D;
    public final AbstractC02450Zr A0E;
    public final AbstractC07080oh A0F;
    public final Object A0G;
    public final boolean A0H;

    public JsonSerializer<Object> A00(AbstractC06960oT r4, Class<?> cls, AnonymousClass0a8 r6) throws AnonymousClass0aG {
        JsonSerializer<Object> A0B2;
        AbstractC06960oT A012;
        AnonymousClass0aI r0 = this.A00;
        if (r0 != null) {
            AnonymousClass0aI A042 = r6.A04(r0, cls);
            A0B2 = r6.A08(A042, this);
            A012 = r4.A01(A042._class, A0B2);
        } else {
            A0B2 = r6.A0B(cls, this);
            A012 = r4.A01(cls, A0B2);
        }
        C06940oR r1 = new C06940oR(A0B2, A012);
        AbstractC06960oT r02 = r1.A01;
        if (r4 != r02) {
            this.A04 = r02;
        }
        return r1.A00;
    }

    public AnonymousClass0Jw A01(AbstractC07200ov r4) {
        C02620aS r1 = this.A06;
        String A002 = r4.A00(r1.getValue());
        if (A002.equals(r1.toString())) {
            return this;
        }
        return new AnonymousClass0Jw(this, new C02620aS(A002));
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

    @Override // X.AbstractC02580aL
    public final AbstractC02450Zr A41() {
        return this.A0E;
    }

    @Override // X.AbstractC02580aL
    public final AnonymousClass0aI A4h() {
        return this.A0C;
    }

    public final String toString() {
        String name;
        String A052;
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
            A052 = ", no static serializer";
        } else {
            A052 = AnonymousClass006.A05(", static serializer of type ", jsonSerializer.getClass().getName());
        }
        sb.append(A052);
        sb.append(')');
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        if (r0 != false) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void A05(java.lang.Object r5, X.AbstractC02640aV r6, X.AnonymousClass0a8 r7) throws java.lang.Exception {
        /*
            r4 = this;
            java.lang.Object r3 = r4.A02(r5)
            if (r3 == 0) goto L_0x0028
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r2 = r4.A02
            if (r2 != 0) goto L_0x001a
            java.lang.Class r1 = r3.getClass()
            X.0oT r0 = r4.A04
            com.fasterxml.jackson.databind.JsonSerializer r2 = r0.A00(r1)
            if (r2 != 0) goto L_0x001a
            com.fasterxml.jackson.databind.JsonSerializer r2 = r4.A00(r0, r1, r7)
        L_0x001a:
            java.lang.Object r1 = r4.A0G
            if (r1 == 0) goto L_0x003a
            java.lang.Object r0 = X.AnonymousClass0Jw.A0I
            if (r0 != r1) goto L_0x0031
            boolean r0 = r2.A0B(r3)
        L_0x0026:
            if (r0 == 0) goto L_0x003a
        L_0x0028:
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r1 = r4.A01
            if (r1 == 0) goto L_0x0036
            r0 = 0
            r1.A0D(r0, r6, r7)
            return
        L_0x0031:
            boolean r0 = r1.equals(r3)
            goto L_0x0026
        L_0x0036:
            r6.A0D()
            return
        L_0x003a:
            if (r3 != r5) goto L_0x004a
            boolean r0 = r2.A08()
            if (r0 != 0) goto L_0x004a
            java.lang.String r1 = "Direct self-reference leading to cycle"
            X.0aG r0 = new X.0aG
            r0.<init>(r1)
            throw r0
        L_0x004a:
            X.0o6 r0 = r4.A03
            if (r0 != 0) goto L_0x0052
            r2.A0D(r3, r6, r7)
            return
        L_0x0052:
            r2.A0A(r3, r6, r7, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Jw.A05(java.lang.Object, X.0aV, X.0a8):void");
    }

    public void A06(Object obj, AbstractC02640aV r6, AnonymousClass0a8 r7) throws Exception {
        boolean equals;
        Class<?> cls;
        AbstractC06960oT r0;
        Object A022 = A02(obj);
        if (A022 != null) {
            JsonSerializer<Object> jsonSerializer = this.A02;
            if (jsonSerializer == null && (jsonSerializer = (r0 = this.A04).A00((cls = A022.getClass()))) == null) {
                jsonSerializer = A00(r0, cls, r7);
            }
            Object obj2 = this.A0G;
            if (obj2 != null) {
                if (A0I == obj2) {
                    equals = jsonSerializer.A0B(A022);
                } else {
                    equals = obj2.equals(A022);
                }
                if (equals) {
                    return;
                }
            }
            if (A022 != obj || jsonSerializer.A08()) {
                r6.A0N(this.A06);
                AnonymousClass0o6 r02 = this.A03;
                if (r02 == null) {
                    jsonSerializer.A0D(A022, r6, r7);
                } else {
                    jsonSerializer.A0A(A022, r6, r7, r02);
                }
            } else {
                throw new AnonymousClass0aG("Direct self-reference leading to cycle");
            }
        } else if (this.A01 != null) {
            r6.A0N(this.A06);
            this.A01.A0D(null, r6, r7);
        }
    }

    public AnonymousClass0Jw(AbstractC02410Zn r3, AbstractC02450Zr r4, AbstractC07080oh r5, AnonymousClass0aI r6, JsonSerializer<?> jsonSerializer, AnonymousClass0o6 r8, AnonymousClass0aI r9, boolean z, Object obj) {
        AnonymousClass0ZY r0;
        this.A0E = r4;
        this.A0F = r5;
        this.A06 = new C02620aS(r3.A0E());
        this.A0D = r3.A07();
        this.A0C = r6;
        this.A02 = jsonSerializer;
        if (jsonSerializer == null) {
            r0 = AnonymousClass0ZY.A00;
        } else {
            r0 = null;
        }
        this.A04 = r0;
        this.A03 = r8;
        this.A07 = r9;
        this.A0H = r3.A0F();
        if (r4 instanceof AnonymousClass0KC) {
            this.A09 = null;
            this.A08 = (Field) r4.A0R();
        } else if (r4 instanceof AnonymousClass0EC) {
            this.A09 = (Method) r4.A0R();
            this.A08 = null;
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A05("Can not pass member of type ", r4.getClass().getName()));
        }
        this.A0A = z;
        this.A0G = obj;
        this.A0B = r3.A0M();
        this.A01 = null;
    }

    public AnonymousClass0Jw(AnonymousClass0Jw r3, C02620aS r4) {
        this.A06 = r4;
        this.A0D = r3.A0D;
        this.A0E = r3.A0E;
        this.A0F = r3.A0F;
        this.A0C = r3.A0C;
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
        this.A0A = r3.A0A;
        this.A0G = r3.A0G;
        this.A0B = r3.A0B;
        this.A03 = r3.A03;
        this.A00 = r3.A00;
        this.A0H = r3.A0H;
    }
}
