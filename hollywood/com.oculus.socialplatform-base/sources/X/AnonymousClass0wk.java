package X;

import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0wk  reason: invalid class name */
public class AnonymousClass0wk implements Iterator<Map.Entry<K, V>>, AnonymousClass02a<K, V> {
    public AnonymousClass02Z<K, V> A00;
    public boolean A01 = true;
    public final /* synthetic */ AnonymousClass02b A02;

    public AnonymousClass0wk(AnonymousClass02b r2) {
        this.A02 = r2;
    }

    @Override // X.AnonymousClass02a
    public final void AAf(@NonNull AnonymousClass02Z<K, V> r3) {
        AnonymousClass02Z<K, V> r0 = this.A00;
        if (r3 == r0) {
            AnonymousClass02Z<K, V> r1 = r0.A01;
            this.A00 = r1;
            boolean z = false;
            if (r1 == null) {
                z = true;
            }
            this.A01 = z;
        }
    }

    public final boolean hasNext() {
        AnonymousClass02Z<K, V> r0;
        if (this.A01) {
            r0 = this.A02.A02;
        } else {
            AnonymousClass02Z<K, V> r02 = this.A00;
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
        AnonymousClass02Z<K, V> r0;
        if (this.A01) {
            this.A01 = false;
            r0 = this.A02.A02;
        } else {
            AnonymousClass02Z<K, V> r02 = this.A00;
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
