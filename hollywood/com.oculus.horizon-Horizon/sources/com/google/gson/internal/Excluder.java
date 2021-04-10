package com.google.gson.internal;

import X.AbstractC08860ym;
import X.AnonymousClass0yl;
import X.C02340aJ;
import X.C08780ya;
import X.C09110zQ;
import com.google.gson.ExclusionStrategy;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Excluder implements AbstractC08860ym, Cloneable {
    public static final Excluder A02 = new Excluder();
    public List<ExclusionStrategy> A00 = Collections.emptyList();
    public List<ExclusionStrategy> A01 = Collections.emptyList();

    @Override // X.AbstractC08860ym
    public final <T> AnonymousClass0yl<T> A1x(C08780ya r8, C09110zQ<T> r9) {
        Class<? super T> cls = r9.A01;
        if (!Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass())) {
            return new C02340aJ(this, true, true, r8, r9);
        }
        A00(this, true);
        A00(this, false);
        return null;
    }

    /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/Class<*>;Z)Z */
    public static void A00(Excluder excluder, boolean z) {
        List<ExclusionStrategy> list;
        if (z) {
            list = excluder.A01;
        } else {
            list = excluder.A00;
        }
        Iterator<ExclusionStrategy> it = list.iterator();
        if (it.hasNext()) {
            it.next();
            throw null;
        }
    }

    @Override // java.lang.Object
    public final Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
