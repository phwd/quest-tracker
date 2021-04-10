package X;

import androidx.annotation.NonNull;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* renamed from: X.1cU  reason: invalid class name */
public final class AnonymousClass1cU implements AbstractC06491aL {
    public final int A00;
    public final AbstractC06491aL A01;

    @Override // X.AbstractC06491aL
    public final void AAv(@NonNull MessageDigest messageDigest) {
        this.A01.AAv(messageDigest);
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(this.A00);
        messageDigest.update(allocate.array());
    }

    @Override // X.AbstractC06491aL
    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass1cU)) {
            return false;
        }
        AnonymousClass1cU r4 = (AnonymousClass1cU) obj;
        if (this.A00 != r4.A00 || !this.A01.equals(r4.A01)) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC06491aL
    public final int hashCode() {
        return C08381eW.A02(this.A01, this.A00);
    }

    public AnonymousClass1cU(int i, AbstractC06491aL r2) {
        this.A00 = i;
        this.A01 = r2;
    }
}
