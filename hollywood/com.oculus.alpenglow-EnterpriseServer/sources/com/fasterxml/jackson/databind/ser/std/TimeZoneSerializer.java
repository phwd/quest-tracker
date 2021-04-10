package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0o6;
import X.C02650aW;
import java.io.IOException;
import java.util.TimeZone;

public final class TimeZoneSerializer extends StdScalarSerializer<TimeZone> {
    public static final TimeZoneSerializer A00 = new TimeZoneSerializer();

    public TimeZoneSerializer() {
        super(TimeZone.class);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8, X.0o6] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdScalarSerializer
    public final void A0A(TimeZone timeZone, AbstractC02640aV r3, AnonymousClass0a8 r4, AnonymousClass0o6 r5) throws IOException, C02650aW {
        TimeZone timeZone2 = timeZone;
        r5.A07(timeZone2, r3, TimeZone.class);
        r3.A0S(timeZone2.getID());
        r5.A06(timeZone2, r3);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final /* bridge */ /* synthetic */ void A0D(Object obj, AbstractC02640aV r3, AnonymousClass0a8 r4) throws IOException, C02650aW {
        r3.A0S(((TimeZone) obj).getID());
    }
}
