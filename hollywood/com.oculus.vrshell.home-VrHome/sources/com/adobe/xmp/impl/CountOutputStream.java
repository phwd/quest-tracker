package com.adobe.xmp.impl;

import java.io.IOException;
import java.io.OutputStream;

public final class CountOutputStream extends OutputStream {
    private int bytesWritten = 0;
    private final OutputStream out;

    CountOutputStream(OutputStream out2) {
        this.out = out2;
    }

    @Override // java.io.OutputStream
    public void write(byte[] buf, int off, int len) throws IOException {
        this.out.write(buf, off, len);
        this.bytesWritten += len;
    }

    @Override // java.io.OutputStream
    public void write(byte[] buf) throws IOException {
        this.out.write(buf);
        this.bytesWritten += buf.length;
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        this.out.write(b);
        this.bytesWritten++;
    }

    public int getBytesWritten() {
        return this.bytesWritten;
    }
}
