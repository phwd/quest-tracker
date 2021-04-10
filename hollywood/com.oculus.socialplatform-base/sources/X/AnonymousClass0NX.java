package X;

import androidx.annotation.VisibleForTesting;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nullable;

/* renamed from: X.0NX  reason: invalid class name */
public final class AnonymousClass0NX {
    public final Object A00 = new Object();
    public final ByteBuffer A01;
    @Nullable
    @VisibleForTesting
    public volatile AnonymousClass0NT A02;
    @Nullable
    public volatile AnonymousClass0NG A03;
    @Nullable
    public volatile AnonymousClass0o0 A04;

    public AnonymousClass0NX(ByteBuffer byteBuffer) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        this.A01 = duplicate;
        duplicate.order(ByteOrder.LITTLE_ENDIAN);
    }
}
