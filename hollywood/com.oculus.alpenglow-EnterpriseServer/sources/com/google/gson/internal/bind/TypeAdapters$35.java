package com.google.gson.internal.bind;

import X.AnonymousClass08D;
import X.AnonymousClass0Bd;
import X.AnonymousClass0C3;
import X.AnonymousClass0Fe;
import X.AnonymousClass0WC;

public class TypeAdapters$35 implements AnonymousClass0C3 {
    public final /* synthetic */ AnonymousClass0Bd A00;
    public final /* synthetic */ Class A01;

    public TypeAdapters$35(Class cls, AnonymousClass0Bd r2) {
        this.A01 = cls;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass0C3
    public final <T2> AnonymousClass0Bd<T2> A1v(AnonymousClass08D r3, AnonymousClass0Fe<T2> r4) {
        Class<? super T> cls = r4.A01;
        if (!this.A01.isAssignableFrom(cls)) {
            return null;
        }
        return new AnonymousClass0WC(this, cls);
    }

    public final String toString() {
        return "Factory[typeHierarchy=" + this.A01.getName() + ",adapter=" + this.A00 + "]";
    }
}
