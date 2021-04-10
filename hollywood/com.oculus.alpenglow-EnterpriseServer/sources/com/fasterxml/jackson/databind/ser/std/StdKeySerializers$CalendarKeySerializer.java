package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0a9;
import X.C02650aW;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class StdKeySerializers$CalendarKeySerializer extends StdSerializer<Calendar> {
    public static final JsonSerializer<?> A00 = new StdKeySerializers$CalendarKeySerializer();

    public StdKeySerializers$CalendarKeySerializer() {
        super(Calendar.class);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Calendar calendar, AbstractC02640aV r6, AnonymousClass0a8 r7) throws IOException, C02650aW {
        String format;
        long timeInMillis = calendar.getTimeInMillis();
        if (r7._config.A06(AnonymousClass0a9.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            format = String.valueOf(timeInMillis);
        } else {
            format = AnonymousClass0a8.A00(r7).format(new Date(timeInMillis));
        }
        r6.A0P(format);
    }
}
