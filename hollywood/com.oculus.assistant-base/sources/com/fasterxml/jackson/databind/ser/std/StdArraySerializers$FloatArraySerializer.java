package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC1024qt;
import X.O5;
import X.PU;
import X.fF;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class StdArraySerializers$FloatArraySerializer extends StdArraySerializers$TypedPrimitiveArraySerializer {
    public static final AbstractC1024qt A00 = new fF(Float.TYPE);

    public StdArraySerializers$FloatArraySerializer() {
        super(float[].class);
    }

    public StdArraySerializers$FloatArraySerializer(StdArraySerializers$FloatArraySerializer stdArraySerializers$FloatArraySerializer, O5 o5, PU pu) {
        super(stdArraySerializers$FloatArraySerializer, o5, pu);
    }
}
