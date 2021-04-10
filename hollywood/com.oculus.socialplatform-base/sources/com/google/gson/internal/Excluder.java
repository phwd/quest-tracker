package com.google.gson.internal;

import X.AnonymousClass0eB;
import X.AnonymousClass13N;
import X.AnonymousClass13Y;
import X.AnonymousClass13Z;
import X.AnonymousClass14H;
import com.google.gson.ExclusionStrategy;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class Excluder implements AnonymousClass13Z, Cloneable {
    public static final Excluder A02 = new Excluder();
    public List<ExclusionStrategy> A00 = Collections.emptyList();
    public List<ExclusionStrategy> A01 = Collections.emptyList();

    @Override // X.AnonymousClass13Z
    public final <T> AnonymousClass13Y<T> A2M(AnonymousClass13N r8, AnonymousClass14H<T> r9) {
        Class<? super T> cls = r9.A01;
        if (!Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass())) {
            return new AnonymousClass0eB(this, true, true, r8, r9);
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
            throw new NullPointerException("shouldSkipClass");
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
