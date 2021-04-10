package com.google.gson.internal.bind;

import X.AnonymousClass08D;
import X.AnonymousClass0Bd;
import X.AnonymousClass0C3;
import X.AnonymousClass0Fe;
import X.C02160Wx;
import java.util.Date;

public class DateTypeAdapter$1 implements AnonymousClass0C3 {
    @Override // X.AnonymousClass0C3
    public final <T> AnonymousClass0Bd<T> A1v(AnonymousClass08D r3, AnonymousClass0Fe<T> r4) {
        if (r4.A01 == Date.class) {
            return new C02160Wx();
        }
        return null;
    }
}
