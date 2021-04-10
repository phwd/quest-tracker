package X;

import androidx.annotation.NonNull;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* renamed from: X.1cT  reason: invalid class name */
public class AnonymousClass1cT implements AnonymousClass1cQ<Long> {
    public final ByteBuffer A00 = ByteBuffer.allocate(8);

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [byte[], java.lang.Object, java.security.MessageDigest] */
    @Override // X.AnonymousClass1cQ
    public final void AAt(@NonNull byte[] bArr, @NonNull Long l, @NonNull MessageDigest messageDigest) {
        Long l2 = l;
        messageDigest.update(bArr);
        ByteBuffer byteBuffer = this.A00;
        synchronized (byteBuffer) {
            byteBuffer.position(0);
            byteBuffer.putLong(l2.longValue());
            messageDigest.update(byteBuffer.array());
        }
    }
}
