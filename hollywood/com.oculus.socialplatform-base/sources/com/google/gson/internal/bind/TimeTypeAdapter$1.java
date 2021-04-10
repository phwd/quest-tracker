package com.google.gson.internal.bind;

import X.AnonymousClass13N;
import X.AnonymousClass13Y;
import X.AnonymousClass13Z;
import X.AnonymousClass14H;
import X.C01350dk;
import java.sql.Time;

public class TimeTypeAdapter$1 implements AnonymousClass13Z {
    @Override // X.AnonymousClass13Z
    public final <T> AnonymousClass13Y<T> A2M(AnonymousClass13N r3, AnonymousClass14H<T> r4) {
        if (r4.A01 == Time.class) {
            return new C01350dk();
        }
        return null;
    }
}
