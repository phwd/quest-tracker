package com.google.gson.internal.bind;

import X.AnonymousClass13N;
import X.AnonymousClass13Y;
import X.AnonymousClass13Z;
import X.AnonymousClass14H;

public class TypeAdapters$33 implements AnonymousClass13Z {
    public final /* synthetic */ AnonymousClass13Y A00;
    public final /* synthetic */ Class A01;
    public final /* synthetic */ Class A02;

    public TypeAdapters$33(Class cls, Class cls2, AnonymousClass13Y r3) {
        this.A02 = cls;
        this.A01 = cls2;
        this.A00 = r3;
    }

    @Override // X.AnonymousClass13Z
    public final <T> AnonymousClass13Y<T> A2M(AnonymousClass13N r3, AnonymousClass14H<T> r4) {
        Class<? super T> cls = r4.A01;
        if (cls == this.A02 || cls == this.A01) {
            return this.A00;
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Factory[type=");
        sb.append(this.A01.getName());
        sb.append("+");
        sb.append(this.A02.getName());
        sb.append(",adapter=");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }
}
