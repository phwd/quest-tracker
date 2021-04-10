package com.google.gson.internal.bind;

import X.AbstractC08860ym;
import X.AnonymousClass0yl;
import X.C08780ya;
import X.C09110zQ;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TypeAdapters$34 implements AbstractC08860ym {
    public final /* synthetic */ AnonymousClass0yl A00;
    public final /* synthetic */ Class A01 = Calendar.class;
    public final /* synthetic */ Class A02 = GregorianCalendar.class;

    public TypeAdapters$34(AnonymousClass0yl r3) {
        this.A00 = r3;
    }

    @Override // X.AbstractC08860ym
    public final <T> AnonymousClass0yl<T> A1x(C08780ya r3, C09110zQ<T> r4) {
        Class<? super T> cls = r4.A01;
        if (cls == this.A01 || cls == this.A02) {
            return this.A00;
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Factory[type=");
        sb.append(this.A01.getName());
        sb.append("+");
        sb.append(this.A02.getName());
        sb.append(",adapter=");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }
}
