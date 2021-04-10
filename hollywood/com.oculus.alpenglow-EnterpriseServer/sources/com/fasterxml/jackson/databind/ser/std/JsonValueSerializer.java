package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AbstractC06790ns;
import X.AbstractC06810nz;
import X.AbstractC06840oE;
import X.AnonymousClass006;
import X.AnonymousClass0a8;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.AnonymousClass0o6;
import X.C02650aW;
import X.C05910ld;
import X.C06290mV;
import X.C06880oJ;
import X.C06890oK;
import X.C06970oU;
import X.EnumC02540aC;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@JacksonStdImpl
public final class JsonValueSerializer extends StdSerializer<Object> implements AbstractC06840oE, AbstractC06790ns, AbstractC06810nz {
    public final AbstractC02580aL A00;
    public final JsonSerializer<Object> A01;
    public final boolean A02;
    public final Method A03;

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(Object obj, AbstractC02640aV r5, AnonymousClass0a8 r6, AnonymousClass0o6 r7) throws IOException, C05910ld {
        try {
            Object invoke = this.A03.invoke(obj, new Object[0]);
            if (invoke == null) {
                r6.A0D(r5);
                return;
            }
            JsonSerializer<Object> jsonSerializer = this.A01;
            if (jsonSerializer == null) {
                jsonSerializer = r6.A0B(invoke.getClass(), this.A00);
            } else if (this.A02) {
                r7.A03(obj, r5);
                jsonSerializer.A0D(invoke, r5, r6);
                r7.A06(obj, r5);
                return;
            }
            jsonSerializer.A0A(invoke, r5, r6, r7);
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
            throw AnonymousClass0aG.A01(e, new C06290mV(obj, AnonymousClass006.A05(this.A03.getName(), "()")));
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r5, AnonymousClass0a8 r6) throws IOException, C02650aW {
        try {
            Object invoke = this.A03.invoke(obj, new Object[0]);
            if (invoke == null) {
                r6.A0D(r5);
                return;
            }
            JsonSerializer<Object> jsonSerializer = this.A01;
            if (jsonSerializer == null) {
                jsonSerializer = r6.A0A(invoke.getClass(), this.A00);
            }
            jsonSerializer.A0D(invoke, r5, r6);
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
            throw AnonymousClass0aG.A01(e, new C06290mV(obj, AnonymousClass006.A05(this.A03.getName(), "()")));
        }
    }

    @Override // X.AbstractC06840oE
    public final JsonSerializer<?> A1x(AnonymousClass0a8 r8, AbstractC02580aL r9) throws AnonymousClass0aG {
        JsonSerializer<Object> jsonSerializer;
        boolean z;
        Class<Double> cls;
        JsonSerializer<Object> jsonSerializer2 = this.A01;
        if (jsonSerializer2 == null) {
            if (r8.A06().A05(EnumC02540aC.USE_STATIC_TYPING) || Modifier.isFinal(this.A03.getReturnType().getModifiers())) {
                AnonymousClass0aI A09 = r8.A07().A09(this.A03.getGenericReturnType(), null);
                AbstractC02580aL r4 = this.A00;
                C06970oU r3 = r8._knownSerializers;
                C06880oJ r1 = r3.A00;
                if (r1 == null) {
                    r1 = new C06880oJ(A09, true);
                    r3.A00 = r1;
                } else {
                    r1.A01 = A09;
                    r1.A02 = null;
                    r1.A03 = true;
                    r1.A00 = (A09.hashCode() - 1) - 1;
                }
                jsonSerializer = r3.A01.A00(r1);
                if (jsonSerializer == null) {
                    C06890oK r6 = r8._serializerCache;
                    synchronized (r6) {
                        jsonSerializer = r6.A01.get(new C06880oJ(A09, true));
                    }
                    if (jsonSerializer == null) {
                        jsonSerializer = r8.A08(A09, r4);
                        AnonymousClass0o6 A04 = r8._serializerFactory.A04(r8._config, A09);
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
        if (jsonSerializer2 instanceof AbstractC06840oE) {
            jsonSerializer = ((AbstractC06840oE) jsonSerializer2).A1x(r8, r9);
            z = this.A02;
            if (this.A00 == r9 && jsonSerializer2 == jsonSerializer && z == z) {
                return this;
            }
        }
        return this;
        return new JsonValueSerializer(this, r9, jsonSerializer, z);
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
    public JsonValueSerializer(com.fasterxml.jackson.databind.ser.std.JsonValueSerializer r2, X.AbstractC02580aL r3, com.fasterxml.jackson.databind.JsonSerializer<?> r4, boolean r5) {
        /*
            r1 = this;
            java.lang.Class r0 = r2.A0C()
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.JsonValueSerializer.<init>(com.fasterxml.jackson.databind.ser.std.JsonValueSerializer, X.0aL, com.fasterxml.jackson.databind.JsonSerializer, boolean):void");
    }

    public JsonValueSerializer(Method method, JsonSerializer<Object> jsonSerializer) {
        super(Object.class);
        this.A03 = method;
        this.A01 = jsonSerializer;
        this.A00 = null;
        this.A02 = true;
    }
}
