package X;

import com.google.gson.annotations.JsonAdapter;

public final class LU implements AbstractC0237hg {
    public final C0232hV A00;

    @Override // X.AbstractC0237hg
    public final <T> hh<T> A1F(C0246hr hrVar, h6<T> h6Var) {
        JsonAdapter jsonAdapter = (JsonAdapter) h6Var.rawType.getAnnotation(JsonAdapter.class);
        if (jsonAdapter == null) {
            return null;
        }
        return (hh<T>) A00(this.A00, hrVar, h6Var, jsonAdapter);
    }

    public LU(C0232hV hVVar) {
        this.A00 = hVVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0017, code lost:
        if (r3 != null) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final X.hh<?> A00(X.C0232hV r3, X.C0246hr r4, X.h6<?> r5, com.google.gson.annotations.JsonAdapter r6) {
        /*
            java.lang.Class r1 = r6.value()
            X.h6 r0 = new X.h6
            r0.<init>(r1)
            X.hL r0 = r3.A00(r0)
            java.lang.Object r3 = r0.A1B()
            boolean r0 = r3 instanceof X.hh
            if (r0 == 0) goto L_0x0025
            X.hh r3 = (X.hh) r3
        L_0x0017:
            if (r3 == 0) goto L_0x0041
        L_0x0019:
            boolean r0 = r6.nullSafe()
            if (r0 == 0) goto L_0x0041
            X.Lv r0 = new X.Lv
            r0.<init>(r3)
            return r0
        L_0x0025:
            boolean r0 = r3 instanceof X.AbstractC0237hg
            if (r0 == 0) goto L_0x0030
            X.hg r3 = (X.AbstractC0237hg) r3
            X.hh r3 = r3.A1F(r4, r5)
            goto L_0x0017
        L_0x0030:
            boolean r2 = r3 instanceof X.AbstractC0242hn
            if (r2 == 0) goto L_0x0042
            r1 = 0
            r0 = r1
            if (r2 == 0) goto L_0x003b
            r1 = r3
            X.hn r1 = (X.AbstractC0242hn) r1
        L_0x003b:
            X.LE r3 = new X.LE
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
            java.lang.String r1 = X.AnonymousClass06.A06(r4, r3, r2, r1, r0)
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.LU.A00(X.hV, X.hr, X.h6, com.google.gson.annotations.JsonAdapter):X.hh");
    }
}
