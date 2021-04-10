package X;

import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.Set;

/* renamed from: X.aW  reason: case insensitive filesystem */
public final class C0298aW<K, V> extends C00062o<K, V> implements Map<K, V> {
    @Nullable
    public AnonymousClass2n<K, V> A00;

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        AnonymousClass2n r1 = this.A00;
        if (r1 == null) {
            r1 = new C0299aX(this);
            this.A00 = r1;
        }
        AnonymousClass2n<K, V>.EntrySet entrySet = r1.A00;
        if (entrySet != null) {
            return entrySet;
        }
        AnonymousClass2j r0 = new AnonymousClass2j(r1);
        r1.A00 = r0;
        return r0;
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        AnonymousClass2n r1 = this.A00;
        if (r1 == null) {
            r1 = new C0299aX(this);
            this.A00 = r1;
        }
        AnonymousClass2n<K, V>.KeySet keySet = r1.A01;
        if (keySet != null) {
            return keySet;
        }
        AnonymousClass2k r0 = new AnonymousClass2k(r1);
        r1.A01 = r0;
        return r0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: X.aW<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        int size = super.A00 + map.size();
        int i = super.A00;
        int[] iArr = this.A01;
        if (iArr.length < size) {
            Object[] objArr = this.A02;
            C00062o.A02(this, size);
            if (super.A00 > 0) {
                System.arraycopy(iArr, 0, this.A01, 0, i);
                System.arraycopy(objArr, 0, this.A02, 0, i << 1);
            }
            C00062o.A03(iArr, objArr, i);
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
        AnonymousClass2n r1 = this.A00;
        if (r1 == null) {
            r1 = new C0299aX(this);
            this.A00 = r1;
        }
        AnonymousClass2n<K, V>.ValuesCollection valuesCollection = r1.A02;
        if (valuesCollection != null) {
            return valuesCollection;
        }
        AnonymousClass2m r0 = new AnonymousClass2m(r1);
        r1.A02 = r0;
        return r0;
    }
}
