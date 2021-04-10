package com.google.gson.internal.bind;

import X.AnonymousClass08D;
import X.AnonymousClass0Bd;
import X.AnonymousClass0C3;
import X.AnonymousClass0Fe;

public class TypeAdapters$31 implements AnonymousClass0C3 {
    public final /* synthetic */ AnonymousClass0Bd A00;
    public final /* synthetic */ AnonymousClass0Fe A01;

    public TypeAdapters$31(AnonymousClass0Fe r1, AnonymousClass0Bd r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass0C3
    public final <T> AnonymousClass0Bd<T> A1v(AnonymousClass08D r2, AnonymousClass0Fe<T> r3) {
        if (r3.equals(this.A01)) {
            return this.A00;
        }
        return null;
    }
}
