package X;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.05t  reason: invalid class name and case insensitive filesystem */
public class C005905t<K, V> implements Iterable<Map.Entry<K, V>> {
    public int A00 = 0;
    public AnonymousClass05r<K, V> A01;
    public AnonymousClass05r<K, V> A02;
    public WeakHashMap<AbstractC005805s<K, V>, Boolean> A03 = new WeakHashMap<>();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C005905t) {
            C005905t r7 = (C005905t) obj;
            if (this.A00 == r7.A00) {
                Iterator<Map.Entry<K, V>> it = iterator();
                Iterator<Map.Entry<K, V>> it2 = r7.iterator();
                while (it.hasNext() && it2.hasNext()) {
                    Map.Entry<K, V> next = it.next();
                    Map.Entry<K, V> next2 = it2.next();
                    if (next == null) {
                        if (next2 == null) {
                        }
                    } else if (!next.equals(next2)) {
                        return false;
                    }
                }
                return !it.hasNext() && !it2.hasNext();
            }
        }
        return false;
    }

    public AnonymousClass05r<K, V> A00(K k) {
        AnonymousClass05r<K, V> r1 = this.A02;
        while (r1 != null && !r1.A02.equals(k)) {
            r1 = r1.A00;
        }
        return r1;
    }

    @Override // java.lang.Iterable
    @NonNull
    public final Iterator<Map.Entry<K, V>> iterator() {
        AnonymousClass0Ma r2 = new AnonymousClass0Ma(this.A02, this.A01);
        this.A03.put(r2, false);
        return r2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public V A01(@NonNull K k) {
        AnonymousClass05r<K, V> A002 = A00(k);
        if (A002 == null) {
            return null;
        }
        this.A00--;
        if (!this.A03.isEmpty()) {
            for (AbstractC005805s<K, V> r0 : this.A03.keySet()) {
                r0.A8X(A002);
            }
        }
        AnonymousClass05r<K, V> r1 = A002.A01;
        if (r1 != null) {
            r1.A00 = A002.A00;
        } else {
            this.A02 = A002.A00;
        }
        AnonymousClass05r<K, V> r02 = A002.A00;
        if (r02 != null) {
            r02.A01 = r1;
        } else {
            this.A01 = r1;
        }
        A002.A00 = null;
        A002.A01 = null;
        return A002.A03;
    }

    public V A02(@NonNull K k, @NonNull V v) {
        AnonymousClass05r<K, V> A002 = A00(k);
        if (A002 != null) {
            return A002.A03;
        }
        AnonymousClass05r<K, V> r1 = new AnonymousClass05r<>(k, v);
        this.A00++;
        AnonymousClass05r<K, V> r0 = this.A01;
        if (r0 == null) {
            this.A02 = r1;
        } else {
            r0.A00 = r1;
            r1.A01 = r0;
        }
        this.A01 = r1;
        return null;
    }

    public final int hashCode() {
        Iterator<Map.Entry<K, V>> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().hashCode();
        }
        return i;
    }
}
