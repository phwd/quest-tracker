package X;

import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.aY  reason: case insensitive filesystem */
public abstract class AbstractC0300aY<K, V> implements Iterator<Map.Entry<K, V>>, AnonymousClass2T<K, V> {
    public AnonymousClass2S<K, V> A00;
    public AnonymousClass2S<K, V> A01;

    public abstract AnonymousClass2S<K, V> A00(AnonymousClass2S<K, V> v);

    public abstract AnonymousClass2S<K, V> A01(AnonymousClass2S<K, V> v);

    @Override // X.AnonymousClass2T
    public final void A5R(@NonNull AnonymousClass2S<K, V> r3) {
        AnonymousClass2S<K, V> r0;
        AnonymousClass2S<K, V> r1 = this.A00;
        if (r1 == r3 && r3 == this.A01) {
            r1 = null;
            this.A01 = null;
            this.A00 = null;
        }
        if (r1 == r3) {
            r1 = A00(r1);
            this.A00 = r1;
        }
        AnonymousClass2S<K, V> r02 = this.A01;
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
        AnonymousClass2S<K, V> r0;
        AnonymousClass2S<K, V> r1 = this.A01;
        AnonymousClass2S<K, V> r02 = this.A00;
        if (r1 == r02 || r02 == null) {
            r0 = null;
        } else {
            r0 = A01(r1);
        }
        this.A01 = r0;
        return r1;
    }

    public AbstractC0300aY(AnonymousClass2S<K, V> r1, AnonymousClass2S<K, V> r2) {
        this.A00 = r2;
        this.A01 = r1;
    }
}
