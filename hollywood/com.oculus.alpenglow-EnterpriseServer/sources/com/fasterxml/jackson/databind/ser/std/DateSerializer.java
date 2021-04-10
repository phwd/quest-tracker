package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C02650aW;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

@JacksonStdImpl
public final class DateSerializer extends DateTimeSerializerBase<Date> {
    public static final DateSerializer A00 = new DateSerializer();

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Date date, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        long time;
        Date date2 = date;
        if (this.A01) {
            if (date2 == null) {
                time = 0;
            } else {
                time = date2.getTime();
            }
            r4.A0K(time);
            return;
        }
        DateFormat dateFormat = ((DateTimeSerializerBase) this).A00;
        if (dateFormat != null) {
            synchronized (dateFormat) {
                r4.A0S(dateFormat.format(date2));
            }
            return;
        }
        r5.A0F(date2, r4);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase
    public final /* bridge */ /* synthetic */ long A0E(Date date) {
        Date date2 = date;
        if (date2 == null) {
            return 0;
        }
        return date2.getTime();
    }

    /* Return type fixed from 'com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase' to match base method */
    @Override // com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase
    public final DateTimeSerializerBase<Date> A0F(boolean z, DateFormat dateFormat) {
        if (z) {
            return new DateSerializer(true, null);
        }
        return new DateSerializer(false, dateFormat);
    }

    public DateSerializer() {
        this(false, null);
    }

    public DateSerializer(boolean z, DateFormat dateFormat) {
        super(Date.class, z, dateFormat);
    }
}
