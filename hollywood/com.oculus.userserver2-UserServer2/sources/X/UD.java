package X;

import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.Map;

public abstract class UD<K, V> implements Iterator<Map.Entry<K, V>>, AnonymousClass2g<K, V> {
    public AnonymousClass2f<K, V> A00;
    public AnonymousClass2f<K, V> A01;

    @Override // X.AnonymousClass2g
    public final void A3o(@NonNull AnonymousClass2f<K, V> r3) {
        AnonymousClass2f<K, V> r0;
        AnonymousClass2f<K, V> r02 = this.A00;
        if (r02 == r3 && r3 == this.A01) {
            r02 = null;
            this.A01 = null;
            this.A00 = null;
        }
        if (r02 == r3) {
            if (!(this instanceof C0048Cq)) {
                r02 = r02.A01;
            } else {
                r02 = r02.A00;
            }
            this.A00 = r02;
        }
        AnonymousClass2f<K, V> r1 = this.A01;
        if (r1 == r3) {
            if (r1 == r02 || r02 == null) {
                r0 = null;
            } else if (!(this instanceof C0048Cq)) {
                r0 = r1.A00;
            } else {
                r0 = r1.A01;
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
        AnonymousClass2f<K, V> r0;
        AnonymousClass2f<K, V> r1 = this.A01;
        AnonymousClass2f<K, V> r02 = this.A00;
        if (r1 == r02 || r02 == null) {
            r0 = null;
        } else if (!(this instanceof C0048Cq)) {
            r0 = r1.A00;
        } else {
            r0 = r1.A01;
        }
        this.A01 = r0;
        return r1;
    }

    public UD(AnonymousClass2f<K, V> r1, AnonymousClass2f<K, V> r2) {
        this.A00 = r2;
        this.A01 = r1;
    }
}
