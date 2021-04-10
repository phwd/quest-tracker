package X;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: X.1gg  reason: invalid class name */
public final class AnonymousClass1gg {
    public final ByteBuffer A00;

    public final short A00(int i) {
        ByteBuffer byteBuffer = this.A00;
        if (byteBuffer.remaining() - i >= 2) {
            return byteBuffer.getShort(i);
        }
        return -1;
    }

    public AnonymousClass1gg(byte[] bArr, int i) {
        this.A00 = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i);
    }
}
