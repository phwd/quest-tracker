package X;

import com.google.common.base.Objects;
import com.google.common.collect.CompactHashMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.Tn  reason: case insensitive filesystem */
public final class C0362Tn extends AbstractSet<Map.Entry<K, V>> {
    public final /* synthetic */ CompactHashMap A00;

    public C0362Tn(CompactHashMap compactHashMap) {
        this.A00 = compactHashMap;
    }

    public final void clear() {
        this.A00.clear();
    }

    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        CompactHashMap compactHashMap = this.A00;
        int A002 = CompactHashMap.A00(compactHashMap, entry.getKey());
        if (A002 == -1 || !Objects.equal(compactHashMap.A07[A002], entry.getValue())) {
            return false;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator iterator() {
        return new u1(this.A00);
    }

    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        CompactHashMap compactHashMap = this.A00;
        int A002 = CompactHashMap.A00(compactHashMap, entry.getKey());
        if (A002 == -1 || !Objects.equal(compactHashMap.A07[A002], entry.getValue())) {
            return false;
        }
        CompactHashMap.A01(compactHashMap, compactHashMap.A06[A002], (int) (compactHashMap.A05[A002] >>> 32));
        return true;
    }

    public final int size() {
        return this.A00.A02;
    }
}
