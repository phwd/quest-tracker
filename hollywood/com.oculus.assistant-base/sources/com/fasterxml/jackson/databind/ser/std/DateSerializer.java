package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.text.DateFormat;
import java.util.Date;

@JacksonStdImpl
public class DateSerializer extends DateTimeSerializerBase {
    public static final DateSerializer A00 = new DateSerializer();

    public DateSerializer() {
        this(false, null);
    }

    public DateSerializer(boolean z, DateFormat dateFormat) {
        super(Date.class, z, dateFormat);
    }
}
