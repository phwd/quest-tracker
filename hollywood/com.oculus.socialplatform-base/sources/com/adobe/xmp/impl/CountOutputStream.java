package com.adobe.xmp.impl;

import java.io.IOException;
import java.io.OutputStream;

public final class CountOutputStream extends OutputStream {
    public int bytesWritten = 0;
    public final OutputStream out;

    public CountOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    public int getBytesWritten() {
        return this.bytesWritten;
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.out.write(i);
        this.bytesWritten++;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
        this.bytesWritten += bArr.length;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        this.bytesWritten += i2;
    }
}
