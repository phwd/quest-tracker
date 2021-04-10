package com.google.gson.internal.bind;

import X.AnonymousClass08D;
import X.AnonymousClass0Bd;
import X.AnonymousClass0C3;
import X.AnonymousClass0Fe;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TypeAdapters$34 implements AnonymousClass0C3 {
    public final /* synthetic */ AnonymousClass0Bd A00;
    public final /* synthetic */ Class A01 = Calendar.class;
    public final /* synthetic */ Class A02 = GregorianCalendar.class;

    public TypeAdapters$34(AnonymousClass0Bd r3) {
        this.A00 = r3;
    }

    @Override // X.AnonymousClass0C3
    public final <T> AnonymousClass0Bd<T> A1v(AnonymousClass08D r3, AnonymousClass0Fe<T> r4) {
        Class<? super T> cls = r4.A01;
        if (cls == this.A01 || cls == this.A02) {
            return this.A00;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.A01.getName() + "+" + this.A02.getName() + ",adapter=" + this.A00 + "]";
    }
}
