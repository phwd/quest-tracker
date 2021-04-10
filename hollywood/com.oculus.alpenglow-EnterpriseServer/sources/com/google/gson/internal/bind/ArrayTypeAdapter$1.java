package com.google.gson.internal.bind;

import X.AnonymousClass08D;
import X.AnonymousClass0Bd;
import X.AnonymousClass0C3;
import X.AnonymousClass0Ch;
import X.AnonymousClass0Fe;
import X.AnonymousClass0X1;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public class ArrayTypeAdapter$1 implements AnonymousClass0C3 {
    @Override // X.AnonymousClass0C3
    public final <T> AnonymousClass0Bd<T> A1v(AnonymousClass08D r4, AnonymousClass0Fe<T> r5) {
        Type componentType;
        Type type = r5.A02;
        boolean z = type instanceof GenericArrayType;
        if (!z && (!(type instanceof Class) || !((Class) type).isArray())) {
            return null;
        }
        if (z) {
            componentType = ((GenericArrayType) type).getGenericComponentType();
        } else {
            componentType = ((Class) type).getComponentType();
        }
        return new AnonymousClass0X1(r4, r4.A07(new AnonymousClass0Fe<>(componentType)), AnonymousClass0Ch.A00(componentType));
    }
}
