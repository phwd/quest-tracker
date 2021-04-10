package com.google.gson.internal.bind;

import X.AnonymousClass13N;
import X.AnonymousClass13Y;
import X.AnonymousClass13Z;
import X.AnonymousClass13k;
import X.AnonymousClass14H;
import com.google.gson.annotations.JsonAdapter;

public final class JsonAdapterAnnotationTypeAdapterFactory implements AnonymousClass13Z {
    public final AnonymousClass13k A00;

    @Override // X.AnonymousClass13Z
    public final <T> AnonymousClass13Y<T> A2M(AnonymousClass13N r3, AnonymousClass14H<T> r4) {
        JsonAdapter jsonAdapter = (JsonAdapter) r4.A01.getAnnotation(JsonAdapter.class);
        if (jsonAdapter == null) {
            return null;
        }
        return (AnonymousClass13Y<T>) A00(this.A00, r3, r4, jsonAdapter);
    }

    public JsonAdapterAnnotationTypeAdapterFactory(AnonymousClass13k r1) {
        this.A00 = r1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0017, code lost:
        if (r3 != null) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final X.AnonymousClass13Y<?> A00(X.AnonymousClass13k r3, X.AnonymousClass13N r4, X.AnonymousClass14H<?> r5, com.google.gson.annotations.JsonAdapter r6) {
        /*
            java.lang.Class r1 = r6.value()
            X.14H r0 = new X.14H
            r0.<init>(r1)
            X.143 r0 = r3.A00(r0)
            java.lang.Object r3 = r0.A2F()
            boolean r0 = r3 instanceof X.AnonymousClass13Y
            if (r0 == 0) goto L_0x0025
            X.13Y r3 = (X.AnonymousClass13Y) r3
        L_0x0017:
            if (r3 == 0) goto L_0x0041
        L_0x0019:
            boolean r0 = r6.nullSafe()
            if (r0 == 0) goto L_0x0041
            X.0eO r0 = new X.0eO
            r0.<init>(r3)
            return r0
        L_0x0025:
            boolean r0 = r3 instanceof X.AnonymousClass13Z
            if (r0 == 0) goto L_0x0030
            X.13Z r3 = (X.AnonymousClass13Z) r3
            X.13Y r3 = r3.A2M(r4, r5)
            goto L_0x0017
        L_0x0030:
            boolean r2 = r3 instanceof X.AnonymousClass13Q
            if (r2 == 0) goto L_0x0042
            r1 = 0
            r0 = r1
            if (r2 == 0) goto L_0x003b
            r1 = r3
            X.13Q r1 = (X.AnonymousClass13Q) r1
        L_0x003b:
            X.0dh r3 = new X.0dh
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
            java.lang.String r1 = X.AnonymousClass006.A0C(r4, r3, r2, r1, r0)
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.A00(X.13k, X.13N, X.14H, com.google.gson.annotations.JsonAdapter):X.13Y");
    }
}
