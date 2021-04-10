package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AnonymousClass0i4;
import X.C02310iT;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class StdKeySerializers$CalendarKeySerializer extends StdSerializer<Calendar> {
    public static final JsonSerializer<?> A00 = new StdKeySerializers$CalendarKeySerializer();

    public StdKeySerializers$CalendarKeySerializer() {
        super(Calendar.class);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Calendar calendar, AbstractC02300iS r6, AbstractC02120i3 r7) throws IOException, C02310iT {
        String format;
        long timeInMillis = calendar.getTimeInMillis();
        if (r7._config.A06(AnonymousClass0i4.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            format = String.valueOf(timeInMillis);
        } else {
            format = AbstractC02120i3.A00(r7).format(new Date(timeInMillis));
        }
        r6.A0R(format);
    }
}
