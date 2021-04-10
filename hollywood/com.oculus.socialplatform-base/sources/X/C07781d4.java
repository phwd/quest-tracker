package X;

import androidx.annotation.NonNull;
import java.nio.ByteBuffer;

/* renamed from: X.1d4  reason: invalid class name and case insensitive filesystem */
public class C07781d4 implements AbstractC07621ck<ByteBuffer> {
    /* Return type fixed from 'X.1e8' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07621ck
    @NonNull
    public final AbstractC08171e8<ByteBuffer> A1n(ByteBuffer byteBuffer) {
        return new AnonymousClass1d5(byteBuffer);
    }

    @Override // X.AbstractC07621ck
    @NonNull
    public final Class<ByteBuffer> A3h() {
        return ByteBuffer.class;
    }
}
