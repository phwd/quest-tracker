package com.google.gson.internal.bind;

import X.AbstractC08860ym;
import X.AnonymousClass0UY;
import X.AnonymousClass0yl;
import X.C08780ya;
import X.C09110zQ;

public class TypeAdapters$30 implements AbstractC08860ym {
    @Override // X.AbstractC08860ym
    public final <T> AnonymousClass0yl<T> A1x(C08780ya r4, C09110zQ<T> r5) {
        Class<? super T> cls = r5.A01;
        if (!Enum.class.isAssignableFrom(cls) || cls == Enum.class) {
            return null;
        }
        if (!cls.isEnum()) {
            cls = cls.getSuperclass();
        }
        return new AnonymousClass0UY(cls);
    }
}
