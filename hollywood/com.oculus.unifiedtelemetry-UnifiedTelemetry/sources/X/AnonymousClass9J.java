package X;

import java.io.OutputStream;

/* renamed from: X.9J  reason: invalid class name */
public class AnonymousClass9J extends OutputStream {
    public final String toString() {
        return "ByteStreams.nullOutputStream()";
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        if (bArr == null) {
            throw null;
        }
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw null;
        }
    }
}
