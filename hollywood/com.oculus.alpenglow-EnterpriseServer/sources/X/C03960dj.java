package X;

import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Set;

/* renamed from: X.0dj  reason: invalid class name and case insensitive filesystem */
public final class C03960dj<K, V> extends AnonymousClass06D<K, V> implements Map<K, V> {
    @Nullable
    public AnonymousClass06C<K, V> A00;

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        AnonymousClass06C r1 = this.A00;
        if (r1 == null) {
            r1 = new C03970dk(this);
            this.A00 = r1;
        }
        AnonymousClass06C<K, V>.EntrySet entrySet = r1.A00;
        if (entrySet != null) {
            return entrySet;
        }
        AnonymousClass068 r0 = new AnonymousClass068(r1);
        r1.A00 = r0;
        return r0;
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        AnonymousClass06C r1 = this.A00;
        if (r1 == null) {
            r1 = new C03970dk(this);
            this.A00 = r1;
        }
        AnonymousClass06C<K, V>.KeySet keySet = r1.A01;
        if (keySet != null) {
            return keySet;
        }
        AnonymousClass069 r0 = new AnonymousClass069(r1);
        r1.A01 = r0;
        return r0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: X.0dj<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        int size = super.A00 + map.size();
        int i = super.A00;
        int[] iArr = this.A01;
        if (iArr.length < size) {
            Object[] objArr = this.A02;
            AnonymousClass06D.A02(this, size);
            if (super.A00 > 0) {
                System.arraycopy(iArr, 0, this.A01, 0, i);
                System.arraycopy(objArr, 0, this.A02, 0, i << 1);
            }
            AnonymousClass06D.A03(iArr, objArr, i);
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
        AnonymousClass06C r1 = this.A00;
        if (r1 == null) {
            r1 = new C03970dk(this);
            this.A00 = r1;
        }
        AnonymousClass06C<K, V>.ValuesCollection valuesCollection = r1.A02;
        if (valuesCollection != null) {
            return valuesCollection;
        }
        AnonymousClass06B r0 = new AnonymousClass06B(r1);
        r1.A02 = r0;
        return r0;
    }
}
