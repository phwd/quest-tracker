package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.C02310iT;
import java.io.IOException;
import java.util.TimeZone;

public class TimeZoneSerializer extends StdScalarSerializer<TimeZone> {
    public static final TimeZoneSerializer A00 = new TimeZoneSerializer();

    public TimeZoneSerializer() {
        super(TimeZone.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final /* bridge */ /* synthetic */ void serialize(Object obj, AbstractC02300iS r3, AbstractC02120i3 r4) throws IOException, C02310iT {
        r3.A0U(((TimeZone) obj).getID());
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3, X.0qd] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer
    public final void serializeWithType(TimeZone timeZone, AbstractC02300iS r3, AbstractC02120i3 r4, AbstractC04550qd r5) throws IOException, C02310iT {
        TimeZone timeZone2 = timeZone;
        r5.A07(timeZone2, r3, TimeZone.class);
        r3.A0U(timeZone2.getID());
        r5.A06(timeZone2, r3);
    }
}
