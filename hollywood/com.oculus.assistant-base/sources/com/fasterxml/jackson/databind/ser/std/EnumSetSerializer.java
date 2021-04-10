package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC1024qt;
import X.O5;
import X.PU;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.util.EnumSet;

public class EnumSetSerializer extends AsArraySerializerBase {
    public EnumSetSerializer(AbstractC1024qt qtVar) {
        super(EnumSet.class, qtVar, true, null, null, null);
    }

    public EnumSetSerializer(EnumSetSerializer enumSetSerializer, O5 o5, PU pu, JsonSerializer jsonSerializer) {
        super(enumSetSerializer, o5, pu, jsonSerializer);
    }
}
