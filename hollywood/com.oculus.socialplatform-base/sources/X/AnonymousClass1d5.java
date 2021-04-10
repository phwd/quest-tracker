package X;

import androidx.annotation.NonNull;
import java.io.IOException;
import java.nio.ByteBuffer;

/* renamed from: X.1d5  reason: invalid class name */
public final class AnonymousClass1d5 implements AbstractC08171e8<ByteBuffer> {
    public final ByteBuffer A00;

    @Override // X.AbstractC08171e8
    public final void A26() {
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC08171e8
    @NonNull
    public final ByteBuffer A9Q() throws IOException {
        ByteBuffer byteBuffer = this.A00;
        byteBuffer.position(0);
        return byteBuffer;
    }

    public AnonymousClass1d5(ByteBuffer byteBuffer) {
        this.A00 = byteBuffer;
    }
}
