package com.fasterxml.jackson.databind.ser.std;

import java.util.TimeZone;

public class TimeZoneSerializer extends StdScalarSerializer {
    public static final TimeZoneSerializer A00 = new TimeZoneSerializer();

    public TimeZoneSerializer() {
        super(TimeZone.class);
    }
}
