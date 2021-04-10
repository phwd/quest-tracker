package com.google.gson.internal.bind;

import X.AnonymousClass07F;
import X.AnonymousClass087;
import X.AnonymousClass0C3;
import X.AnonymousClass0Cp;
import X.AnonymousClass0Fd;
import X.C007207m;
import com.google.gson.internal.Excluder;
import java.lang.reflect.Field;
import java.util.List;

public final class ReflectiveTypeAdapterFactory implements AnonymousClass0C3 {
    public final AnonymousClass087 A00;
    public final AnonymousClass0Cp A01;
    public final AnonymousClass0Fd A02 = AnonymousClass0Fd.A00;
    public final Excluder A03;
    public final JsonAdapterAnnotationTypeAdapterFactory A04;

    private final boolean A00(Field field, boolean z) {
        List<AnonymousClass07F> list;
        Excluder excluder = this.A03;
        Class<?> type = field.getType();
        if ((!Enum.class.isAssignableFrom(type) && (type.isAnonymousClass() || type.isLocalClass())) || Excluder.A00(excluder, type, z) || (136 & field.getModifiers()) != 0 || field.isSynthetic()) {
            return false;
        }
        Class<?> type2 = field.getType();
        if (!Enum.class.isAssignableFrom(type2) && (type2.isAnonymousClass() || type2.isLocalClass())) {
            return false;
        }
        if (z) {
            list = excluder.A01;
        } else {
            list = excluder.A00;
        }
        if (list.isEmpty()) {
            return true;
        }
        C007207m r2 = new C007207m(field);
        for (AnonymousClass07F r0 : list) {
            if (r0.A8O(r2)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a6, code lost:
        if (r26 == null) goto L_0x00a8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ed A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0043 A[SYNTHETIC] */
    @Override // X.AnonymousClass0C3
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> X.AnonymousClass0Bd<T> A1v(X.AnonymousClass08D r33, X.AnonymousClass0Fe<T> r34) {
        /*
        // Method dump skipped, instructions count: 299
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.A1v(X.08D, X.0Fe):X.0Bd");
    }

    public ReflectiveTypeAdapterFactory(AnonymousClass0Cp r2, AnonymousClass087 r3, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory) {
        this.A01 = r2;
        this.A00 = r3;
        this.A03 = excluder;
        this.A04 = jsonAdapterAnnotationTypeAdapterFactory;
    }
}
