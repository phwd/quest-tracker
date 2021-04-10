package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC1024qt;
import X.O5;
import X.PU;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public class IterableSerializer extends AsArraySerializerBase {
    public IterableSerializer(AbstractC1024qt qtVar, boolean z, PU pu, O5 o5) {
        super(Iterable.class, qtVar, z, pu, o5, null);
    }

    public IterableSerializer(IterableSerializer iterableSerializer, O5 o5, PU pu, JsonSerializer jsonSerializer) {
        super(iterableSerializer, o5, pu, jsonSerializer);
    }
}
