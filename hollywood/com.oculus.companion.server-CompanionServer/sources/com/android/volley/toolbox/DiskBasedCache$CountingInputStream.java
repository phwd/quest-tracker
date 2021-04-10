package com.android.volley.toolbox;

import java.io.FilterInputStream;
import java.io.IOException;

class DiskBasedCache$CountingInputStream extends FilterInputStream {
    private long bytesRead;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = super.read();
        if (read != -1) {
            this.bytesRead++;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        if (read != -1) {
            this.bytesRead += (long) read;
        }
        return read;
    }

    /* access modifiers changed from: package-private */
    public long bytesRead() {
        return this.bytesRead;
    }
}
