package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02580aL;
import X.AbstractC02590aM;
import X.AbstractC02640aV;
import X.AbstractC06790ns;
import X.AbstractC06810nz;
import X.AnonymousClass0a8;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.C02650aW;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

public abstract class StdSerializer<T> extends JsonSerializer<T> implements AbstractC06790ns, AbstractC06810nz {
    public final Class<T> A00;

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public abstract void A0D(T t, AbstractC02640aV v, AnonymousClass0a8 v2) throws IOException, C02650aW;

    public static final JsonSerializer<?> A03(AnonymousClass0a8 r2, AbstractC02580aL r3, JsonSerializer<?> jsonSerializer) throws AnonymousClass0aG {
        Object A0g;
        AbstractC02590aM A01 = r2._config.A01();
        if (A01 == null || r3 == null || (A0g = A01.A0g(r3.A41())) == null) {
            return jsonSerializer;
        }
        r2.A05(A0g);
        throw null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        if ((r3 instanceof X.AnonymousClass0aG) != false) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        if (r2._config.A06(X.AnonymousClass0a9.WRAP_EXCEPTIONS) != false) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A04(X.AnonymousClass0a8 r2, java.lang.Throwable r3, java.lang.Object r4, int r5) throws java.io.IOException {
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
            if (r0 != 0) goto L_0x002b
            if (r2 == 0) goto L_0x0020
            X.0a9 r1 = X.AnonymousClass0a9.WRAP_EXCEPTIONS
            X.0FM r0 = r2._config
            boolean r0 = r0.A06(r1)
            r1 = 0
            if (r0 == 0) goto L_0x0021
        L_0x0020:
            r1 = 1
        L_0x0021:
            boolean r0 = r3 instanceof java.io.IOException
            if (r0 == 0) goto L_0x002c
            if (r1 == 0) goto L_0x002b
            boolean r0 = r3 instanceof X.AnonymousClass0aG
            if (r0 != 0) goto L_0x0033
        L_0x002b:
            throw r3
        L_0x002c:
            if (r1 != 0) goto L_0x0033
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L_0x0033
            throw r3
        L_0x0033:
            X.0mV r0 = new X.0mV
            r0.<init>(r4, r5)
            X.0aG r0 = X.AnonymousClass0aG.A01(r3, r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.StdSerializer.A04(X.0a8, java.lang.Throwable, java.lang.Object, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        if ((r3 instanceof X.AnonymousClass0aG) != false) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        if (r2._config.A06(X.AnonymousClass0a9.WRAP_EXCEPTIONS) != false) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A05(X.AnonymousClass0a8 r2, java.lang.Throwable r3, java.lang.Object r4, java.lang.String r5) throws java.io.IOException {
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
            if (r0 != 0) goto L_0x002b
            if (r2 == 0) goto L_0x0020
            X.0a9 r1 = X.AnonymousClass0a9.WRAP_EXCEPTIONS
            X.0FM r0 = r2._config
            boolean r0 = r0.A06(r1)
            r1 = 0
            if (r0 == 0) goto L_0x0021
        L_0x0020:
            r1 = 1
        L_0x0021:
            boolean r0 = r3 instanceof java.io.IOException
            if (r0 == 0) goto L_0x002c
            if (r1 == 0) goto L_0x002b
            boolean r0 = r3 instanceof X.AnonymousClass0aG
            if (r0 != 0) goto L_0x0033
        L_0x002b:
            throw r3
        L_0x002c:
            if (r1 != 0) goto L_0x0033
            boolean r0 = r3 instanceof java.lang.RuntimeException
            if (r0 == 0) goto L_0x0033
            throw r3
        L_0x0033:
            X.0mV r0 = new X.0mV
            r0.<init>(r4, r5)
            X.0aG r0 = X.AnonymousClass0aG.A01(r3, r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.StdSerializer.A05(X.0a8, java.lang.Throwable, java.lang.Object, java.lang.String):void");
    }

    public static final boolean A06(JsonSerializer<?> jsonSerializer) {
        if (jsonSerializer == null || jsonSerializer.getClass().getAnnotation(JacksonStdImpl.class) == null) {
            return false;
        }
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final Class<T> A0C() {
        return this.A00;
    }

    public StdSerializer(AnonymousClass0aI r2) {
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
