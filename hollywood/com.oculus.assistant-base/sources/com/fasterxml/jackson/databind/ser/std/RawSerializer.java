package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public class RawSerializer extends StdSerializer {
    public RawSerializer(Class cls) {
        super(cls, false);
    }
}
