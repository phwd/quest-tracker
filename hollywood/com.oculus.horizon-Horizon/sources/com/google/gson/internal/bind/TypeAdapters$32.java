package com.google.gson.internal.bind;

import X.AbstractC08860ym;
import X.AnonymousClass0yl;
import X.C08780ya;
import X.C09110zQ;

public class TypeAdapters$32 implements AbstractC08860ym {
    public final /* synthetic */ AnonymousClass0yl A00;
    public final /* synthetic */ Class A01;

    public TypeAdapters$32(Class cls, AnonymousClass0yl r2) {
        this.A01 = cls;
        this.A00 = r2;
    }

    @Override // X.AbstractC08860ym
    public final <T> AnonymousClass0yl<T> A1x(C08780ya r3, C09110zQ<T> r4) {
        if (r4.A01 == this.A01) {
            return this.A00;
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Factory[type=");
        sb.append(this.A01.getName());
        sb.append(",adapter=");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }
}
