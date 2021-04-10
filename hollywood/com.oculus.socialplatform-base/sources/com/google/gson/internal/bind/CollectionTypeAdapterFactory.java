package com.google.gson.internal.bind;

import X.AnonymousClass0e1;
import X.AnonymousClass13N;
import X.AnonymousClass13Y;
import X.AnonymousClass13Z;
import X.AnonymousClass13j;
import X.AnonymousClass13k;
import X.AnonymousClass14H;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;

public final class CollectionTypeAdapterFactory implements AnonymousClass13Z {
    public final AnonymousClass13k A00;

    @Override // X.AnonymousClass13Z
    public final <T> AnonymousClass13Y<T> A2M(AnonymousClass13N r5, AnonymousClass14H<T> r6) {
        Type type;
        Type type2 = r6.A02;
        Class<? super T> cls = r6.A01;
        if (!Collection.class.isAssignableFrom(cls)) {
            return null;
        }
        Type A03 = AnonymousClass13j.A03(type2, cls, Collection.class);
        if (A03 instanceof WildcardType) {
            A03 = ((WildcardType) A03).getUpperBounds()[0];
        }
        if (A03 instanceof ParameterizedType) {
            type = ((ParameterizedType) A03).getActualTypeArguments()[0];
        } else {
            type = Object.class;
        }
        return new AnonymousClass0e1(r5, type, r5.A03(new AnonymousClass14H<>(type)), this.A00.A00(r6));
    }

    public CollectionTypeAdapterFactory(AnonymousClass13k r1) {
        this.A00 = r1;
    }
}
