package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class NullSerializer extends StdSerializer<Object> {
    public static final NullSerializer A00 = new NullSerializer();
}
