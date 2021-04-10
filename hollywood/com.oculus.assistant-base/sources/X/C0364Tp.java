package X;

import com.google.common.collect.CompactHashMap;
import java.util.AbstractSet;
import java.util.Iterator;

/* renamed from: X.Tp  reason: case insensitive filesystem */
public final class C0364Tp extends AbstractSet<K> {
    public final /* synthetic */ CompactHashMap A00;

    public C0364Tp(CompactHashMap compactHashMap) {
        this.A00 = compactHashMap;
    }

    public final void clear() {
        this.A00.clear();
    }

    public final boolean contains(Object obj) {
        return this.A00.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator iterator() {
        return new u0(this.A00);
    }

    public final boolean remove(Object obj) {
        CompactHashMap compactHashMap = this.A00;
        int A002 = CompactHashMap.A00(compactHashMap, obj);
        if (A002 == -1) {
            return false;
        }
        CompactHashMap.A01(compactHashMap, compactHashMap.A06[A002], (int) (compactHashMap.A05[A002] >>> 32));
        return true;
    }

    public final int size() {
        return this.A00.A02;
    }
}
