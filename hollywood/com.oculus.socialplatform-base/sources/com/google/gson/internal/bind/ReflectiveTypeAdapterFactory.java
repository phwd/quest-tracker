package com.google.gson.internal.bind;

import X.AnonymousClass13M;
import X.AnonymousClass13Z;
import X.AnonymousClass13k;
import X.AnonymousClass14G;
import com.google.gson.ExclusionStrategy;
import com.google.gson.internal.Excluder;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public final class ReflectiveTypeAdapterFactory implements AnonymousClass13Z {
    public final AnonymousClass13M A00;
    public final AnonymousClass13k A01;
    public final AnonymousClass14G A02 = AnonymousClass14G.A00;
    public final Excluder A03;
    public final JsonAdapterAnnotationTypeAdapterFactory A04;

    private final boolean A00(Field field, boolean z) {
        List<ExclusionStrategy> list;
        Excluder excluder = this.A03;
        Class<?> type = field.getType();
        if (!Enum.class.isAssignableFrom(type) && (type.isAnonymousClass() || type.isLocalClass())) {
            return false;
        }
        Excluder.A00(excluder, z);
        if ((136 & field.getModifiers()) != 0 || field.isSynthetic()) {
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
        Iterator<ExclusionStrategy> it = list.iterator();
        if (!it.hasNext()) {
            return true;
        }
        it.next();
        throw new NullPointerException("shouldSkipField");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:52:0x006f */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.util.AbstractCollection, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.List] */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a6, code lost:
        if (r26 == null) goto L_0x00a8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ed A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0043 A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // X.AnonymousClass13Z
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> X.AnonymousClass13Y<T> A2M(X.AnonymousClass13N r33, X.AnonymousClass14H<T> r34) {
        /*
        // Method dump skipped, instructions count: 299
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.A2M(X.13N, X.14H):X.13Y");
    }

    public ReflectiveTypeAdapterFactory(AnonymousClass13k r2, AnonymousClass13M r3, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory) {
        this.A01 = r2;
        this.A00 = r3;
        this.A03 = excluder;
        this.A04 = jsonAdapterAnnotationTypeAdapterFactory;
    }
}
