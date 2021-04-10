package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC1024qt;
import X.O5;
import X.PU;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import java.util.Iterator;

@JacksonStdImpl
public class IteratorSerializer extends AsArraySerializerBase {
    public IteratorSerializer(AbstractC1024qt qtVar, boolean z, PU pu, O5 o5) {
        super(Iterator.class, qtVar, z, pu, o5, null);
    }

    public IteratorSerializer(IteratorSerializer iteratorSerializer, O5 o5, PU pu, JsonSerializer jsonSerializer) {
        super(iteratorSerializer, o5, pu, jsonSerializer);
    }
}
