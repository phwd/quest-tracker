package X;

import java.nio.ByteBuffer;

/* renamed from: X.Xf  reason: case insensitive filesystem */
public final class C0236Xf extends RW {
    public final String A00;

    @Override // X.RW
    public final ByteBuffer getJavaByteBuffer() {
        return A00(this.A00);
    }

    public C0236Xf(String str) {
        this.A00 = str;
    }
}
