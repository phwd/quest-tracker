package com.fasterxml.jackson.databind.ser.std;

public class StdKeySerializer extends StdSerializer {
    public static final StdKeySerializer A00 = new StdKeySerializer();

    public StdKeySerializer() {
        super(Object.class);
    }
}
