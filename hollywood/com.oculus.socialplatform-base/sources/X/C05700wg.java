package X;

import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* renamed from: X.0wg  reason: invalid class name and case insensitive filesystem */
public class C05700wg<K, V> extends C000502v<K, V> implements Map<K, V> {
    @Nullable
    public AnonymousClass02u<K, V> A00;

    @Override // java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        AnonymousClass02u r1 = this.A00;
        if (r1 == null) {
            r1 = new C05720wi(this);
            this.A00 = r1;
        }
        AnonymousClass02u<K, V>.EntrySet entrySet = r1.A00;
        if (entrySet != null) {
            return entrySet;
        }
        AnonymousClass02q r0 = new AnonymousClass02q(r1);
        r1.A00 = r0;
        return r0;
    }

    @Override // java.util.Map
    public final Set<K> keySet() {
        AnonymousClass02u r1 = this.A00;
        if (r1 == null) {
            r1 = new C05720wi(this);
            this.A00 = r1;
        }
        AnonymousClass02u<K, V>.KeySet keySet = r1.A01;
        if (keySet != null) {
            return keySet;
        }
        AnonymousClass02r r0 = new AnonymousClass02r(r1);
        r1.A01 = r0;
        return r0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: X.0wg<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        A08(super.A00 + map.size());
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public final Collection<V> values() {
        AnonymousClass02u r1 = this.A00;
        if (r1 == null) {
            r1 = new C05720wi(this);
            this.A00 = r1;
        }
        AnonymousClass02u<K, V>.ValuesCollection valuesCollection = r1.A02;
        if (valuesCollection != null) {
            return valuesCollection;
        }
        AnonymousClass02t r0 = new AnonymousClass02t(r1);
        r1.A02 = r0;
        return r0;
    }
}
