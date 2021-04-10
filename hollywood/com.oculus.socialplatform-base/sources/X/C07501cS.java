package X;

import androidx.annotation.NonNull;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* renamed from: X.1cS  reason: invalid class name and case insensitive filesystem */
public class C07501cS implements AnonymousClass1cQ<Integer> {
    public final ByteBuffer A00 = ByteBuffer.allocate(4);

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [byte[], java.lang.Object, java.security.MessageDigest] */
    @Override // X.AnonymousClass1cQ
    public final void AAt(@NonNull byte[] bArr, @NonNull Integer num, @NonNull MessageDigest messageDigest) {
        Integer num2 = num;
        if (num2 != null) {
            messageDigest.update(bArr);
            ByteBuffer byteBuffer = this.A00;
            synchronized (byteBuffer) {
                byteBuffer.position(0);
                byteBuffer.putInt(num2.intValue());
                messageDigest.update(byteBuffer.array());
            }
        }
    }
}
