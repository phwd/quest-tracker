package com.google.gson.internal.bind;

import X.AbstractC08860ym;
import X.AnonymousClass0Uh;
import X.AnonymousClass0yl;
import X.C08780ya;
import X.C09110zQ;

public class TypeAdapters$35 implements AbstractC08860ym {
    public final /* synthetic */ AnonymousClass0yl A00;
    public final /* synthetic */ Class A01;

    public TypeAdapters$35(Class cls, AnonymousClass0yl r2) {
        this.A01 = cls;
        this.A00 = r2;
    }

    @Override // X.AbstractC08860ym
    public final <T2> AnonymousClass0yl<T2> A1x(C08780ya r3, C09110zQ<T2> r4) {
        Class<? super T> cls = r4.A01;
        if (!this.A01.isAssignableFrom(cls)) {
            return null;
        }
        return new AnonymousClass0Uh(this, cls);
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
