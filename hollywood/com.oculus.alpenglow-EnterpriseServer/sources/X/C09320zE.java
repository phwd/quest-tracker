package X;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: X.0zE  reason: invalid class name and case insensitive filesystem */
public final class C09320zE extends OutputStream {
    public boolean A00 = false;
    public final AbstractC09650zy A01;

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A00 = true;
    }

    public C09320zE(AbstractC09650zy r2) {
        this.A01 = r2;
    }

    @Override // java.io.OutputStream
    public final void write(int i) throws IOException {
        if (!this.A00) {
            write(new byte[]{(byte) (i & 255)}, 0, 1);
            return;
        }
        throw new IOException("Stream is closed.");
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) throws IOException {
        if (!this.A00) {
            write(bArr, 0, bArr.length);
            return;
        }
        throw new IOException("Stream is closed.");
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        if (!this.A00) {
            this.A01.A8z(bArr, i, i2);
            return;
        }
        throw new IOException("Stream is closed.");
    }
}
