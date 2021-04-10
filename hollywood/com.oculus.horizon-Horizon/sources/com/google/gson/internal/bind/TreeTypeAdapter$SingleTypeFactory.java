package com.google.gson.internal.bind;

import X.AbstractC08810yd;
import X.AbstractC08860ym;
import X.AnonymousClass0VB;
import X.AnonymousClass0yl;
import X.C08780ya;
import X.C08870ys;
import X.C09110zQ;

public final class TreeTypeAdapter$SingleTypeFactory implements AbstractC08860ym {
    public final AbstractC08810yd<?> A00;
    public final Class<?> A01;

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Object;LX/0zQ<*>;ZLjava/lang/Class<*>;)V */
    public TreeTypeAdapter$SingleTypeFactory(Object obj) {
        AbstractC08810yd<?> r1 = obj instanceof AbstractC08810yd ? (AbstractC08810yd) obj : null;
        this.A00 = r1;
        C08870ys.A00(r1 != null);
        this.A01 = Object.class;
    }

    @Override // X.AbstractC08860ym
    public final <T> AnonymousClass0yl<T> A1x(C08780ya r3, C09110zQ<T> r4) {
        if (this.A01.isAssignableFrom(r4.A01)) {
            return new AnonymousClass0VB(this.A00, r3, r4, this);
        }
        return null;
    }
}
