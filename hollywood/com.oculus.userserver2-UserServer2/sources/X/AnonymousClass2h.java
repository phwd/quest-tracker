package X;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

@RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
/* renamed from: X.2h  reason: invalid class name */
public class AnonymousClass2h<K, V> implements Iterable<Map.Entry<K, V>> {
    public int A00 = 0;
    public AnonymousClass2f<K, V> A01;
    public AnonymousClass2f<K, V> A02;
    public WeakHashMap<AnonymousClass2g<K, V>, Boolean> A03 = new WeakHashMap<>();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AnonymousClass2h) {
            AnonymousClass2h r7 = (AnonymousClass2h) obj;
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

    private final AnonymousClass2f<K, V> A00(K k) {
        if (this instanceof UF) {
            return ((UF) this).A00.get(k);
        }
        AnonymousClass2f<K, V> r1 = this.A02;
        while (r1 != null && !r1.A02.equals(k)) {
            r1 = r1.A00;
        }
        return r1;
    }

    public final V A02(@NonNull K k, @NonNull V v) {
        AnonymousClass2f<K, V> r0;
        if (!(this instanceof UF)) {
            r0 = A00(k);
            if (r0 == null) {
                AnonymousClass2f<K, V> r1 = new AnonymousClass2f<>(k, v);
                this.A00++;
                AnonymousClass2f<K, V> r02 = this.A01;
                if (r02 == null) {
                    this.A02 = r1;
                } else {
                    r02.A00 = r1;
                    r1.A01 = r02;
                }
                this.A01 = r1;
                return null;
            }
        } else {
            UF uf = (UF) this;
            r0 = uf.A00(k);
            if (r0 == null) {
                HashMap<K, AnonymousClass2f<K, V>> hashMap = uf.A00;
                AnonymousClass2f<K, V> r12 = new AnonymousClass2f<>(k, v);
                ((AnonymousClass2h) uf).A00++;
                AnonymousClass2f<K, V> r03 = uf.A01;
                if (r03 == null) {
                    uf.A02 = r12;
                } else {
                    r03.A00 = r12;
                    r12.A01 = r03;
                }
                uf.A01 = r12;
                hashMap.put(k, r12);
                return null;
            }
        }
        return r0.A03;
    }

    @Override // java.lang.Iterable
    @NonNull
    public final Iterator<Map.Entry<K, V>> iterator() {
        C0049Cr cr = new C0049Cr(this.A02, this.A01);
        this.A03.put(cr, false);
        return cr;
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
        AnonymousClass2f<K, V> A002 = A00(k);
        if (A002 == null) {
            return null;
        }
        this.A00--;
        if (!this.A03.isEmpty()) {
            for (AnonymousClass2g<K, V> r0 : this.A03.keySet()) {
                r0.A3o(A002);
            }
        }
        AnonymousClass2f<K, V> r1 = A002.A01;
        if (r1 != null) {
            r1.A00 = A002.A00;
        } else {
            this.A02 = A002.A00;
        }
        AnonymousClass2f<K, V> r02 = A002.A00;
        if (r02 != null) {
            r02.A01 = r1;
        } else {
            this.A01 = r1;
        }
        A002.A00 = null;
        A002.A01 = null;
        return A002.A03;
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
