package com.google.gson.internal.bind;

import X.AnonymousClass0dO;
import X.AnonymousClass13N;
import X.AnonymousClass13Y;
import X.AnonymousClass13Z;
import X.AnonymousClass14H;
import java.sql.Timestamp;
import java.util.Date;

public class TypeAdapters$26 implements AnonymousClass13Z {
    @Override // X.AnonymousClass13Z
    public final <T> AnonymousClass13Y<T> A2M(AnonymousClass13N r3, AnonymousClass14H<T> r4) {
        if (r4.A01 != Timestamp.class) {
            return null;
        }
        return new AnonymousClass0dO(this, r3.A03(new AnonymousClass14H<>(Date.class)));
    }
}
