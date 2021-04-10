package com.google.gson.internal.bind;

import X.AnonymousClass0dD;
import X.AnonymousClass13N;
import X.AnonymousClass13Y;
import X.AnonymousClass13Z;
import X.AnonymousClass14H;

public class TypeAdapters$35 implements AnonymousClass13Z {
    public final /* synthetic */ AnonymousClass13Y A00;
    public final /* synthetic */ Class A01;

    public TypeAdapters$35(Class cls, AnonymousClass13Y r2) {
        this.A01 = cls;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass13Z
    public final <T2> AnonymousClass13Y<T2> A2M(AnonymousClass13N r3, AnonymousClass14H<T2> r4) {
        Class<? super T> cls = r4.A01;
        if (!this.A01.isAssignableFrom(cls)) {
            return null;
        }
        return new AnonymousClass0dD(this, cls);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Factory[typeHierarchy=");
        sb.append(this.A01.getName());
        sb.append(",adapter=");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }
}
