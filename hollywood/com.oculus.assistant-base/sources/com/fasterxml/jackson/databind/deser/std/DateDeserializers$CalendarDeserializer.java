package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@JacksonStdImpl
public class DateDeserializers$CalendarDeserializer extends DateDeserializers$DateBasedDeserializer {
    public static final DateDeserializers$CalendarDeserializer A00 = new DateDeserializers$CalendarDeserializer(GregorianCalendar.class);
    public static final DateDeserializers$CalendarDeserializer A01 = new DateDeserializers$CalendarDeserializer();
    public final Class _calendarClass;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Calendar A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Date A0K = A0K(qiVar, qrVar);
        if (A0K == null) {
            return null;
        }
        Class cls = this._calendarClass;
        if (cls == null) {
            Calendar instance = Calendar.getInstance(qrVar._config._base._timeZone);
            instance.setTime(A0K);
            return instance;
        }
        try {
            Calendar calendar = (Calendar) cls.newInstance();
            calendar.setTimeInMillis(A0K.getTime());
            TimeZone timeZone = qrVar._config._base._timeZone;
            if (timeZone != null) {
                calendar.setTimeZone(timeZone);
            }
            return calendar;
        } catch (Exception e) {
            throw qrVar.A0C(this._calendarClass, e);
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

    public DateDeserializers$CalendarDeserializer(Class cls) {
        super(GregorianCalendar.class);
        this._calendarClass = GregorianCalendar.class;
    }
}
