package com.fasterxml.jackson.databind.ser.std;

import X.OB;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.concurrent.atomic.AtomicReference;

@JacksonStdImpl
public class SerializableSerializer extends StdSerializer {
    public static final SerializableSerializer A00 = new SerializableSerializer();
    public static final AtomicReference A01 = new AtomicReference();

    public SerializableSerializer() {
        super(OB.class);
    }
}
