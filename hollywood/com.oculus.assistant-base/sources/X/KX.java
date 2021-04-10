package X;

import java.io.IOException;
import java.io.InputStream;

public final class KX extends InputStream {
    public int A00;
    public final /* synthetic */ C0976ps A01;

    public KX(C0976ps psVar, int i) {
        this.A01 = psVar;
        this.A00 = i;
    }

    @Override // java.io.InputStream
    public final int available() {
        return this.A00;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() {
        while (true) {
            int available = available();
            if (available > 0) {
                skip((long) available);
            } else {
                return;
            }
        }
    }

    @Override // java.io.InputStream
    public final int read() {
        if (this.A00 == 0) {
            return -1;
        }
        int read = this.A01.A01.read();
        if (read != -1) {
            this.A00--;
            return read;
        }
        throw new IOException("compressed stream terminated prematurely");
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        if (i2 > 0 && this.A00 == 0) {
            return -1;
        }
        int read = this.A01.A01.read(bArr, i, Math.min(this.A00, i2));
        if (read <= 0) {
            return read;
        }
        this.A00 -= read;
        return read;
    }
}
