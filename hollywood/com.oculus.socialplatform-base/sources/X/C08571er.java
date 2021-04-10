package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.nio.ByteBuffer;

/* renamed from: X.1er  reason: invalid class name and case insensitive filesystem */
public final class C08571er implements AbstractC08801fP<AnonymousClass1gA, byte[]> {
    @Override // X.AbstractC08801fP
    @Nullable
    public final AnonymousClass1fR<byte[]> AAj(@NonNull AnonymousClass1fR<AnonymousClass1gA> r6, @NonNull AnonymousClass1cO r7) {
        byte[] bArr;
        ByteBuffer asReadOnlyBuffer = r6.get().A09.A00.A0E.A09.asReadOnlyBuffer();
        if (!asReadOnlyBuffer.isReadOnly() && asReadOnlyBuffer.hasArray()) {
            C08601eu r2 = new C08601eu(asReadOnlyBuffer.array(), asReadOnlyBuffer.arrayOffset(), asReadOnlyBuffer.limit());
            if (r2.A01 == 0 && r2.A00 == r2.A02.length) {
                bArr = asReadOnlyBuffer.array();
                return new AnonymousClass1dU(bArr);
            }
        }
        ByteBuffer asReadOnlyBuffer2 = asReadOnlyBuffer.asReadOnlyBuffer();
        bArr = new byte[asReadOnlyBuffer2.limit()];
        asReadOnlyBuffer2.position(0);
        asReadOnlyBuffer2.get(bArr);
        return new AnonymousClass1dU(bArr);
    }
}
