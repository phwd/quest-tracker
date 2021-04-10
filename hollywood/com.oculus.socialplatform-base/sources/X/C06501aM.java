package X;

import androidx.annotation.NonNull;
import java.security.MessageDigest;

/* renamed from: X.1aM  reason: invalid class name and case insensitive filesystem */
public final class C06501aM implements AbstractC06491aL {
    public final AbstractC06491aL A00;
    public final AbstractC06491aL A01;

    @Override // X.AbstractC06491aL
    public final void AAv(@NonNull MessageDigest messageDigest) {
        this.A01.AAv(messageDigest);
        this.A00.AAv(messageDigest);
    }

    @Override // X.AbstractC06491aL
    public final boolean equals(Object obj) {
        if (!(obj instanceof C06501aM)) {
            return false;
        }
        C06501aM r4 = (C06501aM) obj;
        if (!this.A01.equals(r4.A01) || !this.A00.equals(r4.A00)) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC06491aL
    public final int hashCode() {
        return (this.A01.hashCode() * 31) + this.A00.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DataCacheKey{sourceKey=");
        sb.append(this.A01);
        sb.append(", signature=");
        sb.append(this.A00);
        sb.append('}');
        return sb.toString();
    }

    public C06501aM(AbstractC06491aL r1, AbstractC06491aL r2) {
        this.A01 = r1;
        this.A00 = r2;
    }
}
