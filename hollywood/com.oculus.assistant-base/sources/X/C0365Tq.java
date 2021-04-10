package X;

import com.google.common.collect.CompactHashMap;
import java.util.AbstractCollection;
import java.util.Iterator;

/* renamed from: X.Tq  reason: case insensitive filesystem */
public final class C0365Tq extends AbstractCollection<V> {
    public final /* synthetic */ CompactHashMap A00;

    public C0365Tq(CompactHashMap compactHashMap) {
        this.A00 = compactHashMap;
    }

    public final void clear() {
        this.A00.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new u2(this.A00);
    }

    public final int size() {
        return this.A00.A02;
    }
}
