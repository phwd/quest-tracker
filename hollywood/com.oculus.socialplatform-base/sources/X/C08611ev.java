package X;

import android.content.Context;
import androidx.annotation.NonNull;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: X.1ev  reason: invalid class name and case insensitive filesystem */
public final class C08611ev<T> implements AnonymousClass1eU<T> {
    public final Collection<? extends AnonymousClass1eU<T>> A00;

    @Override // X.AnonymousClass1eU
    @NonNull
    public final AnonymousClass1fR<T> AAk(@NonNull Context context, @NonNull AnonymousClass1fR<T> r6, int i, int i2) {
        Iterator<? extends AnonymousClass1eU<T>> it = this.A00.iterator();
        AnonymousClass1fR<T> r2 = r6;
        while (it.hasNext()) {
            AnonymousClass1fR<T> AAk = ((AnonymousClass1eU) it.next()).AAk(context, r2, i, i2);
            if (r2 != null && !r2.equals(r6) && !r2.equals(AAk)) {
                r2.A8u();
            }
            r2 = AAk;
        }
        return r2;
    }

    @Override // X.AbstractC06491aL
    public final void AAv(@NonNull MessageDigest messageDigest) {
        for (AbstractC06491aL r0 : this.A00) {
            r0.AAv(messageDigest);
        }
    }

    @Override // X.AbstractC06491aL
    public final boolean equals(Object obj) {
        if (obj instanceof C08611ev) {
            return this.A00.equals(((C08611ev) obj).A00);
        }
        return false;
    }

    @Override // X.AbstractC06491aL
    public final int hashCode() {
        return this.A00.hashCode();
    }

    public C08611ev(@NonNull Collection<? extends AnonymousClass1eU<T>> collection) {
        if (!collection.isEmpty()) {
            this.A00 = collection;
            return;
        }
        throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
    }

    @SafeVarargs
    public C08611ev(@NonNull AnonymousClass1eU<T>... r3) {
        if (r3.length != 0) {
            this.A00 = Arrays.asList(r3);
            return;
        }
        throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
    }
}
