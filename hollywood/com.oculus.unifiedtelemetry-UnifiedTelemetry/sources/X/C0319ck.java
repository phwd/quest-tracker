package X;

import java.io.OutputStream;

/* renamed from: X.ck  reason: case insensitive filesystem */
public class C0319ck extends OutputStream {
    public final /* synthetic */ AnonymousClass98 A00;

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() {
    }

    public C0319ck(AnonymousClass98 r1) {
        this.A00 = r1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.A00);
        sb.append(".outputStream()");
        return sb.toString();
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        this.A00.A09((byte) i);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        this.A00.A0K(bArr, i, i2);
    }
}
