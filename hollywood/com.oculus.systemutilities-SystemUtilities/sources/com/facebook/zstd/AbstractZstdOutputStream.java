package com.facebook.zstd;

import java.io.FilterOutputStream;
import java.io.IOException;

public abstract class AbstractZstdOutputStream extends FilterOutputStream {
    public abstract void finish() throws IOException;
}
