package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC1012qg;
import X.AbstractC1031r2;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.text.DateFormat;
import java.util.Calendar;

@JacksonStdImpl
public class CalendarSerializer extends DateTimeSerializerBase {
    public static final CalendarSerializer A00 = new CalendarSerializer();

    public final void A0D(Calendar calendar, AbstractC1012qg qgVar, AbstractC1031r2 r2Var) {
        long timeInMillis;
        if (this.A01) {
            if (calendar == null) {
                timeInMillis = 0;
            } else {
                timeInMillis = calendar.getTimeInMillis();
            }
            qgVar.A0H(timeInMillis);
            return;
        }
        DateFormat dateFormat = ((DateTimeSerializerBase) this).A00;
        if (dateFormat != null) {
            synchronized (dateFormat) {
                qgVar.A0P(dateFormat.format(calendar));
            }
            return;
        }
        r2Var.A0F(calendar.getTime(), qgVar);
    }

    public CalendarSerializer() {
        this(false, null);
    }

    public CalendarSerializer(boolean z, DateFormat dateFormat) {
        super(Calendar.class, z, dateFormat);
    }
}
