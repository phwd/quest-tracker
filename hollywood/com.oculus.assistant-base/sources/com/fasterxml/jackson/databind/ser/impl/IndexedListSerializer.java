package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC1024qt;
import X.O5;
import X.PU;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import java.util.List;

@JacksonStdImpl
public final class IndexedListSerializer extends AsArraySerializerBase {
    public IndexedListSerializer(AbstractC1024qt qtVar, boolean z, PU pu, O5 o5, JsonSerializer jsonSerializer) {
        super(List.class, qtVar, z, pu, o5, jsonSerializer);
    }

    public IndexedListSerializer(IndexedListSerializer indexedListSerializer, O5 o5, PU pu, JsonSerializer jsonSerializer) {
        super(indexedListSerializer, o5, pu, jsonSerializer);
    }
}
