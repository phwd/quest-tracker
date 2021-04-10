package X;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: X.0h5  reason: invalid class name and case insensitive filesystem */
public class C04590h5 extends OutputStream {
    public final /* synthetic */ AnonymousClass0HP A00;

    public C04590h5(AnonymousClass0HP r1) {
        this.A00 = r1;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A00.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() throws IOException {
        AnonymousClass0HP r1 = this.A00;
        if (!r1.A00) {
            r1.flush();
        }
    }

    public final String toString() {
        return this.A00 + ".outputStream()";
    }

    @Override // java.io.OutputStream
    public final void write(int i) throws IOException {
        AnonymousClass0HP r2 = this.A00;
        if (!r2.A00) {
            r2.A01.A09((byte) i);
            r2.A2I();
            return;
        }
        throw new IOException("closed");
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        AnonymousClass0HP r1 = this.A00;
        if (!r1.A00) {
            r1.A01.A0J(bArr, i, i2);
            r1.A2I();
            return;
        }
        throw new IOException("closed");
    }
}
