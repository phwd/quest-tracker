package com.fasterxml.jackson.databind.ser.std;

import X.O5;
import X.PU;

public abstract class StdArraySerializers$TypedPrimitiveArraySerializer extends ArraySerializerBase {
    public final PU A00;

    public StdArraySerializers$TypedPrimitiveArraySerializer(StdArraySerializers$TypedPrimitiveArraySerializer stdArraySerializers$TypedPrimitiveArraySerializer, O5 o5, PU pu) {
        super(stdArraySerializers$TypedPrimitiveArraySerializer, o5);
        this.A00 = pu;
    }

    public StdArraySerializers$TypedPrimitiveArraySerializer(Class cls) {
        super(cls);
        this.A00 = null;
    }
}
