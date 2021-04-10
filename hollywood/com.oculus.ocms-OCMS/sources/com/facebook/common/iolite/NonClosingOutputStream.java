package com.facebook.common.iolite;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.OutputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
class NonClosingOutputStream extends FbFilterOutputStream {
    @Override // java.io.OutputStream, java.io.Closeable, java.io.FilterOutputStream, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    public NonClosingOutputStream(OutputStream outputStream) {
        super(outputStream);
    }
}
