package com.google.gson.internal.bind;

import X.AnonymousClass08D;
import X.AnonymousClass0Bd;
import X.AnonymousClass0C3;
import X.AnonymousClass0Fe;

public class TypeAdapters$32 implements AnonymousClass0C3 {
    public final /* synthetic */ AnonymousClass0Bd A00;
    public final /* synthetic */ Class A01;

    public TypeAdapters$32(Class cls, AnonymousClass0Bd r2) {
        this.A01 = cls;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass0C3
    public final <T> AnonymousClass0Bd<T> A1v(AnonymousClass08D r3, AnonymousClass0Fe<T> r4) {
        if (r4.A01 == this.A01) {
            return this.A00;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.A01.getName() + ",adapter=" + this.A00 + "]";
    }
}
