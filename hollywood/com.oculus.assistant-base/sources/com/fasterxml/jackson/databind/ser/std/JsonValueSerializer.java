package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC0278Pa;
import X.AbstractC1024qt;
import X.AbstractC1031r2;
import X.C0283Pf;
import X.C0284Pg;
import X.C0293Pp;
import X.EnumC1027qy;
import X.O5;
import X.PU;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@JacksonStdImpl
public class JsonValueSerializer extends StdSerializer implements AbstractC0278Pa {
    public final O5 A00;
    public final JsonSerializer A01;
    public final Method A02;
    public final boolean A03;

    @Override // X.AbstractC0278Pa
    public final JsonSerializer A1Y(AbstractC1031r2 r2Var, O5 o5) {
        JsonSerializer jsonSerializer;
        boolean z;
        Class<Double> cls;
        JsonSerializer jsonSerializer2 = this.A01;
        if (jsonSerializer2 == null) {
            if (r2Var.A04().A05(EnumC1027qy.USE_STATIC_TYPING) || Modifier.isFinal(this.A02.getReturnType().getModifiers())) {
                AbstractC1024qt A09 = r2Var.A05().A09(this.A02.getGenericReturnType(), null);
                O5 o52 = this.A00;
                C0293Pp pp = r2Var._knownSerializers;
                C0283Pf pf = pp.A00;
                if (pf == null) {
                    pf = new C0283Pf(A09, true);
                    pp.A00 = pf;
                } else {
                    pf.A01 = A09;
                    pf.A02 = null;
                    pf.A03 = true;
                    pf.A00 = (A09.hashCode() - 1) - 1;
                }
                jsonSerializer = pp.A01.A00(pf);
                if (jsonSerializer == null) {
                    C0284Pg pg = r2Var._serializerCache;
                    synchronized (pg) {
                        jsonSerializer = (JsonSerializer) pg.A01.get(new C0283Pf(A09, true));
                    }
                    if (jsonSerializer == null) {
                        jsonSerializer = r2Var.A08(A09, o52);
                        PU A032 = r2Var._serializerFactory.A03(r2Var._config, A09);
                        if (A032 != null) {
                            jsonSerializer = new TypeWrappedSerializer(A032.A00(o52), jsonSerializer);
                        }
                    }
                }
                Class<Double> cls2 = A09._class;
                if (cls2.isPrimitive()) {
                    if (!(cls2 == Integer.TYPE || cls2 == Boolean.TYPE)) {
                        cls = Double.TYPE;
                    }
                    z = StdSerializer.A05(jsonSerializer);
                    if (o52 == o5 && jsonSerializer2 == jsonSerializer && z == this.A03) {
                        return this;
                    }
                } else {
                    if (!(cls2 == String.class || cls2 == Integer.class || cls2 == Boolean.class)) {
                        cls = Double.class;
                    }
                    z = StdSerializer.A05(jsonSerializer);
                    return this;
                }
                if (cls2 != cls) {
                    z = false;
                    return this;
                }
                z = StdSerializer.A05(jsonSerializer);
                return this;
            }
            return this;
        }
        if (jsonSerializer2 instanceof AbstractC0278Pa) {
            jsonSerializer = ((AbstractC0278Pa) jsonSerializer2).A1Y(r2Var, o5);
            z = this.A03;
            if (this.A00 == o5 && jsonSerializer2 == jsonSerializer && z == z) {
                return this;
            }
        }
        return this;
        return new JsonValueSerializer(this, o5, jsonSerializer, z);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("(@JsonValue serializer for method ");
        Method method = this.A02;
        sb.append(method.getDeclaringClass());
        sb.append("#");
        sb.append(method.getName());
        sb.append(")");
        return sb.toString();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JsonValueSerializer(com.fasterxml.jackson.databind.ser.std.JsonValueSerializer r2, X.O5 r3, com.fasterxml.jackson.databind.JsonSerializer r4, boolean r5) {
        /*
            r1 = this;
            java.lang.Class r0 = r2.A08()
            if (r0 != 0) goto L_0x0008
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
        L_0x0008:
            r1.<init>(r0)
            java.lang.reflect.Method r0 = r2.A02
            r1.A02 = r0
            r1.A01 = r4
            r1.A00 = r3
            r1.A03 = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.JsonValueSerializer.<init>(com.fasterxml.jackson.databind.ser.std.JsonValueSerializer, X.O5, com.fasterxml.jackson.databind.JsonSerializer, boolean):void");
    }

    public JsonValueSerializer(Method method, JsonSerializer jsonSerializer) {
        super(Object.class);
        this.A02 = method;
        this.A01 = jsonSerializer;
        this.A00 = null;
        this.A03 = true;
    }
}
