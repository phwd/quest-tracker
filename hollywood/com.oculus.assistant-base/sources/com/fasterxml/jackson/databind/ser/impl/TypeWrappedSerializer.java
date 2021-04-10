package com.fasterxml.jackson.databind.ser.impl;

import X.PU;
import com.fasterxml.jackson.databind.JsonSerializer;

public final class TypeWrappedSerializer extends JsonSerializer {
    public final JsonSerializer A00;
    public final PU A01;

    public TypeWrappedSerializer(PU pu, JsonSerializer jsonSerializer) {
        this.A01 = pu;
        this.A00 = jsonSerializer;
    }
}
