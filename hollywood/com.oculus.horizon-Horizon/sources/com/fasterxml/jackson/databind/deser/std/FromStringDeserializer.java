package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AnonymousClass006;
import X.AnonymousClass0jg;
import X.C03990gZ;
import java.io.IOException;

public abstract class FromStringDeserializer<T> extends StdScalarDeserializer<T> {
    public static final long serialVersionUID = 1;

    public abstract T A0Q(String str, AbstractC04020gg v) throws IOException, AnonymousClass0jg;

    public T A0P(Object obj, AbstractC04020gg r6) throws IOException, AnonymousClass0jg {
        throw C03990gZ.A00(null, AnonymousClass006.A08("Don't know how to convert embedded Object of type ", obj.getClass().getName(), " into ", this._valueClass.getName()));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:6|7|(1:9)|10|11) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        throw null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001e */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T A09(X.AbstractC04100gp r5, X.AbstractC04020gg r6) throws java.io.IOException, X.AnonymousClass0jg {
        /*
            r4 = this;
            java.lang.String r1 = r5.A0J()
            r3 = 0
            if (r1 == 0) goto L_0x0020
            int r0 = r1.length()
            if (r0 == 0) goto L_0x003f
            java.lang.String r1 = r1.trim()
            int r0 = r1.length()
            if (r0 == 0) goto L_0x003f
            java.lang.Object r0 = r4.A0Q(r1, r6)     // Catch:{ IllegalArgumentException -> 0x001e }
            if (r0 == 0) goto L_0x001e
            return r0
        L_0x001e:
            throw r3     // Catch:{ Exception -> 0x001f }
        L_0x001f:
            throw r3
        L_0x0020:
            X.0ji r1 = r5.A0a()
            X.0ji r0 = X.EnumC04820ji.VALUE_EMBEDDED_OBJECT
            if (r1 != r0) goto L_0x0040
            java.lang.Object r2 = r5.A0S()
            if (r2 == 0) goto L_0x003f
            java.lang.Class<?> r1 = r4._valueClass
            java.lang.Class r0 = r2.getClass()
            boolean r0 = r1.isAssignableFrom(r0)
            if (r0 != 0) goto L_0x003e
            java.lang.Object r2 = r4.A0P(r2, r6)
        L_0x003e:
            return r2
        L_0x003f:
            return r3
        L_0x0040:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.FromStringDeserializer.A09(X.0gp, X.0gg):java.lang.Object");
    }

    public FromStringDeserializer(Class<?> cls) {
        super(cls);
    }
}
