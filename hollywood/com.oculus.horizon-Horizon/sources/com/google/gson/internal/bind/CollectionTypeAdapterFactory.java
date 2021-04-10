package com.google.gson.internal.bind;

import X.AbstractC08860ym;
import X.AnonymousClass0YR;
import X.AnonymousClass0yl;
import X.C08780ya;
import X.C08910yw;
import X.C08920yx;
import X.C09110zQ;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;

public final class CollectionTypeAdapterFactory implements AbstractC08860ym {
    public final C08920yx A00;

    @Override // X.AbstractC08860ym
    public final <T> AnonymousClass0yl<T> A1x(C08780ya r5, C09110zQ<T> r6) {
        Type type;
        Type type2 = r6.A02;
        Class<? super T> cls = r6.A01;
        if (!Collection.class.isAssignableFrom(cls)) {
            return null;
        }
        Type A03 = C08910yw.A03(type2, cls, Collection.class);
        if (A03 instanceof WildcardType) {
            A03 = ((WildcardType) A03).getUpperBounds()[0];
        }
        if (A03 instanceof ParameterizedType) {
            type = ((ParameterizedType) A03).getActualTypeArguments()[0];
        } else {
            type = Object.class;
        }
        return new AnonymousClass0YR(r5, type, r5.A04(new C09110zQ<>(type)), this.A00.A00(r6));
    }

    public CollectionTypeAdapterFactory(C08920yx r1) {
        this.A00 = r1;
    }
}
