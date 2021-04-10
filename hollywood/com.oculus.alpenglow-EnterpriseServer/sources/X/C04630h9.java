package X;

import java.io.OutputStream;

/* renamed from: X.0h9  reason: invalid class name and case insensitive filesystem */
public class C04630h9 extends OutputStream {
    public final /* synthetic */ AnonymousClass0HR A00;

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() {
    }

    public C04630h9(AnonymousClass0HR r1) {
        this.A00 = r1;
    }

    public final String toString() {
        return this.A00 + ".outputStream()";
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        this.A00.A09((byte) i);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        this.A00.A0J(bArr, i, i2);
    }
}
