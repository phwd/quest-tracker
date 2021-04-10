package X;

import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0dm  reason: invalid class name and case insensitive filesystem */
public class C03990dm implements Iterator<Map.Entry<K, V>>, AbstractC005805s<K, V> {
    public AnonymousClass05r<K, V> A00;
    public boolean A01 = true;
    public final /* synthetic */ C005905t A02;

    public C03990dm(C005905t r2) {
        this.A02 = r2;
    }

    @Override // X.AbstractC005805s
    public final void A8X(@NonNull AnonymousClass05r<K, V> r3) {
        AnonymousClass05r<K, V> r0 = this.A00;
        if (r3 == r0) {
            AnonymousClass05r<K, V> r1 = r0.A01;
            this.A00 = r1;
            boolean z = false;
            if (r1 == null) {
                z = true;
            }
            this.A01 = z;
        }
    }

    public final boolean hasNext() {
        AnonymousClass05r<K, V> r0;
        if (this.A01) {
            r0 = this.A02.A02;
        } else {
            AnonymousClass05r<K, V> r02 = this.A00;
            if (r02 == null) {
                return false;
            }
            r0 = r02.A00;
        }
        if (r0 != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        AnonymousClass05r<K, V> r0;
        if (this.A01) {
            this.A01 = false;
            r0 = this.A02.A02;
        } else {
            AnonymousClass05r<K, V> r02 = this.A00;
            if (r02 != null) {
                r0 = r02.A00;
            } else {
                r0 = null;
            }
        }
        this.A00 = r0;
        return r0;
    }
}
