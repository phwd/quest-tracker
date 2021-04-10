package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02230iJ;
import X.AbstractC02300iS;
import X.AbstractC04490qS;
import X.AbstractC04500qW;
import X.C02180iD;
import X.C02310iT;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

public abstract class StdSerializer<T> extends JsonSerializer<T> implements AbstractC04490qS, AbstractC04500qW {
    public final Class<T> A00;

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public abstract void serialize(T t, AbstractC02300iS v, AbstractC02120i3 v2) throws IOException, C02310iT;

    public static final JsonSerializer<?> A03(AbstractC02120i3 r2, AbstractC02220iI r3, JsonSerializer<?> jsonSerializer) throws C02180iD {
        Object A0g;
        AbstractC02230iJ A01 = r2._config.A01();
        if (A01 == null || r3 == null || (A0g = A01.A0g(r3.A4N())) == null) {
            return jsonSerializer;
        }
        r2.A05(A0g);
        throw new NullPointerException("getOutputType");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        if (r2._config.A06(X.AnonymousClass0i4.WRAP_EXCEPTIONS) != false) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A04(X.AbstractC02120i3 r2, java.lang.Throwable r3, java.lang.Object r4, int r5) throws java.io.IOException {
        /*
        L_0x0000:
            boolean r0 = r3 instanceof java.lang.reflect.InvocationTargetException
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r0 = r3.getCause()
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r3 = r3.getCause()
            goto L_0x0000
        L_0x000f:
            boolean r0 = r3 instanceof java.lang.Error
            if (r0 != 0) goto L_0x003d
            if (r2 == 0) goto L_0x0020
            X.0i4 r1 = X.AnonymousClass0i4.WRAP_EXCEPTIONS
            X.0HM r0 = r2._config
            boolean r0 = r0.A06(r1)
            r1 = 0
            if (r0 == 0) goto L_0x0021
        L_0x0020:
            r1 = 1
        L_0x0021:
            boolean r0 = r3 instanceof java.io.IOException
            if (r0 == 0) goto L_0x002c
            if (r1 == 0) goto L_0x002b
            boolean r0 = r3 instanceof X.C02180iD
            if (r0 != 0) goto L_0x0033
        L_0x002b:
            throw r3
        L_0x002c:
            if (r1 != 0) goto L_0x0033
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L_0x0033
            throw r3
        L_0x0033:
            X.0p3 r0 = new X.0p3
            r0.<init>(r4, r5)
            X.0iD r0 = X.C02180iD.A01(r3, r0)
            throw r0
        L_0x003d:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.StdSerializer.A04(X.0i3, java.lang.Throwable, java.lang.Object, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        if (r2._config.A06(X.AnonymousClass0i4.WRAP_EXCEPTIONS) != false) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A05(X.AbstractC02120i3 r2, java.lang.Throwable r3, java.lang.Object r4, java.lang.String r5) throws java.io.IOException {
        /*
        L_0x0000:
            boolean r0 = r3 instanceof java.lang.reflect.InvocationTargetException
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r0 = r3.getCause()
            if (r0 == 0) goto L_0x000f
            java.lang.Throwable r3 = r3.getCause()
            goto L_0x0000
        L_0x000f:
            boolean r0 = r3 instanceof java.lang.Error
            if (r0 != 0) goto L_0x003d
            if (r2 == 0) goto L_0x0020
            X.0i4 r1 = X.AnonymousClass0i4.WRAP_EXCEPTIONS
            X.0HM r0 = r2._config
            boolean r0 = r0.A06(r1)
            r1 = 0
            if (r0 == 0) goto L_0x0021
        L_0x0020:
            r1 = 1
        L_0x0021:
            boolean r0 = r3 instanceof java.io.IOException
            if (r0 == 0) goto L_0x002c
            if (r1 == 0) goto L_0x002b
            boolean r0 = r3 instanceof X.C02180iD
            if (r0 != 0) goto L_0x0033
        L_0x002b:
            throw r3
        L_0x002c:
            if (r1 != 0) goto L_0x0033
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L_0x0033
            throw r3
        L_0x0033:
            X.0p3 r0 = new X.0p3
            r0.<init>(r4, r5)
            X.0iD r0 = X.C02180iD.A01(r3, r0)
            throw r0
        L_0x003d:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.StdSerializer.A05(X.0i3, java.lang.Throwable, java.lang.Object, java.lang.String):void");
    }

    public static final boolean A06(JsonSerializer<?> jsonSerializer) {
        if (jsonSerializer == null || jsonSerializer.getClass().getAnnotation(JacksonStdImpl.class) == null) {
            return false;
        }
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        throw new NullPointerException("expectAnyFormat");
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final Class<T> handledType() {
        return this.A00;
    }

    public StdSerializer(AbstractC02190iF r2) {
        this.A00 = (Class<T>) r2._class;
    }

    public StdSerializer(Class<T> cls) {
        this.A00 = cls;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Class<?> */
    /* JADX WARN: Multi-variable type inference failed */
    public StdSerializer(Class<?> cls, boolean z) {
        this.A00 = cls;
    }
}
