package com.google.gson.internal.bind;

import X.AnonymousClass08D;
import X.AnonymousClass0Bd;
import X.AnonymousClass0C3;
import X.AnonymousClass0Fe;

public class TypeAdapters$33 implements AnonymousClass0C3 {
    public final /* synthetic */ AnonymousClass0Bd A00;
    public final /* synthetic */ Class A01;
    public final /* synthetic */ Class A02;

    public TypeAdapters$33(Class cls, Class cls2, AnonymousClass0Bd r3) {
        this.A02 = cls;
        this.A01 = cls2;
        this.A00 = r3;
    }

    @Override // X.AnonymousClass0C3
    public final <T> AnonymousClass0Bd<T> A1v(AnonymousClass08D r3, AnonymousClass0Fe<T> r4) {
        Class<? super T> cls = r4.A01;
        if (cls == this.A02 || cls == this.A01) {
            return this.A00;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.A01.getName() + "+" + this.A02.getName() + ",adapter=" + this.A00 + "]";
    }
}
