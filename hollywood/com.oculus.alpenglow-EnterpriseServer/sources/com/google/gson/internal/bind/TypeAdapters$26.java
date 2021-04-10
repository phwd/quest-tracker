package com.google.gson.internal.bind;

import X.AnonymousClass08D;
import X.AnonymousClass0Bd;
import X.AnonymousClass0C3;
import X.AnonymousClass0Fe;
import X.AnonymousClass0WN;
import java.sql.Timestamp;
import java.util.Date;

public class TypeAdapters$26 implements AnonymousClass0C3 {
    @Override // X.AnonymousClass0C3
    public final <T> AnonymousClass0Bd<T> A1v(AnonymousClass08D r3, AnonymousClass0Fe<T> r4) {
        if (r4.A01 != Timestamp.class) {
            return null;
        }
        return new AnonymousClass0WN(this, r3.A07(new AnonymousClass0Fe<>(Date.class)));
    }
}
