package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.q0;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@JacksonStdImpl
public class DateDeserializers$CalendarDeserializer extends DateDeserializers$DateBasedDeserializer<Calendar> {
    public static final DateDeserializers$CalendarDeserializer A00 = new DateDeserializers$CalendarDeserializer(GregorianCalendar.class);
    public static final DateDeserializers$CalendarDeserializer A01 = new DateDeserializers$CalendarDeserializer();
    public final Class<? extends Calendar> _calendarClass;

    /* Return type fixed from 'com.fasterxml.jackson.databind.deser.std.DateDeserializers$DateBasedDeserializer' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.DateDeserializers$DateBasedDeserializer
    public final DateDeserializers$DateBasedDeserializer<Calendar> A0P(DateFormat dateFormat, String str) {
        return new DateDeserializers$CalendarDeserializer(this, dateFormat, str);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Calendar A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Date A0M = A0M(ww, wn);
        if (A0M == null) {
            return null;
        }
        Class<? extends Calendar> cls = this._calendarClass;
        if (cls == null) {
            Calendar instance = Calendar.getInstance(wn._config._base._timeZone);
            instance.setTime(A0M);
            return instance;
        }
        try {
            Calendar calendar = (Calendar) cls.newInstance();
            calendar.setTimeInMillis(A0M.getTime());
            TimeZone timeZone = wn._config._base._timeZone;
            if (timeZone != null) {
                calendar.setTimeZone(timeZone);
            }
            return calendar;
        } catch (Exception e) {
            throw wn.A0C(this._calendarClass, e);
        }
    }

    public DateDeserializers$CalendarDeserializer() {
        super(Calendar.class);
        this._calendarClass = null;
    }

    public DateDeserializers$CalendarDeserializer(DateDeserializers$CalendarDeserializer dateDeserializers$CalendarDeserializer, DateFormat dateFormat, String str) {
        super(dateDeserializers$CalendarDeserializer, dateFormat, str);
        this._calendarClass = dateDeserializers$CalendarDeserializer._calendarClass;
    }

    public DateDeserializers$CalendarDeserializer(Class<? extends Calendar> cls) {
        super(GregorianCalendar.class);
        this._calendarClass = GregorianCalendar.class;
    }
}
