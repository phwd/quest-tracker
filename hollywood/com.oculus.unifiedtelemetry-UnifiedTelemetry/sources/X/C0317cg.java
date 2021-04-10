package X;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: X.cg  reason: case insensitive filesystem */
public class C0317cg extends OutputStream {
    public final /* synthetic */ AnonymousClass94 A00;

    public C0317cg(AnonymousClass94 r1) {
        this.A00 = r1;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A00.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() throws IOException {
        AnonymousClass94 r1 = this.A00;
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
        AnonymousClass94 r2 = this.A00;
        if (!r2.A00) {
            r2.A01.A09((byte) i);
            r2.A1v();
            return;
        }
        throw new IOException("closed");
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        AnonymousClass94 r1 = this.A00;
        if (!r1.A00) {
            r1.A01.A0K(bArr, i, i2);
            r1.A1v();
            return;
        }
        throw new IOException("closed");
    }
}
