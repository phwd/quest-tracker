package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9r;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@JacksonStdImpl
public class DateDeserializers$CalendarDeserializer extends DateDeserializers$DateBasedDeserializer<Calendar> {
    public static final DateDeserializers$CalendarDeserializer A00 = new DateDeserializers$CalendarDeserializer(GregorianCalendar.class);
    public static final DateDeserializers$CalendarDeserializer A01 = new DateDeserializers$CalendarDeserializer();
    public final Class<? extends Calendar> _calendarClass = null;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Calendar A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        Date A07 = A07(rn, rd);
        if (A07 == null) {
            return null;
        }
        Class<? extends Calendar> cls = this._calendarClass;
        if (cls == null) {
            throw null;
        }
        try {
            ((Calendar) cls.newInstance()).setTimeInMillis(A07.getTime());
            throw null;
        } catch (Exception unused) {
            throw null;
        }
    }

    public DateDeserializers$CalendarDeserializer() {
        super(Calendar.class);
    }

    public DateDeserializers$CalendarDeserializer(Class<? extends Calendar> cls) {
        super(GregorianCalendar.class);
    }
}
