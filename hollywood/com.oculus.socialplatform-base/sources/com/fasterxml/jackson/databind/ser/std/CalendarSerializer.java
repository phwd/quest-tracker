package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.C02310iT;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

@JacksonStdImpl
public class CalendarSerializer extends DateTimeSerializerBase<Calendar> {
    public static final CalendarSerializer A00 = new CalendarSerializer();

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase
    public final /* bridge */ /* synthetic */ long A00(Calendar calendar) {
        Calendar calendar2 = calendar;
        if (calendar2 == null) {
            return 0;
        }
        return calendar2.getTimeInMillis();
    }

    /* Return type fixed from 'com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase' to match base method */
    @Override // com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase
    public final DateTimeSerializerBase<Calendar> A01(boolean z, DateFormat dateFormat) {
        if (z) {
            return new CalendarSerializer(true, null);
        }
        return new CalendarSerializer(false, dateFormat);
    }

    /* renamed from: A02 */
    public final void serialize(Calendar calendar, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C02310iT {
        long timeInMillis;
        if (this.A01) {
            if (calendar == null) {
                timeInMillis = 0;
            } else {
                timeInMillis = calendar.getTimeInMillis();
            }
            r4.A0N(timeInMillis);
            return;
        }
        DateFormat dateFormat = ((DateTimeSerializerBase) this).A00;
        if (dateFormat != null) {
            synchronized (dateFormat) {
                r4.A0U(dateFormat.format(calendar));
            }
            return;
        }
        r5.A0H(calendar.getTime(), r4);
    }

    public CalendarSerializer() {
        this(false, null);
    }

    public CalendarSerializer(boolean z, DateFormat dateFormat) {
        super(Calendar.class, z, dateFormat);
    }
}
