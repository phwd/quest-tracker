package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class UnknownSerializer extends StdSerializer {
    public UnknownSerializer() {
        super(Object.class);
    }
}
