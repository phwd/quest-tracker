package com.google.gson.internal.bind;

import X.AbstractC08860ym;
import X.AnonymousClass0yl;
import X.C01400Us;
import X.C08780ya;
import X.C09110zQ;
import java.sql.Timestamp;
import java.util.Date;

public class TypeAdapters$26 implements AbstractC08860ym {
    @Override // X.AbstractC08860ym
    public final <T> AnonymousClass0yl<T> A1x(C08780ya r3, C09110zQ<T> r4) {
        if (r4.A01 != Timestamp.class) {
            return null;
        }
        return new C01400Us(this, r3.A04(new C09110zQ<>(Date.class)));
    }
}
