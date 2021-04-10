package X;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class E7 {
    public final Object A00 = new Object();
    public final ByteBuffer A01;
    public volatile E0 A02;

    public E7(ByteBuffer byteBuffer) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        this.A01 = duplicate;
        duplicate.order(ByteOrder.LITTLE_ENDIAN);
    }
}
