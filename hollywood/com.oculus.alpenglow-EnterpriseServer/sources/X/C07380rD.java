package X;

import com.google.common.collect.CompactHashMap;
import java.util.AbstractCollection;
import java.util.Iterator;

/* renamed from: X.0rD  reason: invalid class name and case insensitive filesystem */
public class C07380rD extends AbstractCollection<V> {
    public final /* synthetic */ CompactHashMap A00;

    public C07380rD(CompactHashMap compactHashMap) {
        this.A00 = compactHashMap;
    }

    public final void clear() {
        this.A00.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<V> iterator() {
        return new AnonymousClass0YF(this.A00);
    }

    public final int size() {
        return this.A00.A02;
    }
}
