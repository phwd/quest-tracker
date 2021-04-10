package com.google.gson.internal.bind;

import X.AnonymousClass13N;
import X.AnonymousClass13Y;
import X.AnonymousClass13Z;
import X.AnonymousClass14H;
import X.C01360dm;
import java.sql.Date;

public class SqlDateTypeAdapter$1 implements AnonymousClass13Z {
    @Override // X.AnonymousClass13Z
    public final <T> AnonymousClass13Y<T> A2M(AnonymousClass13N r3, AnonymousClass14H<T> r4) {
        if (r4.A01 == Date.class) {
            return new C01360dm();
        }
        return null;
    }
}
