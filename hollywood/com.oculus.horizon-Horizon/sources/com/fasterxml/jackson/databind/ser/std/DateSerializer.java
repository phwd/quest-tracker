package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.Date;

@JacksonStdImpl
public final class DateSerializer extends DateTimeSerializerBase<Date> {
    public static final DateSerializer A00 = new DateSerializer();
}
