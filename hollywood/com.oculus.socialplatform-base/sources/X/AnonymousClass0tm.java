package X;

import com.google.common.base.Objects;
import com.google.common.collect.CompactHashMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0tm  reason: invalid class name */
public class AnonymousClass0tm extends AbstractSet<Map.Entry<K, V>> {
    public final /* synthetic */ CompactHashMap A00;

    public AnonymousClass0tm(CompactHashMap compactHashMap) {
        this.A00 = compactHashMap;
    }

    public final void clear() {
        this.A00.clear();
    }

    public final boolean contains(@NullableDecl Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        CompactHashMap compactHashMap = this.A00;
        int A01 = CompactHashMap.A01(compactHashMap, entry.getKey());
        if (A01 == -1 || !Objects.equal(compactHashMap.A00[A01], entry.getValue())) {
            return false;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new AnonymousClass0fl(this.A00);
    }

    public final boolean remove(@NullableDecl Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        CompactHashMap compactHashMap = this.A00;
        int A01 = CompactHashMap.A01(compactHashMap, entry.getKey());
        if (A01 == -1 || !Objects.equal(compactHashMap.A00[A01], entry.getValue())) {
            return false;
        }
        CompactHashMap.A02(compactHashMap, compactHashMap.A07[A01], (int) (compactHashMap.A06[A01] >>> 32));
        return true;
    }

    public final int size() {
        return this.A00.A03;
    }
}
