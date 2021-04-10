package X;

import com.adobe.xmp.impl.Base64;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: X.1gS  reason: invalid class name */
public final class AnonymousClass1gS implements AnonymousClass1gm {
    public final ByteBuffer A00;

    @Override // X.AnonymousClass1gm
    public final short A5B() throws AnonymousClass1hG {
        ByteBuffer byteBuffer = this.A00;
        if (byteBuffer.remaining() >= 1) {
            return (short) (byteBuffer.get() & Base64.INVALID);
        }
        throw new AnonymousClass1hG();
    }

    @Override // X.AnonymousClass1gm
    public final int A8r(byte[] bArr, int i) {
        ByteBuffer byteBuffer = this.A00;
        int min = Math.min(i, byteBuffer.remaining());
        if (min == 0) {
            return -1;
        }
        byteBuffer.get(bArr, 0, min);
        return min;
    }

    @Override // X.AnonymousClass1gm
    public final long skip(long j) {
        ByteBuffer byteBuffer = this.A00;
        int min = (int) Math.min((long) byteBuffer.remaining(), j);
        byteBuffer.position(byteBuffer.position() + min);
        return (long) min;
    }

    public AnonymousClass1gS(ByteBuffer byteBuffer) {
        this.A00 = byteBuffer;
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
    }

    @Override // X.AnonymousClass1gm
    public final int A5A() throws AnonymousClass1hG {
        return (A5B() << 8) | A5B();
    }
}
