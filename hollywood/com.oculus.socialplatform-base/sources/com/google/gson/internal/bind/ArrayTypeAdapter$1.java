package com.google.gson.internal.bind;

import X.AnonymousClass0e2;
import X.AnonymousClass13N;
import X.AnonymousClass13Y;
import X.AnonymousClass13Z;
import X.AnonymousClass13j;
import X.AnonymousClass14H;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public class ArrayTypeAdapter$1 implements AnonymousClass13Z {
    @Override // X.AnonymousClass13Z
    public final <T> AnonymousClass13Y<T> A2M(AnonymousClass13N r4, AnonymousClass14H<T> r5) {
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
        return new AnonymousClass0e2(r4, r4.A03(new AnonymousClass14H<>(componentType)), AnonymousClass13j.A00(componentType));
    }
}
