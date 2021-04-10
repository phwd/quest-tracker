package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC1024qt;
import X.O5;
import X.PU;
import X.fF;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class StdArraySerializers$LongArraySerializer extends StdArraySerializers$TypedPrimitiveArraySerializer {
    public static final AbstractC1024qt A00 = new fF(Long.TYPE);

    public StdArraySerializers$LongArraySerializer() {
        super(long[].class);
    }

    public StdArraySerializers$LongArraySerializer(StdArraySerializers$LongArraySerializer stdArraySerializers$LongArraySerializer, O5 o5, PU pu) {
        super(stdArraySerializers$LongArraySerializer, o5, pu);
    }
}
