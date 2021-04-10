package X;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* renamed from: X.0NN  reason: invalid class name */
public final class AnonymousClass0NN {
    public static final Charset A01 = Charset.forName("UTF-8");
    public ByteBuffer A00;

    public AnonymousClass0NN() {
        ByteBuffer allocate = ByteBuffer.allocate(128);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        this.A00 = allocate;
    }
}
