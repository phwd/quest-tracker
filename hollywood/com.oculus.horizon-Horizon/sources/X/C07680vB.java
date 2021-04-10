package X;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: X.0vB  reason: invalid class name and case insensitive filesystem */
public class C07680vB extends OutputStream {
    public final /* synthetic */ C00570Av A00;

    public C07680vB(C00570Av r1) {
        this.A00 = r1;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A00.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() throws IOException {
        C00570Av r1 = this.A00;
        if (!r1.A00) {
            r1.flush();
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.A00);
        sb.append(".outputStream()");
        return sb.toString();
    }

    @Override // java.io.OutputStream
    public final void write(int i) throws IOException {
        C00570Av r2 = this.A00;
        if (!r2.A00) {
            r2.A01.A09((byte) i);
            r2.A2P();
            return;
        }
        throw new IOException("closed");
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        C00570Av r1 = this.A00;
        if (!r1.A00) {
            r1.A01.A0J(bArr, i, i2);
            r1.A2P();
            return;
        }
        throw new IOException("closed");
    }
}
