package X;

import androidx.annotation.NonNull;
import java.security.MessageDigest;

/* renamed from: X.1S3  reason: invalid class name */
public final class AnonymousClass1S3 implements AbstractC06491aL {
    public final Object A00;

    @Override // X.AbstractC06491aL
    public final void AAv(@NonNull MessageDigest messageDigest) {
        messageDigest.update(this.A00.toString().getBytes(AbstractC06491aL.A00));
    }

    @Override // X.AbstractC06491aL
    public final boolean equals(Object obj) {
        if (obj instanceof AnonymousClass1S3) {
            return this.A00.equals(((AnonymousClass1S3) obj).A00);
        }
        return false;
    }

    @Override // X.AbstractC06491aL
    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ObjectKey{object=");
        sb.append(this.A00);
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass1S3(@NonNull Object obj) {
        AnonymousClass1S2.A00(obj);
        this.A00 = obj;
    }
}
