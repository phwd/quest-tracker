package X;

import com.google.common.collect.CompactHashMap;
import java.util.AbstractSet;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0to  reason: invalid class name */
public class AnonymousClass0to extends AbstractSet<K> {
    public final /* synthetic */ CompactHashMap A00;

    public AnonymousClass0to(CompactHashMap compactHashMap) {
        this.A00 = compactHashMap;
    }

    public final void clear() {
        this.A00.clear();
    }

    public final boolean contains(Object obj) {
        return this.A00.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<K> iterator() {
        return new C01660fm(this.A00);
    }

    public final boolean remove(@NullableDecl Object obj) {
        CompactHashMap compactHashMap = this.A00;
        int A01 = CompactHashMap.A01(compactHashMap, obj);
        if (A01 == -1) {
            return false;
        }
        CompactHashMap.A02(compactHashMap, compactHashMap.A07[A01], (int) (compactHashMap.A06[A01] >>> 32));
        return true;
    }

    public final int size() {
        return this.A00.A03;
    }
}
