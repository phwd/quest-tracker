package com.fasterxml.jackson.core;

import java.io.Closeable;
import java.io.Flushable;

public abstract class JsonGenerator implements Versioned, Closeable, Flushable {
    protected JsonGenerator() {
    }
}
