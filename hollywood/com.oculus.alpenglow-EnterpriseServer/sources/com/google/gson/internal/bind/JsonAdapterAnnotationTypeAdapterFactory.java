package com.google.gson.internal.bind;

import X.AnonymousClass08D;
import X.AnonymousClass0Bd;
import X.AnonymousClass0C3;
import X.AnonymousClass0Cp;
import X.AnonymousClass0Fe;
import com.google.gson.annotations.JsonAdapter;

public final class JsonAdapterAnnotationTypeAdapterFactory implements AnonymousClass0C3 {
    public final AnonymousClass0Cp A00;

    @Override // X.AnonymousClass0C3
    public final <T> AnonymousClass0Bd<T> A1v(AnonymousClass08D r3, AnonymousClass0Fe<T> r4) {
        JsonAdapter jsonAdapter = (JsonAdapter) r4.A01.getAnnotation(JsonAdapter.class);
        if (jsonAdapter == null) {
            return null;
        }
        return (AnonymousClass0Bd<T>) A00(this.A00, r3, r4, jsonAdapter);
    }

    public JsonAdapterAnnotationTypeAdapterFactory(AnonymousClass0Cp r1) {
        this.A00 = r1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0017, code lost:
        if (r2 != null) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final X.AnonymousClass0Bd<?> A00(X.AnonymousClass0Cp r5, X.AnonymousClass08D r6, X.AnonymousClass0Fe<?> r7, com.google.gson.annotations.JsonAdapter r8) {
        /*
        // Method dump skipped, instructions count: 109
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.A00(X.0Cp, X.08D, X.0Fe, com.google.gson.annotations.JsonAdapter):X.0Bd");
    }
}
