package com.google.gson.internal.bind;

import X.AnonymousClass0dh;
import X.AnonymousClass13N;
import X.AnonymousClass13Q;
import X.AnonymousClass13Y;
import X.AnonymousClass13Z;
import X.AnonymousClass13f;
import X.AnonymousClass14H;

public final class TreeTypeAdapter$SingleTypeFactory implements AnonymousClass13Z {
    public final AnonymousClass13Q<?> A00;
    public final Class<?> A01;

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Object;LX/14H<*>;ZLjava/lang/Class<*>;)V */
    public TreeTypeAdapter$SingleTypeFactory(Object obj) {
        AnonymousClass13Q<?> r1 = obj instanceof AnonymousClass13Q ? (AnonymousClass13Q) obj : null;
        this.A00 = r1;
        AnonymousClass13f.A00(r1 != null);
        this.A01 = Object.class;
    }

    @Override // X.AnonymousClass13Z
    public final <T> AnonymousClass13Y<T> A2M(AnonymousClass13N r3, AnonymousClass14H<T> r4) {
        if (this.A01.isAssignableFrom(r4.A01)) {
            return new AnonymousClass0dh(this.A00, r3, r4, this);
        }
        return null;
    }
}
