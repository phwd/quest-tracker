package com.google.gson.internal.bind;

import X.AnonymousClass13N;
import X.AnonymousClass13Y;
import X.AnonymousClass13Z;
import X.AnonymousClass14H;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TypeAdapters$34 implements AnonymousClass13Z {
    public final /* synthetic */ AnonymousClass13Y A00;
    public final /* synthetic */ Class A01 = Calendar.class;
    public final /* synthetic */ Class A02 = GregorianCalendar.class;

    public TypeAdapters$34(AnonymousClass13Y r3) {
        this.A00 = r3;
    }

    @Override // X.AnonymousClass13Z
    public final <T> AnonymousClass13Y<T> A2M(AnonymousClass13N r3, AnonymousClass14H<T> r4) {
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
