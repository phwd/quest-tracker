package X;

import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Set;

/* renamed from: X.0ss  reason: invalid class name and case insensitive filesystem */
public final class C07490ss<K, V> extends C000602o<K, V> implements Map<K, V> {
    @Nullable
    public AnonymousClass02n<K, V> A00;

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        AnonymousClass02n r1 = this.A00;
        if (r1 == null) {
            r1 = new C07500st(this);
            this.A00 = r1;
        }
        AnonymousClass02n<K, V>.EntrySet entrySet = r1.A00;
        if (entrySet != null) {
            return entrySet;
        }
        AnonymousClass02j r0 = new AnonymousClass02j(r1);
        r1.A00 = r0;
        return r0;
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        AnonymousClass02n r1 = this.A00;
        if (r1 == null) {
            r1 = new C07500st(this);
            this.A00 = r1;
        }
        AnonymousClass02n<K, V>.KeySet keySet = r1.A01;
        if (keySet != null) {
            return keySet;
        }
        AnonymousClass02k r0 = new AnonymousClass02k(r1);
        r1.A01 = r0;
        return r0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: X.0ss<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        int size = super.A00 + map.size();
        int i = super.A00;
        int[] iArr = this.A01;
        if (iArr.length < size) {
            Object[] objArr = this.A02;
            C000602o.A02(this, size);
            if (super.A00 > 0) {
                System.arraycopy(iArr, 0, this.A01, 0, i);
                System.arraycopy(objArr, 0, this.A02, 0, i << 1);
            }
            C000602o.A03(iArr, objArr, i);
        }
        if (super.A00 == i) {
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
            return;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Map
    public final Collection<V> values() {
        AnonymousClass02n r1 = this.A00;
        if (r1 == null) {
            r1 = new C07500st(this);
            this.A00 = r1;
        }
        AnonymousClass02n<K, V>.ValuesCollection valuesCollection = r1.A02;
        if (valuesCollection != null) {
            return valuesCollection;
        }
        AnonymousClass02m r0 = new AnonymousClass02m(r1);
        r1.A02 = r0;
        return r0;
    }
}
