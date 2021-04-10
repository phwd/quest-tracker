package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.Calendar;

@JacksonStdImpl
public final class CalendarSerializer extends DateTimeSerializerBase<Calendar> {
    public static final CalendarSerializer A00 = new CalendarSerializer();
}
