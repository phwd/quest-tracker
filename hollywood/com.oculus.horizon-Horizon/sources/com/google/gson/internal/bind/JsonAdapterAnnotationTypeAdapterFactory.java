package com.google.gson.internal.bind;

import X.AbstractC08860ym;
import X.AnonymousClass0yl;
import X.C08780ya;
import X.C08920yx;
import X.C09110zQ;
import com.google.gson.annotations.JsonAdapter;

public final class JsonAdapterAnnotationTypeAdapterFactory implements AbstractC08860ym {
    public final C08920yx A00;

    @Override // X.AbstractC08860ym
    public final <T> AnonymousClass0yl<T> A1x(C08780ya r3, C09110zQ<T> r4) {
        JsonAdapter jsonAdapter = (JsonAdapter) r4.A01.getAnnotation(JsonAdapter.class);
        if (jsonAdapter == null) {
            return null;
        }
        return (AnonymousClass0yl<T>) A00(this.A00, r3, r4, jsonAdapter);
    }

    public JsonAdapterAnnotationTypeAdapterFactory(C08920yx r1) {
        this.A00 = r1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0017, code lost:
        if (r3 != null) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final X.AnonymousClass0yl<?> A00(X.C08920yx r3, X.C08780ya r4, X.C09110zQ<?> r5, com.google.gson.annotations.JsonAdapter r6) {
        /*
            java.lang.Class r1 = r6.value()
            X.0zQ r0 = new X.0zQ
            r0.<init>(r1)
            X.0zC r0 = r3.A00(r0)
            java.lang.Object r3 = r0.A1q()
            boolean r0 = r3 instanceof X.AnonymousClass0yl
            if (r0 == 0) goto L_0x0025
            X.0yl r3 = (X.AnonymousClass0yl) r3
        L_0x0017:
            if (r3 == 0) goto L_0x0041
        L_0x0019:
            boolean r0 = r6.nullSafe()
            if (r0 == 0) goto L_0x0041
            X.0c2 r0 = new X.0c2
            r0.<init>(r3)
            return r0
        L_0x0025:
            boolean r0 = r3 instanceof X.AbstractC08860ym
            if (r0 == 0) goto L_0x0030
            X.0ym r3 = (X.AbstractC08860ym) r3
            X.0yl r3 = r3.A1x(r4, r5)
            goto L_0x0017
        L_0x0030:
            boolean r2 = r3 instanceof X.AbstractC08810yd
            if (r2 == 0) goto L_0x0042
            r1 = 0
            r0 = r1
            if (r2 == 0) goto L_0x003b
            r1 = r3
            X.0yd r1 = (X.AbstractC08810yd) r1
        L_0x003b:
            X.0VB r3 = new X.0VB
            r3.<init>(r1, r4, r5, r0)
            goto L_0x0019
        L_0x0041:
            return r3
        L_0x0042:
            java.lang.String r4 = "Invalid attempt to bind an instance of "
            java.lang.Class r0 = r3.getClass()
            java.lang.String r3 = r0.getName()
            java.lang.String r2 = " as a @JsonAdapter for "
            java.lang.String r1 = r5.toString()
            java.lang.String r0 = ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer."
            java.lang.String r1 = X.AnonymousClass006.A09(r4, r3, r2, r1, r0)
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.A00(X.0yx, X.0ya, X.0zQ, com.google.gson.annotations.JsonAdapter):X.0yl");
    }
}
