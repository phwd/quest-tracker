package com.google.gson.internal.bind;

import X.AnonymousClass08D;
import X.AnonymousClass0Bd;
import X.AnonymousClass0C3;
import X.AnonymousClass0Fe;
import X.AnonymousClass0W3;

public class TypeAdapters$30 implements AnonymousClass0C3 {
    @Override // X.AnonymousClass0C3
    public final <T> AnonymousClass0Bd<T> A1v(AnonymousClass08D r4, AnonymousClass0Fe<T> r5) {
        Class<? super T> cls = r5.A01;
        if (!Enum.class.isAssignableFrom(cls) || cls == Enum.class) {
            return null;
        }
        if (!cls.isEnum()) {
            cls = cls.getSuperclass();
        }
        return new AnonymousClass0W3(cls);
    }
}
