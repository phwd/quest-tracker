package com.facebook.zstd;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class AbstractZstdOutputStream extends FilterOutputStream {
    public static final int DEFAULT_COMPRESSION_LEVEL = 13;

    public abstract void finish() throws IOException;

    public AbstractZstdOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public AbstractZstdOutputStream(OutputStream outputStream, int i, int i2) {
        super(outputStream);
    }
}
