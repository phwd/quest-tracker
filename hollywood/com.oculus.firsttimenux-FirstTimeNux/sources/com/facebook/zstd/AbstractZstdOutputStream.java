package com.facebook.zstd;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class AbstractZstdOutputStream extends FilterOutputStream {
    public static final int DEFAULT_COMPRESSION_LEVEL = 13;

    public abstract void finish() throws IOException;

    public AbstractZstdOutputStream(OutputStream out) {
        super(out);
    }

    public AbstractZstdOutputStream(OutputStream out, int bufferSize, int preset) {
        super(out);
    }
}
