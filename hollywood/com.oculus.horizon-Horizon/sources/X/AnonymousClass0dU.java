package X;

import com.google.common.collect.MapMakerInternalMap;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0dU  reason: invalid class name */
public final class AnonymousClass0dU extends AnonymousClass0oy<K, V> {
    public V A00;
    public final K A01;
    public final /* synthetic */ MapMakerInternalMap A02;

    public AnonymousClass0dU(MapMakerInternalMap mapMakerInternalMap, K k, V v) {
        this.A02 = mapMakerInternalMap;
        this.A01 = k;
        this.A00 = v;
    }

    @Override // X.AnonymousClass0oy
    public final boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (!this.A01.equals(entry.getKey()) || !this.A00.equals(entry.getValue())) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass0oy
    public final int hashCode() {
        return this.A01.hashCode() ^ this.A00.hashCode();
    }

    @Override // java.util.Map.Entry, X.AnonymousClass0oy
    public final V setValue(V v) {
        V v2 = (V) this.A02.put(this.A01, v);
        this.A00 = v;
        return v2;
    }

    @Override // java.util.Map.Entry, X.AnonymousClass0oy
    public final K getKey() {
        return this.A01;
    }

    @Override // java.util.Map.Entry, X.AnonymousClass0oy
    public final V getValue() {
        return this.A00;
    }
}
