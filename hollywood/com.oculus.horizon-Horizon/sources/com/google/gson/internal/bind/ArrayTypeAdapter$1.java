package com.google.gson.internal.bind;

import X.AbstractC08860ym;
import X.AnonymousClass0yl;
import X.C01960Yg;
import X.C08780ya;
import X.C08910yw;
import X.C09110zQ;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public class ArrayTypeAdapter$1 implements AbstractC08860ym {
    @Override // X.AbstractC08860ym
    public final <T> AnonymousClass0yl<T> A1x(C08780ya r4, C09110zQ<T> r5) {
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
        return new C01960Yg(r4, r4.A04(new C09110zQ<>(componentType)), C08910yw.A00(componentType));
    }
}
