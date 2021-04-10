package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04490qS;
import X.AbstractC04500qW;
import X.AbstractC04550qd;
import X.AbstractC04600qk;
import X.AnonymousClass006;
import X.AnonymousClass0qp;
import X.AnonymousClass0qq;
import X.AnonymousClass0r0;
import X.C02180iD;
import X.C02310iT;
import X.C03620oC;
import X.C04030p3;
import X.EnumC02160i9;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@JacksonStdImpl
public class JsonValueSerializer extends StdSerializer<Object> implements AbstractC04600qk, AbstractC04490qS, AbstractC04500qW {
    public final AbstractC02220iI A00;
    public final JsonSerializer<Object> A01;
    public final boolean A02;
    public final Method A03;

    @Override // X.AbstractC04600qk
    public final JsonSerializer<?> A2P(AbstractC02120i3 r8, AbstractC02220iI r9) throws C02180iD {
        JsonSerializer<Object> jsonSerializer;
        boolean z;
        Class<Double> cls;
        JsonSerializer<Object> jsonSerializer2 = this.A01;
        if (jsonSerializer2 == null) {
            if (r8.A06().A05(EnumC02160i9.USE_STATIC_TYPING) || Modifier.isFinal(this.A03.getReturnType().getModifiers())) {
                AbstractC02190iF A09 = r8.A07().A09(this.A03.getGenericReturnType(), null);
                AbstractC02220iI r4 = this.A00;
                AnonymousClass0r0 r3 = r8._knownSerializers;
                AnonymousClass0qp r1 = r3.A00;
                if (r1 == null) {
                    r1 = new AnonymousClass0qp(A09, true);
                    r3.A00 = r1;
                } else {
                    r1.A01 = A09;
                    r1.A02 = null;
                    r1.A03 = true;
                    r1.A00 = (A09.hashCode() - 1) - 1;
                }
                jsonSerializer = r3.A01.A00(r1);
                if (jsonSerializer == null) {
                    AnonymousClass0qq r6 = r8._serializerCache;
                    synchronized (r6) {
                        jsonSerializer = r6.A01.get(new AnonymousClass0qp(A09, true));
                    }
                    if (jsonSerializer == null) {
                        jsonSerializer = r8.A09(A09, r4);
                        AbstractC04550qd A04 = r8._serializerFactory.A04(r8._config, A09);
                        if (A04 != null) {
                            jsonSerializer = new TypeWrappedSerializer(A04.A00(r4), jsonSerializer);
                        }
                    }
                }
                Class<?> cls2 = A09._class;
                if (cls2.isPrimitive()) {
                    if (!(cls2 == Integer.TYPE || cls2 == Boolean.TYPE)) {
                        cls = Double.TYPE;
                    }
                    z = StdSerializer.A06(jsonSerializer);
                    if (r4 == r9 && jsonSerializer2 == jsonSerializer && z == this.A02) {
                        return this;
                    }
                } else {
                    if (!(cls2 == String.class || cls2 == Integer.class || cls2 == Boolean.class)) {
                        cls = Double.class;
                    }
                    z = StdSerializer.A06(jsonSerializer);
                    return this;
                }
                if (cls2 != cls) {
                    z = false;
                    return this;
                }
                z = StdSerializer.A06(jsonSerializer);
                return this;
            }
            return this;
        }
        if (jsonSerializer2 instanceof AbstractC04600qk) {
            jsonSerializer = ((AbstractC04600qk) jsonSerializer2).A2P(r8, r9);
            z = this.A02;
            if (this.A00 == r9 && jsonSerializer2 == jsonSerializer && z == z) {
                return this;
            }
        }
        return this;
        return new JsonValueSerializer(this, r9, jsonSerializer, z);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        AbstractC02220iI r0;
        JsonSerializer<Object> jsonSerializer = this.A01;
        if (jsonSerializer != null) {
            jsonSerializer.acceptJsonFormatVisitor(r3, null);
        } else if (r4 == null && ((r0 = this.A00) == null || r0.A59() == null)) {
            throw new NullPointerException("getProvider");
        } else {
            throw new NullPointerException("getProvider");
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r6, AbstractC02120i3 r7) throws IOException, C02310iT {
        try {
            Object invoke = this.A03.invoke(obj, new Object[0]);
            if (invoke == null) {
                r7.A0E(r6);
                return;
            }
            JsonSerializer<Object> jsonSerializer = this.A01;
            if (jsonSerializer == null) {
                jsonSerializer = r7.A0C(invoke.getClass(), true, this.A00);
            }
            jsonSerializer.serialize(invoke, r6, r7);
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            e = e2;
            while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                e = e.getCause();
            }
            if (e instanceof Error) {
                throw e;
            }
            throw C02180iD.A01(e, new C04030p3(obj, AnonymousClass006.A07(this.A03.getName(), "()")));
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(Object obj, AbstractC02300iS r5, AbstractC02120i3 r6, AbstractC04550qd r7) throws IOException, C03620oC {
        try {
            Object invoke = this.A03.invoke(obj, new Object[0]);
            if (invoke == null) {
                r6.A0E(r5);
                return;
            }
            JsonSerializer<Object> jsonSerializer = this.A01;
            if (jsonSerializer == null) {
                jsonSerializer = r6.A0B(invoke.getClass(), this.A00);
            } else if (this.A02) {
                r7.A03(obj, r5);
                jsonSerializer.serialize(invoke, r5, r6);
                r7.A06(obj, r5);
                return;
            }
            jsonSerializer.serializeWithType(invoke, r5, r6, r7);
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            e = e2;
            while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                e = e.getCause();
            }
            if (e instanceof Error) {
                throw e;
            }
            throw C02180iD.A01(e, new C04030p3(obj, AnonymousClass006.A07(this.A03.getName(), "()")));
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("(@JsonValue serializer for method ");
        Method method = this.A03;
        sb.append(method.getDeclaringClass());
        sb.append("#");
        sb.append(method.getName());
        sb.append(")");
        return sb.toString();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JsonValueSerializer(com.fasterxml.jackson.databind.ser.std.JsonValueSerializer r2, X.AbstractC02220iI r3, com.fasterxml.jackson.databind.JsonSerializer<?> r4, boolean r5) {
        /*
            r1 = this;
            java.lang.Class r0 = r2.handledType()
            if (r0 != 0) goto L_0x0008
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
        L_0x0008:
            r1.<init>(r0)
            java.lang.reflect.Method r0 = r2.A03
            r1.A03 = r0
            r1.A01 = r4
            r1.A00 = r3
            r1.A02 = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.JsonValueSerializer.<init>(com.fasterxml.jackson.databind.ser.std.JsonValueSerializer, X.0iI, com.fasterxml.jackson.databind.JsonSerializer, boolean):void");
    }

    public JsonValueSerializer(Method method, JsonSerializer<Object> jsonSerializer) {
        super(Object.class);
        this.A03 = method;
        this.A01 = jsonSerializer;
        this.A00 = null;
        this.A02 = true;
    }
}
