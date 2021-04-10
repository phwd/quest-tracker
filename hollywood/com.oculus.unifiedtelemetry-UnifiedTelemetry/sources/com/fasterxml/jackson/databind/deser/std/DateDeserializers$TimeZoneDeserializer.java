package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.q0;
import java.io.IOException;
import java.util.TimeZone;

public class DateDeserializers$TimeZoneDeserializer extends FromStringDeserializer<TimeZone> {
    public static final DateDeserializers$TimeZoneDeserializer A00 = new DateDeserializers$TimeZoneDeserializer();

    public DateDeserializers$TimeZoneDeserializer() {
        super(TimeZone.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final TimeZone A0Q(String str, AbstractC0226Wn wn) throws IOException, q0 {
        return TimeZone.getTimeZone(str);
    }
}
