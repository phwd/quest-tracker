package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.security.MessageDigest;

/* renamed from: X.1cO  reason: invalid class name */
public final class AnonymousClass1cO implements AbstractC06491aL {
    public final C05700wg<C07491cP<?>, Object> A00 = new AnonymousClass1cW();

    @Override // X.AbstractC06491aL
    public final void AAv(@NonNull MessageDigest messageDigest) {
        int i = 0;
        while (true) {
            C05700wg<C07491cP<?>, Object> r1 = this.A00;
            if (i < r1.size()) {
                Object[] objArr = r1.A02;
                int i2 = i << 1;
                C07491cP r4 = (C07491cP) objArr[i2];
                Object obj = objArr[i2 + 1];
                AnonymousClass1cQ<T> r2 = r4.A00;
                if (r4.A03 == null) {
                    r4.A03 = r4.A02.getBytes(AbstractC06491aL.A00);
                }
                r2.AAt(r4.A03, obj, messageDigest);
                i++;
            } else {
                return;
            }
        }
    }

    @Nullable
    public final <T> T A00(@NonNull C07491cP<T> r3) {
        C05700wg<C07491cP<?>, Object> r1 = this.A00;
        return r1.containsKey(r3) ? (T) r1.get(r3) : r3.A01;
    }

    @Override // X.AbstractC06491aL
    public final boolean equals(Object obj) {
        if (obj instanceof AnonymousClass1cO) {
            return this.A00.equals(((AnonymousClass1cO) obj).A00);
        }
        return false;
    }

    @Override // X.AbstractC06491aL
    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Options{values=");
        sb.append(this.A00);
        sb.append('}');
        return sb.toString();
    }
}
