package com.google.gson.internal.bind;

import X.AnonymousClass0d4;
import X.AnonymousClass13N;
import X.AnonymousClass13Y;
import X.AnonymousClass13Z;
import X.AnonymousClass14H;

public class TypeAdapters$30 implements AnonymousClass13Z {
    @Override // X.AnonymousClass13Z
    public final <T> AnonymousClass13Y<T> A2M(AnonymousClass13N r4, AnonymousClass14H<T> r5) {
        Class<? super T> cls = r5.A01;
        if (!Enum.class.isAssignableFrom(cls) || cls == Enum.class) {
            return null;
        }
        if (!cls.isEnum()) {
            cls = cls.getSuperclass();
        }
        return new AnonymousClass0d4(cls);
    }
}
