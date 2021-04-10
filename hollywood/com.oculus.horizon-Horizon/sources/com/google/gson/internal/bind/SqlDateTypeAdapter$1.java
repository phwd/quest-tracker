package com.google.gson.internal.bind;

import X.AbstractC08860ym;
import X.AnonymousClass0WF;
import X.AnonymousClass0yl;
import X.C08780ya;
import X.C09110zQ;
import java.sql.Date;

public class SqlDateTypeAdapter$1 implements AbstractC08860ym {
    @Override // X.AbstractC08860ym
    public final <T> AnonymousClass0yl<T> A1x(C08780ya r3, C09110zQ<T> r4) {
        if (r4.A01 == Date.class) {
            return new AnonymousClass0WF();
        }
        return null;
    }
}
