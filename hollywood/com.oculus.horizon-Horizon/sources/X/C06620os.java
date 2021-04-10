package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableEntry;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0os  reason: invalid class name and case insensitive filesystem */
public class C06620os implements Iterator<Map.Entry<K, Collection<V>>> {
    @NullableDecl
    public Collection<V> A00;
    public final Iterator<Map.Entry<K, Collection<V>>> A01;
    public final /* synthetic */ AnonymousClass0eR A02;

    public C06620os(AnonymousClass0eR r2) {
        this.A02 = r2;
        this.A01 = r2.A00.entrySet().iterator();
    }

    public final boolean hasNext() {
        return this.A01.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Map.Entry<K, Collection<V>> next = this.A01.next();
        this.A00 = next.getValue();
        AnonymousClass0eR r0 = this.A02;
        K key = next.getKey();
        return new ImmutableEntry(key, r0.A01.A03(key, next.getValue()));
    }

    public final void remove() {
        boolean z = false;
        if (this.A00 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        this.A01.remove();
        this.A02.A01.A00 -= this.A00.size();
        this.A00.clear();
        this.A00 = null;
    }
}
