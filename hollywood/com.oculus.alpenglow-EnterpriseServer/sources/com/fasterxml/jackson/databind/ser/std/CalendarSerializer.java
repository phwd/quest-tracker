package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C02650aW;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

@JacksonStdImpl
public final class CalendarSerializer extends DateTimeSerializerBase<Calendar> {
    public static final CalendarSerializer A00 = new CalendarSerializer();

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase
    public final /* bridge */ /* synthetic */ long A0E(Calendar calendar) {
        Calendar calendar2 = calendar;
        if (calendar2 == null) {
            return 0;
        }
        return calendar2.getTimeInMillis();
    }

    /* Return type fixed from 'com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase' to match base method */
    @Override // com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase
    public final DateTimeSerializerBase<Calendar> A0F(boolean z, DateFormat dateFormat) {
        if (z) {
            return new CalendarSerializer(true, null);
        }
        return new CalendarSerializer(false, dateFormat);
    }

    /* renamed from: A0G */
    public final void A0D(Calendar calendar, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        long timeInMillis;
        if (this.A01) {
            if (calendar == null) {
                timeInMillis = 0;
            } else {
                timeInMillis = calendar.getTimeInMillis();
            }
            r4.A0K(timeInMillis);
            return;
        }
        DateFormat dateFormat = ((DateTimeSerializerBase) this).A00;
        if (dateFormat != null) {
            synchronized (dateFormat) {
                r4.A0S(dateFormat.format(calendar));
            }
            return;
        }
        r5.A0F(calendar.getTime(), r4);
    }

    public CalendarSerializer() {
        this(false, null);
    }

    public CalendarSerializer(boolean z, DateFormat dateFormat) {
        super(Calendar.class, z, dateFormat);
    }
}
