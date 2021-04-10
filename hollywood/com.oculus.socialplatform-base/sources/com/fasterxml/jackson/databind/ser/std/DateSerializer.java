package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.C02310iT;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

@JacksonStdImpl
public class DateSerializer extends DateTimeSerializerBase<Date> {
    public static final DateSerializer A00 = new DateSerializer();

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase
    public final /* bridge */ /* synthetic */ long A00(Date date) {
        Date date2 = date;
        if (date2 == null) {
            return 0;
        }
        return date2.getTime();
    }

    /* Return type fixed from 'com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase' to match base method */
    @Override // com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase
    public final DateTimeSerializerBase<Date> A01(boolean z, DateFormat dateFormat) {
        if (z) {
            return new DateSerializer(true, null);
        }
        return new DateSerializer(false, dateFormat);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Date date, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C02310iT {
        long time;
        Date date2 = date;
        if (this.A01) {
            if (date2 == null) {
                time = 0;
            } else {
                time = date2.getTime();
            }
            r4.A0N(time);
            return;
        }
        DateFormat dateFormat = ((DateTimeSerializerBase) this).A00;
        if (dateFormat != null) {
            synchronized (dateFormat) {
                r4.A0U(dateFormat.format(date2));
            }
            return;
        }
        r5.A0H(date2, r4);
    }

    public DateSerializer() {
        this(false, null);
    }

    public DateSerializer(boolean z, DateFormat dateFormat) {
        super(Date.class, z, dateFormat);
    }
}
