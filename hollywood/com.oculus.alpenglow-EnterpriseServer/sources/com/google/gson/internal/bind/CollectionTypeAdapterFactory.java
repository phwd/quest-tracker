package com.google.gson.internal.bind;

import X.AnonymousClass08D;
import X.AnonymousClass0Bd;
import X.AnonymousClass0C3;
import X.AnonymousClass0Ch;
import X.AnonymousClass0Cp;
import X.AnonymousClass0Fe;
import X.AnonymousClass0X0;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;

public final class CollectionTypeAdapterFactory implements AnonymousClass0C3 {
    public final AnonymousClass0Cp A00;

    @Override // X.AnonymousClass0C3
    public final <T> AnonymousClass0Bd<T> A1v(AnonymousClass08D r5, AnonymousClass0Fe<T> r6) {
        Type type;
        Type type2 = r6.A02;
        Class<? super T> cls = r6.A01;
        if (!Collection.class.isAssignableFrom(cls)) {
            return null;
        }
        Type A03 = AnonymousClass0Ch.A03(type2, cls, Collection.class);
        if (A03 instanceof WildcardType) {
            A03 = ((WildcardType) A03).getUpperBounds()[0];
        }
        if (A03 instanceof ParameterizedType) {
            type = ((ParameterizedType) A03).getActualTypeArguments()[0];
        } else {
            type = Object.class;
        }
        return new AnonymousClass0X0(r5, type, r5.A07(new AnonymousClass0Fe<>(type)), this.A00.A00(r6));
    }

    public CollectionTypeAdapterFactory(AnonymousClass0Cp r1) {
        this.A00 = r1;
    }
}
