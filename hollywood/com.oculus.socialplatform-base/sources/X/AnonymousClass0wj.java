package X;

import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0wj  reason: invalid class name */
public abstract class AnonymousClass0wj<K, V> implements Iterator<Map.Entry<K, V>>, AnonymousClass02a<K, V> {
    public AnonymousClass02Z<K, V> A00;
    public AnonymousClass02Z<K, V> A01;

    public abstract AnonymousClass02Z<K, V> A00(AnonymousClass02Z<K, V> v);

    public abstract AnonymousClass02Z<K, V> A01(AnonymousClass02Z<K, V> v);

    @Override // X.AnonymousClass02a
    public final void AAf(@NonNull AnonymousClass02Z<K, V> r3) {
        AnonymousClass02Z<K, V> r0;
        AnonymousClass02Z<K, V> r1 = this.A00;
        if (r1 == r3 && r3 == this.A01) {
            r1 = null;
            this.A01 = null;
            this.A00 = null;
        }
        if (r1 == r3) {
            r1 = A00(r1);
            this.A00 = r1;
        }
        AnonymousClass02Z<K, V> r02 = this.A01;
        if (r02 == r3) {
            if (r02 == r1 || r1 == null) {
                r0 = null;
            } else {
                r0 = A01(r02);
            }
            this.A01 = r0;
        }
    }

    public final boolean hasNext() {
        if (this.A01 != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        AnonymousClass02Z<K, V> r0;
        AnonymousClass02Z<K, V> r1 = this.A01;
        AnonymousClass02Z<K, V> r02 = this.A00;
        if (r1 == r02 || r02 == null) {
            r0 = null;
        } else {
            r0 = A01(r1);
        }
        this.A01 = r0;
        return r1;
    }

    public AnonymousClass0wj(AnonymousClass02Z<K, V> r1, AnonymousClass02Z<K, V> r2) {
        this.A00 = r2;
        this.A01 = r1;
    }
}
